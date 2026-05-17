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
    private TableView<Practicante> listaPracticantes;
    
    @FXML
    private TableColumn<Practicante, String> columnaNombre;
    
    @FXML
    private TableColumn<Practicante, String> columnaApellidoPaterno;
    
    @FXML
    private TableColumn<Practicante, String> columnaApellidoMaterno;

    @FXML
    private TableColumn<Practicante, String> columnaCorreoInstitucional;
    
    @FXML
    private TableColumn<Practicante, String> columnaMatricula;
    
    @FXML
    private TableColumn<Practicante, String> columnaEstado;
    
    @FXML
    private TableColumn<Practicante, String> columnaGenero;
    
    @FXML
    private TableColumn<Practicante, LocalDate> columnaFechaNacimiento;
    
    @FXML
    private TableColumn<Practicante, String> columnaHablaLenguaIndigena;

    @FXML
    public void initialize() {

        listaPracticantes.setPlaceholder(new Label("No hay practicantes registrados"));
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        columnaApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        columnaApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        columnaCorreoInstitucional.setCellValueFactory(new PropertyValueFactory<>("correoInstitucional"));
        columnaMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        columnaEstado.setCellValueFactory(new PropertyValueFactory<>("estado"));
        columnaGenero.setCellValueFactory(new PropertyValueFactory<>("genero"));
        columnaFechaNacimiento.setCellValueFactory(new PropertyValueFactory<>("fechaNacimiento"));
        columnaHablaLenguaIndigena.setCellValueFactory(cellData -> {
            boolean valor = cellData.getValue().getHablaLenguaIndigena();
            return new SimpleStringProperty(valor ? "Sí" : "No");
        });
    
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
    }
    
}
