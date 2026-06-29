/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import java.time.LocalDate;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdao.SolicitudProyectosDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaSolicitudProyectoDAO {

    private SolicitudProyectosDAO solicitudDAO;
    private UsuarioDAO usuarioDAO;
    private PracticanteDAO practicanteDAO;
    private OrganizacionDAO organizacionDAO;
    private ProyectoDAO proyectoDAO;

    private final String correoPrueba = "solicitud.prueba@uv.mx";
    private final String matriculaPrueba = "S24000001";
    private final String nombreOrganizacionPrueba = "Organizacion Solicitud";
    private final String nombreProyectoPrueba = "Proyecto para Solicitud";

    @Before
    public void inicializarDatosPrueba() {

        solicitudDAO = new SolicitudProyectosDAO();
        usuarioDAO = new UsuarioDAO();
        practicanteDAO = new PracticanteDAO();
        organizacionDAO = new OrganizacionDAO();
        proyectoDAO = new ProyectoDAO();
    }

    @After
    public void eliminarDatosPrueba() throws OperacionesDeDaoExcepcion {

        Proyecto proyecto = proyectoDAO.consultarProyecto(nombreProyectoPrueba);
        Usuario usuario = usuarioDAO.consultarUsuario(usuarioDAO.buscarIdPorCorreo(correoPrueba));
        Organizacion organizacion = organizacionDAO.consultarOrganizacion(nombreOrganizacionPrueba);

        if (usuario != null && proyecto != null) {
            solicitudDAO.eliminarSolicitud(usuario.getIdUsuario(), proyecto.getIdProyecto());
        }

        if (proyecto != null) {
            proyectoDAO.eliminarProyecto(nombreProyectoPrueba);
        }

        if (organizacion != null) {
            organizacionDAO.eliminarOrganizacion(nombreOrganizacionPrueba);
        }

        if (usuario != null) {
            practicanteDAO.eliminarPracticante(usuario.getIdUsuario());
            usuarioDAO.eliminarUsuario(usuario.getIdUsuario());
        }
    }

    @Test
    public void pruebaGuardarSolicitudExitoso() throws OperacionesDeDaoExcepcion {

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

        List<Proyecto> proyectos = solicitudDAO.obtenerProyectosSolicitados(idUsuario);

        assertFalse(proyectos.isEmpty());
    }

    @Test
    public void pruebaObtenerProyectosSolicitadosExistente() throws OperacionesDeDaoExcepcion {

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

        List<Proyecto> proyectos = solicitudDAO.obtenerProyectosSolicitados(idUsuario);

        assertFalse(proyectos.isEmpty());
    }

    @Test
    public void pruebaObtenerProyectosSolicitadosSinSolicitudes() throws OperacionesDeDaoExcepcion {

        usuarioDAO.insertarUsuario(crearUsuario());

        int idUsuario = usuarioDAO.buscarIdPorCorreo(correoPrueba);

        Practicante practicante = crearPracticante();
        practicante.setIdUsuario(idUsuario);

        practicanteDAO.insertarPracticante(practicante);

        List<Proyecto> proyectos = solicitudDAO.obtenerProyectosSolicitados(idUsuario);

        assertTrue(proyectos.isEmpty());
    }

    private Usuario crearUsuario() {

        Usuario usuario = new Usuario();

        usuario.setNombre("Solicitud");
        usuario.setApellidoPaterno("Prueba");
        usuario.setApellidoMaterno("DAO");
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

        return practicante;
    }

    private Organizacion crearOrganizacion() {

        Organizacion organizacion = new Organizacion();

        organizacion.setNombre(nombreOrganizacionPrueba);
        organizacion.setDireccion("Direccion prueba");
        organizacion.setSector("Publico");
        organizacion.setEsActivo(true);

        return organizacion;
    }

    private Proyecto crearProyecto(Organizacion organizacion) {

        Proyecto proyecto = new Proyecto();

        proyecto.setNombre(nombreProyectoPrueba);
        proyecto.setObjetivoGeneral("Objetivo prueba");
        proyecto.setNombreResponsable("Responsable prueba");
        proyecto.setContactoResponsable("responsable@uv.mx");
        proyecto.setCupoMaximo(5);
        proyecto.setMetodologia("Metodologia prueba");
        proyecto.setEsActivo(true);
        proyecto.setOrganizacion(organizacion);

        return proyecto;
    }
    
}
