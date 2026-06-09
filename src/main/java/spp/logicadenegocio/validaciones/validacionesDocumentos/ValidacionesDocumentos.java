/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesdocumentos;

import java.io.File;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;


/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionesDocumentos {
    
    public void archivoValidoPorReglaDeNegocio(File archivoSeleccionado) throws ReglaDeNegocioExcepcion {
       
        long bytesPorMegaByte = 1024L * 1024L;
        long limiteTamañaoMB = 50L;
        long limiteMaximoBytes = bytesPorMegaByte * limiteTamañaoMB;
        
        if (archivoSeleccionado.length() > limiteMaximoBytes){
            throw new ReglaDeNegocioExcepcion("El archivo pesa más de lo permitido");
        }  
        
    }
    
    public void validarArchivoSeleccionado(File archivoSeleccionado)throws ProcesamientoSistemaExcepcion{
        
        if (archivoSeleccionado == null) {
            throw new ProcesamientoSistemaExcepcion("Error: No se recibió ningún archivo para guardar.");
        }
        
    }

}