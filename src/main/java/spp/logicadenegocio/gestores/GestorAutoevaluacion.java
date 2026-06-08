/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;


import spp.utilerias.generadordocumentospdf.GeneradorDocumentoPdf;
import org.thymeleaf.context.Context;
import spp.logicadenegocio.clasesdto.Autoevaluacion;
import spp.logicadenegocio.clasesdto.ReporteParcial;
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
        
        GeneradorDocumentoPdf generadorDocumentoPdf = new GeneradorDocumentoPdf();
        
        completarDatosBaseDeDatos(autoevaluacion);
        
        //Context contextoThymeleaf = prepararContexto(autoevaluacion);
        
        //String htmlProcesado = generadorDocumentoPdf.procesarPlantillaHtml(contextoThymeleaf, "/documentos/autoevaluacion/", "autoevaluacion");
                
        //generadorDocumentoPdf.generarArchivoPdf(htmlProcesado, "Autoevaluacion_");
        
    }
    
    private void completarDatosBaseDeDatos(Autoevaluacion autoevaluacion)throws OperacionesDeDaoExcepcion{
        /*
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        int idPracticante = sesionUsuario.getIdUsuario();
        String matricula = sesionUsuario.getIdentificador();
        
        ReporteDAO autoevaluacionDAO = new ReporteDAO();
        Autoevaluacion autoevaluacion;
        autoevaluacion = autoevaluacionDAO.recuperarDatosReporte(idPracticante); 
        */
    }
    
}
