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
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.gestores.GestorPracticantes;
import spp.presentacion.controladores.practicante.ControladorBaseListaPracticantes;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorPracticantesConDocumentos extends ControladorBaseListaPracticantes{
    
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

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar practicantes",
            "Hubo un error al recuperar los practicantes");

        }

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
