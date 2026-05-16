/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.mensajeria;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoMensaje;
import spp.logicadenegocio.gestores.GestorMensajesEnviados;
import spp.logicadenegocio.gestores.GestorMensajesRecibidos;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaMensajes {
    
    @FXML
    private TableView<Mensaje> tablaMensajes;
    
    @FXML
    private TableColumn<Mensaje, String> columnaAsunto;

    @FXML
    private TableColumn<Mensaje, String> columnaCorreoUsuario;

    @FXML
    private TableColumn<Mensaje, LocalDateTime> columnaFecha;
    
    @FXML
    private Label tituloMensajes;
    
    private GestorMensajesRecibidos gestorMensajesRecibidos;
    
    private GestorMensajesEnviados gestorMensajesEnviados;
    
    private TipoMensaje tipoMensaje;
    
    public void setTipoMensaje(TipoMensaje tipoMensaje) {
        
        System.out.println(tipoMensaje);
        this.tipoMensaje = tipoMensaje;
        
        switch(tipoMensaje) {

            case RECIBIDOS ->
                tituloMensajes.setText("Mensajes Recibidos");

            case ENVIADOS ->
                tituloMensajes.setText("Mensajes Enviados");
                
        }   
        
        configurarColumnas();

        cargarMensajes();
    }   
    
    @FXML
    public void initialize() {

        gestorMensajesRecibidos = new GestorMensajesRecibidos();
        
        gestorMensajesEnviados = new GestorMensajesEnviados();
        
        tablaMensajes.setPlaceholder(new Label("No hay mensajes"));

        tablaMensajes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
    }
    
    private void configurarColumnas() {

        columnaAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));

        switch(tipoMensaje) {

            case RECIBIDOS -> {

                columnaCorreoUsuario.setText("Remitente");
                columnaCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoRemitente"));
                
            }

            case ENVIADOS -> {

                columnaCorreoUsuario.setText("Destinatario");
                columnaCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoDestinatario"));
                
            }
        }
        
        columnaFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
        columnaFecha.setCellFactory(columna -> {
            return new TableCell<Mensaje, LocalDateTime>() {

                private final DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

                @Override
                protected void updateItem(LocalDateTime fecha, boolean estaVacio) {
                    super.updateItem(fecha, estaVacio);

                    if (estaVacio || fecha == null) {
                        setText(null);
                    } else {
                        setText(fecha.format(formatoFecha));
                    }
                }
            };
        });
        
    }
    
    private void cargarMensajes() {

        try {

            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuario = sesionUsuario.getIdUsuario();

            List<Mensaje> mensajes = new ArrayList<>();;

            switch(tipoMensaje) {
                
                case RECIBIDOS -> {
                    System.out.println("Consultando recibidos");
                    mensajes = gestorMensajesRecibidos.consultarMensajes(idUsuario);
                    System.out.println(mensajes.size());
                }
                case ENVIADOS -> {
                    System.out.println("Consultando enviados");
                    mensajes = gestorMensajesEnviados.consultarMensajesEnviados(idUsuario);
                }
            }
            
            tablaMensajes.getItems().clear();
            tablaMensajes.setItems(FXCollections.observableArrayList(mensajes));

        } catch(ReglaDeNegocioExcepcion e) {

            VentanaMensaje ventanaMensaje = new VentanaMensaje();
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
