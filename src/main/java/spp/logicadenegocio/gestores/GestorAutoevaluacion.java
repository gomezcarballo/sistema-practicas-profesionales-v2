/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;


import spp.utilerias.generadordocumentospdf.GeneradorDocumentoPdf;
import org.thymeleaf.context.Context;
import spp.logicadenegocio.clasesdto.Autoevaluacion;
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
        
        //completarDatosBaseDeDatos(autoevaluacion);
        
        //Context contextoThymeleaf = prepararContexto(autoevaluacion);
        
        //String htmlProcesado = generadorDocumentoPdf.procesarPlantillaHtml(contextoThymeleaf, "/documentos/autoevaluacion/", "autoevaluacion");
                
        //generadorDocumentoPdf.generarArchivoPdf(htmlProcesado, "Autoevaluacion_");
        
    }
    
}
