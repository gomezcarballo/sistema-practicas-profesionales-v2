/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import spp.logicadenegocio.enums.TipoMensaje;
import spp.presentacion.mensajeria.ControladorListaMensajes;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorSubMenuMensajes {
    
    private void abrirMensajes(TipoMensaje tipoMensaje, String tituloVentana) {

        try {

            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/fxml/GUI-ListaMensajes.fxml"));

            Parent raizVentana = cargadorFXML.load();

            ControladorListaMensajes controlador = cargadorFXML.getController();

            controlador.setTipoMensaje(tipoMensaje);

            Stage escenario = new Stage();

            escenario.setScene(new Scene(raizVentana));

            escenario.setTitle(tituloVentana);

            escenario.show();

        } catch(IOException e) {

            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Error de acceso",
                "No se pudo abrir la ventana de mensajes");
            
        }
    }
    
    @FXML
    private void abrirMensajesRecibidos(){
        
         abrirMensajes(TipoMensaje.RECIBIDOS, "Mensajes Recibidos");
        
    }
    
    @FXML
    private void abrirMensajesEnviados(){
        
        abrirMensajes(TipoMensaje.ENVIADOS, "Mensajes Enviados");
        
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
