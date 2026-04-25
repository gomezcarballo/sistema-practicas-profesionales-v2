/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
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
    
    Usuario usuario = new Usuario();
    int idUsuario;
    
    @Before
    public void recursoInsertarUsuario()throws OperacionesDeDaoExcepcion{
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuario.setNombre("Lizbeth");
        usuario.setApellidos("Hernandez Gonzalez");
        usuario.setContraseña("password");
        usuario.setEsActivo(true);
        idUsuario = usuarioDao.registrarUsuario(usuario);
        
    }
    
    @Test
    public void pruebaInsertarProfesorDaoExitoso()throws OperacionesDeDaoExcepcion{
       
        Profesor profesor = new Profesor();
        ProfesorDAO profesorDao = new ProfesorDAO();
        
        profesor.setIdUsuario(usuario.getIdUsuario());
        profesor.setNumeroDePersonal("p2402");
        
        boolean registroExitoso = profesorDao.registrarProfesor(profesor);
        assertTrue(registroExitoso);
        
    }
}
