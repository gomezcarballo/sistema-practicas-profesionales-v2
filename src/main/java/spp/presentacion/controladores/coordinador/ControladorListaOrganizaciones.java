/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.utilerias.cerradordeventanas.CerradorVentana;

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
    private TableColumn<Organizacion, String> colEstado;
    
    @FXML
    public void initialize() {

        tblListaOrganizaciones.setPlaceholder(new Label("No hay organizaciones registradas"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colSector.setCellValueFactory(new PropertyValueFactory<>("sector"));
        colEstado.setCellValueFactory(cellData -> {
            boolean valor = cellData.getValue().getEsActivo();
            return new SimpleStringProperty(valor ? "Activo" : "Inactivo");
        });
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
