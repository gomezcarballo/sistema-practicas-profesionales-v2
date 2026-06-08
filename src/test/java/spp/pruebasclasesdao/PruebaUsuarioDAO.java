/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class PruebaUsuarioDAO {
    
    private UsuarioDAO usuarioDAO;
    private Usuario usuarioPrueba;
    private int idUsuarioPrueba;

    @Before
    public void inicializarDatosPrueba() {

        usuarioDAO = new UsuarioDAO();

        usuarioPrueba = new Usuario();
        usuarioPrueba.setNombre("Juan");
        usuarioPrueba.setApellidoPaterno("Perez");
        usuarioPrueba.setApellidoMaterno("Lopez");
        usuarioPrueba.setCorreoInstitucional("prueba_" + System.currentTimeMillis() + "@uv.mx");
        usuarioPrueba.setContraseña("123456");
        usuarioPrueba.setEsActivo(true);

        idUsuarioPrueba = 0;
        
    }

    @After
    public void eliminarDatosPrueba() throws OperacionesDeDaoExcepcion {

        if (idUsuarioPrueba > 0) {

            Usuario usuario = usuarioDAO.consultarUsuario(idUsuarioPrueba);

            if (usuario != null) {
                usuarioDAO.eliminarUsuario(idUsuarioPrueba);
            }
        }
    }

    @Test
    public void pruebaInsertarUsuarioExitoso()throws OperacionesDeDaoExcepcion {

        idUsuarioPrueba = usuarioDAO.insertarUsuario(usuarioPrueba);

        Usuario usuarioConsultado = usuarioDAO.consultarUsuario(idUsuarioPrueba);

        assertNotNull(usuarioConsultado);
        
    }

    @Test
    public void pruebaConsultarUsuarioExistente()throws OperacionesDeDaoExcepcion {

        idUsuarioPrueba = usuarioDAO.insertarUsuario(usuarioPrueba);

        Usuario usuarioConsultado = usuarioDAO.consultarUsuario(idUsuarioPrueba);

        assertNotNull(usuarioConsultado);
        
    }

    @Test
    public void pruebaConsultarUsuarioInexistente()throws OperacionesDeDaoExcepcion {

        Usuario usuarioConsultado = usuarioDAO.consultarUsuario(-1);

        assertNull(usuarioConsultado);
        
    }

    @Test
    public void pruebaEliminarUsuarioExistente()throws OperacionesDeDaoExcepcion {

        idUsuarioPrueba = usuarioDAO.insertarUsuario(usuarioPrueba);

        boolean eliminacionExitosa = usuarioDAO.eliminarUsuario(idUsuarioPrueba);

        idUsuarioPrueba = 0;

        assertTrue(eliminacionExitosa);
        
    }

    @Test
    public void pruebaEliminarUsuarioInexistente()throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = usuarioDAO.eliminarUsuario(-1);

        assertFalse(eliminacionExitosa);
        
    }

    @Test
    public void pruebaActualizarContrasenaExistente()throws OperacionesDeDaoExcepcion {

        idUsuarioPrueba = usuarioDAO.insertarUsuario(usuarioPrueba);

        boolean actualizacionExitosa = usuarioDAO.actualizarContraseña(idUsuarioPrueba, "Nueva123");

        assertTrue(actualizacionExitosa);
        
    }

    @Test
    public void pruebaActualizarContrasenaInexistente()throws OperacionesDeDaoExcepcion {

        boolean actualizacionExitosa = usuarioDAO.actualizarContraseña(-1, "Nueva123");

        assertFalse(actualizacionExitosa);
        
    }

    @Test
    public void pruebaBuscarIdPorCorreoExistente() throws OperacionesDeDaoExcepcion {

        idUsuarioPrueba = usuarioDAO.insertarUsuario(usuarioPrueba);

        int idEncontrado = usuarioDAO.buscarIdPorCorreo(usuarioPrueba.getCorreoInstitucional());

        assertEquals(idUsuarioPrueba, idEncontrado);
        
    }

    @Test
    public void pruebaBuscarIdPorCorreoInexistente()throws OperacionesDeDaoExcepcion {

        int idEncontrado = usuarioDAO.buscarIdPorCorreo("correo_inexistente@uv.mx");

        assertEquals(0, idEncontrado);
        
    }
    
}
