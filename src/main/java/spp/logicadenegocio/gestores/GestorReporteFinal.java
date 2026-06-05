package spp.logicadenegocio.gestores;

import java.io.FileOutputStream;
import java.io.OutputStream;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;

import java.io.File;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import spp.logicadenegocio.clasesdao.ReporteDAO;
import spp.logicadenegocio.clasesdto.ReporteFinal;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

public class GestorReporteFinal {

    public void generarReporteParcial(ReporteFinal reporte) throws ProcesamientoSistemaExcepcion, OperacionesDeDaoExcepcion {
        
        completarDatosDeBaseDeDatos(reporte);

        Context contextoThymeleaf = prepararContexto(reporte);

        String htmlProcesado = procesarPlantillaHtml(contextoThymeleaf);
        
        String rutaInicioUsuario = System.getProperty("user.home");
        
        String rutaDescargas = rutaInicioUsuario + File.separator + "Downloads";
        String identificador = String.valueOf(SesionUsuario.getInstancia().getIdentificador());
        String nombreBase = "Reporte_Final_" + identificador;
        String extension = ".pdf";

        File archivoPdf = new File(rutaDescargas, nombreBase + extension);
        int contador = 1;

        while (archivoPdf.exists()) {
            archivoPdf = new File(rutaDescargas, nombreBase + " (" + contador + ")" + ".pdf");
            contador++;
        }

        exportarAPdf(htmlProcesado, archivoPdf.getAbsolutePath());
        
    }

    private void completarDatosDeBaseDeDatos(ReporteFinal reporte) throws OperacionesDeDaoExcepcion {

        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        int idPracticante = sesionUsuario.getIdUsuario();
        String matricula = sesionUsuario.getIdentificador();

        ReporteDAO reporteParcialDAO = new ReporteDAO();
        ReporteParcial reporteBaseDatos;
        reporteBaseDatos = reporteParcialDAO.recuperarDatosReporte(idPracticante); 

        reporte.setNrc(reporteBaseDatos.getNrc());
        reporte.setProfesor(reporteBaseDatos.getProfesor());
        reporte.setAlumno(reporteBaseDatos.getAlumno());
        reporte.setOrganizacion(reporteBaseDatos.getOrganizacion());
        reporte.setObjetivoGeneral(reporteBaseDatos.getObjetivoGeneral());
        reporte.setMetodologia(reporteBaseDatos.getMetodologia());
        reporte.setNombreResponsable(reporteBaseDatos.getNombreResponsable());
        reporte.setProyecto(reporteBaseDatos.getProyecto());
        reporte.setMatricula(matricula);

    }

    private Context prepararContexto(ReporteFinal reporte) {
        
        Context contexto = new Context();
        
        contexto.setVariable("carrera",reporte.getCarrera());
        contexto.setVariable("nrc", reporte.getNrc());
        contexto.setVariable("profesor", reporte.getProfesor());
        contexto.setVariable("periodoEscolar", reporte.getPeriodoEscolar());
        contexto.setVariable("proyecto", reporte.getProyecto());
        contexto.setVariable("organizacion", reporte.getOrganizacion());
        contexto.setVariable("fechaReporte", reporte.getFechaReporte());
        contexto.setVariable("objetivoGeneral", reporte.getObjetivoGeneral());
        contexto.setVariable("metodologia", reporte.getMetodologia());
        contexto.setVariable("observaciones", reporte.getObservaciones());
        contexto.setVariable("actividades", reporte.getActividades());
        contexto.setVariable("alumno", reporte.getAlumno());
        contexto.setVariable("matricula",reporte.getMatricula());
        return contexto;
        
    }

    private String procesarPlantillaHtml(Context contexto) {
        
        ClassLoaderTemplateResolver resolutor = new ClassLoaderTemplateResolver();
        resolutor.setPrefix("/documentos/reportefinal/");
        resolutor.setSuffix(".html");
        resolutor.setTemplateMode("HTML");
        resolutor.setCharacterEncoding("UTF-8");

        TemplateEngine motorPlantillas = new TemplateEngine();
        motorPlantillas.setTemplateResolver(resolutor);

        return motorPlantillas.process("reporteFinal", contexto);
        
    }

    private void exportarAPdf(String html, String rutaCompleta) throws ProcesamientoSistemaExcepcion {
        
        try (OutputStream flujoSalida = new FileOutputStream(rutaCompleta)) {
            
            PdfRendererBuilder constructorPdf = new PdfRendererBuilder();
            
            constructorPdf.withHtmlContent(html, null);
            constructorPdf.toStream(flujoSalida);
            constructorPdf.run();
            
        } catch (Exception excepcion) {
            
            throw new ProcesamientoSistemaExcepcion("Ocurrió un error al generar el PDF: " + excepcion.getMessage());
            
        }
        
    }
}