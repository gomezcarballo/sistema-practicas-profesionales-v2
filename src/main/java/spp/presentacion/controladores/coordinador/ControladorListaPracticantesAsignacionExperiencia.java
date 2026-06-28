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

public class ControladorListaPracticantesAsignacionExperiencia extends ControladorBaseListaPracticantes {

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

            List<Practicante> practicantes = gestorPracticantes.recuperarPracticantesParaAsignacionEE();

            tblListaPracticantes.getItems().clear();

            tblListaPracticantes.setItems(FXCollections.observableArrayList(practicantes));

        } catch (OperacionesDeDaoExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar practicantes",
            e.getMessage());

        }

    }

    @FXML
    private void asignarExperienciaEducativa(ActionEvent evento){

        Practicante practicanteSeleccionado = obtenerPracticanteSeleccionado();

        if(practicanteSeleccionado != null){

            FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador( 
                "/fxml/VistaAsignacionExperienciaEducativa.fxml", "Asignacion Experiencia Educativa");
                
            if(cargador != null){

                ControladorAsignacionExperienciaEducativa controlador = cargador.getController();
                controlador.inicializarDatos(practicanteSeleccionado);

                CerradorVentana.cerrarVentana(evento);

            }
        }

    }

   @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubMenuPracticantes.fxml", 
        "Menu de Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }

}
