/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionProyecto;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaValidacionProyecto {
    
    private Proyecto crearProyectoValido() {

        Proyecto proyecto = new Proyecto();

        proyecto.setNombre("Sistema");
        proyecto.setNombreResponsable("Endric");
        proyecto.setContactoResponsable("Contacto");
        proyecto.setObjetivoGeneral("Objetivo");
        proyecto.setMetodologia("Metodologia");
        proyecto.setCupoMaximo(10);

        return proyecto;
        
    }

    @Test
    public void pruebaCamposValidos() {

        Proyecto proyecto = crearProyectoValido();

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaObjetivoGeneralLongitudLimite() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setObjetivoGeneral("A".repeat(300));

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaObjetivoGeneralLongitudExcedida() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setObjetivoGeneral("A".repeat(301));

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMetodologiaLongitudLimite() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setMetodologia("A".repeat(200));

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaMetodologiaLongitudExcedida() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setMetodologia("A".repeat(201));

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaContactoResponsableLongitudLimite() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setContactoResponsable("A".repeat(50));

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaContactoResponsableLongitudExcedida() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setContactoResponsable("A".repeat(51));

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaCupoMaximoValido() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setCupoMaximo(49);

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
            
        }
        
    }

    @Test
    public void pruebaCupoMaximoLimite() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setCupoMaximo(50);

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMensajeObjetivoGeneral() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setObjetivoGeneral("A".repeat(301));

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El objetivo general excede la longitud maxima de 300 caracteres", e.getMessage());
            
        }
        
    }

    @Test
    public void pruebaMensajeMetodologia() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setMetodologia("A".repeat(201));

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("La metodología excede la longitud maxima de 200 caracteres", e.getMessage());
            
        }
        
    }

    @Test
    public void pruebaMensajeContactoResponsable() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setContactoResponsable("A".repeat(51));

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El contacto del responsable excede la longitud maxima de 50 caracteres", e.getMessage());
            
        }
        
    }

    @Test
    public void pruebaMensajeCupoMaximo() {

        Proyecto proyecto = crearProyectoValido();
        proyecto.setCupoMaximo(50);

        ValidacionProyecto validacion = new ValidacionProyecto();

        try {

            validacion.sonCamposValidosPorReglaNegocio(proyecto);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("El cupo excede el numero maximo de 50 lugares permitidos", e.getMessage());
            
        }
    }
    
}
