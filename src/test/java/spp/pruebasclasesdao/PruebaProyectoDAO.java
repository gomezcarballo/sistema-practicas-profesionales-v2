/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class PruebaProyectoDAO {
    
    private ProyectoDAO proyectoDAO;
    private OrganizacionDAO organizacionDAO;

    private String nombreProyectoPrueba;
    private String nombreOrganizacionPrueba;

    @Before
    public void inicializarDatosPrueba() {

        proyectoDAO = new ProyectoDAO();
        organizacionDAO = new OrganizacionDAO();

        long tiempo = System.currentTimeMillis();

        nombreProyectoPrueba = "ProyectoPrueba_" + tiempo;

        nombreOrganizacionPrueba = "OrganizacionPrueba_" + tiempo;
        
    }

    @After
    public void eliminarDatosPrueba() throws OperacionesDeDaoExcepcion {

        Proyecto proyecto =  proyectoDAO.consultarProyecto(nombreProyectoPrueba);

        if (proyecto != null) {
            proyectoDAO.eliminarProyecto(nombreProyectoPrueba);
        }

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        if (organizacion != null) {
            organizacionDAO.eliminarOrganizacion(nombreOrganizacionPrueba);
        }
    }

    private Organizacion crearOrganizacion() {

        Organizacion organizacion = new Organizacion();

        organizacion.setNombre(nombreOrganizacionPrueba);

        organizacion.setDireccion("Direccion Prueba");

        organizacion.setSector("Tecnologia");

        organizacion.setEsActivo(true);

        return organizacion;
    }

    private Proyecto crearProyecto(Organizacion organizacion) {

        Proyecto proyecto = new Proyecto();

        proyecto.setNombre( nombreProyectoPrueba);

        proyecto.setObjetivoGeneral("Objetivo General");

        proyecto.setNombreResponsable("Responsable");

        proyecto.setContactoResponsable("responsable@uv.mx");

        proyecto.setMetodologia("Metodologia");

        proyecto.setCupoMaximo(5);

        proyecto.setEsActivo(true);

        proyecto.setOrganizacion(organizacion);

        return proyecto;
    }

    @Test
    public void pruebaInsertarProyectoExitoso()throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(crearOrganizacion());

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        boolean registroExitoso = proyectoDAO.insertarProyecto(crearProyecto(organizacion));

        assertTrue(registroExitoso);
    }

    @Test
    public void pruebaConsultarProyectoExistente()throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(crearOrganizacion());

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        proyectoDAO.insertarProyecto(crearProyecto(organizacion));

        Proyecto proyecto = proyectoDAO.consultarProyecto(nombreProyectoPrueba);

        assertNotNull(proyecto);
    }

    @Test
    public void pruebaConsultarProyectoInexistente()throws OperacionesDeDaoExcepcion {

        Proyecto proyecto = proyectoDAO.consultarProyecto("ProyectoInexistente");

        assertNull(proyecto);
    }

    @Test
    public void pruebaDisminuirCupoProyectoExistente()throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(crearOrganizacion());

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        proyectoDAO.insertarProyecto(crearProyecto(organizacion));

        Proyecto proyecto = proyectoDAO.consultarProyecto(nombreProyectoPrueba);

        boolean actualizacionExitosa = proyectoDAO.disminuirCupoProyecto(proyecto.getIdProyecto());

        assertTrue(actualizacionExitosa);
    }

    @Test
    public void pruebaDisminuirCupoProyectoInexistente()throws OperacionesDeDaoExcepcion {

        boolean actualizacionExitosa = proyectoDAO.disminuirCupoProyecto(-1);

        assertFalse(actualizacionExitosa);
    }

   @Test
    public void pruebaInactivarProyectoExistente() throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(crearOrganizacion());

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        proyectoDAO.insertarProyecto(crearProyecto(organizacion));

        Proyecto proyecto = proyectoDAO.consultarProyecto(nombreProyectoPrueba);

        boolean inactivacionExitosa = proyectoDAO.inactivarProyecto(proyecto.getIdProyecto());

        assertTrue(inactivacionExitosa);
    }

    @Test
    public void pruebaInactivarProyectoInexistente() throws OperacionesDeDaoExcepcion {

        boolean inactivacionExitosa = proyectoDAO.inactivarProyecto(-1);

        assertFalse(inactivacionExitosa);
    }

    @Test
    public void pruebaInactivarProyectosDeOrganizacionExistente() throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(crearOrganizacion());

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        proyectoDAO.insertarProyecto(crearProyecto(organizacion));

        boolean inactivacionExitosa = proyectoDAO.inactivarProyectosDeOrganizacion(organizacion.getIdOrganizacion());

        assertTrue(inactivacionExitosa);
    }

    @Test
    public void pruebaActualizarProyectoExistente() throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(crearOrganizacion());

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        proyectoDAO.insertarProyecto(crearProyecto(organizacion));

        Proyecto proyecto = proyectoDAO.consultarProyecto(nombreProyectoPrueba);

        proyecto.setMetodologia("Nueva metodologia");

        boolean actualizacionExitosa = proyectoDAO.actualizarProyecto(proyecto);

        assertTrue(actualizacionExitosa);
    }

    @Test
    public void pruebaActualizarProyectoInexistente() throws OperacionesDeDaoExcepcion {

        Proyecto proyecto = new Proyecto();

        proyecto.setIdProyecto(-1);

        boolean actualizacionExitosa = proyectoDAO.actualizarProyecto(proyecto);

        assertFalse(actualizacionExitosa);
    }

    @Test
    public void pruebaObtenerProyectosActivosExistente() throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(crearOrganizacion());

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        proyectoDAO.insertarProyecto(crearProyecto(organizacion));

        List<Proyecto> proyectos = proyectoDAO.obtenerProyectosActivos();

        assertFalse(proyectos.isEmpty());
    }

    @Test
    public void pruebaObtenerProyectosActivosPorOrganizacionExistente() throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(crearOrganizacion());

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        proyectoDAO.insertarProyecto(crearProyecto(organizacion));

        List<Proyecto> proyectos = proyectoDAO.obtenerProyectosActivosPorOrganizacion(organizacion.getIdOrganizacion());

        assertFalse(proyectos.isEmpty());
    }
    
}
