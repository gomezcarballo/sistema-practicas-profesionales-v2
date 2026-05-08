/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.gestores.GestorAsignacionProyectos;
import spp.logicadenegocio.gestores.GestorProyectos;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorAsignacionProyecto {
    
    @FXML
    private TableView<Proyecto> listaProyectosSolicitados;
    
    @FXML
    private TableColumn<Proyecto, String> columnaNombre;
    
    @FXML
    private TableColumn<Proyecto, String> columnaDescripcion;

    @FXML
    private TableColumn<Proyecto, String> columnaNombreResponsable;
    
    @FXML
    private TableColumn<Proyecto, Integer> columnaCupoMaximo;
    
    private GestorProyectos gestorProyectos;
    
    private GestorAsignacionProyectos gestorAsignacionProyecto;
    
    @FXML
    public void initialize() {
        
        gestorProyectos = new GestorProyectos();
        
        gestorAsignacionProyecto = new GestorAsignacionProyectos();
        
        listaProyectosSolicitados.setPlaceholder(new Label("No hay proyectos solicitados por este practicante"));
        
        listaProyectosSolicitados.setEditable(true);
        
        listaProyectosSolicitados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        configurarColumnas();

        cargarProyectosSolicitados();
        
    }
    
    private void  configurarColumnas(){
        
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        columnaNombreResponsable.setCellValueFactory(new PropertyValueFactory<>("nombreResponsable"));

        columnaCupoMaximo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
        
    }
    
    private void cargarProyectosSolicitados(){
        try{
            
            int idUsuario = 10;
            
            ObservableList<Proyecto> proyectos = FXCollections.observableArrayList
            (gestorProyectos.recuperarProyectosSolicitados(idUsuario));
            
            listaProyectosSolicitados.setItems(proyectos);
            
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar Proyectos", 
            "Hubo un error al recuperar los Proyectos solicitados. Intente más tarde");

        }
    } 
    
    @FXML
    private void asignarProyecto(ActionEvent evento) {

        Proyecto proyectoSeleccionado = listaProyectosSolicitados.getSelectionModel().getSelectedItem();
        
        if(proyectoSeleccionado == null) {

            VentanaMensaje ventanaMensaje = new VentanaMensaje();

            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar un proyecto para asignar");
            return;

        }
        
        registrarAsignacionProyecto(proyectoSeleccionado);

    }
    
    private void registrarAsignacionProyecto(Proyecto proyectoSeleccionado) {

        try {
            
            int idUsuario = 10;
            gestorAsignacionProyecto.asignarProyecto(proyectoSeleccionado.getIdProyecto(), idUsuario);

            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Asignación exitosa",
            "El proyecto fue asignado correctamente");

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
