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
import spp.logicadenegocio.gestores.GestorMensajes;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

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
    private void leerDatosMensaje(ActionEvent evento){
        
        if(sonCamposValidos()){
            
            Mensaje mensajeNuevo = crearMensaje();
            
            String correoDestinatario = txtDestinatario.getText().trim().toLowerCase();
            
            registrarMensaje(mensajeNuevo, correoDestinatario, evento);
            
        }else{
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
        
    }
    
    private Mensaje crearMensaje(){
        
        Mensaje mensajeNuevo = new Mensaje();   
        
        String destinatario = txtDestinatario.getText().trim();
        String asunto = txtAsunto.getText();       
        String cuerpo = taCuerpoMensaje.getText();

        mensajeNuevo.setCorreoDestinatario(destinatario);
        mensajeNuevo.setAsunto(asunto);
        mensajeNuevo.setCuerpo(cuerpo);
        
        return mensajeNuevo;
        
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
    private void registrarMensaje(Mensaje mensajeNuevo, String correoDestinatario, ActionEvent evento){
        
        try{
            
            GestorMensajes gestor = new GestorMensajes();
            gestor.enviarMensaje(mensajeNuevo, correoDestinatario);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Envio Exitoso", 
            "Mensaje enviado correctamente");
            
            regresar(evento);
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Envio fallido", 
            e.getMessage());
  
        }
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubMenuMensajes.fxml", 
        "Mensajes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
