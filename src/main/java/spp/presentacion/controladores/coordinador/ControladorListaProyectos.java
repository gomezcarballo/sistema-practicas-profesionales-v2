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
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.gestores.GestorProyectos;
import spp.presentacion.menus.ControladorSubMenuProyectos;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaProyectos {
    
    @FXML
    private TableView<Proyecto> tblListaProyectos;

    @FXML
    private TableColumn<Proyecto, String> colNombre;

    @FXML
    private TableColumn<Proyecto, String> colDescripcion;

    @FXML
    private TableColumn<Proyecto, String> colResponsable;

    @FXML
    private TableColumn<Proyecto, Integer> colCupoMaximo;
    
    @FXML
    private Label lblTituloProyectos;

    private Organizacion organizacion;

    private GestorProyectos gestorProyectos;
    
    @FXML
    public void initialize(){

        gestorProyectos = new GestorProyectos();

        tblListaProyectos.setPlaceholder(new Label("No hay proyectos registrados"));

        tblListaProyectos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();

    }

    public void inicializarDatos(Organizacion organizacion){

        this.organizacion = organizacion;

        lblTituloProyectos.setText("Proyectos de " + organizacion.getNombre());

        cargarProyectos();

    }

    private void configurarColumnas(){

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));
        colResponsable.setCellValueFactory(new PropertyValueFactory<>("nombreResponsable"));
        colCupoMaximo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));

    }

    private void cargarProyectos(){

        try{

            List<Proyecto> proyectos = gestorProyectos.recuperarProyectosActivosPorOrganizacion(organizacion.getIdOrganizacion());

            tblListaProyectos.getItems().clear();

            tblListaProyectos.setItems(FXCollections.observableArrayList(proyectos));

        }catch(ReglaDeNegocioExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje( Alert.AlertType.ERROR, "Error al recuperar proyectos",
            "Hubo un error al recuperar los proyectos");

        }

    }

    private Proyecto obtenerProyectoSeleccionado() {

        Proyecto proyectoSeleccionado = tblListaProyectos.getSelectionModel().getSelectedItem();

        if (proyectoSeleccionado == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING,"Sin selección",
            "Debe seleccionar un proyecto");

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

                controlador.cargarProyecto(proyectoSeleccionado, organizacion);

            }

        }

    }
    
    @FXML
    private void abrirActualizarProyecto(){
        
        Proyecto proyectoSeleccionado = obtenerProyectoSeleccionado();

        if(proyectoSeleccionado == null){
            return;
        }

        FXMLLoader cargadorFormulario = CargadorVentana.cargarVentanaConControlador(
        "/fxml/VistaFormularioProyecto.fxml", "Actualizar Proyecto");

        if(cargadorFormulario != null){

            ControladorFormularioProyecto controlador = cargadorFormulario.getController();
            controlador.inicializarDatos(proyectoSeleccionado, organizacion);

        }

    }
    
    @FXML
    public void abrirInactivarProyecto(ActionEvent evento){

        Proyecto proyectoSeleccionado = obtenerProyectoSeleccionado();
        
        if(proyectoSeleccionado == null){
            return;
        }
        
        boolean confirmado = VentanaMensaje.mostrarConfirmacion("Confirmar inactivación",
        "¿Desea inactivar el proyecto " + proyectoSeleccionado.getNombre() + "?");

        if (confirmado) {
            
            try {

            gestorProyectos.inactivarProyecto(proyectoSeleccionado.getIdProyecto());

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION,
            "Proyecto inactivado", "El proyecto fue inactivado correctamente");

            cargarProyectos();

            }catch (ReglaDeNegocioExcepcion e) {

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,
                "Error al inactivar Proyecto", e.getMessage());

            }
            
        }

    }   

    @FXML
    public void regresar(ActionEvent evento){
        
        FXMLLoader cargadorSubMenu = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaSubMenuProyectos.fxml", "Menú de Proyectos");

        if(cargadorSubMenu != null){

            ControladorSubMenuProyectos controlador = cargadorSubMenu.getController();

            controlador.inicializarDatos(organizacion);

            CerradorVentana.cerrarVentana(evento);
        }

    }
    
}
