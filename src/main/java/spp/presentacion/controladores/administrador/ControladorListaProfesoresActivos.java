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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.gestores.GestorProfesores;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaProfesoresActivos {
    
    @FXML
    private TableView<Profesor> tblProfesoresActivos;
    
    @FXML
    private TableColumn<Profesor, String> colNombre;

    @FXML
    private TableColumn<Profesor, String> colApellidoPaterno;
    
    @FXML
    private TableColumn<Profesor, String> colApellidoMaterno;
    
    @FXML
    private TableColumn<Profesor, String> colCorreoinstitucional;

    private Profesor profesorNuevo;


    @FXML
    public void initialize(){

        tblProfesoresActivos.setPlaceholder(new Label("No hay profesores activos"));

        tblProfesoresActivos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();

        cargarProfesoresActivos();

    }

    public void inicializarDatos(Profesor profesorNuevo){

        this.profesorNuevo = profesorNuevo;

    }

    private void configurarColumnas(){

        
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colCorreoinstitucional.setCellValueFactory(new PropertyValueFactory<>("correoInstitucional"));

    }

    private void cargarProfesoresActivos(){

        try{
            
            GestorProfesores gestorProfesores = new GestorProfesores();
            List<Profesor> profesores = gestorProfesores.obtenerProfesoresActivos();

            tblProfesoresActivos.getItems().clear();

            tblProfesoresActivos.setItems(FXCollections.observableArrayList(profesores));

        }catch(ReglaDeNegocioExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Error",e.getMessage());

        }

    }

    private Profesor obtenerProfesorSeleccionado(){

        Profesor profesorSeleccionado = tblProfesoresActivos.getSelectionModel().getSelectedItem();

        if(profesorSeleccionado == null){

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING,"Profesor no seleccionado",
            "Debe seleccionar un profesor.");

        }

        return profesorSeleccionado;

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
