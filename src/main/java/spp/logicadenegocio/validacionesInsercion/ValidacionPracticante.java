/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionesInsercion;

import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionPracticante {
    
    public String registrarPracticante(Practicante practicante){
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(practicante.getNombre());
        usuario.setApellidoPaterno(practicante.getApellidoPaterno());
        usuario.setContraseña("password");
        usuario.setEsActivo(true);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        PracticanteDAO practicanteDao = new PracticanteDAO();
        
        try{
            
            int idUsuario = usuarioDao.insertarUsuario(usuario);
            
            practicante.setIdUsuario(idUsuario);
            practicanteDao.insertarPracticante(practicante);
            return "Practicante registrado correctamente";
            
        }catch(OperacionesDeDaoExcepcion e){
            
            return "No se pudo registrar. Intente más tarde.";   
            
        }
       
    }
}
