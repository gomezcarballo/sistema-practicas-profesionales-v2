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
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionOrganizacion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
*/
/**
 *
 * @author gomes
 */
public class PruebaValidacionOrganizacion {
    /*
    @Test
    public void pruebaCamposValidos() {

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Microsoft");
        organizacion.setDireccion("Calle Principal");

        ValidacionOrganizacion validacion = new ValidacionOrganizacion();

        try {

            validacion.sonCamposValidosPorReglaNegocio(organizacion);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaNombreConNumeros() {

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Microsoft123");
        organizacion.setDireccion("Calle Principal");

        ValidacionOrganizacion validacion = new ValidacionOrganizacion();

        try {

            validacion.sonCamposValidosPorReglaNegocio(organizacion);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaNombreConSimbolos() {

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Microsoft@");
        organizacion.setDireccion("Calle Principal");

        ValidacionOrganizacion validacion = new ValidacionOrganizacion();

        try {

            validacion.sonCamposValidosPorReglaNegocio(organizacion);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaNombreLongitudLimite() {

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("A".repeat(50));
        organizacion.setDireccion("Calle Principal");

        ValidacionOrganizacion validacion = new ValidacionOrganizacion();

        try {

            validacion.sonCamposValidosPorReglaNegocio(organizacion);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaNombreLongitudExcedida() {

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("A".repeat(51));
        organizacion.setDireccion("Calle Principal");

        ValidacionOrganizacion validacion = new ValidacionOrganizacion();

        try {

            validacion.sonCamposValidosPorReglaNegocio(organizacion);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaDireccionLongitudLimite() {

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Microsoft");
        organizacion.setDireccion("A".repeat(50));

        ValidacionOrganizacion validacion = new ValidacionOrganizacion();

        try {

            validacion.sonCamposValidosPorReglaNegocio(organizacion);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaDireccionLongitudExcedida() {

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Microsoft");
        organizacion.setDireccion("A".repeat(51));

        ValidacionOrganizacion validacion = new ValidacionOrganizacion();

        try {

            validacion.sonCamposValidosPorReglaNegocio(organizacion);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMensajeNombreInvalido() {

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Microsoft123");
        organizacion.setDireccion("Calle Principal");

        ValidacionOrganizacion validacion = new ValidacionOrganizacion();

        try {

            validacion.sonCamposValidosPorReglaNegocio(organizacion);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals( "El nombre solo debe contener letras.", e.getMessage());
            
        }
        
    }

    @Test
    public void pruebaMensajeDireccionExcedida() {

        Organizacion organizacion = new Organizacion();
        organizacion.setNombre("Microsoft");
        organizacion.setDireccion("A".repeat(51));

        ValidacionOrganizacion validacion = new ValidacionOrganizacion();

        try {

            validacion.sonCamposValidosPorReglaNegocio(organizacion);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("La dirección excede la longitud maxima de 50 caracteres", e.getMessage());
            
        }
        
    }
    */
}
