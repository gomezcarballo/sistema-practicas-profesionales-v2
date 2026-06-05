/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.validaciones.validacionenviomensajes.ValidacionEnvioMensaje;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaValidacionEnvioMensaje {
    
    @Test
    public void pruebaValidarTamañoMensajeExitoso() {

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("Hola");
        mensaje.setCuerpo("Mensaje de prueba");

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();

        try {

            validacion.validarTamañoMensaje(mensaje);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaValidarTamañoMensajeAsuntoLimite() {

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("A".repeat(50));
        mensaje.setCuerpo("Prueba");

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();

        try {

            validacion.validarTamañoMensaje(mensaje);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    }

    @Test
    public void pruebaValidarTamañoMensajeAsuntoExcedeLimite() {

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("A".repeat(51));
        mensaje.setCuerpo("Prueba");

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();

        try {

            validacion.validarTamañoMensaje(mensaje);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaValidarTamañoMensajeCuerpoLimite() {

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("Hola");
        mensaje.setCuerpo("A".repeat(750));

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();

        try {

            validacion.validarTamañoMensaje(mensaje);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    }

    @Test
    public void pruebaValidarTamañoMensajeCuerpoExcedeLimite() {

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("Hola");
        mensaje.setCuerpo("A".repeat(751));

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();

        try {

            validacion.validarTamañoMensaje(mensaje);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
        }
        
    }

    @Test
    public void pruebaValidarTamañoMensajeAsuntoYCuerpoExcedidos() {

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("A".repeat(51));
        mensaje.setCuerpo("A".repeat(751));

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();

        try {

            validacion.validarTamañoMensaje(mensaje);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaValidarTamañoMensajeAsuntoVacio() {

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("");
        mensaje.setCuerpo("Prueba");

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();

        try {

            validacion.validarTamañoMensaje(mensaje);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    }

    @Test
    public void pruebaValidarTamañoMensajeCuerpoVacio() {

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("Hola");
        mensaje.setCuerpo("");

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();

        try {

            validacion.validarTamañoMensaje(mensaje);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    }

    @Test
    public void pruebaValidarMensajeExcepcionAsunto() {

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("A".repeat(51));
        mensaje.setCuerpo("Prueba");

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();

        try {

            validacion.validarTamañoMensaje(mensaje);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El asunto excede el tamaño máximo permitido de 50 caracteres.", e.getMessage());
            
        }
    }

    @Test
    public void pruebValidarMensajeExcepcionCuerpo() {

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("Hola");
        mensaje.setCuerpo("A".repeat(751));

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();

        try {

            validacion.validarTamañoMensaje(mensaje);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El cuerpo del mensaje excede el tamaño máximo permitido de 750 caracteres.", e.getMessage());
            
        }
    }
    
}
