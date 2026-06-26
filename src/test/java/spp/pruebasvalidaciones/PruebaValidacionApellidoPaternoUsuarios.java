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
public class PruebaValidacionApellidoPaternoUsuarios {

   
    private ValidacionDatos validacion;

    @Before
    public void configurar() {
        validacion = new ValidacionDatos();
    }
    
    @Test
    public void pruebaValidarApellidoPaternoValido() {

        assertTrue("El apellido paterno 'Hernandez' debería ser válido en formato", 
            validacion.esFormatoSoloLetrasValido("Hernandez"));
            
        assertTrue("El apellido paterno 'Hernandez' debería ser válido en longitud", 
            validacion.esLongitudApellidoPaternoValida("Hernandez"));
            
    }
    
    @Test
    public void pruebaValidarApellidoPaternoConEspacios() {

        assertTrue("El apellido paterno con espacios debería ser válido en formato", 
            validacion.esFormatoSoloLetrasValido("Ladron de Guevara"));

    }
    
    @Test
    public void pruebaValidarApellidoPaternoConAcentos() {

        assertTrue("El apellido paterno con acentos debería ser válido en formato", 
            validacion.esFormatoSoloLetrasValido("Gómez"));

    }
    
    @Test
    public void pruebaValidarApellidoPaternoConNumeros() {

        assertFalse("El apellido paterno con números debería ser inválido en formato", 
            validacion.esFormatoSoloLetrasValido("Herrera1"));

    }
    
    @Test
    public void pruebaValidarApellidoPaternoConSimbolos() {

        assertFalse("El apellido paterno con símbolos debería ser inválido en formato", 
            validacion.esFormatoSoloLetrasValido("Jimenez@"));

    }
    
    @Test
    public void pruebaValidarApellidoPaternoLongitudLimite() {

        assertTrue("El apellido paterno de 30 caracteres debería ser válido", 
            validacion.esLongitudApellidoPaternoValida("A".repeat(30)));
            
    }
    
    @Test
    public void pruebaValidarApellidoPaternoLongitudExcedida() {

        assertFalse("El apellido paterno de 31 caracteres debería ser inválido", 
            validacion.esLongitudApellidoPaternoValida("A".repeat(31)));
            
    }

    @Test
    public void pruebaValidarApellidoPaternoVacio() {
        
        assertTrue("El apellido paterno vacío es válido en cuanto a tamaño se refiere", 
            validacion.esLongitudApellidoPaternoValida(""));

        assertFalse("El apellido paterno vacío es inválido en formato", 
            validacion.esFormatoSoloLetrasValido(""));
            
    }
    
    @Test
    public void pruebaValidarApellidoPaternoNull() {
        
        assertFalse("El apellido paterno null debería ser inválido en formato", 
            validacion.esFormatoSoloLetrasValido(null));
            
        assertFalse("El apellido paterno null debería ser inválido en longitud", 
            validacion.esLongitudApellidoPaternoValida(null));
            
    }
    
}
