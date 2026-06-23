/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.validaciones.validacionesiniciosesion.ValidacionInicioDeSesion;


/**
 *
 * @author gomes
 */
public class PruebaValidacionInicioSesion {

    private ValidacionInicioDeSesion validacion;
    
    @Before
    public void setUp() {

        validacion = new ValidacionInicioDeSesion();
        
    }

    @Test
    public void pruebaMatriculaValidaMayuscula() {

        assertTrue("Una matrícula con 'S' mayúscula y 8 dígitos debe ser válida",
            validacion.sonFormatosValidos("S23014567"));

    }
    
    @Test
    public void pruebaMatriculaValidaMinuscula() {

        assertTrue("Una matrícula con 's' minúscula y 8 dígitos debe ser válida",
            validacion.sonFormatosValidos("s23014567"));

    }

    @Test
    public void pruebaCorreoValido() {

        assertTrue("Un correo con formato usuario@uv.mx debe ser válido",
            validacion.sonFormatosValidos("usuario@uv.mx"));

    }

     @Test
    public void pruebaCorreoValidoConPuntos() {

        assertTrue("Un correo con puntos en el nombre debe ser válido",
            validacion.sonFormatosValidos("juan.perez@uv.mx"));

    }
    @Test
    public void pruebaMatriculaSinS() {
        assertFalse("Una matrícula sin la letra 'S' inicial debe ser inválida",
            validacion.sonFormatosValidos("23014567"));
    }

    @Test
    public void pruebaMatriculaConLetraIncorrecta() {
        assertFalse("Una matrícula que empieza con 'A' en lugar de 'S' debe ser inválida",
            validacion.sonFormatosValidos("A23014567"));
    }

    @Test
    public void pruebaMatriculaIncompleta() {
        assertFalse("Una matrícula con menos de 8 dígitos debe ser inválida",
            validacion.sonFormatosValidos("S123"));
    }

    @Test
    public void pruebaCorreoSinArroba() {
        assertFalse("Un correo sin '@' debe ser inválido",
            validacion.sonFormatosValidos("usuariouv.mx"));
    }

    @Test
    public void pruebaCorreoSinDominio() {
        assertFalse("Un correo con '@' pero sin dominio debe ser inválido",
            validacion.sonFormatosValidos("usuario@"));
    }

    @Test
    public void pruebaMensajeIdentificadorInvalido() {
        assertFalse("Un identificador que no es matrícula ni correo debe ser inválido",
            validacion.sonFormatosValidos("ABC123"));
    }
    
}
