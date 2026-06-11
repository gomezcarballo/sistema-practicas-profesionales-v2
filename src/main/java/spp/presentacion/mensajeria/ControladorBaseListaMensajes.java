/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.mensajeria;

import java.time.LocalDateTime;
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
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.gestores.GestorMensajes;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.formatofechas.ConvertidorFechaHoraLocal;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public abstract class ControladorBaseListaMensajes {
    
    @FXML 
    protected TableView<Mensaje> tblMensajes;
    
    @FXML 
    protected TableColumn<Mensaje, String> colAsunto;
    
    @FXML 
    protected TableColumn<Mensaje, String> colCorreoUsuario;
    
    @FXML 
    protected TableColumn<Mensaje, LocalDateTime> colFecha;
    
    @FXML 
    protected Label lblTituloMensajes;
    
    protected GestorMensajes gestorMensajes;
    
    @FXML
    public void initialize() {
        
        gestorMensajes = new GestorMensajes();
        tblMensajes.setPlaceholder(new Label("No hay mensajes"));
        tblMensajes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        actualizarVista();
        
    }
    
    private void actualizarVista() {
        
        configurarColumnasComunes();
        configurarColumnaEspecifica(); 
        configurarFecha();
        cargarMensajes();
        
    }
    
    private void configurarColumnasComunes() {
        
        colAsunto.setCellValueFactory(new PropertyValueFactory<>("asunto"));
        
    }
    
    private void configurarFecha() {
        
        colFecha.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        colFecha.setCellFactory(TextFieldTableCell.forTableColumn(new ConvertidorFechaHoraLocal()));
    
    }
    
    private void cargarMensajes() {
        
        try {
            
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuario = sesionUsuario.getIdUsuario();
            
            List<Mensaje> mensajes = consultarMensajesBD(idUsuario);
            
            tblMensajes.getItems().clear();
            tblMensajes.setItems(FXCollections.observableArrayList(mensajes));

        } catch(ReglaDeNegocioExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar mensajes",
            "Hubo un error al recuperar los mensajes");
        
        }
    }
    
    protected abstract String obtenerTituloMensajes();
    protected abstract void configurarColumnaEspecifica();
    protected abstract List<Mensaje> consultarMensajesBD(int idUsuario) throws ReglaDeNegocioExcepcion;
    
    @FXML
    protected void verMensaje() {
        
        Mensaje mensajeSeleccionado = tblMensajes.getSelectionModel().getSelectedItem();

        if(mensajeSeleccionado == null) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección", "Debe seleccionar un mensaje");
            return;
            
        }

        FXMLLoader cargadorDetalleMensaje = CargadorVentana.cargarVentanaConControlador("/fxml/VistaDetalleMensaje.fxml", "Detalle del Mensaje");
        
        if(cargadorDetalleMensaje != null){
            
            ControladorDetalleMensaje controlador = cargadorDetalleMensaje.getController();
            controlador.cargarMensaje(mensajeSeleccionado);
        
        }
        
    }
    
    @FXML
    protected void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubMenuMensajes.fxml", "Mensajes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
