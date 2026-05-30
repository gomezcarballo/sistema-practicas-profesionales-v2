/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.gestores.GestorAsignacionProyectos;
import spp.logicadenegocio.gestores.GestorSolicitudesProyectos;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorAsignacionProyecto {
    
    @FXML
    private TableView<Proyecto> tblProyectosSolicitados;
    
    @FXML
    private TableColumn<Proyecto, String> colNombre;
    
    @FXML
    private TableColumn<Proyecto, String> colDescripcion;

    @FXML
    private TableColumn<Proyecto, String> colNombreResponsable;
    
    @FXML
    private TableColumn<Proyecto, Integer> colCupoMaximo;
    
    private GestorSolicitudesProyectos gestorSolicitudes;
    
    private GestorAsignacionProyectos gestorAsignacionProyecto;
    
    private Practicante practicante;
    
    public void inicializarDatos(Practicante practicante){

        this.practicante = practicante;
        
        cargarProyectosSolicitados();

    }
    
    @FXML
    public void initialize() {
        
        gestorSolicitudes = new GestorSolicitudesProyectos();
        
        gestorAsignacionProyecto = new GestorAsignacionProyectos();
        
        tblProyectosSolicitados.setPlaceholder(new Label("No hay proyectos solicitados por este practicante"));
        
        tblProyectosSolicitados.setEditable(true);
        
        tblProyectosSolicitados.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        configurarColumnas();
        
    }
    
    private void  configurarColumnas(){
        
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));

        colDescripcion.setCellValueFactory(new PropertyValueFactory<>("descripcion"));

        colNombreResponsable.setCellValueFactory(new PropertyValueFactory<>("nombreResponsable"));

        colCupoMaximo.setCellValueFactory(new PropertyValueFactory<>("cupoMaximo"));
        
    }
    
    private void cargarProyectosSolicitados(){
        
        try{
                        
            List<Proyecto> lista = gestorSolicitudes.recuperarProyectosSolicitados(practicante.getIdUsuario());

            tblProyectosSolicitados.setItems(FXCollections.observableArrayList(lista));
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar Proyectos", 
            "Hubo un error al recuperar los Proyectos solicitados. Intente más tarde");

        }
        
    } 
    
    @FXML
    private void asignarProyecto(ActionEvent evento) {

        Proyecto proyectoSeleccionado = tblProyectosSolicitados.getSelectionModel().getSelectedItem();
        
        if(proyectoSeleccionado == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar un proyecto para asignar");
            return;

        }
        
        registrarAsignacionProyecto(proyectoSeleccionado, evento);

    }
    
    private void registrarAsignacionProyecto(Proyecto proyectoSeleccionado, ActionEvent evento) {

        try {
            
            gestorAsignacionProyecto.asignarProyecto(proyectoSeleccionado.getIdProyecto(), practicante.getIdUsuario());

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Asignación exitosa",
            "El proyecto fue asignado correctamente");
            
            regresar(evento);

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error", e.getMessage());

        }
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaSolicitudesPracticantes.fxml", "Solicitudes de Proyectos");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
