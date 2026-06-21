/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionenviomensajes;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionEnvioMensaje {

    private final int MAXIMO_CARACTERES_DESTINATARIO = 50;
    private final int MAXIMO_CARACTERES_ASUNTO = 50;
    private final int MAXIMO_CARACTERES_CUERPO = 750;

    public List<String> validarEnviarMensaje(Mensaje mensaje) throws OperacionesDeDaoExcepcion{

        List<String> listaValidaciones = new ArrayList<>(); 
        String destinatario = mensaje.getCorreoDestinatario();
        String asunto = mensaje.getAsunto();
        String cuerpo = mensaje.getCuerpo();
        String mensajeAlerta;

        if(!esTamañoDestinatarioValido(destinatario)){
            mensajeAlerta = "El destinatario excede de " + MAXIMO_CARACTERES_DESTINATARIO + " caracteres";
            listaValidaciones.add(mensajeAlerta);
        }

        if(!esTamañoAsuntoValido(asunto)){
            mensajeAlerta = "El asunto excede el tamaño máximo de " + MAXIMO_CARACTERES_ASUNTO + " caracteres";
            listaValidaciones.add(mensajeAlerta);
        }

        if(!esTamañoCuerpoValido(cuerpo)){ 
            mensajeAlerta = "El cuerpo del mensaje excede de " + MAXIMO_CARACTERES_CUERPO + " caracteres";
            listaValidaciones.add(mensajeAlerta);
        }
        if(listaValidaciones.isEmpty()){
            if (!existeDestinatario(destinatario)) {
                mensajeAlerta =  "No se encontró el destinatario en el sistema."; 
                listaValidaciones.add(mensajeAlerta);
            }
        }
        return listaValidaciones;

    }
    
    public boolean existeDestinatario(String destinatario)throws OperacionesDeDaoExcepcion{
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        boolean esDestinatarioValido = false;
        
        int idUsuario = usuarioDAO.buscarIdPorCorreo(destinatario);

        if (idUsuario > 0) {

            esDestinatarioValido = true;
        
        }
        
        return esDestinatarioValido;
    }
    
    public boolean esTamañoDestinatarioValido(String destinatario){
        
        boolean esTamañoValido = false;
        
        if (destinatario != null && destinatario.length() <= MAXIMO_CARACTERES_DESTINATARIO) {
            esTamañoValido = true; 
        }

        return esTamañoValido;
    }

    public boolean esTamañoAsuntoValido (String asunto){
        
        boolean esTamañoValido = false;
        
        if (asunto != null && asunto.length() <= MAXIMO_CARACTERES_ASUNTO) {
            
            esTamañoValido = true;
            
        }

        return esTamañoValido;  

    }

    public boolean esTamañoCuerpoValido (String cuerpoMensaje){

        boolean esTamañoValido = false;
        
        if (cuerpoMensaje != null && cuerpoMensaje.length() <= MAXIMO_CARACTERES_CUERPO) {
            
            esTamañoValido = true;

        }
        
        return esTamañoValido;
    }
    
}