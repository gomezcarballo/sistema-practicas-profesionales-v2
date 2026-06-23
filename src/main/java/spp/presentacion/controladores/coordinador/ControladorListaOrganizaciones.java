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
import spp.logicadenegocio.gestores.GestorOrganizaciones;
import spp.presentacion.controladores.menus.ControladorSubMenuProyectos;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaOrganizaciones {
    
    @FXML
    private TableView<Organizacion> tblListaOrganizaciones;
    
    @FXML
    private TableColumn<Organizacion, String> colNombre;
    
    @FXML
    private TableColumn<Organizacion, String> colDireccion;

    @FXML
    private TableColumn<Organizacion, String> colSector;    
    
    @FXML
    private Label lblTituloOrganizaciones;

    private GestorOrganizaciones gestorOrganizaciones;
    
    @FXML
    public void initialize() {
        
        gestorOrganizaciones = new GestorOrganizaciones();

        lblTituloOrganizaciones.setText("Lista de Organizaciones");

        tblListaOrganizaciones.setPlaceholder(new Label( "No hay organizaciones registradas"));
        
        tblListaOrganizaciones.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        configurarColumnas();
        
        cargarOrganizaciones();        
        
    }
    
    private void configurarColumnas() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colDireccion.setCellValueFactory(new PropertyValueFactory<>("direccion"));
        colSector.setCellValueFactory(new PropertyValueFactory<>("sector"));

    }
    
    private void cargarOrganizaciones() {

        try {

            List<Organizacion> organizaciones = gestorOrganizaciones.recuperarOrganizacionesActivas();

            tblListaOrganizaciones.getItems().clear();

            tblListaOrganizaciones.setItems(FXCollections.observableArrayList(organizaciones));

        } catch(OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar organizaciones",
            "Hubo un error al recuperar las organizaciones");

        }

    }
    
    private Organizacion obtenerOrganizacionSeleccionada() {

        Organizacion organizacionSeleccionada = tblListaOrganizaciones.getSelectionModel().getSelectedItem();

        if (organizacionSeleccionada == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING,"Sin selección",
            "Debe seleccionar una organización");

        }

        return organizacionSeleccionada;

    }
    
    @FXML
    private void abrirDetalleOrganizacion(ActionEvent evento) {

        Organizacion organizacionSeleccionada = obtenerOrganizacionSeleccionada();

        if(organizacionSeleccionada == null) {
            return;
        }

        FXMLLoader cargadorDetalleOrganizacion = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaDetalleOrganizacion.fxml", "Detalle Organización");

        if(cargadorDetalleOrganizacion != null){

            ControladorDetalleOrganizacion controlador = cargadorDetalleOrganizacion.getController();
            controlador.cargarOrganizacion(organizacionSeleccionada);
            CerradorVentana.cerrarVentana(evento);

        }

    }
    
    @FXML
    private void abrirActualizarOrganizacion(){
        
        Organizacion organizacionSeleccionada = obtenerOrganizacionSeleccionada();

        if(organizacionSeleccionada == null) {
            return;
        }

        FXMLLoader cargadorFormulario = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaFormularioOrganizacion.fxml","Actualizar Organización");

        if(cargadorFormulario != null){

            ControladorGestionOrganizacion controlador = cargadorFormulario.getController();
            controlador.inicializarDatos(organizacionSeleccionada);

        }

    }   
    
    @FXML
    private void abrirMenuProyectos(ActionEvent evento){
        
        Organizacion organizacionSeleccionada = obtenerOrganizacionSeleccionada();

        if(organizacionSeleccionada == null) {
            return;
        }

        FXMLLoader cargadorMenuProyectos = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaSubMenuProyectos.fxml", "Menú de Proyectos de " + organizacionSeleccionada.getNombre());

        if(cargadorMenuProyectos != null){

            ControladorSubMenuProyectos controlador = cargadorMenuProyectos.getController();
            controlador.inicializarDatos(organizacionSeleccionada);
            CerradorVentana.cerrarVentana(evento);

        }
    
    }
    
    @FXML
    public void abrirInactivarOrganizacion(ActionEvent evento){

        Organizacion organizacionSeleccionada = obtenerOrganizacionSeleccionada();

        if(organizacionSeleccionada == null){
            return;
        }

        boolean confirmado = VentanaMensaje.mostrarConfirmacion("Confirmar inactivación",
        "¿Desea inactivar la organización " + organizacionSeleccionada.getNombre() + "?"
        + "\n Esta acción desactivará todos sus proyectos.");

        if (confirmado) {

            try {

                gestorOrganizaciones.inactivarOrganizacion(organizacionSeleccionada.getIdOrganizacion());

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION,
                "Organización inactivada", "La organización fue inactivada correctamente");

                cargarOrganizaciones();

            }catch (OperacionesDeDaoExcepcion e) {

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,
                "Error al inactivar Organización", e.getMessage());

            }
            
        }

    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubMenuOrganizacionVinculada.fxml", 
        "Menu de Organizaciones Vinculadas");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
