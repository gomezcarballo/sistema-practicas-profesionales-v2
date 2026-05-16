/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;
import spp.utilerias.cargadordeventanas.CargadorVentana;

/**
 *
 * @author gomes
 */
public class ControladorSubMenuMensajes {
    
    @FXML
    private void abrirMensajesRecibidos(){
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-ListaMensajesRecibidos.fxml", "Mensajes Recibidos");
        
    }
    
    @FXML
    private void abrirMensajesEnviados(){
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-ListaMensajesEnviados.fxml", "Mensajes Enviados");
        
    }
    
    @FXML
    private void abrirEnvioMensaje(){
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-EnvioMensajes.fxml", "Enviar Mensaje");
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
