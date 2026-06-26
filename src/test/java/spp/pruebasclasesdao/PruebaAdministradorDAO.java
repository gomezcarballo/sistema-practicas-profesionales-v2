/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import java.util.logging.Level;
import org.junit.After;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdao.AdministradorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaAdministradorDAO {
    
    private UsuarioDAO usuarioDAO;
    private AdministradorDAO administradorDAO;

    private int idUsuarioPrueba;
    private String numeroPersonalPrueba;
    
    @Before
    public void inicializarDatosPrueba() throws OperacionesDeDaoExcepcion {
        
        usuarioDAO = new UsuarioDAO();
        administradorDAO = new AdministradorDAO();

        numeroPersonalPrueba = "78623";

        Usuario usuario = new Usuario();
        usuario.setNombre("Lalo");
        usuario.setApellidoPaterno("Mendez");
        usuario.setApellidoMaterno("Lopez");
        usuario.setCorreoInstitucional("admin@uv.mx");
        usuario.setContraseña("123");
        usuario.setEsActivo(true);

        idUsuarioPrueba = usuarioDAO.insertarUsuario(usuario);

        Administrador administrador = new Administrador();
        administrador.setIdUsuario(idUsuarioPrueba);
        administrador.setNumeroDePersonal(numeroPersonalPrueba);

        administradorDAO.insertarAdministrador(administrador);
        
    }

    @After
    public void eliminarDatosPrueba() {
        
        try {
            administradorDAO.eliminarAdministrador(idUsuarioPrueba);
        } catch (OperacionesDeDaoExcepcion e) {
            RegistroErrores.registrarError(Level.WARNING, "Fallo al eliminar administrador en limpieza de prueba", e);
        }
        
        try {
            usuarioDAO.eliminarUsuario(idUsuarioPrueba);
        } catch (OperacionesDeDaoExcepcion e) {
            RegistroErrores.registrarError(Level.WARNING, "Fallo al eliminar usuario en limpieza de prueba", e);
        }
            
    }

    @Test
    public void pruebaInsertarAdministradorExitoso() throws OperacionesDeDaoExcepcion {

        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setNombre("Erika");
        usuarioNuevo.setApellidoPaterno("Meneses");
        usuarioNuevo.setApellidoMaterno("Rico");
        usuarioNuevo.setCorreoInstitucional("admin455@uv.mx");
        usuarioNuevo.setContraseña("123");
        usuarioNuevo.setEsActivo(true);
        int idNuevo = usuarioDAO.insertarUsuario(usuarioNuevo);

        Administrador adminNuevo = new Administrador();
        adminNuevo.setIdUsuario(idNuevo);
        adminNuevo.setNumeroDePersonal("75698");

        administradorDAO.insertarAdministrador(adminNuevo);
        
        Administrador resultado = administradorDAO.consultarAdministrador(adminNuevo.getNumeroDePersonal());
        
        try {
            administradorDAO.eliminarAdministrador(idNuevo);
        } catch (OperacionesDeDaoExcepcion e) {
            RegistroErrores.registrarError(Level.WARNING, "Fallo al eliminar administrador insertado en prueba", e);
        }
        
        try {
            usuarioDAO.eliminarUsuario(idNuevo);
        } catch (OperacionesDeDaoExcepcion e) {
            RegistroErrores.registrarError(Level.WARNING, "Fallo al eliminar usuario insertado en prueba", e);
        }

        assertNotNull(resultado);
        
    }

    @Test
    public void pruebaConsultarAdministradorExistente() throws OperacionesDeDaoExcepcion {
        
        Administrador resultado = administradorDAO.consultarAdministrador(numeroPersonalPrueba);
        assertNotNull(resultado);
        
    }

    @Test
    public void pruebaInactivarAdministradorExitoso() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = administradorDAO.inactivarAdministrador(idUsuarioPrueba);
        assertTrue(resultado);
        
    }

    @Test
    public void pruebaConsultarAdministradorNoExistente() throws OperacionesDeDaoExcepcion {
        
        Administrador resultado = administradorDAO.consultarAdministrador("FALSO_999");
        assertNull(resultado);
        
    }

    @Test(expected = OperacionesDeDaoExcepcion.class)
    public void pruebaInsertarAdministradorDuplicado() throws OperacionesDeDaoExcepcion {
       
        Administrador duplicado = new Administrador();
        duplicado.setIdUsuario(idUsuarioPrueba);
        duplicado.setNumeroDePersonal(numeroPersonalPrueba);

        administradorDAO.insertarAdministrador(duplicado);
        
    }
    
}
