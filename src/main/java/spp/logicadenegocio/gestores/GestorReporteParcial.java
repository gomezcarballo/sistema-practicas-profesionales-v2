package spp.logicadenegocio.gestores;

import spp.utilerias.generadordocumentospdf.GeneradorDocumentoPdf;
import org.thymeleaf.context.Context;
import spp.logicadenegocio.clasesdao.EncabezadoReporteDAO;
import spp.logicadenegocio.clasesdto.EncabezadoReporte;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

public class GestorReporteParcial {

    public void generarReporteParcial(ReporteParcial reporte) throws ProcesamientoSistemaExcepcion, 
    OperacionesDeDaoExcepcion {
        
        completarDatosDeBaseDeDatos(reporte);

        Context contextoThymeleaf = prepararContexto(reporte);

        GeneradorDocumentoPdf generadorDocumentoPdf = new GeneradorDocumentoPdf();

        String rutaPlantillaHtml = "/documentos/reporteparcial/";

        String nombrePlantillaHtml = "reporteParcial";

        String htmlProcesado = generadorDocumentoPdf.procesarPlantillaHtml(contextoThymeleaf, 
            rutaPlantillaHtml, nombrePlantillaHtml);

        String prefijoNombreArchivo = "Reporte_Parcial";
        
        generadorDocumentoPdf.generarArchivoPdf(htmlProcesado, prefijoNombreArchivo);
        
    }

    private void completarDatosDeBaseDeDatos(ReporteParcial reporte) throws OperacionesDeDaoExcepcion {

        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        int idPracticante = sesionUsuario.getIdUsuario();
        String matricula = sesionUsuario.getIdentificador();

        EncabezadoReporteDAO encabezadoReporteDAO = new EncabezadoReporteDAO();
        EncabezadoReporte reporteBaseDatos;
        reporteBaseDatos = encabezadoReporteDAO.recuperarDatosReporte(idPracticante); 

        reporte.setNrc(reporteBaseDatos.getNrc());
        reporte.setProfesor(reporteBaseDatos.getProfesor());
        reporte.setAlumno(reporteBaseDatos.getAlumno());
        reporte.setOrganizacion(reporteBaseDatos.getOrganizacion());
        reporte.setObjetivoGeneral(reporteBaseDatos.getObjetivoGeneral());
        reporte.setMetodologia(reporteBaseDatos.getMetodologia());
        reporte.setNombreResponsableProyecto(reporteBaseDatos.getNombreResponsableProyecto());
        reporte.setProyecto(reporteBaseDatos.getProyecto());
        reporte.setMatricula(matricula);
        reporte.asignarCarreraPorDefecto();
        reporte.asignarFechaActual();
        reporte.asignarNumeroInforme();
        reporte.calcularPeriodoEscolar();

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