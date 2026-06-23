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
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.gestores.GestorPracticantes;
import spp.presentacion.controladores.practicante.ControladorBaseListaPracticantes;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaPracticantes extends ControladorBaseListaPracticantes{

    private GestorPracticantes gestorPracticantes;
    
    @FXML
    @Override
    public void initialize() {

        gestorPracticantes = new GestorPracticantes();

        super.initialize();
    
    }
    
    @Override
    protected void cargarDatosEspecificos() {

        try {

            List<Practicante> practicantes = gestorPracticantes.recuperarPracticantesActivos();

            tblListaPracticantes.getItems().clear();

            tblListaPracticantes.setItems(FXCollections.observableArrayList(practicantes));

        } catch (OperacionesDeDaoExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Error al recuperar practicantes",
            "Hubo un error al recuperar los practicantes");
        }
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

                cargarDatosEspecificos();

            } catch (OperacionesDeDaoExcepcion e) {

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
