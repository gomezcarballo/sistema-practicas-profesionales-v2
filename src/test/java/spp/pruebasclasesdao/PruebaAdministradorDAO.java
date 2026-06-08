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
    
    private static int secuencia = (int) (System.currentTimeMillis() % 10000);

    @Before
    public void inicializarDatosPrueba() throws OperacionesDeDaoExcepcion {
        
        usuarioDAO = new UsuarioDAO();
        administradorDAO = new AdministradorDAO();

        secuencia++;
        numeroPersonalPrueba = "AD" + secuencia;

        Usuario usuario = new Usuario();
        usuario.setNombre("Test");
        usuario.setApellidoPaterno("Admin");
        usuario.setApellidoMaterno("DAO");
        usuario.setCorreoInstitucional("admin" + secuencia + "@uv.mx");
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

        secuencia++;
        Usuario uNuevo = new Usuario();
        uNuevo.setNombre("Test2");
        uNuevo.setApellidoPaterno("Insert");
        uNuevo.setApellidoMaterno("DAO");
        uNuevo.setCorreoInstitucional("admin" + secuencia + "@uv.mx");
        uNuevo.setContraseña("123");
        uNuevo.setEsActivo(true);
        int idNuevo = usuarioDAO.insertarUsuario(uNuevo);

        Administrador adminNuevo = new Administrador();
        adminNuevo.setIdUsuario(idNuevo);
        adminNuevo.setNumeroDePersonal("AD" + secuencia);


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
