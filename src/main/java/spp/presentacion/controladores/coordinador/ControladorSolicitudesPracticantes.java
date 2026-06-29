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
import javafx.scene.control.Alert.AlertType;
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
public class ControladorSolicitudesPracticantes extends ControladorBaseListaPracticantes {
    
    private GestorPracticantes gestorSolicitudesPracticantes;
    
    @FXML
    @Override
    public void initialize() {

        gestorSolicitudesPracticantes = new GestorPracticantes();

        super.initialize();
        
        cargarDatosEspecificos();
        
    }
  
    
    @Override
    protected void cargarDatosEspecificos() {

        try {

            List<Practicante> lista = gestorSolicitudesPracticantes.obtenerPracticantesConSolicitudes();

            tblListaPracticantes.setItems(FXCollections.observableArrayList(lista));

        } catch (OperacionesDeDaoExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error", e.getMessage());
        
        }
        
    }
    
    @FXML
    private void verSolicitudes(ActionEvent evento){
        
        Practicante practicanteSeleccionado = obtenerPracticanteSeleccionado();
        
        if (practicanteSeleccionado == null) {
            return;
        }
        
        FXMLLoader cargadorSolicitudes = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaAsignacionProyecto.fxml", "Asignación a un Proyecto");

        if(cargadorSolicitudes != null){

            ControladorAsignacionProyecto controlador = cargadorSolicitudes.getController();

            controlador.inicializarDatos(practicanteSeleccionado);

            CerradorVentana.cerrarVentana(evento);
            
        }
        
    }
    
    @FXML
    private void regresar(ActionEvent evento){
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaMenuPrincipalCoordinador.fxml", 
        "Menu Principal para Coordinador");
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
