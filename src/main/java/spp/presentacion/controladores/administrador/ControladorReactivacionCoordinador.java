/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.gestores.GestorCoordinadores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorReactivacionCoordinador {
    
    @FXML
    private TableView<Coordinador> tblCoordinadoresInactivos;
    
    @FXML
    private TableColumn<Coordinador, String> colNombre;

    @FXML
    private TableColumn<Coordinador, String> colApellidoPaterno;
    
    @FXML
    private TableColumn<Coordinador, String> colApellidoMaterno;
    
    @FXML
    private TableColumn<Coordinador, String> colCorreoInstitucional;
    
    @FXML
    private void initialize() {
        
        tblCoordinadoresInactivos.setPlaceholder(new Label("No hay Coordinadores inactivos"));

        tblCoordinadoresInactivos.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();

        cargarCoordinadoresInactivos();
        
    }
    
    private void configurarColumnas(){
        
        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        colCorreoInstitucional.setCellValueFactory(new PropertyValueFactory<>("correoInstitucional"));

    }
    
    private void cargarCoordinadoresInactivos(){

        try{
            
            GestorCoordinadores gestorCoordinadores = new GestorCoordinadores();
            List<Coordinador> coordinadores = gestorCoordinadores.obtenerCoordinadoresInactivos();

            tblCoordinadoresInactivos.getItems().clear();

            tblCoordinadoresInactivos.setItems(FXCollections.observableArrayList(coordinadores));

        }catch(OperacionesDeDaoExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR,"Error",e.getMessage());

        }

    }
    
    private Coordinador obtenerCoordinadorSeleccionado(){

        Coordinador coordinadorSeleccionado = tblCoordinadoresInactivos.getSelectionModel().getSelectedItem();

        if(coordinadorSeleccionado == null){

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING,"Coordinador no seleccionado",
            "Debe seleccionar un coordinador.");

        }

        return coordinadorSeleccionado;

    }
    
    @FXML
    private void reactivarCoordinador(ActionEvent evento) {
        
        Coordinador coordinadorSeleccionado = obtenerCoordinadorSeleccionado();

        if (coordinadorSeleccionado != null) {
            
            procesarReactivacion(coordinadorSeleccionado, evento);
            
        }
        
    }

    private void procesarReactivacion(Coordinador coordinador, ActionEvent evento) {
        
        try {
            
            GestorCoordinadores gestor = new GestorCoordinadores();
            boolean continuarRegistro = true;

            if (gestor.verificarCoordinadorActivo()) {
                
                continuarRegistro = VentanaMensaje.mostrarConfirmacion("Coordinador activo",
                "Ya existe un Coordinador activo. ¿Desea inactivarlo para continuar con la reactivación?");
                
            }

            if (continuarRegistro) {
                
                gestor.reemplazarPorCoordinadorInactivo(coordinador.getIdUsuario());

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Reactivación exitosa",
                "Coordinador reactivado exitosamente");

                irMenuPrincipal(evento);
                
            }

        } catch (OperacionesDeDaoExcepcion | ProcesamientoSistemaExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Reactivación fallida", e.getMessage());
            
        }
        
    }
    
    private void irMenuPrincipal(ActionEvent evento) {

        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalAdministrador.fxml", "Menu Principal para Administrador");

        CerradorVentana.cerrarVentana(evento);
    
    }
    
    @FXML
    private void cancelar(ActionEvent evento){

        irMenuPrincipal(evento);

    }

    
}
