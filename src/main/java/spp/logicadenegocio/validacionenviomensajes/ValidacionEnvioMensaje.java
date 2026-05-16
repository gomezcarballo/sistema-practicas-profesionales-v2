/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionenviomensajes;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import spp.accesoadatos.ConexionBD;
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
    
    public boolean enviarMensaje(Mensaje mensaje, String correoDestinatario)throws ReglaDeNegocioExcepcion{
        
        esDestinatarioValido(correoDestinatario);
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        MensajeDAO mensajeDAO = new MensajeDAO();
        EnvioMensajeDAO envioMensajeDAO = new EnvioMensajeDAO();
        
        boolean envioExitoso;
        
        try{
            
            int idMensaje = mensajeDAO.insertarMensaje(mensaje);
            envioExitoso = envioMensajeDAO.insertarEnvioMensaje(idMensaje, sesionUsuario.getIdUsuario(), 
            correoDestinatario);
            
        }catch(OperacionesDeDaoExcepcion e){
           
            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar un mensaje.", e);
             throw new ReglaDeNegocioExcepcion("No se pudo enviar el Mensaje por un problema "
                + "interno del sistema. Intente más tarde.", e);
            
        }
        return envioExitoso;
    }
    
    public boolean esDestinatarioValido(String correoDestinatario) throws ReglaDeNegocioExcepcion{
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        try {

            if(!usuarioDAO.existeCorreo(correoDestinatario)) {

                throw new ReglaDeNegocioExcepcion("No se encontró el destinatario");
                
            }

            return true;

        } catch(OperacionesDeDaoExcepcion e) {

            throw new ReglaDeNegocioExcepcion("Error al validar el destinatario", e);
        }
        
    }
    
}
