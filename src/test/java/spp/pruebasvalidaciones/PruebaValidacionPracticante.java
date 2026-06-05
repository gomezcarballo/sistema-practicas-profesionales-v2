/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionPracticante;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaValidacionPracticante {
    
    private Practicante crearPracticanteValido() {

        Practicante practicante = new Practicante();

        practicante.setMatricula("S12345678");
        practicante.setNombre("Juan");
        practicante.setApellidoPaterno("Perez");
        practicante.setApellidoMaterno("Lopez");

        return practicante;
        
    }

    @Test
    public void pruebaCamposValidos() {

        Practicante practicante = crearPracticanteValido();

        ValidacionPracticante validacion = new ValidacionPracticante();

        try {

            validacion.sonCamposValidosPorReglaNegocio(practicante);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaMatriculaConSPorMayuscula() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("S87654321");

        ValidacionPracticante validacion = new ValidacionPracticante();

        try {

            validacion.sonCamposValidosPorReglaNegocio(practicante);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaMatriculaConSPorMinuscula() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("s87654321");

        ValidacionPracticante validacion = new ValidacionPracticante();

        try {

            validacion.sonCamposValidosPorReglaNegocio(practicante);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaMatriculaSinSInicial() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("12345678");

        ValidacionPracticante validacion = new ValidacionPracticante();

        try {

            validacion.sonCamposValidosPorReglaNegocio(practicante);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMatriculaConMenosDigitos() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("S1234567");

        ValidacionPracticante validacion = new ValidacionPracticante();

        try {

            validacion.sonCamposValidosPorReglaNegocio(practicante);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMatriculaConMasDigitos() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("S123456789");

        ValidacionPracticante validacion = new ValidacionPracticante();

        try {

            validacion.sonCamposValidosPorReglaNegocio(practicante);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMatriculaConLetras() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("S1234A78");

        ValidacionPracticante validacion = new ValidacionPracticante();

        try {

            validacion.sonCamposValidosPorReglaNegocio(practicante);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMatriculaVacia() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("");

        ValidacionPracticante validacion = new ValidacionPracticante();

        try {

            validacion.sonCamposValidosPorReglaNegocio(practicante);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMensajeMatriculaInvalida() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("12345678");

        ValidacionPracticante validacion = new ValidacionPracticante();

        try {

            validacion.sonCamposValidosPorReglaNegocio(practicante);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals( "Matricula no valida. Debe comenzar con S seguido de 8 números.", e.getMessage());
            
        }
        
    }

    @Test
    public void pruebaMatriculaConCaracterEspecial() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("S1234-78");

        ValidacionPracticante validacion = new ValidacionPracticante();

        try {

            validacion.sonCamposValidosPorReglaNegocio(practicante);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
}
    

