/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Organizacion;

/**
 *
 * @author gomes
 */
public class ControladorListaOrganizaciones {
    
    @FXML
    private TableView<Organizacion> listaOrganizaciones;
    
    @FXML
    private TableColumn<Organizacion, String> columnaNombre;
    
    @FXML
    private TableColumn<Organizacion, String> columnaDireccion;

    @FXML
    private TableColumn<Organizacion, String> columnaSector;
    
    @FXML
    private TableColumn<Organizacion, String> columnaEstado;
    
    @FXML
    public void initialize() {

        listaOrganizaciones.setPlaceholder(new Label("No hay organizaciones registradas"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        columnaSector.setCellValueFactory(new PropertyValueFactory<>("sector"));
        columnaEstado.setCellValueFactory(cellData -> {
            boolean valor = cellData.getValue().getEsActivo();
            return new SimpleStringProperty(valor ? "Activo" : "Inactivo");
        });
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
