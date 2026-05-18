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
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoMensaje;
import spp.logicadenegocio.gestores.GestorMensajesEnviados;
import spp.logicadenegocio.gestores.GestorMensajesRecibidos;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaMensajes {
    
    @FXML
    private TableView<Mensaje> tblMensajes;
    
    @FXML
    private TableColumn<Mensaje, String> colAsunto;

    @FXML
    private TableColumn<Mensaje, String> colCorreoUsuario;

    @FXML
    private TableColumn<Mensaje, LocalDateTime> colFecha;
    
    @FXML
    private Label lblTituloMensajes;
    
    private GestorMensajesRecibidos gestorMensajesRecibidos;
    
    private GestorMensajesEnviados gestorMensajesEnviados;
    
    private TipoMensaje tipoMensaje;
    
    public void setTipoMensaje(TipoMensaje tipoMensaje) {
        
        this.tipoMensaje = tipoMensaje;
        
        switch(tipoMensaje) {

            case RECIBIDOS ->
                lblTituloMensajes.setText("Mensajes Recibidos");

            case ENVIADOS ->
                lblTituloMensajes.setText("Mensajes Enviados");
                
        }   
        
        configurarColumnas();

        cargarMensajes();
    }   
    
    @FXML
    public void initialize() {

        gestorMensajesRecibidos = new GestorMensajesRecibidos();
        
        gestorMensajesEnviados = new GestorMensajesEnviados();
        
        tblMensajes.setPlaceholder(new Label("No hay mensajes"));

        tblMensajes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
    }
    
    private void configurarColumnas() {

        colAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));

        switch(tipoMensaje) {

            case RECIBIDOS -> {

                colCorreoUsuario.setText("Remitente");
                colCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoRemitente"));
                
            }

            case ENVIADOS -> {

                colCorreoUsuario.setText("Destinatario");
                colCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoDestinatario"));
                
            }
        }
        
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        
        colFecha.setCellFactory(columna -> {
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
                    mensajes = gestorMensajesRecibidos.consultarMensajesRecibidos(idUsuario);
                }
                case ENVIADOS -> {
                    mensajes = gestorMensajesEnviados.consultarMensajesEnviados(idUsuario);
                }
            }
            
            tblMensajes.getItems().clear();
            tblMensajes.setItems(FXCollections.observableArrayList(mensajes));

        } catch(ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Error al recuperar mensajes",
            "Hubo un error al recuperar los mensajes");

        }
    }
    
    @FXML
    private void verMensaje() {

        Mensaje mensajeSeleccionado = tblMensajes.getSelectionModel().getSelectedItem();

        if(mensajeSeleccionado == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING,"Sin selección",
            "Debe seleccionar un mensaje");
            return;
            
        }

        FXMLLoader cargadorDetalleMensaje = CargadorVentana.cargarVentanaConControlador("/fxml/VistaDetalleMensaje.fxml",
        "Detalle del Mensaje");
        
        if(cargadorDetalleMensaje != null){
            
            ControladorDetalleMensaje controlador = cargadorDetalleMensaje.getController();
            controlador.cargarMensaje(mensajeSeleccionado);
            
        }
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
