/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionActividad;

/**
 *
 * @author gomes
 */
public class PruebaValidacionActividad {
    
    corregir/*rivate ValidacionActividad validacion;
    private Actividad actividad;

    @Before
    public void configurar() {
        validacion = new ValidacionActividad();
        actividad = new Actividad();
    }
    
    @Test
    public void pruebaValidarActividadExitosa() {

        actividad.setTitulo("Actividad");
        actividad.setDescripcion("Descripcion");

        List<String> listaValidaciones = validacion.validarRegistroActividad(actividad);
        assertTrue("La lista debería estar vacía cuando no hay errores", 
            listaValidaciones.isEmpty());
            
    }

    @Test
    public void pruebaValidarTamañoTituloLimite() {

        actividad.setTitulo("A".repeat(50));
        assertTrue("El título de 50 caracteres debería ser válido", 
            validacion.esTamañoTituloValido(actividad.getTitulo()));

    }

    @Test
    public void pruebaValidarTamañoTituloExcedeLimite() {

        actividad.setTitulo("A".repeat(51));
        assertFalse("El título de 51 caracteres debería ser inválido", 
            validacion.esTamañoTituloValido(actividad.getTitulo()));
            
    }

    @Test
    public void pruebaValidarTamañoDescripcionLimite() {

        actividad.setDescripcion("A".repeat(100));
        assertTrue("La descripción de 100 caracteres debería ser válida", 
            validacion.esTamañoDescripcionValido(actividad.getDescripcion()));
            
    }

    @Test
    public void pruebaValidarTamañoDescripcionExcedeLimite() {

        actividad.setDescripcion("A".repeat(101));
        assertFalse("La descripción de 101 caracteres debería ser inválida", 
            validacion.esTamañoDescripcionValido(actividad.getDescripcion()));
            
    }

    @Test
    public void pruebaValidarTamañoCamposExcedidos() {

        actividad.setTitulo("A".repeat(51));
        actividad.setDescripcion("A".repeat(101));

        boolean esTituloValido = validacion.esTamañoTituloValido(actividad.getTitulo());
        boolean esDescripcionValida = validacion.esTamañoDescripcionValido(actividad.getDescripcion());
        
        assertFalse("Todos los campos deberían ser inválidos", 
            esTituloValido && esDescripcionValida);

    }

    @Test
    public void pruebaValidarTituloVacio() {

        actividad.setTitulo("");
        assertFalse("El título vacío debería ser inválido", 
            validacion.esTamañoTituloValido(actividad.getTitulo()));

    }

    @Test
    public void pruebaValidarDescripcionVacia() {

        actividad.setDescripcion("");
        assertFalse("La descripción vacía debería ser inválida", 
            validacion.esTamañoDescripcionValido(actividad.getDescripcion()));

    }
    */
}
