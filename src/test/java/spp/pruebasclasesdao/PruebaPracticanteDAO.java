/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package spp.pruebasclasesdao;

import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import org.junit.Before;
import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdao.SolicitudDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaPracticanteDAO {
    
    private PracticanteDAO practicanteDAO;
    private UsuarioDAO usuarioDAO;
    private OrganizacionDAO organizacionDAO;
    private ProyectoDAO proyectoDAO;
    private SolicitudDAO solicitudDAO;

    private final String correoPrueba = "practicante.prueba@uv.mx";
    private final String matriculaPrueba = "S24000001";
    private final String nombreOrganizacionPrueba = "Organizacion Practicante";
    private final String nombreProyectoPrueba = "Proyecto Practicante";
    
    private Usuario crearUsuario() {

        Usuario usuario = new Usuario();

        usuario.setNombre("Juan");
        usuario.setApellidoPaterno("Perez");
        usuario.setApellidoMaterno("Lopez");
        usuario.setCorreoInstitucional(correoPrueba);
        usuario.setContraseña("123456");
        usuario.setEsActivo(true);

        return usuario;
    }

    private Practicante crearPracticante() {

        Practicante practicante = new Practicante();

        practicante.setMatricula(matriculaPrueba);
        practicante.setGenero("Masculino");
        practicante.setFechaNacimiento(LocalDate.of(2000, 1, 1));
        practicante.setHablaLenguaIndigena(false);
        practicante.setNrcAsignado("12345");

        return practicante;
    }

    private Organizacion crearOrganizacion() {

        Organizacion organizacion = new Organizacion();

        organizacion.setNombre(nombreOrganizacionPrueba);
        organizacion.setDireccion("Direccion de prueba");
        organizacion.setSector("Publico");
        organizacion.setEsActivo(true);

        return organizacion;
    }

    private Proyecto crearProyecto(Organizacion organizacion) {

        Proyecto proyecto = new Proyecto();

        proyecto.setNombre(nombreProyectoPrueba);
        proyecto.setObjetivoGeneral("Objetivo de prueba");
        proyecto.setNombreResponsable("Responsable");
        proyecto.setContactoResponsable("responsable@uv.mx");
        proyecto.setMetodologia("Metodologia");
        proyecto.setCupoMaximo(5);
        proyecto.setEsActivo(true);
        proyecto.setOrganizacion(organizacion);

        return proyecto;
    }
    
    @Before
    public void inicializarDatosPrueba() {

        practicanteDAO = new PracticanteDAO();
        usuarioDAO = new UsuarioDAO();
        organizacionDAO = new OrganizacionDAO();
        proyectoDAO = new ProyectoDAO();
        solicitudDAO = new SolicitudDAO();
    }

    @After
    public void eliminarDatosPrueba() throws OperacionesDeDaoExcepcion {

        Proyecto proyecto = proyectoDAO.consultarProyecto(nombreProyectoPrueba);
        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        int idUsuario = usuarioDAO.buscarIdPorCorreo(correoPrueba);

        if (idUsuario != 0 && proyecto != null) {
            solicitudDAO.eliminarSolicitud(idUsuario, proyecto.getIdProyecto());
        }

        if (idUsuario != 0) {
            proyectoDAO.desasignarProyecto(idUsuario);
        }

        if (proyecto != null) {
            proyectoDAO.eliminarProyecto(nombreProyectoPrueba);
        }

        if (organizacion != null) {
            organizacionDAO.eliminarOrganizacion(nombreOrganizacionPrueba);
        }

        if (idUsuario != 0) {
            practicanteDAO.eliminarPracticante(idUsuario);
            usuarioDAO.eliminarUsuario(idUsuario);
        }
    }

    @Test
    public void pruebaInsertarPracticanteExitoso() throws OperacionesDeDaoExcepcion {

        usuarioDAO.insertarUsuario(crearUsuario());

        int idUsuario = usuarioDAO.buscarIdPorCorreo(correoPrueba);

        Practicante practicante = crearPracticante();
        practicante.setIdUsuario(idUsuario);

        boolean registroExitoso = practicanteDAO.insertarPracticante(practicante);

        assertTrue(registroExitoso);
    }

    @Test
    public void pruebaConsultarPracticantesExitoso() throws OperacionesDeDaoExcepcion {

        usuarioDAO.insertarUsuario(crearUsuario());

        int idUsuario = usuarioDAO.buscarIdPorCorreo(correoPrueba);

        Practicante practicante = crearPracticante();
        practicante.setIdUsuario(idUsuario);

        practicanteDAO.insertarPracticante(practicante);

        List<Practicante> practicantes = practicanteDAO.consultarPracticantes();

        assertFalse(practicantes.isEmpty());
    }

    @Test
    public void pruebaInactivarPracticanteExistente() throws OperacionesDeDaoExcepcion {

        usuarioDAO.insertarUsuario(crearUsuario());

        int idUsuario = usuarioDAO.buscarIdPorCorreo(correoPrueba);

        Practicante practicante = crearPracticante();
        practicante.setIdUsuario(idUsuario);

        practicanteDAO.insertarPracticante(practicante);

        boolean inactivacionExitosa = practicanteDAO.inactivarPracticante(idUsuario);

        assertTrue(inactivacionExitosa);
    }

    @Test
    public void pruebaInactivarPracticanteInexistente() throws OperacionesDeDaoExcepcion {

        boolean inactivacionExitosa = practicanteDAO.inactivarPracticante(-1);

        assertFalse(inactivacionExitosa);
    }

    @Test
    public void pruebaBuscarPracticanteExistente() throws OperacionesDeDaoExcepcion {

        usuarioDAO.insertarUsuario(crearUsuario());

        int idUsuario = usuarioDAO.buscarIdPorCorreo(correoPrueba);

        Practicante practicante = crearPracticante();
        practicante.setIdUsuario(idUsuario);

        practicanteDAO.insertarPracticante(practicante);

        UsuarioEncontrado usuarioEncontrado = practicanteDAO.buscarPracticante(matriculaPrueba);

        assertNotNull(usuarioEncontrado);
    }

    @Test
    public void pruebaBuscarPracticanteInexistente() throws OperacionesDeDaoExcepcion {

        UsuarioEncontrado usuarioEncontrado = practicanteDAO.buscarPracticante("NO_EXISTE");

        assertNull(usuarioEncontrado);
    }

    @Test
    public void pruebaTieneProyectoAsignadoVerdadero() throws OperacionesDeDaoExcepcion {

        usuarioDAO.insertarUsuario(crearUsuario());

        int idUsuario = usuarioDAO.buscarIdPorCorreo(correoPrueba);

        Practicante practicante = crearPracticante();
        practicante.setIdUsuario(idUsuario);

        practicanteDAO.insertarPracticante(practicante);

        organizacionDAO.insertarOrganizacion(crearOrganizacion());

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        proyectoDAO.insertarProyecto(crearProyecto(organizacion));

        Proyecto proyecto = proyectoDAO.consultarProyecto(nombreProyectoPrueba);

        proyectoDAO.asignarProyecto(proyecto.getIdProyecto(), idUsuario);

        boolean tieneProyecto = practicanteDAO.tieneProyectoAsignado(idUsuario);

        assertTrue(tieneProyecto);
    }

    @Test
    public void pruebaTieneProyectoAsignadoFalso() throws OperacionesDeDaoExcepcion {

        usuarioDAO.insertarUsuario(crearUsuario());

        int idUsuario = usuarioDAO.buscarIdPorCorreo(correoPrueba);

        Practicante practicante = crearPracticante();
        practicante.setIdUsuario(idUsuario);

        practicanteDAO.insertarPracticante(practicante);

        boolean tieneProyecto = practicanteDAO.tieneProyectoAsignado(idUsuario);

        assertFalse(tieneProyecto);
    }

    @Test
    public void pruebaConsultarPracticantesConSolicitudesExistente() throws OperacionesDeDaoExcepcion {

        usuarioDAO.insertarUsuario(crearUsuario());

        int idUsuario = usuarioDAO.buscarIdPorCorreo(correoPrueba);

        Practicante practicante = crearPracticante();
        practicante.setIdUsuario(idUsuario);

        practicanteDAO.insertarPracticante(practicante);

        organizacionDAO.insertarOrganizacion(crearOrganizacion());

        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        proyectoDAO.insertarProyecto(crearProyecto(organizacion));

        Proyecto proyecto = proyectoDAO.consultarProyecto(nombreProyectoPrueba);

        solicitudDAO.guardarSolicitud(idUsuario, proyecto.getIdProyecto());

        List<Practicante> practicantes = practicanteDAO.consultarPracticantesConSolicitudes();

        assertFalse(practicantes.isEmpty());
    }
  
}
