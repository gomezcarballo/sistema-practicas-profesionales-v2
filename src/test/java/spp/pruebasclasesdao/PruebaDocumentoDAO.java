/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import spp.logicadenegocio.clasesdao.DocumentoDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class PruebaDocumentoDAO {
    
    Usuario usuario = new Usuario();
    int idUsuario;
    
    @Before
    public void recursoInsertarUsuario()throws OperacionesDeDaoExcepcion{
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuario.setNombre("Jorge Octavio");
        usuario.setApellidoPaterno("Ocharan Hernandez");
        usuario.setContraseña("password");
        usuario.setEsActivo(true);
        idUsuario = usuarioDao.insertarUsuario(usuario);
        
    }
    
    @Test
    public void pruebaRegistrarDocumentoDAOExitoso()throws OperacionesDeDaoExcepcion{
        
        Documento documento = new Documento();
        DocumentoDAO documentoDao = new DocumentoDAO();
        
        documento.setNombre("BDDS Procemientos almacenados");
        documento.setRuta("ruta/ejemplo/BDDS Procemientos almacenados");
        documento.setTipo("Actividad");
        //documento.setIdUsuario(usuario);
        
        boolean registroExitoso = documentoDao.insertarDocumento(documento);
        assertTrue(registroExitoso);
        
    }
    
    @After
    public void recursoEliminarUsuario()throws OperacionesDeDaoExcepcion{
        
        if(idUsuario < 0){
           
           UsuarioDAO usuarioDao = new UsuarioDAO();
           usuarioDao.eliminarUsuario(idUsuario);
           System.out.println("Usuario de prueba eliminado");
           
        }
    }
}

