/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

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
public class ControladorListaPracticantes {
    
    @FXML
    private TableView<Practicante> tblListaPracticantes;
    
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

        tblListaPracticantes.setPlaceholder(new Label("No hay practicantes registrados"));

        tblListaPracticantes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();

        cargarPracticantes();
    
    }
    
    private void configurarColumnas() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));

    }
    
    private void cargarPracticantes() {

        try {

            List<Practicante> practicantes = gestorPracticantes.recuperarPracticantesActivos();

            tblListaPracticantes.getItems().clear();

            tblListaPracticantes.setItems(FXCollections.observableArrayList(practicantes));

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Error al recuperar practicantes",
            "Hubo un error al recuperar los practicantes");
        }
    }
    
    private Practicante obtenerPracticanteSeleccionado() {

        Practicante practicanteSeleccionado = tblListaPracticantes.getSelectionModel().getSelectedItem();

        if (practicanteSeleccionado == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar un practicante");
        }

        return practicanteSeleccionado;
    }

    @FXML
    private void abrirDetallePracticante(ActionEvent evento) {

        Practicante practicanteSeleccionado = obtenerPracticanteSeleccionado();

        if (practicanteSeleccionado == null) {
            return;
        }

        FXMLLoader cargadorDetalle = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaDetallePracticante.fxml", "Detalle Practicante");

        if (cargadorDetalle != null) {

            ControladorDetallePracticante controlador = cargadorDetalle.getController();

            controlador.cargarPracticante(practicanteSeleccionado);
            CerradorVentana.cerrarVentana(evento);
            
        }
    }

    @FXML
    private void abrirInactivarPracticante(ActionEvent evento) {

        Practicante practicanteSeleccionado = obtenerPracticanteSeleccionado();

        if (practicanteSeleccionado == null) {
            return;
        }

        boolean confirmado = VentanaMensaje.mostrarConfirmacion("Confirmar inactivación",
        "¿Desea inactivar al practicante seleccionado?");

        if (confirmado) {

            try {

                gestorPracticantes.inactivarPracticante(practicanteSeleccionado.getIdUsuario());

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION,
                "Practicante inactivado", "El practicante fue inactivado correctamente");

                cargarPracticantes();

            } catch (ReglaDeNegocioExcepcion e) {

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al inactivar practicante",
                e.getMessage());
                
            }
        }
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaSubMenuPracticantes.fxml", "Menu de Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
