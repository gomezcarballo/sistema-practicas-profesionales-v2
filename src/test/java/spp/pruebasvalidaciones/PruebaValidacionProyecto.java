package spp.pruebasvalidaciones;


import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionProyecto;

/**
 *
 * @author gomes
 */
public class PruebaValidacionProyecto {

    private ValidacionProyecto validacion;
    private Proyecto proyecto;

    @Before
    public void configurar() {
        validacion = new ValidacionProyecto();
        proyecto = new Proyecto();
    }
    
    private Proyecto crearProyectoValido() {
        
        Proyecto nuevoProyecto = new Proyecto();
        nuevoProyecto.setNombre("Sistema");
        nuevoProyecto.setNombreResponsable("Endric");
        nuevoProyecto.setContactoResponsable("Contacto");
        nuevoProyecto.setObjetivoGeneral("Objetivo");
        nuevoProyecto.setMetodologia("Metodologia");
        nuevoProyecto.setCupoMaximo(10);

        return nuevoProyecto;
        
    }

    @Test
    public void pruebaCamposValidos() {
        
        Proyecto proyectoValido = crearProyectoValido();
        List<String> errores = validacion.validarRegistroProyecto(proyectoValido);
        
        assertTrue("La lista de errores debería estar vacía para un proyecto válido", errores.isEmpty());
        
    }

    @Test
    public void pruebaObjetivoGeneralLongitudLimite() {
        
        proyecto.setObjetivoGeneral("A".repeat(300));
        
        assertTrue("El objetivo general de 300 caracteres debería ser válido", 
            validacion.esObjetivoGeneralValido(proyecto.getObjetivoGeneral()));
            
    }

    @Test
    public void pruebaObjetivoGeneralLongitudExcedida() {
        
        proyecto.setObjetivoGeneral("A".repeat(301));
        
        assertFalse("El objetivo general de 301 caracteres debería ser inválido", 
            validacion.esObjetivoGeneralValido(proyecto.getObjetivoGeneral()));
            
    }

    @Test
    public void pruebaMetodologiaLongitudLimite() {
        
        proyecto.setMetodologia("A".repeat(200));
        
        assertTrue("La metodología de 200 caracteres debería ser válida", 
            validacion.esMetodologiaValida(proyecto.getMetodologia()));
            
    }

    @Test
    public void pruebaMetodologiaLongitudExcedida() {
        
        proyecto.setMetodologia("A".repeat(201));
        
        assertFalse("La metodología de 201 caracteres debería ser inválida", 
            validacion.esMetodologiaValida(proyecto.getMetodologia()));
            
    }

    @Test
    public void pruebaContactoResponsableLongitudLimite() {
        
        proyecto.setContactoResponsable("A".repeat(50));
        
        assertTrue("El contacto de 50 caracteres debería ser válido", 
            validacion.esContactoResponsableValido(proyecto.getContactoResponsable()));
            
    }

    @Test
    public void pruebaContactoResponsableLongitudExcedida() {
        
        proyecto.setContactoResponsable("A".repeat(51));
        
        assertFalse("El contacto de 51 caracteres debería ser inválido", 
            validacion.esContactoResponsableValido(proyecto.getContactoResponsable()));
            
    }

    @Test
    public void pruebaCupoMaximoValido() {
        
        proyecto.setCupoMaximo(49);
        
        assertTrue("Un cupo menor a 50 debería ser válido", 
            validacion.esCupoMaximoValido(proyecto.getCupoMaximo()));
            
    }

    
    @Test
    public void pruebaMensajeObjetivoGeneral() {
        
        Proyecto proyectoInvalido = crearProyectoValido();
        proyectoInvalido.setObjetivoGeneral("A".repeat(301));
        
        List<String> errores = validacion.validarRegistroProyecto(proyectoInvalido);
        
        assertFalse("La lista de errores debería contener el error del objetivo general", errores.isEmpty());
        
    }

    @Test
    public void pruebaMensajeMetodologia() {
        
        Proyecto proyectoInvalido = crearProyectoValido();
        proyectoInvalido.setMetodologia("A".repeat(201));
        
        List<String> errores = validacion.validarRegistroProyecto(proyectoInvalido);
        
        assertFalse("La lista de errores debería contener el error de metodología", errores.isEmpty());
        
    }

    @Test
    public void pruebaMensajeContactoResponsable() {
        
        Proyecto proyectoInvalido = crearProyectoValido();
        proyectoInvalido.setContactoResponsable("A".repeat(51));
        
        List<String> errores = validacion.validarRegistroProyecto(proyectoInvalido);
        
        assertFalse("La lista de errores debería contener el error de contacto", errores.isEmpty());
        
    }

    @Test
    public void pruebaMensajeCupoMaximo() {
        
        Proyecto proyectoInvalido = crearProyectoValido();
        proyectoInvalido.setCupoMaximo(50);
        
        List<String> errores = validacion.validarRegistroProyecto(proyectoInvalido);
        
        assertFalse("La lista de errores debería contener el error de cupo", errores.isEmpty());
        
    }
    
}
