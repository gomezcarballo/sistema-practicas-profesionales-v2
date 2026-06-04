package spp.logicadenegocio.gestores;

import java.io.FileOutputStream;
import java.io.OutputStream;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import spp.logicadenegocio.clasesdao.ReporteParcialDAO;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

public class GestorReporteParcial {

    public void generarReporteParcial(ReporteParcial reporte) throws ProcesamientoSistemaExcepcion, OperacionesDeDaoExcepcion {
        
        completarDatosDeBaseDeDatos(reporte);

        Context contextoThymeleaf = prepararContexto(reporte);

        String htmlProcesado = procesarPlantillaHtml(contextoThymeleaf);

        exportarAPdf(htmlProcesado, "Reporte_Parcial_" + reporte.getNrc() + ".pdf");
        
    }

    private void completarDatosDeBaseDeDatos(ReporteParcial reporte) throws OperacionesDeDaoExcepcion {

        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        int idPracticante = sesionUsuario.getIdUsuario();

        ReporteParcialDAO reporteParcialDAO = new ReporteParcialDAO();
        ReporteParcial reporteBaseDatos;
        reporteBaseDatos = reporteParcialDAO.recuperarDatosReporte(idPracticante); 

        reporte.setNrc(reporteBaseDatos.getNrc());
        reporte.setProfesor(reporteBaseDatos.getProfesor());
        reporte.setAlumno(reporteBaseDatos.getAlumno());
        reporte.setOrganizacion(reporteBaseDatos.getOrganizacion());
        reporte.setObjetivoGeneral(reporteBaseDatos.getObjetivoGeneral());
        reporte.setMetodologia(reporteBaseDatos.getMetodologia());
        reporte.setNombreResponsable(reporteBaseDatos.getNombreResponsable());

    }

    private Context prepararContexto(ReporteParcial reporte) {
        
        int numeroInforme = 12345;
        String carrera = "Ingenieria de Software";
        Context contexto = new Context();
        
        contexto.setVariable("carrera", carrera );
        contexto.setVariable("nrc", reporte.getNrc());
        contexto.setVariable("profesor", reporte.getProfesor());
        contexto.setVariable("periodoEscolar", reporte.getPeriodoEscolar());
        contexto.setVariable("proyecto", reporte.getProyecto());
        contexto.setVariable("organizacion", reporte.getOrganizacion());
        contexto.setVariable("fechaReporte", reporte.getFechaReporte());
        contexto.setVariable("periodoHoras", reporte.getPeriodoReporteYHorasCubiertas());
        contexto.setVariable("numInforme", numeroInforme);
        contexto.setVariable("objetivoGeneral", reporte.getObjetivoGeneral());
        contexto.setVariable("metodologia", reporte.getMetodologia());
        contexto.setVariable("resultados", reporte.getResultados());
        contexto.setVariable("observaciones", reporte.getObservaciones());
        contexto.setVariable("actividades", reporte.getActividades());
        contexto.setVariable("alumno", reporte.getAlumno());
        return contexto;
        
    }

    private String procesarPlantillaHtml(Context contexto) {
        
        ClassLoaderTemplateResolver resolutor = new ClassLoaderTemplateResolver();
        resolutor.setPrefix("/documentos/reporteparcial/");
        resolutor.setSuffix(".html");
        resolutor.setTemplateMode("HTML");
        resolutor.setCharacterEncoding("UTF-8");

        TemplateEngine motorPlantillas = new TemplateEngine();
        motorPlantillas.setTemplateResolver(resolutor);

        return motorPlantillas.process("reporteParcial", contexto);
        
    }

    private void exportarAPdf(String html, String nombreArchivo) throws ProcesamientoSistemaExcepcion {
        
        try (OutputStream flujoSalida = new FileOutputStream(nombreArchivo)) {
            
            PdfRendererBuilder constructorPdf = new PdfRendererBuilder();
            
            constructorPdf.withHtmlContent(html, null);
            constructorPdf.toStream(flujoSalida);
            constructorPdf.run();
            
        } catch (Exception excepcion) {
            
            throw new ProcesamientoSistemaExcepcion("Ocurrió un error al generar el PDF: " + excepcion.getMessage());
            
        }
        
    }
}