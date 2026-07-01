/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdao.ProfesorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaProfesorDAO {
    
    private UsuarioDAO usuarioDAO;
    private ProfesorDAO profesorDAO;

    private int idUsuarioPrueba;
    private Profesor profesorPrueba;

    @Before
    public void inicializarDatos() throws OperacionesDeDaoExcepcion {
        
        usuarioDAO = new UsuarioDAO();
        profesorDAO = new ProfesorDAO();

        Usuario usuario = crearUsuarioPrueba();
        idUsuarioPrueba = usuarioDAO.insertarUsuario(usuario);

        profesorPrueba = crearProfesorPrueba(idUsuarioPrueba);
        profesorDAO.insertarProfesor(profesorPrueba);
        
    }

    @After
    public void eliminarDatos() throws OperacionesDeDaoExcepcion {
        
        profesorDAO.eliminarProfesor(idUsuarioPrueba);
        usuarioDAO.eliminarUsuario(idUsuarioPrueba);
        
    }

    @Test
    public void pruebaInsertarProfesorExitoso() throws OperacionesDeDaoExcepcion {
        
        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setNombre("Luz Fernanda");
        usuarioNuevo.setApellidoPaterno("Herrera");
        usuarioNuevo.setApellidoMaterno("Juarez");
        usuarioNuevo.setCorreoInstitucional("luz@uv.mx");
        usuarioNuevo.setContraseña("secreta123");
        usuarioNuevo.setEsActivo(true);
        
        int idUsuarioNuevo = usuarioDAO.insertarUsuario(usuarioNuevo);
        
        Profesor profesorNuevo = new Profesor();
        profesorNuevo.setIdUsuario(idUsuarioNuevo);
        profesorNuevo.setNumeroDePersonal("99887");
        
        boolean resultado = profesorDAO.insertarProfesor(profesorNuevo);
        
        profesorDAO.eliminarProfesor(idUsuarioNuevo);
        usuarioDAO.eliminarUsuario(idUsuarioNuevo);
        
        assertTrue(resultado);
        
    }

    @Test
    public void pruebaConsultarProfesoresActivosNoVacio() throws OperacionesDeDaoExcepcion {
        
        List<Profesor> lista = profesorDAO.consultarProfesoresActivos();
        assertFalse(lista.isEmpty());
        
    }

    @Test
    public void pruebaCantidadProfesoresActivosMayorACero() throws OperacionesDeDaoExcepcion {
        
        int cantidad = profesorDAO.obtenerCantidadProfesoresActivos();
        assertTrue(cantidad > 0);
        
    }

    @Test
    public void pruebaInactivarProfesorExitoso() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = profesorDAO.inactivarProfesor(idUsuarioPrueba);
        assertTrue(resultado);
        
    }

    @Test
    public void pruebaReactivarProfesorExitoso() throws OperacionesDeDaoExcepcion {
        
        profesorDAO.inactivarProfesor(idUsuarioPrueba);
        boolean resultado = profesorDAO.reactivarProfesor(idUsuarioPrueba);
        assertTrue(resultado);
        
    }

    @Test
    public void pruebaEliminarProfesorExitoso() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = profesorDAO.eliminarProfesor(idUsuarioPrueba);
        assertTrue(resultado);
        
    }

    private Usuario crearUsuarioPrueba() {
        
        Usuario usuario = new Usuario();
        usuario.setNombre("Endric");
        usuario.setApellidoPaterno("Vera");
        usuario.setApellidoMaterno("Toledo");
        usuario.setCorreoInstitucional("endric@uv.mx");
        usuario.setContraseña("secreta123");
        usuario.setEsActivo(true);
        return usuario;
        
    }

    private Profesor crearProfesorPrueba(int idUsuario) {
        
        Profesor profesor = new Profesor();
        profesor.setIdUsuario(idUsuario);
        profesor.setNumeroDePersonal("87654");
        return profesor;
        
    }
    
}
