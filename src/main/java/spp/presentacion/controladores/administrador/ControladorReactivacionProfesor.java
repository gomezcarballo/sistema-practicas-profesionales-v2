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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionProfesor;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorReactivacionProfesor {
    
    @FXML
    private TableView<Profesor> tblProfesoresInactivos;
    
    @FXML
    private TableColumn<Profesor, String> colNombre;

    @FXML
    private TableColumn<Profesor, String> colApellidoPaterno;
    
    @FXML
    private TableColumn<Profesor, String> colApellidoMaterno;
    
    @FXML
    private TableColumn<Profesor, String> colCorreoInstitucional;
    
    @FXML
    private void initialize(){
        
        tblProfesoresInactivos.setPlaceholder(new Label("No hay Profesores inactivos"));

        tblProfesoresInactivos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();

        cargarProfesoresInactivos();
        
    }
    
    private void configurarColumnas(){

        
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colCorreoInstitucional.setCellValueFactory(new PropertyValueFactory<>("correoInstitucional"));

    }
    
    private void cargarProfesoresInactivos(){

        try{
            
            ValidacionProfesor validacion = new ValidacionProfesor();
            List<Profesor> profesores = validacion.obtenerProfesoresInactivos();

            tblProfesoresInactivos.getItems().clear();

            tblProfesoresInactivos.setItems(FXCollections.observableArrayList(profesores));

        }catch(ReglaDeNegocioExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR,"Error",e.getMessage());

        }

    }
    
    private Profesor obtenerCoordinadorSeleccionado(){

        Profesor profesorSeleccionado = tblProfesoresInactivos.getSelectionModel().getSelectedItem();

        if(profesorSeleccionado == null){

            VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING,"Profesor no seleccionado",
            "Debe seleccionar un profesor.");

        }

        return profesorSeleccionado;

    }
    
    @FXML 
    private void reactivarProfesor(ActionEvent evento){
        
        Profesor profesorSeleccionado = new Profesor();
        
        if(profesorSeleccionado != null){
            
            ValidacionProfesor validacion = new ValidacionProfesor();
            
            try{

                if(validacion.hayCupoProfesores()){

                    validacion.reactivarProfesorInactivo(profesorSeleccionado.getIdUsuario());

                    VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Reactivación Exitosa", 
                    "Profesor reactivado exitosamente");

                    CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalAdministrador.fxml", 
                    "Menu Principal para Administrador");
                    CerradorVentana.cerrarVentana(evento);

                }else{

                    FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador
                    ("/fxml/VistaListaProfesoresActivos.fxml", "Profesores Activos");

                    if(cargador != null){
                        ControladorListaProfesoresActivos controlador = cargador.getController();
                        controlador.inicializarDatos(profesorSeleccionado);

                    }

                }

            }catch(ReglaDeNegocioExcepcion e){

                VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Reactivación fallida", 
                e.getMessage());

            }
        
        }
    }
    
    public void registrarProfesorConReemplazo(Profesor profesorNuevo, Profesor profesorAnterior, ActionEvent evento){

        try{

            ValidacionProfesor validacion = new ValidacionProfesor();

            validacion.inactivarProfesor(profesorAnterior.getIdUsuario());

            validacion.reactivarProfesorInactivo(profesorNuevo.getIdUsuario());

            VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Reactivación exitosa",
            "Profesor reactivado exitosamente");

            CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalAdministrador.fxml", 
            "Menu Principal para Administrador");
            CerradorVentana.cerrarVentana(evento);

        }catch(ReglaDeNegocioExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Reactivación fallida", e.getMessage());

        }

    }

    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalAdministrador.fxml", 
        "Menu Principal para Administrador");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
