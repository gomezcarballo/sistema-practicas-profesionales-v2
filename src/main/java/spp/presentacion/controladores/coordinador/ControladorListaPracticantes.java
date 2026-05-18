/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.time.LocalDate;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.utilerias.cerradordeventanas.CerradorVentana;

/**
 *
 * @author gomes
 */
public class ControladorListaPracticantes {
    
    @FXML
    private TableView<Practicante> tblListaPracticantes;
    
    @FXML
    private TableColumn<Practicante, String> colNombre;
    
    @FXML
    private TableColumn<Practicante, String> colApellidoPaterno;
    
    @FXML
    private TableColumn<Practicante, String> colApellidoMaterno;

    @FXML
    private TableColumn<Practicante, String> colCorreoInstitucional;
    
    @FXML
    private TableColumn<Practicante, String> colMatricula;
    
    @FXML
    private TableColumn<Practicante, String> colEstado;
    
    @FXML
    private TableColumn<Practicante, String> colGenero;
    
    @FXML
    private TableColumn<Practicante, LocalDate> colFechaNacimiento;
    
    @FXML
    private TableColumn<Practicante, String> colHablaLenguaIndigena;

    @FXML
    public void initialize() {

        tblListaPracticantes.setPlaceholder(new Label("No hay practicantes registrados"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colCorreoInstitucional.setCellValueFactory(new PropertyValueFactory<>("correoInstitucional"));
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        colGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        colFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        colHablaLenguaIndigena.setCellValueFactory(cellData -> {
            boolean valor = cellData.getValue().getHablaLenguaIndigena();
            return new SimpleStringProperty(valor ? "Sí" : "No");
        });
    
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
    }
    
}
