/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import java.util.List;
import java.util.logging.Level;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import org.junit.Test;
import org.junit.Before;
import static org.junit.Assert.assertTrue;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.clasesdao.CoordinadorDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class PruebaCoordinadorDAO {
    
    private UsuarioDAO usuarioDAO;
    private CoordinadorDAO coordinadorDAO;

    private int idUsuario;
    private String correoUnico;

    @Before
    public void inicializarDatosPrueba() throws OperacionesDeDaoExcepcion {
        
        usuarioDAO = new UsuarioDAO();
        coordinadorDAO = new CoordinadorDAO();

        correoUnico = "coord_test_" + System.currentTimeMillis() + "@uv.mx";
        idUsuario = crearUsuarioBase();
        crearCoordinadorBase();
        
    }

    @After
    public void eliminarDatosPrueba() {
        
        try {
            coordinadorDAO.eliminarCoordinador(idUsuario);
        } catch (OperacionesDeDaoExcepcion e) {
            RegistroErrores.registrarError(Level.WARNING, "Fallo al eliminar coordinador en limpieza de prueba", e);
        }
        
        try {
            usuarioDAO.eliminarUsuario(idUsuario);
        } catch (OperacionesDeDaoExcepcion e) {
            RegistroErrores.registrarError(Level.WARNING, "Fallo al eliminar usuario en limpieza de prueba", e);
        }

    }

    private int crearUsuarioBase() throws OperacionesDeDaoExcepcion {
        
        Usuario usuario = new Usuario();
        usuario.setNombre("Javier");
        usuario.setApellidoPaterno("Lily");
        usuario.setApellidoMaterno("Reyes");
        usuario.setCorreoInstitucional(correoUnico);
        usuario.setContraseña("123");
        usuario.setEsActivo(true);

        return usuarioDAO.insertarUsuario(usuario);
        
    }

    private void crearCoordinadorBase() throws OperacionesDeDaoExcepcion {
        
        Coordinador coordinador = new Coordinador();
        coordinador.setIdUsuario(idUsuario);
        coordinador.setNumeroDePersonal("69954");

        coordinadorDAO.insertarCoordinador(coordinador);
        
    }

    @Test
    public void pruebaInsertarCoordinadorExitoso() throws OperacionesDeDaoExcepcion {
        
        boolean existe = coordinadorDAO.existeCoordinadorActivo();
        assertTrue(existe);
        
    }

    @Test
    public void pruebaConsultarCoordinadoresInactivosNoVacio() throws OperacionesDeDaoExcepcion {
        
        coordinadorDAO.inactivarCoordinador(idUsuario);
        List<Coordinador> resultado = coordinadorDAO.consultarCoordinadoresInactivos();
        
        assertFalse(resultado.isEmpty());
        
    }

    @Test
    public void pruebaInactivarCoordinadorExitoso() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = coordinadorDAO.inactivarCoordinador(idUsuario);
        assertTrue(resultado);
        
    }

    @Test
    public void pruebaReactivarCoordinadorExitoso() throws OperacionesDeDaoExcepcion {
        
        coordinadorDAO.inactivarCoordinador(idUsuario);
        boolean resultado = coordinadorDAO.reactivarCoordinador(idUsuario);
        
        assertTrue(resultado);
        
    }

    @Test
    public void pruebaExisteCoordinadorActivo() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = coordinadorDAO.existeCoordinadorActivo();
        assertTrue(resultado);
        
    }

}
