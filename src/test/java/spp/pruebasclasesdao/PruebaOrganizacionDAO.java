/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package spp.pruebasclasesdao;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;
import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaOrganizacionDAO {
    
    private OrganizacionDAO organizacionDAO;
    private Organizacion organizacionPrueba;

    @Before
    public void inicializarDatosPrueba() {

        organizacionDAO = new OrganizacionDAO();

        organizacionPrueba = new Organizacion();
        organizacionPrueba.setNombre("OrganizacionPrueba");
        organizacionPrueba.setDireccion("Direccion Prueba");
        organizacionPrueba.setSector("Tecnologia");
        organizacionPrueba.setEsActivo(true);
        
    }

    @After
    public void eliminarDatosPrueba() throws OperacionesDeDaoExcepcion {

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(organizacionPrueba.getNombre());

        if (organizacion != null) {
            organizacionDAO.eliminarOrganizacion(organizacionPrueba.getNombre());
        }
    }

    @Test
    public void pruebaInsertarOrganizacionExitoso()throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(organizacionPrueba);

        Organizacion organizacionConsultada = organizacionDAO.consultarOrganizacion(organizacionPrueba.getNombre());

        assertNotNull(organizacionConsultada);
    }

    @Test
    public void pruebaConsultarOrganizacionExistente()throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(organizacionPrueba);

        Organizacion organizacionConsultada = organizacionDAO.consultarOrganizacion(organizacionPrueba.getNombre());

        assertNotNull(organizacionConsultada);
    }

    @Test
    public void pruebaConsultarOrganizacionInexistente()throws OperacionesDeDaoExcepcion {

        Organizacion organizacionConsultada = organizacionDAO.consultarOrganizacion("OrganizacionInexistente");

        assertNull(organizacionConsultada);
        
    }

    @Test
    public void pruebaInactivarOrganizacionExistente() throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(organizacionPrueba);

        Organizacion organizacionConsultada = organizacionDAO.consultarOrganizacion(organizacionPrueba.getNombre());

        boolean inactivacionExitosa = organizacionDAO.inactivarOrganizacion(organizacionConsultada.getIdOrganizacion());

        assertTrue(inactivacionExitosa);
    }

    @Test
    public void pruebaInactivarOrganizacionInexistente()throws OperacionesDeDaoExcepcion {

        boolean inactivacionExitosa = organizacionDAO.inactivarOrganizacion(-1);

        assertFalse(inactivacionExitosa);
    }

    @Test
    public void pruebaActualizarOrganizacionExistente()throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(organizacionPrueba);

        Organizacion organizacionConsultada = organizacionDAO.consultarOrganizacion(organizacionPrueba.getNombre());

        organizacionConsultada.setDireccion("Nueva Direccion");

        boolean actualizacionExitosa = organizacionDAO.actualizarOrganizacion(organizacionConsultada);

        assertTrue(actualizacionExitosa);
    }

    @Test
    public void pruebaActualizarOrganizacionInexistente()throws OperacionesDeDaoExcepcion {

        Organizacion organizacion = new Organizacion();

        organizacion.setIdOrganizacion(-1);
        organizacion.setNombre("Inexistente");
        organizacion.setDireccion("Direccion");
        organizacion.setSector("Sector");

        boolean actualizacionExitosa = organizacionDAO.actualizarOrganizacion(organizacion);

        assertFalse(actualizacionExitosa);
    }

    @Test
    public void pruebaObtenerOrganizacionesActivasExistente() throws OperacionesDeDaoExcepcion {

        organizacionDAO.insertarOrganizacion(organizacionPrueba);

        List<Organizacion> organizaciones = organizacionDAO.obtenerOrganizacionesActivas();

        assertFalse(organizaciones.isEmpty());
    }
    
}
