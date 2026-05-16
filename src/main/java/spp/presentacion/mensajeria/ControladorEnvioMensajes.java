/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.mensajeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.validacionenviomensajes.ValidacionEnvioMensaje;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorEnvioMensajes {
    
    @FXML
    private TextField ingresaAsunto;
    
    @FXML
    private TextField ingresaDestinatario;
    
    @FXML
    private TextArea ingresaCuerpo;
    
    @FXML
    private void leerDatosMensaje(){
        
        if(sonCamposValidos()){
            
            String asunto = ingresaAsunto.getText();
            String correoDestinatario = ingresaDestinatario.getText().trim().toLowerCase();
            String cuerpo = ingresaCuerpo.getText();
            
            Mensaje mensajeNuevo = new Mensaje();        
            
            mensajeNuevo.setAsunto(asunto);
            mensajeNuevo.setCuerpo(cuerpo);
            
            registrarMensaje(mensajeNuevo, correoDestinatario);
            
        }else{
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
        
    }
    
    @FXML
    private boolean sonCamposValidos(){

        boolean sonCamposValidos = true; 

        if(ingresaAsunto.getText().isBlank() || ingresaDestinatario.getText().isBlank() || 
           ingresaCuerpo.getText().isBlank()){
            
            sonCamposValidos = false; 

        }
        
        return sonCamposValidos; 
        
    }
    
    @FXML
    private void registrarMensaje(Mensaje mensajeNuevo, String correoDestinatario){
        
        try{
            
            ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();
            validacion.enviarMensaje(mensajeNuevo, correoDestinatario);
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Envio Exitoso", 
            "Mensaje enviado correctamente");
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Envio fallido", 
            e.getMessage());
            
        }
        
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
