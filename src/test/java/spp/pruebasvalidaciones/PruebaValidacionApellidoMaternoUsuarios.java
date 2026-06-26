package spp.pruebasvalidaciones;
 

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionDatos;

/**
 *
 * @author gomes
 */
public class PruebaValidacionApellidoMaternoUsuarios {
     
    
    private ValidacionDatos validacion;

    @Before
    public void configurar() {
        validacion = new ValidacionDatos();
    }
    
    @Test
    public void pruebaValidarApellidoMaternoValido() {

        assertTrue("El apellido materno 'Hernandez' debería ser válido en formato", 
            validacion.esFormatoApellidoMaternoValido("Hernandez"));
            
        assertTrue("El apellido materno 'Hernandez' debería ser válido en longitud", 
            validacion.esLongitudApellidoMaternoValida("Hernandez"));
            
    }
    
    @Test
    public void pruebaValidarApellidoMaternoConEspacios() {

        assertTrue("El apellido materno con espacios debería ser válido en formato", 
            validacion.esFormatoApellidoMaternoValido("Ladron de Guevara"));

    }
    
    @Test
    public void pruebaValidarApellidoMaternoConAcentos() {

        assertTrue("El apellido materno con acentos debería ser válido en formato", 
            validacion.esFormatoApellidoMaternoValido("Gómez"));

    }
    
    @Test
    public void pruebaValidarApellidoMaternoConNumeros() {

        assertFalse("El apellido materno con números debería ser inválido en formato", 
            validacion.esFormatoApellidoMaternoValido("Herrera1"));

    }
    
    @Test
    public void pruebaValidarApellidoMaternoConSimbolos() {

        assertFalse("El apellido materno con símbolos debería ser inválido en formato", 
            validacion.esFormatoApellidoMaternoValido("Jimenez@"));

    }
    
    @Test
    public void pruebaValidarApellidoMaternoLongitudLimite() {

        assertTrue("El apellido materno de 30 caracteres debería ser válido", 
            validacion.esLongitudApellidoMaternoValida("A".repeat(30)));
            
    }
    
    @Test
    public void pruebaValidarApellidoMaternoLongitudExcedida() {

        assertFalse("El apellido materno de 31 caracteres debería ser inválido", 
            validacion.esLongitudApellidoMaternoValida("A".repeat(31)));
            
    }   
    
}
