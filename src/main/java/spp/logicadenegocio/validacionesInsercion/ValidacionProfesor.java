/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionesInsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.ProfesorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionProfesor {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionProfesor.class.getName());
    
    public void ingresarProfesor(Profesor profesor){
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(profesor.getNombre());
        usuario.setApellidoPaterno(profesor.getApellidoPaterno());
        usuario.setContraseña("password");
        usuario.setEsActivo(true);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        ProfesorDAO profesorDao = new ProfesorDAO();
        
        try{
            
            int idUsuario = usuarioDao.insertarUsuario(usuario);
            
            profesor.setIdUsuario(idUsuario);
            profesorDao.insertarProfesor(profesor);
            
        }catch(OperacionesDeDaoExcepcion e){
            
           bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo profesor.", e);
            
        }
        
    }
}
