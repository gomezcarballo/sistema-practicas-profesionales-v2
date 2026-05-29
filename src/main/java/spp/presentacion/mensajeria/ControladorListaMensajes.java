/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.mensajeria;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.StringConverter;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoMensaje;
import spp.logicadenegocio.gestores.GestorMensajes;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.formatofechas.FormatoFechas;
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
        
    private GestorMensajes gestorMensajes;
    
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
        
        configurarFecha();

        cargarMensajes();
    }   
    
    @FXML
    public void initialize() {
        
        gestorMensajes = new GestorMensajes();
        
        tblMensajes.setPlaceholder(new Label("No hay mensajes"));

        tblMensajes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
    }
    
    private void configurarColumnas() {

        colAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));

        switch(tipoMensaje) {

            case RECIBIDOS : {

                colCorreoUsuario.setText("Remitente");
                colCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoRemitente"));
                
            return;
            
            }

            case ENVIADOS : {

                colCorreoUsuario.setText("Destinatario");
                colCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoDestinatario"));
                
            return; 
            
            }
        }
   
    }
    
    private void configurarFecha(){
        
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));

        colFecha.setCellFactory(TextFieldTableCell.forTableColumn
            (new StringConverter<LocalDateTime>() {

                    @Override
                    public String toString(LocalDateTime fecha) {
                        return FormatoFechas.formatearFechaHora(fecha);
                    }

                    @Override
                    public LocalDateTime fromString(String texto) {
                        return LocalDateTime.parse(texto);
                    }
                }
            )
        );
            
    }
    
    private void cargarMensajes() {

        try {

            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuario = sesionUsuario.getIdUsuario();

            List<Mensaje> mensajes = new ArrayList<>();

            switch(tipoMensaje) {
                
                case RECIBIDOS -> {
                    mensajes = gestorMensajes.consultarMensajesRecibidos(idUsuario);
                }
                case ENVIADOS -> {
                    mensajes = gestorMensajes.consultarMensajesEnviados(idUsuario);
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
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubMenuMensajes.fxml", 
        "Mensajes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
