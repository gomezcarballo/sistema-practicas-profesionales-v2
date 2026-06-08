/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.profesor;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
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
public class ControladorPracticantesConDocumentos {
    
    @FXML
    private TableView<Practicante> tblPracticantes;

    @FXML
    private TableColumn<Practicante, String> colMatricula;

    @FXML
    private TableColumn<Practicante, String> colNombre;

    @FXML
    private TableColumn<Practicante, String> colApellidoPaterno;

    @FXML
    private TableColumn<Practicante, String> colApellidoMaterno;

    private GestorPracticantes gestorPracticantes;

    @FXML
    public void initialize() {

        gestorPracticantes = new GestorPracticantes();

        tblPracticantes.setPlaceholder(new Label("No hay practicantes registrados"));

        tblPracticantes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();

        cargarPracticantes();

    }

    private void configurarColumnas() {

        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));

    }

    private void cargarPracticantes() {

        try {

            List<Practicante> practicantes = gestorPracticantes.recuperarPracticantesActivos();

            tblPracticantes.getItems().clear();

            tblPracticantes.setItems(FXCollections.observableArrayList(practicantes));

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar practicantes",
            "Hubo un error al recuperar los practicantes");

        }

    }

    private Practicante obtenerPracticanteSeleccionado() {

        Practicante practicanteSeleccionado = tblPracticantes.getSelectionModel().getSelectedItem();

        if (practicanteSeleccionado == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar un practicante de la tabla");

        }

        return practicanteSeleccionado;

    }

    @FXML
    private void verDocumentos(ActionEvent evento) {

        Practicante practicanteSeleccionado = obtenerPracticanteSeleccionado();

        if (practicanteSeleccionado != null) {

            FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaListaDocumentos.fxml", 
            "Documentos del Practicante");

            if (cargador != null) {

                ControladorListaDocumentos controlador = cargador.getController();
                controlador.inicializarDatos(practicanteSeleccionado);

                CerradorVentana.cerrarVentana(evento);

            }

        }

    }

    @FXML
    public void regresar(ActionEvent evento) {

        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalProfesor.fxml", "Menú para Profesores");
        CerradorVentana.cerrarVentana(evento);

    }
    
}
