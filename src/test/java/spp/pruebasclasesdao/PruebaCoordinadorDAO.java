/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import static org.junit.Assert.assertTrue;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.clasesdao.CoordinadorDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class PruebaCoordinadorDAO {
    
    Usuario usuario = new Usuario();
    int idUsuario;
    
    @Before
    public void recursoInsertarUsuario()throws OperacionesDeDaoExcepcion{
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuario.setNombre("Juan Carlos");
        usuario.setApellidos("Perez Arriaga");
        usuario.setContraseña("password");
        usuario.setEsActivo(true);
        idUsuario = usuarioDao.registrarUsuario(usuario);
        
    }
    
    @Test
    public void pruebaInsertarCoordinadorDaoExitoso()throws OperacionesDeDaoExcepcion{
       
        Coordinador coordinador = new Coordinador();
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        
        coordinador.setIdUsuario(idUsuario);
        coordinador.setNumeroDePersonal("p2401");
        
        boolean registroExitoso = coordinadorDao.registrarCoordinador(coordinador);
        assertTrue(registroExitoso);
        
    }
    
    @After
    public void recursoEliminarUsuario()throws OperacionesDeDaoExcepcion{
        
        if(idUsuario < 0){
            
           CoordinadorDAO coordinadorDao = new CoordinadorDAO();
           UsuarioDAO usuarioDao = new UsuarioDAO();
           coordinadorDao.eliminarCoordinador("p2401");
           usuarioDao.eliminarUsuario(idUsuario);
           System.out.println("Usuario y coordinador de prueba eliminados");
           
        }
    }
}
