/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

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
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.gestores.GestorOrganizaciones;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaOrganizaciones {
    
    @FXML
    private TableView<Organizacion> tblListaOrganizaciones;
    
    @FXML
    private TableColumn<Organizacion, String> colNombre;
    
    @FXML
    private TableColumn<Organizacion, String> colDireccion;

    @FXML
    private TableColumn<Organizacion, String> colSector;    
    
    @FXML
    private Label lblTituloOrganizaciones;

    private GestorOrganizaciones gestorOrganizaciones;
    
    @FXML
    public void initialize() {
        
        gestorOrganizaciones = new GestorOrganizaciones();

        lblTituloOrganizaciones.setText("Lista de Organizaciones");

        tblListaOrganizaciones.setPlaceholder(new Label( "No hay organizaciones registradas"));
        
        tblListaOrganizaciones.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        configurarColumnas();
        
        cargarOrganizaciones();        
        
    }
    
    private void configurarColumnas() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colSector.setCellValueFactory(new PropertyValueFactory<>("sector"));

    }
    
    private void cargarOrganizaciones() {

        try {

            List<Organizacion> organizaciones = gestorOrganizaciones.recuperarOrganizacionesActivas();

            tblListaOrganizaciones.getItems().clear();

            tblListaOrganizaciones.setItems(FXCollections.observableArrayList(organizaciones));

        } catch(ReglaDeNegocioExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar organizaciones",
                    "Hubo un error al recuperar las organizaciones");

        }

    }
    
    @FXML
    private void verDetalleOrganizacion() {

        Organizacion organizacionSeleccionada = tblListaOrganizaciones.getSelectionModel().getSelectedItem();

        if(organizacionSeleccionada == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING,"Sin selección",
            "Debe seleccionar una organización");

            return;

        }

        FXMLLoader cargadorDetalleOrganizacion = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaDetalleOrganizacion.fxml", "Detalle Organización");

        if(cargadorDetalleOrganizacion != null){

            ControladorDetalleOrganizacion controlador = cargadorDetalleOrganizacion.getController();

            controlador.cargarOrganizacion(organizacionSeleccionada);

        }

    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
