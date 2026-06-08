/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

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
        usuario.setNombre("Test");
        usuario.setApellidoPaterno("Admin");
        usuario.setApellidoMaterno("DAO");
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
    public void eliminarDatosPrueba() throws OperacionesDeDaoExcepcion {

        administradorDAO.eliminarAdministrador(idUsuarioPrueba);

        usuarioDAO.eliminarUsuario(idUsuarioPrueba);
            
    }


    @Test
    public void pruebaInsertarAdministradorExitoso() throws OperacionesDeDaoExcepcion {

        Usuario usuarioNuevo = new Usuario();
        usuarioNuevo.setNombre("Test2");
        usuarioNuevo.setApellidoPaterno("Insert");
        usuarioNuevo.setApellidoMaterno("DAO");
        usuarioNuevo.setCorreoInstitucional("admin455@uv.mx");
        usuarioNuevo.setContraseña("123");
        usuarioNuevo.setEsActivo(true);
        int idNuevo = usuarioDAO.insertarUsuario(usuarioNuevo);

        Administrador adminNuevo = new Administrador();
        adminNuevo.setIdUsuario(idNuevo);
        adminNuevo.setNumeroDePersonal("75698");


        administradorDAO.insertarAdministrador(adminNuevo);
        

        Administrador resultado = administradorDAO.consultarAdministrador(adminNuevo.getNumeroDePersonal());
        

        administradorDAO.eliminarAdministrador(idNuevo);
        usuarioDAO.eliminarUsuario(idNuevo);

        assertNotNull(resultado);
    }

    @Test
    public void pruebaConsultarAdministradorExistente() throws OperacionesDeDaoExcepcion {
        
        Administrador resultado = administradorDAO.consultarAdministrador(numeroPersonalPrueba);
        assertNotNull(resultado);
        
    }

    @Test
    public void pruebaInactivarAdministradorExitoso() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = administradorDAO.inactivarAdministrador();
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
