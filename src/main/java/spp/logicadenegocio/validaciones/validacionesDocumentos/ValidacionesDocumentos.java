package spp.logicadenegocio.validaciones.validacionesdocumentos;

import java.io.File;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionesDocumentos {
    
    public boolean esTamañoValidoArchivo(File archivoSeleccionado) {
       
        long bytesPorMegaByte = 1024L * 1024L;
        long limiteTamañaoMB = 50L;
        long limiteMaximoBytes = bytesPorMegaByte * limiteTamañaoMB;
        boolean esArchivoValido = false;

        if (archivoSeleccionado !=null && archivoSeleccionado.length() <= limiteMaximoBytes){
            esArchivoValido = true;
        }  

        return esArchivoValido;
        
    }

}