/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.validaciones.validacionenviomensajes.ValidacionEnvioMensaje;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;


/**
 *
 * @author gomes
 */

public class PruebaValidacionEnvioMensaje {

    private ValidacionEnvioMensaje validacion;
    private Mensaje mensaje;

    @Before
    public void configurar() {
        validacion = new ValidacionEnvioMensaje();
        mensaje = new Mensaje();
    }
    
    @Test
    public void pruebaValidarTamañoMensajeExitoso() throws OperacionesDeDaoExcepcion{

        mensaje.setCorreoDestinatario("zS24013261@estudiantes.uv.mx");
        mensaje.setAsunto("Hola");
        mensaje.setCuerpo("Mensaje de prueba");


        List<String> listaValidaciones = validacion.validarEnviarMensaje(mensaje);
        assertTrue("La lista debería estar vacía cuando no hay errores", 
            listaValidaciones.isEmpty());

    }

    @Test
    public void pruebaValidarTamañoMensajeAsuntoLimite() {

        mensaje.setAsunto("A".repeat(50));
        assertTrue("El asunto de 50 caracteres debería ser válido", 
            validacion.esTamañoAsuntoValido(mensaje.getAsunto()));

    }

    @Test
    public void pruebaValidarTamañoMensajeAsuntoExcedeLimite() {

        mensaje.setAsunto("A".repeat(51));

        assertFalse("El asunto de 51 caracteres debería ser inválido", 
            validacion.esTamañoAsuntoValido(mensaje.getAsunto()));
    }

    @Test
    public void pruebaValidarTamañoMensajeCuerpoLimite() {

        mensaje.setCuerpo("A".repeat(750));
        assertTrue("El cuerpo de 750 caracteres debería ser válido", 
            validacion.esTamañoCuerpoValido( mensaje.getCuerpo() ));
    }

    @Test
    public void pruebaValidarTamañoMensajeCuerpoExcedeLimite() {

        mensaje.setCuerpo("A".repeat(751));
        assertFalse("El cuerpo de 751 caracteres debería ser inválido", 
            validacion.esTamañoCuerpoValido(mensaje.getCuerpo()));
    }

    @Test
    public void pruebaValidarTamañoMensajeDestinatarioLimite(){

        mensaje.setCorreoDestinatario("alejandro.rodriguez.gonzalez1992@estudiantes.uv.mx");
        assertTrue("El destinatario de 50 caracteres debería ser válido", 
            validacion.esTamañoDestinatarioValido(mensaje.getCorreoDestinatario()));

    }

    @Test
    public void pruebaValidarTamañoMensajeDestinatarioExcedeLimite(){

        mensaje.setCorreoDestinatario("alejandro.fernando.rodriguez.gonzalez.1992@estudiantes.uv.mx");
        assertFalse("El destinatario de más de 50 caracteres debería ser inválido", 
            validacion.esTamañoDestinatarioValido(mensaje.getCorreoDestinatario()));
    
    }

    @Test
    public void pruebaValidarTamañoMensajeCamposExcedidos() {

        mensaje.setAsunto("A".repeat(51));
        mensaje.setCuerpo("A".repeat(751));
        mensaje.setCorreoDestinatario("A".repeat(51));

        boolean esAsuntoValido = validacion.esTamañoAsuntoValido(mensaje.getAsunto());
        boolean esCuerpoValido = validacion.esTamañoCuerpoValido(mensaje.getCuerpo());
        boolean esDestinatarioValido = validacion.esTamañoDestinatarioValido(mensaje.getCorreoDestinatario());
        
        assertFalse("Todos los campos deberían ser inválidos", 
            esAsuntoValido && esCuerpoValido && esDestinatarioValido);

    }

    @Test
    public void pruebaValidarTamañoMensajeAsuntoVacio() {

        mensaje.setAsunto("");
        assertFalse("El asunto vacío debería ser inválido", 
            validacion.esTamañoAsuntoValido(mensaje.getAsunto()));

    }

    @Test
    public void pruebaValidarTamañoMensajeCuerpoVacio() {

        mensaje.setCuerpo("");
        assertFalse("El cuerpo vacío debería ser inválido", 
            validacion.esTamañoCuerpoValido(mensaje.getCuerpo()));

    }

    @Test
    public void pruebaValidarTamañoDestinatarioVacio(){

        mensaje.setCorreoDestinatario("");
        assertFalse("El destinatario vacío debería ser inválido", 
            validacion.esTamañoDestinatarioValido(mensaje.getCorreoDestinatario()));

    }   

}
