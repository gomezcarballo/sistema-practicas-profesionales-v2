/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.gestores.GestorProyectos;
import spp.logicadenegocio.gestores.GestorSolicitudesProyectos;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaProyectos {
    
    @FXML
    private TableView<Proyecto> listaProyectos;
    
    @FXML
    private TableColumn<Proyecto, Boolean> columnaSeleccionar;
    
    @FXML
    private TableColumn<Proyecto, String> columnaNombre;
    
    @FXML
    private TableColumn<Proyecto, String> columnaDescripcion;

    @FXML
    private TableColumn<Proyecto, String> columnaNombreResponsable;
    
    @FXML
    private TableColumn<Proyecto, Integer> columnaCupoMaximo;
    
    private GestorProyectos gestorProyectos;
    
    private GestorSolicitudesProyectos gestorSolicitudes;
    
    private static final int MAXIMO_SOLICITADOS = 3;
    
    @FXML
    public void initialize() {
        
        gestorProyectos = new GestorProyectos();
        
        gestorSolicitudes = new GestorSolicitudesProyectos();
        
        listaProyectos.setPlaceholder(new Label("No hay proyectos disponibles"));
        
        listaProyectos.setEditable(true);

        columnaSeleccionar.setEditable(true);
        
        configurarColumnas();

        cargarProyectos();
        
    }
    
    private void  configurarColumnas(){
        
        columnaNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        columnaDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        columnaNombreResponsable.setCellValueFactory(new PropertyValueFactory<>("nombreResponsable"));

        columnaCupoMaximo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
        
        columnaSeleccionar.setCellValueFactory(celda -> celda.getValue().propiedadEsSeleccionado());
        
        columnaSeleccionar.setCellFactory(CheckBoxTableCell.forTableColumn(columnaSeleccionar));
        
    }
    
    private void cargarProyectos(){
        try{
            
            int idOrganizacion = 1;
            
            ObservableList<Proyecto> proyectos = FXCollections.observableArrayList
            (gestorProyectos.recuperarProyectosActivos(idOrganizacion));
            
            listaProyectos.setItems(proyectos);
            
            configurarEventosSeleccion();
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar Proyectos", 
            "Hubo un error al recuperar los Proyectos. Intente más tarde");

        }
    }
    
    private void configurarEventosSeleccion() {

        for(Proyecto proyecto : listaProyectos.getItems()) {

            configurarListenerSeleccionProyecto(proyecto);
        }
    }
    
    private void configurarListenerSeleccionProyecto(Proyecto proyecto) {

        proyecto.propiedadEsSeleccionado().addListener((observable, valorAnterior, valorNuevo) -> {
            
            actualizarContador();

        });
    }
    
    private void actualizarContador() {

        int seleccionados = 0;

        for(Proyecto proyecto : listaProyectos.getItems()) {

            if(proyecto.getEsSeleccionado()) {

                seleccionados++;
            }
        }
        
        if(seleccionados > MAXIMO_SOLICITADOS){
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Límite excedido", 
            "Solo puede seleccionar 3 proyectos");
            
        }
    }
    
    @FXML
    private void solicitarProyectos(ActionEvent evento) {

        List<Proyecto> proyectosSeleccionados = new ArrayList<>();

        for(Proyecto proyecto : listaProyectos.getItems()) {

            if(proyecto.getEsSeleccionado()) {

                proyectosSeleccionados.add(proyecto);
                
            }
        }

        if(proyectosSeleccionados.isEmpty()) {

            VentanaMensaje ventanaMensaje = new VentanaMensaje();

            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar al menos  un proyecto");
            return;

        }
        
        registrarSolicitudesSeleccionadas(proyectosSeleccionados);

    }
    
    private void registrarSolicitudesSeleccionadas(List<Proyecto> proyectosSeleccionados) {

        try {
            
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();

            int idUsuario = sesionUsuario.getIdUsuario();
            
            gestorSolicitudes.registrarSolicitudes(idUsuario, proyectosSeleccionados);

            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Solicitudes registradas",
            "Las solicitudes se registraron correctamente");

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
