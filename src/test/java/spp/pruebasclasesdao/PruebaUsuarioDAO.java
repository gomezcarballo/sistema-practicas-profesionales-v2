/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class PruebaUsuarioDAO {
    
    @Test
    public void pruebaInsertarUsuarioDaoExitosa()throws OperacionesDeDaoExcepcion{
       
        Usuario usuario = new Usuario();
        UsuarioDAO usuarioDao = new UsuarioDAO();
        
        usuario.setNombre("Jorge Octavio");
        usuario.setApellidoPaterno("Ocharan Hernandez");
        usuario.setContraseña("password");
        usuario.setEsActivo(true);
        
        int idGenerado = usuarioDao.insertarUsuario(usuario);
        assertTrue("No se generó un ID válido", idGenerado > 0);
        
    }
    
}
