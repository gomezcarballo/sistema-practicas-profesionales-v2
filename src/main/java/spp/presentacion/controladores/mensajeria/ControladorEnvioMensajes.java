/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.mensajeria;

import java.util.ArrayList;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.gestores.GestorMensajes;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
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
            
            registrarMensaje(mensajeNuevo, evento);
            
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
    private void registrarMensaje(Mensaje mensajeNuevo, ActionEvent evento){
        
        try{
            
            GestorMensajes gestor = new GestorMensajes();
            if(validarMensaje(mensajeNuevo)){
                gestor.enviarMensaje(mensajeNuevo);
            
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Envio Exitoso", 
                "Mensaje enviado correctamente");
                
                regresar(evento);
            }
            
        }catch(OperacionesDeDaoExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Envio fallido", 
            e.getMessage());
  
        }
        
    }

    private boolean validarMensaje(Mensaje mensajeNuevo){

        boolean mensajeValido = false; 
        try{

            List<String> listaValidaciones = new ArrayList<>();
            GestorMensajes gestor = new GestorMensajes();
            listaValidaciones = gestor.validarCamposDeMensaje(mensajeNuevo);

            if(listaValidaciones.isEmpty()){
                mensajeValido = true;
            }else{
                mostrarVentanaErrores(listaValidaciones);
            }   

        }catch(OperacionesDeDaoExcepcion e){
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error de busqueda", 
            e.getMessage());
        }

        return mensajeValido;
    }

    private void mostrarVentanaErrores(List<String> listaValidaciones) {
        
        VentanaMensaje.mostrarVentanaErrores(listaValidaciones);
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubMenuMensajes.fxml", 
        "Mensajes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
