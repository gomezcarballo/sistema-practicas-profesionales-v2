package spp.logicadenegocio.gestores;

import org.thymeleaf.context.Context;
import spp.logicadenegocio.clasesdao.EncabezadoReporteDAO;
import spp.logicadenegocio.clasesdto.ReporteFinal;
import spp.logicadenegocio.clasesdto.EncabezadoReporte;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.generadordocumentospdf.GeneradorDocumentoPdf;

public class GestorReporteFinal {

    public void generarReporteFinal(ReporteFinal reporte) throws ProcesamientoSistemaExcepcion, 
    OperacionesDeDaoExcepcion {
        
        completarDatosDeBaseDeDatos(reporte);
        completarDatosCalculados(reporte);

        Context contextoThymeleaf = prepararContexto(reporte);

        GeneradorDocumentoPdf generadorDocumentoPdf = new GeneradorDocumentoPdf();

        String rutaPlantillaHtml = "/documentos/reportefinal/";

        String nombrePlantillaHtml = "reporteFinal";

        String htmlProcesado = generadorDocumentoPdf.procesarPlantillaHtml(contextoThymeleaf, 
            rutaPlantillaHtml, nombrePlantillaHtml);

        String prefijoNombreArchivo = "Reporte_Final";
        
        generadorDocumentoPdf.generarArchivoPdf(htmlProcesado, prefijoNombreArchivo);
        
    }

    private void completarDatosDeBaseDeDatos(ReporteFinal reporte) throws OperacionesDeDaoExcepcion {

        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        int idPracticante = sesionUsuario.getIdUsuario();
        String matricula = sesionUsuario.getIdentificador();

        EncabezadoReporteDAO reporteParcialDAO = new EncabezadoReporteDAO();
        EncabezadoReporte reporteBaseDatos;
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

    private void completarDatosCalculados(ReporteFinal reporte) {
        
        reporte.asignarFechaActual();
        reporte.calcularPeriodoEscolar();
        reporte.asignarCarreraPorDefecto();
        reporte.asignarTipoReporteDefecto();

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

}