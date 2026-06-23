/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import static org.junit.Assert.assertEquals;

import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionOrganizacion;

/**
 *
 * @author gomes
 */
public class PruebaValidacionOrganizacion {

    private ValidacionOrganizacion validacion;
    private Organizacion organizacion;

    @Before
    public void configurar() {
        validacion = new ValidacionOrganizacion();
        organizacion = new Organizacion();
    }
    
    @Test
    public void pruebaCamposValidos() {
        organizacion.setNombre("Microsoft");
        organizacion.setDireccion("Calle Principal");

        List<String> errores = validacion.validarRegistroOrganizacion(organizacion);
        
        assertTrue(errores.isEmpty());
    }

    @Test
    public void pruebaNombreConNumeros() {
        organizacion.setNombre("Microsoft123");
        organizacion.setDireccion("Calle Principal");

        List<String> errores = validacion.validarRegistroOrganizacion(organizacion);
        
        assertFalse(errores.isEmpty());
    }

    @Test
    public void pruebaNombreConSimbolos() {
        organizacion.setNombre("Microsoft@");
        organizacion.setDireccion("Calle Principal");

        List<String> errores = validacion.validarRegistroOrganizacion(organizacion);
        
        assertFalse(errores.isEmpty());
    }

    @Test
    public void pruebaNombreLongitudLimite() {
        organizacion.setNombre("A".repeat(50));
        organizacion.setDireccion("Calle Principal");

        List<String> errores = validacion.validarRegistroOrganizacion(organizacion);
        
        assertTrue(errores.isEmpty());
    }

    @Test
    public void pruebaNombreLongitudExcedida() {
        organizacion.setNombre("A".repeat(51));
        organizacion.setDireccion("Calle Principal");

        List<String> errores = validacion.validarRegistroOrganizacion(organizacion);
        
        assertFalse(errores.isEmpty());
    }

    @Test
    public void pruebaDireccionLongitudLimite() {
        organizacion.setNombre("Microsoft");
        organizacion.setDireccion("A".repeat(50));

        List<String> errores = validacion.validarRegistroOrganizacion(organizacion);
        
        assertTrue(errores.isEmpty());
    }

    @Test
    public void pruebaDireccionLongitudExcedida() {
        organizacion.setNombre("Microsoft");
        organizacion.setDireccion("A".repeat(51));

        List<String> errores = validacion.validarRegistroOrganizacion(organizacion);
        
        assertFalse(errores.isEmpty());
    }
   
}
