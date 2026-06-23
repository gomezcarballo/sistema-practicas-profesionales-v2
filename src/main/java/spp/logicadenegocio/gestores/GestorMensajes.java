/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdao.EnvioMensajeDAO;
import spp.logicadenegocio.clasesdao.MensajeDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.interfacesdao.IMensajeDAO;
import spp.logicadenegocio.validaciones.validacionenviomensajes.ValidacionEnvioMensaje;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorMensajes {
    
    private IMensajeDAO mensajeDAO;

    public List<String> validarCamposDeMensaje(Mensaje mensaje) throws OperacionesDeDaoExcepcion{

        ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();
        List<String> listaValidaciones = new ArrayList<>();
        listaValidaciones = validacion.validarEnviarMensaje(mensaje);
        return listaValidaciones;

    }
    
    public boolean enviarMensaje(Mensaje mensaje)throws OperacionesDeDaoExcepcion{
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        MensajeDAO mensajeDAO = new MensajeDAO();
        EnvioMensajeDAO envioMensajeDAO = new EnvioMensajeDAO();

        boolean envioExitoso;

        String correoDestinatario = mensaje.getCorreoDestinatario();
        int idDestinatario = new UsuarioDAO().buscarIdPorCorreo(correoDestinatario);
        
        int idMensaje = mensajeDAO.insertarMensaje(mensaje);
        
        envioExitoso = envioMensajeDAO.insertarEnvioMensaje(idMensaje, sesionUsuario.getIdUsuario(), 
        idDestinatario);
    
        return envioExitoso;
        
    }

    public List<Mensaje> consultarMensajesEnviados(int idUsuario)throws OperacionesDeDaoExcepcion{

        mensajeDAO = new MensajeDAO();
        return mensajeDAO.consultarMensajesEnviados(idUsuario);

        
    }
    
    public List<Mensaje> consultarMensajesRecibidos(int idUsuario)throws OperacionesDeDaoExcepcion{
        
        mensajeDAO = new MensajeDAO();
        return mensajeDAO.consultarMensajesPorDestinatario(idUsuario);

        
    }
    
}
