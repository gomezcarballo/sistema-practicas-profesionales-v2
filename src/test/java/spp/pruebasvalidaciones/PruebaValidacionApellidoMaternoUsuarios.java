/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionDatos;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaValidacionApellidoMaternoUsuarios {
    
    @Test
    public void pruebaValidarApellidoMaternoValido() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoMaterno("Hernandez");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoMaternoConEspacios() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoMaterno("Ladron de Guevara");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoMaternoConAcentos() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoMaterno("Gómez");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoMaternoConNumeros() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoMaterno("Herrera1");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoMaternoConSimbolos() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoMaterno("Jimenez@");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoMaternoLongitudLimite() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoMaterno("A".repeat(30));

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoMaternoLongitudExcedida() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoMaterno("A".repeat(31));
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoMaternoMensajeCaracteresInvalidos() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoMaterno("Sanchez1");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El apellido materno solo debe contener letras.", e.getMessage());
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoMaternoMensajeLongitud() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoMaterno("A".repeat(31));
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El apellido materno excede la longitud máxima de 30 caracteres.", e.getMessage());
            
        }
        
    }
    
    @Test
    public void pruebaValidarApellidoMaternoNull() {

        ValidacionDatos validacion = new ValidacionDatos();

        try {

            validacion.validarApellidoMaterno(null);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }
    
}
