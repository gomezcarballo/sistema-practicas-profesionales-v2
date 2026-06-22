package spp.pruebasvalidaciones;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.validaciones.validacionesdocumentos.ValidacionesDocumentos;

/**
 *
 * @author gomes
 */
public class PruebaValidacionDocumento {
    
    private final long BYTES_POR_MEGABYTES = 1024L * 1024L;
    private final long LIMITE_TAMAÑO_MB = 50L;
    private final long LIMITE_MAXIMO_BYTES = BYTES_POR_MEGABYTES * LIMITE_TAMAÑO_MB;

    private ValidacionesDocumentos validacion;
    private File carpetaTemporal;

    @Before
    public void configurar() throws IOException {
        validacion = new ValidacionesDocumentos();
        carpetaTemporal = File.createTempFile("junit", "");
        carpetaTemporal.delete();
        carpetaTemporal.mkdir();
    }
    
    @After
    public void tearDown() {
        if(carpetaTemporal != null && carpetaTemporal.exists()) {
            for(File archivo : carpetaTemporal.listFiles()) {
                archivo.delete();
            }
            carpetaTemporal.delete();
        }
    }
    
    private File crearArchivoTemporal(String nombre) throws IOException {
        File archivo = new File(carpetaTemporal, nombre);
        archivo.createNewFile();
        return archivo;
    }

    @Test
    public void pruebaArchivoMenorAlLimite() throws IOException {

        File archivo = crearArchivoTemporal("prueba.txt");
        
        try (FileOutputStream salida = new FileOutputStream(archivo)) {
            salida.write("Contenido pequeño".getBytes());
        }
        
        assertTrue("El archivo menor al límite debe ser válido", 
            validacion.esTamañoValidoArchivo(archivo));

    }

    @Test
    public void pruebaArchivoMayorAlLimite() throws IOException {

        File archivo = crearArchivoTemporal("pruebaGrande.txt");
        
        try (FileOutputStream salida = new FileOutputStream(archivo)) {
            byte[] datos = new byte[(int) (LIMITE_MAXIMO_BYTES + 1)];
            salida.write(datos);
        }
        
        assertFalse("El archivo mayor al límite debe ser inválido", 
            validacion.esTamañoValidoArchivo(archivo));

    }

    @Test
    public void pruebaArchivoExactamenteEnElLimite() throws IOException {
        File archivo = crearArchivoTemporal("limite.txt");
        
        try (FileOutputStream salida = new FileOutputStream(archivo)) {
            byte[] datos = new byte[(int) LIMITE_MAXIMO_BYTES];
            salida.write(datos);
        }
        
        assertTrue("El archivo exactamente en el límite debe ser válido", 
            validacion.esTamañoValidoArchivo(archivo));
    }

    @Test
    public void pruebaArchivoVacio() throws IOException {
        File archivo = crearArchivoTemporal("vacio.txt");
        
        assertTrue("El archivo vacío (0 bytes) debe ser válido", 
            validacion.esTamañoValidoArchivo(archivo));
    }
    
}
