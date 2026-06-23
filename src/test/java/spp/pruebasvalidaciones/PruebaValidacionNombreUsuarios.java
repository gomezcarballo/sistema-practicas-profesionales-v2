/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;
 
import static org.junit.Assert.assertEquals;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionDatos;

/**
 *
 * @author gomes
 */
public class PruebaValidacionNombreUsuarios {
  
      
    private ValidacionDatos validacion;

    @Before
    public void configurar() {
        validacion = new ValidacionDatos();
    }
    
    @Test
    public void pruebaValidarNombreValido() {
        assertTrue("El nombre 'Juan' debería ser válido en formato", 
            validacion.esFormatoSoloLetrasValido("Juan"));
            
        assertTrue("El nombre 'Juan' debería ser válido en longitud", 
            validacion.esLongitudNombreValida("Juan"));
    }
    
    @Test
    public void pruebaValidarNombreConEspacios() {
        assertTrue("El nombre con espacios debería ser válido en formato", 
            validacion.esFormatoSoloLetrasValido("Juan Carlos"));
    }
    
    @Test
    public void pruebaValidarNombreConAcentos() {
        assertTrue("El nombre con acentos debería ser válido en formato", 
            validacion.esFormatoSoloLetrasValido("José"));
    }
    
    @Test
    public void pruebaValidarNombreConNumeros() {
        assertFalse("El nombre con números debería ser inválido en formato", 
            validacion.esFormatoSoloLetrasValido("Juan1"));
    }
    
    @Test
    public void pruebaValidarNombreConSimbolos() {
        assertFalse("El nombre con símbolos debería ser inválido en formato", 
            validacion.esFormatoSoloLetrasValido("Juan@"));
    }
    
    @Test
    public void pruebaValidarNombreLongitudLimite() {
        assertTrue("El nombre de 50 caracteres debería ser válido", 
            validacion.esLongitudNombreValida("A".repeat(50)));
    }
    
    @Test
    public void pruebaValidarNombreLongitudExcedida() {
        assertFalse("El nombre de 51 caracteres debería ser inválido", 
            validacion.esLongitudNombreValida("A".repeat(51)));
    }
    
    @Test
    public void pruebaValidarNombreVacio() {
        assertTrue("El nombre vacío es válido en cuanto a tamaño se refiere", 
            validacion.esLongitudNombreValida(""));

        assertFalse("El nombre vacío es inválido en formato", 
            validacion.esFormatoSoloLetrasValido(""));
    }
    
    @Test
    public void pruebaValidarNombreNull() {
        assertFalse("El nombre null debería ser inválido en formato", 
            validacion.esFormatoSoloLetrasValido(null));
            
        assertFalse("El nombre null debería ser inválido en longitud", 
            validacion.esLongitudNombreValida(null));
    }

}
