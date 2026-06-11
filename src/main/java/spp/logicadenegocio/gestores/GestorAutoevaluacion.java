package spp.logicadenegocio.gestores;

import org.thymeleaf.context.Context;
import spp.utilerias.generadordocumentospdf.GeneradorDocumentoPdf;
import spp.logicadenegocio.clasesdao.EncabezadoReporteDAO;
import spp.logicadenegocio.clasesdto.Autoevaluacion;
import spp.logicadenegocio.clasesdto.EncabezadoReporte;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

/**
 *
 * @author gomes
 */
public class GestorAutoevaluacion{
    
    public void generarAutoevaluacion(Autoevaluacion autoevaluacion)throws ProcesamientoSistemaExcepcion, 
    OperacionesDeDaoExcepcion {

        completarDatosBaseDeDatos(autoevaluacion);

        Context contextoThymeleaf = prepararContexto(autoevaluacion);
        
        GeneradorDocumentoPdf generadorDocumentoPdf = new GeneradorDocumentoPdf();
        
        String rutaPlantillaHtml = "/documentos/autoevaluacion/";

        String nombrePlantillaHtml = "autoevaluacion";

        String htmlProcesado = generadorDocumentoPdf.procesarPlantillaHtml(contextoThymeleaf, 
            rutaPlantillaHtml, nombrePlantillaHtml);
                
        String prefijoNombreArchivo = "Autoevaluacion_";

        generadorDocumentoPdf.generarArchivoPdf(htmlProcesado, prefijoNombreArchivo);
        
    }
    
    private void completarDatosBaseDeDatos(Autoevaluacion autoevaluacion)throws OperacionesDeDaoExcepcion{
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        int idPracticante = sesionUsuario.getIdUsuario();
        String matricula = sesionUsuario.getIdentificador();
        
        EncabezadoReporteDAO encabezadoReporteDAO = new EncabezadoReporteDAO();
        EncabezadoReporte reporteBaseDatos;
        reporteBaseDatos = encabezadoReporteDAO.recuperarDatosReporte(idPracticante); 

        autoevaluacion.setNrc(reporteBaseDatos.getNrc());
        autoevaluacion.setProfesor(reporteBaseDatos.getProfesor());
        autoevaluacion.setAlumno(reporteBaseDatos.getAlumno());
        autoevaluacion.setOrganizacion(reporteBaseDatos.getOrganizacion());
        autoevaluacion.setObjetivoGeneral(reporteBaseDatos.getObjetivoGeneral());
        autoevaluacion.setMetodologia(reporteBaseDatos.getMetodologia());
        autoevaluacion.setNombreResponsableProyecto(reporteBaseDatos.getNombreResponsableProyecto());
        autoevaluacion.setProyecto(reporteBaseDatos.getProyecto());
        autoevaluacion.setMatricula(matricula);
        autoevaluacion.asignarCarreraPorDefecto();

    }

    private Context prepararContexto(Autoevaluacion autoevaluacion) {
        
        Context contexto = new Context();
        
        contexto.setVariable("nombreProyecto", autoevaluacion.getProyecto());
        contexto.setVariable("organizacion", autoevaluacion.getOrganizacion());
        contexto.setVariable("nombreAlumno", autoevaluacion.getAlumno());
        contexto.setVariable("matricula",autoevaluacion.getMatricula());
        contexto.setVariable("responsableProyecto", autoevaluacion.getNombreResponsableProyecto());
        contexto.setVariable("valorPrimeraAfirmacion", autoevaluacion.getValorPrimeraAfirmacion());
        contexto.setVariable("valorSegundaAfirmacion", autoevaluacion.getValorSegundaAfirmacion());
        contexto.setVariable("valorTerceraAfirmacion", autoevaluacion.getValorTerceraAfirmacion());
        contexto.setVariable("valorCuartaAfirmacion", autoevaluacion.getValorCuartaAfirmacion());
        contexto.setVariable("valorQuintaAfirmacion", autoevaluacion.getValorQuintaAfirmacion());
        contexto.setVariable("valorSextaAfirmacion", autoevaluacion.getValorSextaAfirmacion());
        contexto.setVariable("valorSeptimaAfirmacion", autoevaluacion.getValorSeptimaAfirmacion());
        contexto.setVariable("valorOctavaAfirmacion", autoevaluacion.getValorOctavaAfirmacion());
        contexto.setVariable("valorNovenaAfirmacion", autoevaluacion.getValorNovenaAfirmacion());
        contexto.setVariable("valorDecimaAfirmacion", autoevaluacion.getValorDecimaAfirmacion());
        contexto.setVariable("puntuacionFinal", autoevaluacion.getPuntuacionFinal());

        return contexto;
        
    }
    
}
