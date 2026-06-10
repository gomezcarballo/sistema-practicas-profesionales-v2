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
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.gestores.GestorDocumentos;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaDocumentos {
    
    @FXML
    private TableView<Documento> tblDocumentos;

    @FXML
    private TableColumn<Documento, String> colNombre;

    @FXML
    private TableColumn<Documento, String> colTipo;

    private Practicante practicanteSeleccionado;
    
    @FXML
    public void initialize() {

        tblDocumentos.setPlaceholder(new Label("No hay documentos subidos"));

        tblDocumentos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();
        
    }

    public void inicializarDatos(Practicante practicante) {

        this.practicanteSeleccionado = practicante;
        
        cargarDocumentos();

    }

    private void configurarColumnas() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        colTipo.setCellValueFactory(new PropertyValueFactory<>("tipo"));

    }

    private void cargarDocumentos() {

        try {
            
            GestorDocumentos gestorDocumentos = new GestorDocumentos();
            List<Documento> documentos = gestorDocumentos.recuperarDocumentosPorPracticante(practicanteSeleccionado);

            tblDocumentos.getItems().clear();

            tblDocumentos.setItems(FXCollections.observableArrayList(documentos));

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar documentos",
            "Hubo un error al recuperar los documentos");

        }

    }

    private Documento obtenerDocumentoSeleccionado() {

        Documento documentoSeleccionado = tblDocumentos.getSelectionModel().getSelectedItem();

        if (documentoSeleccionado == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar un documento");

        }

        return documentoSeleccionado;

    }
    
    @FXML
    private void irACalificar(ActionEvent evento) {

        Documento documentoSeleccionado = obtenerDocumentoSeleccionado();

        if (documentoSeleccionado != null) {
        
            FXMLLoader cargadorEvaluacionDocumento = CargadorVentana.cargarVentanaConControlador("/fxml/VistaEvaluacionDocumentos.fxml",
            "Evaluar Documento");

            if (cargadorEvaluacionDocumento != null) {

                ControladorEvaluacionDocumentos controlador = cargadorEvaluacionDocumento.getController();
                controlador.inicializarDatos(documentoSeleccionado, practicanteSeleccionado);

                CerradorVentana.cerrarVentana(evento);

            }

        }

    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaPracticantesConDocumentos.fxml", "Lista de Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
