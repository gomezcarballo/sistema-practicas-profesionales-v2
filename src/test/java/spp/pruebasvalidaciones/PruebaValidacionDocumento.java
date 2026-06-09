/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import java.io.File;
import java.io.FileOutputStream;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.logicadenegocio.validaciones.validacionesdocumentos.ValidacionesDocumentos;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaValidacionDocumento {
    
    private final long BYTES_POR_MEGABYTES = 1024L * 1024L;
    private final long LIMITE_TAMAÑO_MB = 50L;
    private final long LIMITE_MAXIMO_BYTES = BYTES_POR_MEGABYTES * LIMITE_TAMAÑO_MB;
    
    @Test
    public void pruebaArchivoMenorAlLimite() {

        ValidacionesDocumentos validacion = new ValidacionesDocumentos();

        try {

            File archivo = File.createTempFile("prueba", ".txt");

            validacion.archivoValidoPorReglaDeNegocio(archivo);

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaArchivoMayorAlLimite() {

        ValidacionesDocumentos validacion = new ValidacionesDocumentos();

        try {

            File archivo = File.createTempFile("pruebaGrande", ".txt");

            try (FileOutputStream salida = new FileOutputStream(archivo)) {

                byte[] datos = new byte[(int) (LIMITE_MAXIMO_BYTES + 1)];
                salida.write(datos);

            }

            validacion.archivoValidoPorReglaDeNegocio(archivo);

            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaMensajeArchivoMayorAlLimite() {

        ValidacionesDocumentos validacion = new ValidacionesDocumentos();

        try {

            File archivo = File.createTempFile("pruebaGrande", ".txt");

            try (FileOutputStream salida = new FileOutputStream(archivo)) {

                byte[] datos = new byte[(int) (LIMITE_MAXIMO_BYTES + 1)];
                salida.write(datos);

            }

            validacion.archivoValidoPorReglaDeNegocio(archivo);

            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals( "El archivo pesa más de lo permitido", e.getMessage());

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaValidarArchivoSeleccionadoValido() {

        ValidacionesDocumentos validacion = new ValidacionesDocumentos();

        try {

            File archivo = File.createTempFile("prueba", ".txt");

            validacion.validarArchivoSeleccionado(archivo);

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaValidarArchivoSeleccionadoNulo() {

        ValidacionesDocumentos validacion = new ValidacionesDocumentos();

        try {

            validacion.validarArchivoSeleccionado(null);

            fail();

        } catch (ProcesamientoSistemaExcepcion e) {

            assertTrue(true);

        }

    }

    @Test
    public void pruebaMensajeArchivoSeleccionadoNulo() {

        ValidacionesDocumentos validacion = new ValidacionesDocumentos();

        try {

            validacion.validarArchivoSeleccionado(null);

            fail();

        } catch (ProcesamientoSistemaExcepcion e) {

            assertEquals("Error: No se recibió ningún archivo para guardar.", e.getMessage());

        }

    }

    @Test
    public void pruebaArchivoExactamenteCincoMegabytes() {

        ValidacionesDocumentos validacion = new ValidacionesDocumentos();

        try {

            File archivo = File.createTempFile("cincoMB", ".txt");

            try (FileOutputStream salida = new FileOutputStream(archivo)) {

                byte[] datos = new byte[(int) LIMITE_MAXIMO_BYTES];
                salida.write(datos);

            }

            validacion.archivoValidoPorReglaDeNegocio(archivo);

        } catch (Exception e) {

            fail();

        }

    }

    @Test
    public void pruebaArchivoVacio() {

        ValidacionesDocumentos validacion = new ValidacionesDocumentos();

        try {

            File archivo = File.createTempFile("vacio", ".txt");

            validacion.archivoValidoPorReglaDeNegocio(archivo);

        } catch (Exception e) {

            fail();

        }

    }
    
}
