/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.mensajeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.validacionenviomensajes.ValidacionEnvioMensaje;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorEnvioMensajes {
    
    @FXML
    private TextField txtAsunto;
    
    @FXML
    private TextField txtDestinatario;
    
    @FXML
    private TextArea taCuerpoMensaje;
    
    @FXML
    private void leerDatosMensaje(){
        
        if(sonCamposValidos()){
            
            String asunto = txtAsunto.getText();
            String correoDestinatario = txtDestinatario.getText().trim().toLowerCase();
            String cuerpo = taCuerpoMensaje.getText();
            
            Mensaje mensajeNuevo = new Mensaje();        
            
            mensajeNuevo.setAsunto(asunto);
            mensajeNuevo.setCuerpo(cuerpo);
            
            registrarMensaje(mensajeNuevo, correoDestinatario);
            
        }else{
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
        
    }
    
    @FXML
    private boolean sonCamposValidos(){

        boolean sonCamposValidos = true; 

        if(txtAsunto.getText().isBlank() || txtDestinatario.getText().isBlank() || 
           taCuerpoMensaje.getText().isBlank()){
            
            sonCamposValidos = false; 

        }
        
        return sonCamposValidos; 
        
    }
    
    @FXML
    private void registrarMensaje(Mensaje mensajeNuevo, String correoDestinatario){
        
        try{
            
            ValidacionEnvioMensaje validacion = new ValidacionEnvioMensaje();
            validacion.enviarMensaje(mensajeNuevo, correoDestinatario);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Envio Exitoso", 
            "Mensaje enviado correctamente");
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Envio fallido", 
            e.getMessage());
            
        }
        
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
