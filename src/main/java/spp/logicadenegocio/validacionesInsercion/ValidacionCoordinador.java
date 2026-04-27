/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionesInsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.CoordinadorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionCoordinador {
    private static final Logger bitacora = Logger.getLogger(ValidacionCoordinador.class.getName());

    public void ingresarCoordinador(Coordinador coordinador){
        
        Usuario usuario = new Usuario();
       
        usuario.setNombre(coordinador.getNombre());
        usuario.setApellidoPaterno(coordinador.getApellidoPaterno());
        usuario.setApellidoMaterno(coordinador.getApellidoMaterno());
        usuario.setContraseña("password");
        usuario.setEsActivo(true);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        
        try{
            
            int idUsuario = usuarioDao.insertarUsuario(usuario);
            
            coordinador.setIdUsuario(idUsuario);
            coordinadorDao.insertarCoordinador(coordinador);
            
        }catch(OperacionesDeDaoExcepcion e){

           //throw new excepcion personalizada o de java. Se manda el mensaje que no se pudo insertar

            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo coordinador.", e);
            
            //return "No se pudo registrar. Intente más tarde.";

            
        }
    }
    
    
}
