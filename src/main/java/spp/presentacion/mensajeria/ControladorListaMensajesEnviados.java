/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.mensajeria;

import java.time.LocalDateTime;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.gestores.GestorMensajesEnviados;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaMensajesEnviados {
    
    @FXML
    private TableView<Mensaje> tablaMensajesEnviados;

    @FXML
    private TableColumn<Mensaje, String> columnaAsunto;

    @FXML
    private TableColumn<Mensaje, String> columnaDestinatario;

    @FXML
    private TableColumn<Mensaje, LocalDateTime> columnaFecha;
    
    private GestorMensajesEnviados gestorMensajesEnviados;

    @FXML
    public void initialize() {

        gestorMensajesEnviados = new GestorMensajesEnviados();

        tablaMensajesEnviados.setPlaceholder(new Label("No hay mensajes enviados"));

        tablaMensajesEnviados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();

        cargarMensajesEnviados();
        
    }
    
    private void configurarColumnas() {

        columnaAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));

        columnaDestinatario.setCellValueFactory(new PropertyValueFactory<>("correoDestinatario"));

        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
    }
    
    private void cargarMensajesEnviados() {

        try {

            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuario = sesionUsuario.getIdUsuario();

            ObservableList<Mensaje> mensajes = FXCollections.observableArrayList
            (gestorMensajesEnviados.consultarMensajesEnviados(idUsuario));
            
            tablaMensajesEnviados.setItems(mensajes);

        } catch(ReglaDeNegocioExcepcion e) {

            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Error al recuperar mensajes",
                "Hubo un error al recuperar los mensajes enviados");
        }
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
