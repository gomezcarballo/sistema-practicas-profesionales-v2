/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionenviomensajes;

import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionEnvioMensaje {
   
    public void existeDestinatario(String destinatario)throws ReglaDeNegocioExcepcion{
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        try {

            int idUsuario = usuarioDAO.buscarIdPorCorreo(destinatario);

            if (idUsuario == 0) {

                throw new ReglaDeNegocioExcepcion("No se encontró el destinatario en el sistema. "
                        + "Verifique el correo institucional e intente nuevamente");
                
            }

        } catch (OperacionesDeDaoExcepcion e) {

            RegistroErrores.registrarError(Level.SEVERE,"Error al validar destinatario", e);

            throw new ReglaDeNegocioExcepcion("Error al validar el usuario. Intente más tarde.",e);
            
        }
  
    }
    
    public void validarTamañoMensaje(Mensaje mensaje) throws ReglaDeNegocioExcepcion {
        
        int maximoCaracteresAsunto = 50;
        
        if (mensaje.getAsunto().length() > maximoCaracteresAsunto) {

            throw new ReglaDeNegocioExcepcion( "El asunto excede el tamaño máximo permitido de " 
            + maximoCaracteresAsunto + " caracteres.");
            
        }
        
        int maximoCaracteresCuerpo = 750;
        
        if (mensaje.getCuerpo().length() > maximoCaracteresCuerpo) {

           throw new ReglaDeNegocioExcepcion("El cuerpo del mensaje excede el tamaño máximo permitido de " 
           + maximoCaracteresCuerpo + " caracteres.");

        }
        
    }
    
}