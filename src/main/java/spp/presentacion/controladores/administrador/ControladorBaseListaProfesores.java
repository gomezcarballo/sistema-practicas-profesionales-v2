/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public abstract class ControladorBaseListaProfesores {
    
    @FXML
    protected TableView<Profesor> tblListaProfesores;
    
    @FXML
    protected TableColumn<Profesor, String> colNombre;

    @FXML
    protected TableColumn<Profesor, String> colApellidoPaterno;
    
    @FXML
    protected TableColumn<Profesor, String> colApellidoMaterno;
    
    @FXML
    protected TableColumn<Profesor, String> colCorreoInstitucional;

    @FXML
    public void initialize() {

        tblListaProfesores.setPlaceholder(new Label("No hay profesores disponibles"));
        tblListaProfesores.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();

    }

    private void configurarColumnas() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colCorreoInstitucional.setCellValueFactory(new PropertyValueFactory<>("correoInstitucional"));

    }

    protected Profesor obtenerProfesorSeleccionado() {

        Profesor profesorSeleccionado = tblListaProfesores.getSelectionModel().getSelectedItem();

        if (profesorSeleccionado == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Profesor no seleccionado",
            "Debe seleccionar un profesor.");

        }

        return profesorSeleccionado;

    }

    protected abstract void cargarDatosEspecificos();
    
}
