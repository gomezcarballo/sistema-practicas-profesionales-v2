/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.logicadenegocio.validaciones.validacionesiniciosesion.ValidacionInicioDeSesion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaValidacionInicioSesion {
    
    @Test
    public void pruebaMatriculaValidaMayuscula() {

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();

        try {

            validacion.sonCamposValidosPorReglaNegocio("S23014567");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();

        }

    }

    @Test
    public void pruebaMatriculaValidaMinuscula() {

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();

        try {

            validacion.sonCamposValidosPorReglaNegocio("s23014567");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();

        }

    }

    @Test
    public void pruebaCorreoValido() {

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();

        try {

            validacion.sonCamposValidosPorReglaNegocio("usuario@uv.mx");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();

        }

    }

    @Test
    public void pruebaCorreoValidoConPuntos() {

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();

        try {

            validacion.sonCamposValidosPorReglaNegocio("juan.perez@uv.mx");

        } catch (ReglaDeNegocioExcepcion e) {

            fail();

        }

    }

    @Test
    public void pruebaMatriculaSinS() {

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();

        try {

            validacion.sonCamposValidosPorReglaNegocio("23014567");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);

        }

    }

    @Test
    public void pruebaMatriculaConLetraIncorrecta() {

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();

        try {

            validacion.sonCamposValidosPorReglaNegocio("A23014567");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);

        }

    }

    @Test
    public void pruebaMatriculaIncompleta() {

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();

        try {

            validacion.sonCamposValidosPorReglaNegocio("S123");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);

        }

    }

    @Test
    public void pruebaCorreoSinArroba() {

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();

        try {

            validacion.sonCamposValidosPorReglaNegocio("usuariouv.mx");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);

        }

    }

    @Test
    public void pruebaCorreoSinDominio() {

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();

        try {

            validacion.sonCamposValidosPorReglaNegocio("usuario@");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);

        }

    }

    @Test
    public void pruebaMensajeIdentificadorInvalido() {

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();

        try {

            validacion.sonCamposValidosPorReglaNegocio("ABC123");
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("Identificador no valido. Ingresa una matricula o correo institucional", e.getMessage());

        }

    }
    
}
