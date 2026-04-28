/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Proyecto;

/**
 *
 * @author gomes
 */
public class ControladorListaProyectos {
    
    @FXML
    private TableView<Proyecto> listaProyectos;
    
    @FXML
    private TableColumn<Proyecto, String> columnaNombre;
    
    @FXML
    private TableColumn<Proyecto, String> columnaDescripcion;

    @FXML
    private TableColumn<Proyecto, String> columnaNombreResponsable;
    
    @FXML
    private TableColumn<Proyecto, String> columnaCupoMaximo;
    
    @FXML
    public void initialize() {

        listaProyectos.setPlaceholder(new Label("No hay proyectos disponibles"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        columnaNombreResponsable.setCellValueFactory(new PropertyValueFactory<>("nombreResponsable"));
        columnaCupoMaximo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
        
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
