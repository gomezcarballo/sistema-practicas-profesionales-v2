/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.stage.Stage;
import spp.logicadenegocio.enums.TipoMensaje;
import spp.presentacion.mensajeria.ControladorListaMensajes;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorSubMenuMensajes {
    
    private void abrirMensajes(TipoMensaje tipoMensaje, String tituloVentana) {

        try {

            FXMLLoader cargadorFXML = new FXMLLoader(getClass().getResource("/fxml/VistaListaMensajes.fxml"));

            Parent raizVentana = cargadorFXML.load();

            ControladorListaMensajes controlador = cargadorFXML.getController();

            controlador.setTipoMensaje(tipoMensaje);

            Stage escenario = new Stage();

            escenario.setScene(new Scene(raizVentana));

            escenario.setTitle(tituloVentana);

            escenario.show();

        } catch(IOException e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Error de acceso",
                "No se pudo abrir la ventana de mensajes");
            
        }
    }
    
    @FXML
    private void abrirMensajesRecibidos(ActionEvent evento){
        
         abrirMensajes(TipoMensaje.RECIBIDOS, "Mensajes Recibidos");
         CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    private void abrirMensajesEnviados(ActionEvent evento){
        
        abrirMensajes(TipoMensaje.ENVIADOS, "Mensajes Enviados");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    private void abrirEnvioMensaje(ActionEvent evento){
        
        CargadorVentana.cargarVentana("/fxml/VistaEnvioMensajes.fxml", "Enviar Mensaje");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
