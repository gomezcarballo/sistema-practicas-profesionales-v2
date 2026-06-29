/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public abstract class ControladorBaseAsignacionEE {
    
    @FXML 
    protected TableView<ExperienciaEducativa> tblListaExperiencias;

    @FXML
    protected TableColumn<ExperienciaEducativa, String> colNombre;

    @FXML
    protected TableColumn<ExperienciaEducativa, String> colPeriodo;
    
    @FXML
    protected TableColumn<ExperienciaEducativa, String> colNRC;

    @FXML
    public void initialize() {

        tblListaExperiencias.setPlaceholder(new Label("No hay experiencias educativas registradas."));
        tblListaExperiencias.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();
        
    }

    private void configurarColumnas() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreExperienciaEducativa"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));
        colNRC.setCellValueFactory(new PropertyValueFactory<>("nrc"));

    }

    protected void cargarExperienciasEducativas(List<ExperienciaEducativa> listaExperienciaEducativas) {

        if (listaExperienciaEducativas != null && !listaExperienciaEducativas.isEmpty()) {

            tblListaExperiencias.getItems().clear();
            tblListaExperiencias.setItems(FXCollections.observableArrayList(listaExperienciaEducativas));

        } else {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Error al cargar grupos",
                "No se pudieron mostrar los grupos disponibles. Inténtelo más tarde.");
                
        }
        
    }

    protected ExperienciaEducativa obtenerExperienciaSeleccionada() {
        
        ExperienciaEducativa experienciaSeleccionada = tblListaExperiencias.getSelectionModel().getSelectedItem();
        
        if (experienciaSeleccionada == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar una experiencia educativa de la tabla.");

        }

        return experienciaSeleccionada;
        
    }
    
}
