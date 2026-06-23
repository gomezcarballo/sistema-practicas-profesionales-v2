/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;
import static org.junit.Assert.assertEquals;

import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionPracticante;

/**
 *
 * @author gomes
 */
public class PruebaValidacionPracticante {

    private ValidacionPracticante validacion;
    
    @Before
    public void configurar() {
        validacion = new ValidacionPracticante();
    }
    
    private Practicante crearPracticanteValido() {

        Practicante practicante = new Practicante();

        practicante.setMatricula("S12345678");
        practicante.setNombre("Juan");
        practicante.setApellidoPaterno("Perez");
        practicante.setApellidoMaterno("Lopez");
        practicante.setCorreoInstitucional("zs12345678@estudiantes.uv.mx");
        practicante.setFechaNacimiento(LocalDate.of(2000, 1, 1)); 

        return practicante;
        
    }

    @Test
    public void pruebaCamposValidos() {

        Practicante practicante = crearPracticanteValido();

        List<String> errores = validacion.validarRegistroPracticante(practicante);

        assertTrue(errores.isEmpty());
        
    }

    @Test
    public void pruebaMatriculaConSPorMayuscula() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("S87654321");

        List<String> errores = validacion.validarRegistroPracticante(practicante);

        assertTrue(errores.isEmpty());
        
    }

    @Test
    public void pruebaMatriculaConSPorMinuscula() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("s87654321");

        List<String> errores = validacion.validarRegistroPracticante(practicante);

        assertTrue(errores.isEmpty());
        
    }

    @Test
    public void pruebaMatriculaSinSInicial() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("12345678");

        List<String> errores = validacion.validarRegistroPracticante(practicante);

        assertFalse(errores.isEmpty());
        
    }

    @Test
    public void pruebaMatriculaConMenosDigitos() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("S1234567");

        List<String> errores = validacion.validarRegistroPracticante(practicante);

        assertFalse(errores.isEmpty());
        
    }

    @Test
    public void pruebaMatriculaConMasDigitos() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("S123456789");

        List<String> errores = validacion.validarRegistroPracticante(practicante);

        assertFalse(errores.isEmpty());
        
    }

    @Test
    public void pruebaMatriculaConLetras() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("S1234A78");

        List<String> errores = validacion.validarRegistroPracticante(practicante);

        assertFalse(errores.isEmpty());
        
    }

    @Test
    public void pruebaMatriculaVacia() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("");

        List<String> errores = validacion.validarRegistroPracticante(practicante);

        assertFalse(errores.isEmpty());
        
    }

    @Test
    public void pruebaMatriculaConCaracterEspecial() {

        Practicante practicante = crearPracticanteValido();
        practicante.setMatricula("S1234-78");

        List<String> errores = validacion.validarRegistroPracticante(practicante);

        assertFalse(errores.isEmpty());
        
    }
    
    @Test
    public void pruebaFechaNacimientoFutura() {

        Practicante practicante = crearPracticanteValido();
        practicante.setFechaNacimiento(LocalDate.now().plusDays(1));

        List<String> errores = validacion.validarRegistroPracticante(practicante);

        assertFalse(errores.isEmpty());
        
    }
    
}
    

