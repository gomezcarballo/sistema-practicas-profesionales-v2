/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionenviomensajes;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.EnvioMensajeDAO;
import spp.logicadenegocio.clasesdao.MensajeDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionEnvioMensaje {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionEnvioMensaje.class.getName());
    
    private static final int MAXIMO_CARACTERES_ASUNTO = 50;
    private static final int MAXIMO_CARACTERES_CUERPO = 750;
    
    public boolean enviarMensaje(Mensaje mensaje, String correoDestinatario)throws ReglaDeNegocioExcepcion{
                
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        MensajeDAO mensajeDAO = new MensajeDAO();
        EnvioMensajeDAO envioMensajeDAO = new EnvioMensajeDAO();

        boolean envioExitoso;
        
        try{
            
            validarTamañoMensaje(mensaje);
            
            existeDestinatario(correoDestinatario);
            
            int idDestinatario = new UsuarioDAO().buscarIdPorCorreo(correoDestinatario);
            
            int idMensaje = mensajeDAO.insertarMensaje(mensaje);
            
            envioExitoso = envioMensajeDAO.insertarEnvioMensaje(idMensaje, sesionUsuario.getIdUsuario(), 
            idDestinatario);
            
        }catch(OperacionesDeDaoExcepcion e){
           
            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar un mensaje.", e);
             throw new ReglaDeNegocioExcepcion("No se pudo enviar el Mensaje por un problema "
                + "interno del sistema. Intente más tarde.", e);
            
        }
        return envioExitoso;
    }
    
    public void existeDestinatario(String destinatario)throws ReglaDeNegocioExcepcion{
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        
        try {

            int idUsuario = usuarioDAO.buscarIdPorCorreo(destinatario);

            if (idUsuario == 0) {

                throw new ReglaDeNegocioExcepcion("No se encontró el destinatario en el sistema. "
                        + "Verifique el correo institucional e intente nuevamente");
                
            }

        } catch (OperacionesDeDaoExcepcion e) {

            bitacora.log(Level.SEVERE,"Error al validar destinatario", e);

            throw new ReglaDeNegocioExcepcion("Error al validar el usuario. Intente más tarde.",e);
            
        }
  
    }
    
    public void validarTamañoMensaje(Mensaje mensaje) throws ReglaDeNegocioExcepcion {
        
        if (mensaje.getAsunto().length() > MAXIMO_CARACTERES_ASUNTO) {

            throw new ReglaDeNegocioExcepcion( "El asunto excede el tamaño máximo permitido de " 
            + MAXIMO_CARACTERES_ASUNTO + " caracteres.");
            
        }
        
         if (mensaje.getCuerpo().length() > MAXIMO_CARACTERES_CUERPO) {

            throw new ReglaDeNegocioExcepcion("El cuerpo del mensaje excede el tamaño máximo permitido de " 
            + MAXIMO_CARACTERES_CUERPO + " caracteres.");

         }
        
    }
    
}