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
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.gestores.GestorProyectos;
import spp.logicadenegocio.gestores.GestorSolicitudesProyectos;
import spp.presentacion.controladores.coordinador.ControladorDetalleProyecto;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorSolicitudProyectos {
    
    @FXML
    private TableView<Proyecto> tblListaProyectos;
    
    @FXML
    private TableColumn<Proyecto, Boolean> colSeleccionar;
    
    @FXML
    private TableColumn<Proyecto, String> colNombre;
    
    @FXML
    private TableColumn<Proyecto, String> colDescripcion;

    @FXML
    private TableColumn<Proyecto, String> colNombreOrganizacion;
    
    @FXML
    private TableColumn<Proyecto, Integer> colCupoMaximo;
    
    private GestorProyectos gestorProyectos;
    
    private GestorSolicitudesProyectos gestorSolicitudes;
        
    @FXML
    public void initialize() {
        
        gestorProyectos = new GestorProyectos();
        
        gestorSolicitudes = new GestorSolicitudesProyectos();
        
        tblListaProyectos.setPlaceholder(new Label("No hay proyectos disponibles"));
        
        tblListaProyectos.setEditable(true);

        colSeleccionar.setEditable(true);
        
        configurarColumnas();

        cargarProyectos();
        
    }
    
    private void  configurarColumnas(){
        
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        colNombreOrganizacion.setCellValueFactory(new PropertyValueFactory<>("nombreOrganizacion"));

        colCupoMaximo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
        
        colSeleccionar.setCellValueFactory(celda -> celda.getValue().propiedadEsSeleccionado());
        
        colSeleccionar.setCellFactory(CheckBoxTableCell.forTableColumn(colSeleccionar));
        
    }
    
    private Proyecto obtenerProyectoSeleccionado() {

        Proyecto proyectoSeleccionado = tblListaProyectos.getSelectionModel().getSelectedItem();

        if (proyectoSeleccionado == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING,"Sin selección",
            "Debe seleccionar un proyecto para poder ver sus detalles");

        }

        return proyectoSeleccionado;

    }
    
    @FXML
    private void abrirDetalleProyecto(ActionEvent evento){

        Proyecto proyectoSeleccionado = obtenerProyectoSeleccionado();

         if (proyectoSeleccionado != null) {
        
            FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaDetalleProyecto.fxml",
            "Detalle Proyecto");

            if (cargador != null) {

                ControladorDetalleProyecto controlador = cargador.getController();

                controlador.cargarProyecto(proyectoSeleccionado);

            }

        }

    }
    
    private void cargarProyectos(){
        try{
            
            
            ObservableList<Proyecto> proyectos = FXCollections.observableArrayList
            (gestorProyectos.recuperarProyectosActivos());
            
            tblListaProyectos.setItems(proyectos);
                        
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar Proyectos", 
            "Hubo un error al recuperar los Proyectos. Intente más tarde");

        }
    }
    
    @FXML
    private void solicitarProyectos(ActionEvent evento) {

        List<Proyecto> proyectosSeleccionados = obtenerProyectosSeleccionados();
        registrarSolicitudesSeleccionadas(proyectosSeleccionados, evento);

    }
    
    private List<Proyecto> obtenerProyectosSeleccionados() {

        List<Proyecto> proyectosSeleccionados = new ArrayList<>();

        for(Proyecto proyecto : tblListaProyectos.getItems()) {

            if(proyecto.getEsSeleccionado()) {

                proyectosSeleccionados.add(proyecto);
            }
        }

        return proyectosSeleccionados;
    }
    
    
    private void registrarSolicitudesSeleccionadas(List<Proyecto> proyectosSeleccionados, ActionEvent evento) {

        try {
                     
            gestorSolicitudes.registrarSolicitudes(proyectosSeleccionados);

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Solicitudes registradas",
            "Las solicitudes se registraron correctamente");
            
            CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
            "Menú Principal para Practicante");
            CerradorVentana.cerrarVentana(evento);

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error", e.getMessage());
            
        }
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
        "Menú Principal para Practicante");
        CerradorVentana.cerrarVentana(evento);
        
    }

}
