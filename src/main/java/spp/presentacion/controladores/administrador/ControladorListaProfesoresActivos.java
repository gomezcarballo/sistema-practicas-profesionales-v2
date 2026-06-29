/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.gestores.GestorProfesores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaProfesoresActivos extends ControladorBaseListaProfesores{
    
    private Profesor profesorNuevo;
    private GestorProfesores gestorProfesores;

    @FXML
    @Override
    public void initialize(){

        gestorProfesores = new GestorProfesores();
        super.initialize();
        cargarDatosEspecificos();

    }

    public void inicializarDatos(Profesor profesorNuevo){

        this.profesorNuevo = profesorNuevo;

    }

    @Override
    protected void cargarDatosEspecificos() {

        try {
            
            List<Profesor> profesores = gestorProfesores.obtenerProfesoresActivos();
            tblListaProfesores.getItems().clear();
            tblListaProfesores.setItems(FXCollections.observableArrayList(profesores));

        } catch (OperacionesDeDaoExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error", e.getMessage());

        }

    }


    @FXML
    private void inactivarProfesor(ActionEvent evento){

        Profesor profesorSeleccionado = obtenerProfesorSeleccionado();

        if(profesorSeleccionado == null){
            return;
        }
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaRegistroProfesor.fxml",
        "Registro Profesor");

        if (cargador != null) {

            ControladorRegistroProfesor controlador = cargador.getController();

            controlador.registrarProfesorConReemplazo(profesorNuevo, profesorSeleccionado, evento);
            
        }
        
    }

    @FXML
    private void cancelar(ActionEvent evento){

        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalAdministrador.fxml",
        "Menu Principal para Administrador");

        CerradorVentana.cerrarVentana(evento);

    }
    
}
