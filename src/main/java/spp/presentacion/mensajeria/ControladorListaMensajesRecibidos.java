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
import spp.logicadenegocio.gestores.GestorMensajesRecibidos;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaMensajesRecibidos {
    
    @FXML
    private TableView<Mensaje> tablaMensajesRecibidos;
    
    @FXML
    private TableColumn<Mensaje, String> columnaAsunto;

    @FXML
    private TableColumn<Mensaje, String> columnaCorreoRemitente;

    @FXML
    private TableColumn<Mensaje, LocalDateTime> columnaFecha;
    
    private GestorMensajesRecibidos gestorMensajesRecibidos;
    
    @FXML
    public void initialize() {

        gestorMensajesRecibidos = new GestorMensajesRecibidos();

        tablaMensajesRecibidos.setPlaceholder(new Label("No hay mensajes"));

        tablaMensajesRecibidos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();

        cargarMensajes();
    }
    
    private void configurarColumnas() {

        columnaAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));

        columnaCorreoRemitente.setCellValueFactory(new PropertyValueFactory<>("correoRemitente"));

        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
    }
    
    private void cargarMensajes() {

        try {

            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            String correo = "z"+ sesionUsuario.getIdentificador().toLowerCase() + "@estudiantes.uv.mx";

            ObservableList<Mensaje> mensajes = FXCollections.observableArrayList
            (gestorMensajesRecibidos.consultarMensajes(correo));

            tablaMensajesRecibidos.setItems(mensajes);

        } catch(ReglaDeNegocioExcepcion e) {

            VentanaMensaje ventanaMensaje =new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Error al recuperar mensajes",
            "Hubo un error al recuperar los mensajes");

        }
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
