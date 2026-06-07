package spp.logicadenegocio.gestores;

import spp.utilerias.generadordocumentospdf.GeneradorDocumentoPdf;
import org.thymeleaf.context.Context;

import spp.logicadenegocio.clasesdao.ReporteDAO;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

public class GestorReporteParcial {

    public void generarReporteParcial(ReporteParcial reporte) throws ProcesamientoSistemaExcepcion, 
    OperacionesDeDaoExcepcion {
        
        GeneradorDocumentoPdf generadorDocumentoPdf = new GeneradorDocumentoPdf();
        
        completarDatosDeBaseDeDatos(reporte);

        Context contextoThymeleaf = prepararContexto(reporte);

        String htmlProcesado = generadorDocumentoPdf.procesarPlantillaHtml(contextoThymeleaf, "/documentos/reporteparcial/", "reporteParcial");
        
        generadorDocumentoPdf.generarArchivoPdf(htmlProcesado, "Reporte_Parcial_");
        
    }

    private void completarDatosDeBaseDeDatos(ReporteParcial reporte) throws OperacionesDeDaoExcepcion {

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

    private Context prepararContexto(ReporteParcial reporte) {
        
        Context contexto = new Context();
        
        contexto.setVariable("carrera",reporte.getCarrera());
        contexto.setVariable("nrc", reporte.getNrc());
        contexto.setVariable("profesor", reporte.getProfesor());
        contexto.setVariable("periodoEscolar", reporte.getPeriodoEscolar());
        contexto.setVariable("proyecto", reporte.getProyecto());
        contexto.setVariable("organizacion", reporte.getOrganizacion());
        contexto.setVariable("fechaReporte", reporte.getFechaReporte());
        contexto.setVariable("periodoHoras", reporte.getPeriodoReporteYHorasCubiertas());
        contexto.setVariable("numInforme", reporte.getNumeroInforme());
        contexto.setVariable("objetivoGeneral", reporte.getObjetivoGeneral());
        contexto.setVariable("metodologia", reporte.getMetodologia());
        contexto.setVariable("resultados", reporte.getResultados());
        contexto.setVariable("observaciones", reporte.getObservaciones());
        contexto.setVariable("actividades", reporte.getActividades());
        contexto.setVariable("alumno", reporte.getAlumno());
        contexto.setVariable("matricula",reporte.getMatricula());
        return contexto;
        
    }
    
}