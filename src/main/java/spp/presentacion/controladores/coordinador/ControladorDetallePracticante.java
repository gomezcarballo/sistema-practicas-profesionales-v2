/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.formatofechas.FormatoFechas;

/**
 *
 * @author gomes
 */
public class ControladorDetallePracticante {
    
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidoPaterno;

    @FXML
    private TextField txtApellidoMaterno;

    @FXML
    private TextField txtCorreoInstitucional;

    @FXML
    private TextField txtMatricula;

    @FXML
    private TextField txtGenero;

    @FXML
    private TextField txtFechaNacimiento;

    @FXML
    private TextField txtHablaLenguaIndigena;

    @FXML
    public void initialize() {

        txtNombre.setEditable(false);

        txtApellidoPaterno.setEditable(false);

        txtApellidoMaterno.setEditable(false);

        txtCorreoInstitucional.setEditable(false);

        txtMatricula.setEditable(false);

        txtGenero.setEditable(false);

        txtFechaNacimiento.setEditable(false);

        txtHablaLenguaIndigena.setEditable(false);
        
    }

    public void cargarPracticante(Practicante practicante) {

        txtNombre.setText(practicante.getNombre());
        txtApellidoPaterno.setText(practicante.getApellidoPaterno());
        txtApellidoMaterno.setText(practicante.getApellidoMaterno());
        txtCorreoInstitucional.setText(practicante.getCorreoInstitucional());
        txtMatricula.setText(practicante.getMatricula());
        txtGenero.setText(practicante.getGenero());
        txtFechaNacimiento.setText(FormatoFechas.formatearFecha(practicante.getFechaNacimiento()));
        txtHablaLenguaIndigena.setText(practicante.getHablaLenguaIndigena() ? "Sí" : "No");
        
    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaListaPracticantes.fxml", "Lista de Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
