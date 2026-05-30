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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.gestores.GestorPracticantes;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorSolicitudesPracticantes {
    
    @FXML
    private TableView<Practicante> tblPracticantes;

    @FXML
    private TableColumn<Practicante, String> colNombre;
    
    @FXML
    private TableColumn<Practicante, String> colApellidoPaterno;
    
    @FXML
    private TableColumn<Practicante, String> colApellidoMaterno;
    
    @FXML
    private TableColumn<Practicante, String> colMatricula;

    private GestorPracticantes gestorSolicitudesPracticantes;
    
    @FXML
    public void initialize() {

        gestorSolicitudesPracticantes = new GestorPracticantes();

        tblPracticantes.setPlaceholder(new Label("No hay practicantes con solicitudes"));

        tblPracticantes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();
                
        cargarPracticantesConSolicitudes();
        
    }
    
    private void configurarColumnas() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
                
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
                
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        
    }
    
    private void cargarPracticantesConSolicitudes() {

        try {

            List<Practicante> lista = gestorSolicitudesPracticantes.obtenerPracticantesConSolicitudes();

            tblPracticantes.setItems(FXCollections.observableArrayList(lista));

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error", e.getMessage());
        
        }
        
    }
    
    private Practicante obtenerPracticanteSeleccionado() {

        Practicante practicanteSeleccionado = tblPracticantes.getSelectionModel().getSelectedItem();

        if (practicanteSeleccionado == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING,"Sin selección",
            "Debe seleccionar un practicante");

        }

        return practicanteSeleccionado;

    }
    
    @FXML
    private void verSolicitudes(ActionEvent evento){
        
        Practicante practicanteSeleccionado = obtenerPracticanteSeleccionado();
        
        if (practicanteSeleccionado == null) {
            return;
        }
        
        FXMLLoader cargadorSolicitudes = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaAsignacionProyecto.fxml", "Asignación a un Proyecto");

        if(cargadorSolicitudes != null){

            ControladorAsignacionProyecto controlador = cargadorSolicitudes.getController();

            controlador.inicializarDatos(practicanteSeleccionado);

            CerradorVentana.cerrarVentana(evento);
            
        }
        
    }
    
    @FXML
    private void regresar(ActionEvent evento){
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaMenuPrincipalCoordinador.fxml", 
        "Menu Principal para Coordinador");
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
