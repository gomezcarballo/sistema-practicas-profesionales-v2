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
public class PruebaValidacionApellidoPaternoUsuarios {
    /* 
    @Test
    public void pruebaValidarApellidoPaternoValido() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoPaterno("Hernandez");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoPaternoConEspacios() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoPaterno("Ladron de Guevara");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoPaternoConAcentos() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoPaterno("Gómez");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoPaternoConNumeros() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoPaterno("Herrera1");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoPaternoConSimbolos() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoPaterno("Jimenez@");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoPaternoLongitudLimite() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoPaterno("A".repeat(30));

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoPaternoLongitudExcedida() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoPaterno("A".repeat(31));
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoPaternoMensajeCaracteresInvalidos() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoPaterno("Sanchez1");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El apellido paterno solo debe contener letras.", e.getMessage());
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoPaternoMensajeLongitud() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoPaterno("A".repeat(31));
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El apellido paterno excede la longitud maxima de 30 caracteres.", e.getMessage());
            
        }
        
    }
    */
}
