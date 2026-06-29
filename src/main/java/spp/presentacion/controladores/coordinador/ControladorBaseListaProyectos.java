/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public abstract class ControladorBaseListaProyectos {
    
    @FXML 
    protected TableView<Proyecto> tblListaProyectos;
    
    @FXML 
    protected TableColumn<Proyecto, String> colNombre;
    
    @FXML 
    protected TableColumn<Proyecto, Integer> colCupoMaximo;
    
    @FXML
    public void initialize() {
        
        tblListaProyectos.setPlaceholder(new Label("No hay proyectos disponibles"));
        tblListaProyectos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnasBase();
                
    }
    
    private void configurarColumnasBase() {
        
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colCupoMaximo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
    
    }
    
    protected Proyecto obtenerProyectoSeleccionado() {
        
        Proyecto proyectoSeleccionado = tblListaProyectos.getSelectionModel().getSelectedItem();
        
        if (proyectoSeleccionado == null) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar un proyecto");
        
        }
        return proyectoSeleccionado;
    }

    protected abstract void configurarColumnasEspecificas();
    
}
