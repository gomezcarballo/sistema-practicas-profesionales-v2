/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionActividad;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaValidacionActividad {
    
    @Test
    public void pruebaValidarCamposValidosExitosa() {

        Actividad actividad = new Actividad();
        actividad.setTitulo("Actividad");
        actividad.setDescripcion("Descripcion");

        ValidacionActividad validacion = new ValidacionActividad();

        try {

            validacion.sonCamposValidosPorReglaDeNegocio(actividad);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    }

    @Test
    public void pruebaValidarCamposValidosTituloLimite() {

        Actividad actividad = new Actividad();
        actividad.setTitulo("A".repeat(50));
        actividad.setDescripcion("Descripcion");

        ValidacionActividad validacion = new ValidacionActividad();

        try {

            validacion.sonCamposValidosPorReglaDeNegocio(actividad);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaValidarCamposValidosTituloExcedeLimite() {

        Actividad actividad = new Actividad();
        actividad.setTitulo("A".repeat(51));
        actividad.setDescripcion("Descripcion");

        ValidacionActividad validacion = new ValidacionActividad();

        try {

            validacion.sonCamposValidosPorReglaDeNegocio(actividad);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaValidarCamposValidosDescripcionLimite() {

        Actividad actividad = new Actividad();
        actividad.setTitulo("Actividad");
        actividad.setDescripcion("A".repeat(100));

        ValidacionActividad validacion = new ValidacionActividad();

        try {

            validacion.sonCamposValidosPorReglaDeNegocio(actividad);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaValidarCamposValidosDescripcionExcedeLimite() {

        Actividad actividad = new Actividad();
        actividad.setTitulo("Actividad");
        actividad.setDescripcion("A".repeat(101));

        ValidacionActividad validacion = new ValidacionActividad();

        try {

            validacion.sonCamposValidosPorReglaDeNegocio(actividad);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaValidarCamposValidosTituloYDescripcionExcedidos() {

        Actividad actividad = new Actividad();
        actividad.setTitulo("A".repeat(51));
        actividad.setDescripcion("A".repeat(101));

        ValidacionActividad validacion = new ValidacionActividad();

        try {

            validacion.sonCamposValidosPorReglaDeNegocio(actividad);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaValidarCamposValidosTituloVacio() {

        Actividad actividad = new Actividad();
        actividad.setTitulo("");
        actividad.setDescripcion("Descripcion");

        ValidacionActividad validacion = new ValidacionActividad();

        try {

            validacion.sonCamposValidosPorReglaDeNegocio(actividad);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaValidarCamposValidosDescripcionVacia() {

        Actividad actividad = new Actividad();
        actividad.setTitulo("Actividad");
        actividad.setDescripcion("");

        ValidacionActividad validacion = new ValidacionActividad();

        try {

            validacion.sonCamposValidosPorReglaDeNegocio(actividad);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    }

    @Test
    public void pruebaValidarCamposValidosMensajeExcepcionTitulo() {

        Actividad actividad = new Actividad();
        actividad.setTitulo("A".repeat(51));
        actividad.setDescripcion("Descripcion");

        ValidacionActividad validacion = new ValidacionActividad();

        try {

            validacion.sonCamposValidosPorReglaDeNegocio(actividad);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El título excede la longitud maxima de 50 caracteres", e.getMessage());
            
        }
        
    }

    @Test
    public void pruebaValidarCamposValidosMensajeExcepcionDescripcion() {

        Actividad actividad = new Actividad();
        actividad.setTitulo("Actividad");
        actividad.setDescripcion("A".repeat(101));

        ValidacionActividad validacion = new ValidacionActividad();

        try {

            validacion.sonCamposValidosPorReglaDeNegocio(actividad);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("La descripción excede la longitud maxima de 100 caracteres", e.getMessage());
            
        }
        
    }
    
}
