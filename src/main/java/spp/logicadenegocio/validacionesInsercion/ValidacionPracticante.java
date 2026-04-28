/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionesInsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionPracticante {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionPracticante.class.getName());
    
    public boolean ingresarPracticante(Practicante practicante)throws ReglaDeNegocioExcepcion{
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(practicante.getNombre());
        usuario.setApellidoPaterno(practicante.getApellidoPaterno());
        usuario.setApellidoMaterno(practicante.getApellidoMaterno());
        usuario.setContraseña("password");
        usuario.setEsActivo(true);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        PracticanteDAO practicanteDao = new PracticanteDAO();
        boolean registroExitoso;
        
        try{
            
            int idUsuario = usuarioDao.insertarUsuario(usuario);
            
            practicante.setIdUsuario(idUsuario);
            registroExitoso = practicanteDao.insertarPracticante(practicante);
            
            
        }catch(OperacionesDeDaoExcepcion e){
            
            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo practicante.", e);
            
            throw new ReglaDeNegocioExcepcion("Los valores no cumplen con el formato requerido", e);
            
        }
        return registroExitoso;
    }
}
