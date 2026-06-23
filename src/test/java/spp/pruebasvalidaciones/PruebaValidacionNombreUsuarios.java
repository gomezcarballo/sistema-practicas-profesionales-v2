/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;
/* 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionDatos;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
*/
/**
 *
 * @author gomes
 */
public class PruebaValidacionNombreUsuarios {
    /* 
   @Test
    public void pruebaValidarNombreValido() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarNombre("Juan");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    } 
    
    @Test
    public void pruebaValidarNombreConEspacios() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarNombre("Juan Carlos");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }
    
    @Test
    public void pruebaValidarNombreConAcentos() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarNombre("José");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }
    
    @Test
    public void pruebaValidarNombreConNumeros() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarNombre("Juan1");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaValidarNombreConSimbolos() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarNombre("Juan@");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaValidarNombreLongitudLimite() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarNombre("A".repeat(50));

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }
    
    @Test
    public void pruebaValidarNombreLongitudExcedida() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarNombre("A".repeat(51));
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaValidarNombreMensajeCaracteresInvalidos() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarNombre("Juan1");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El nombre solo debe contener letras.", e.getMessage());
            
        }
        
    }
    
    @Test
    public void pruebaValidarNombreMensajeLongitud() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarNombre("A".repeat(51));
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El nombre excede la longitud maxima de 50 caracteres", e.getMessage());
            
        }
        
    }
 */
}
