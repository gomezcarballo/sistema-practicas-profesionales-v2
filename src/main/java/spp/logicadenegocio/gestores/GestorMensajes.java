/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.EnvioMensajeDAO;
import spp.logicadenegocio.clasesdao.MensajeDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.interfacesdao.IMensajeDAO;
import spp.logicadenegocio.validaciones.validacionenviomensajes.ValidacionEnvioMensaje;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorMensajes {
    
    private IMensajeDAO mensajeDAO;
    
    public boolean enviarMensaje(Mensaje mensaje, String correoDestinatario)throws ReglaDeNegocioExcepcion{
                
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        MensajeDAO mensajeDAO = new MensajeDAO();
        EnvioMensajeDAO envioMensajeDAO = new EnvioMensajeDAO();

        boolean envioExitoso;
        
        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();
            
        validacion.validarTamañoMensaje(mensaje);
            
        validacion.existeDestinatario(correoDestinatario);
        
        try{
  
            int idDestinatario = new UsuarioDAO().buscarIdPorCorreo(correoDestinatario);
            
            int idMensaje = mensajeDAO.insertarMensaje(mensaje);
            
            envioExitoso = envioMensajeDAO.insertarEnvioMensaje(idMensaje, sesionUsuario.getIdUsuario(), 
            idDestinatario);
            
        }catch(OperacionesDeDaoExcepcion e){
           
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico de base de datos al registrar un mensaje.", e);
             throw new ReglaDeNegocioExcepcion("No se pudo enviar el Mensaje por un problema "
                + "interno del sistema. Intente más tarde.", e);
            
        }
        
        return envioExitoso;
        
    }

    public List<Mensaje> consultarMensajesEnviados(int idUsuario)throws ReglaDeNegocioExcepcion{
        
        try{
           
           mensajeDAO = new MensajeDAO();
           return mensajeDAO.consultarMensajesEnviados(idUsuario);
           
        }catch(OperacionesDeDaoExcepcion e){
            
           throw new ReglaDeNegocioExcepcion("No se pudieron obtener los mensajes");
           
        }
        
    }
    
    public List<Mensaje> consultarMensajesRecibidos(int idUsuario)throws ReglaDeNegocioExcepcion{
        
        try{
           
           mensajeDAO = new MensajeDAO();
           return mensajeDAO.consultarMensajesPorDestinatario(idUsuario);
           
       }catch(OperacionesDeDaoExcepcion e){
           
           throw new ReglaDeNegocioExcepcion("No se pudieron obtener los mensajes");
           
       }
        
    }
    
}
