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

        if(!validarTamañoDestinatario(destinatario)){
            mensajeAlerta = "El destinatario excede de " + MAXIMO_CARACTERES_DESTINATARIO + " caracteres";
            listaValidaciones.add(mensajeAlerta);
        }

        if(!validarTamañoAsunto(asunto)){
            mensajeAlerta = "El asunto excede el tamaño máximo de " + MAXIMO_CARACTERES_ASUNTO + " caracteres";
            listaValidaciones.add(mensajeAlerta);
        }

        if(!validarTamañoCuerpo(cuerpo)){ 
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
        boolean esDestinatarioValido = true;
        
        int idUsuario = usuarioDAO.buscarIdPorCorreo(destinatario);

        if (idUsuario == 0) {

            esDestinatarioValido = false;
        
        }
        
        return esDestinatarioValido;
    }
    
    public boolean validarTamañoDestinatario(String destinatario){
        
        boolean esTamañoValido = true;
        
        if (destinatario != null && destinatario.length() > MAXIMO_CARACTERES_DESTINATARIO) {
            esTamañoValido = false; 
        }

        return esTamañoValido;
    }

    public boolean validarTamañoAsunto (String asunto){
        
        boolean esTamañoValido = true;
        
        if (asunto.length() > MAXIMO_CARACTERES_ASUNTO) {
            
            esTamañoValido = false;
            
        }

        return esTamañoValido;  

    }

    public boolean validarTamañoCuerpo (String cuerpoMensaje){

        boolean esTamañoValido =true;
        
        if (cuerpoMensaje.length() > MAXIMO_CARACTERES_CUERPO) {
            
            esTamañoValido = false;

        }
        
        return esTamañoValido;
    }
    
}