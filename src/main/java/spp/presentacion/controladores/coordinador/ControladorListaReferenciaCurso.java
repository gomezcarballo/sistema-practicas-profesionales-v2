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
import spp.logicadenegocio.clasesdto.ReferenciaCurso;
import spp.logicadenegocio.gestores.GestorReferenciaCurso;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaReferenciaCurso {
    
    @FXML
    private TableView<ReferenciaCurso> tblListaNRC;

    @FXML
    private TableColumn<ReferenciaCurso, String> colNrc;
    
    @FXML
    public void initialize(){

        tblListaNRC.setPlaceholder(new Label("No hay NRCs registrados en el sistema"));

        tblListaNRC.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();
        
        cargarNrcs();
        
    }

    private void configurarColumnas(){
        
        colNrc.setCellValueFactory(new PropertyValueFactory<>("nrc"));

    }
    
    private void cargarNrcs(){

        try{
            
            GestorReferenciaCurso gestorNrc = new GestorReferenciaCurso();
            List<ReferenciaCurso> listaNrcs = gestorNrc.consultarTodosLosNRC(); 

            tblListaNRC.getItems().clear();

            tblListaNRC.setItems(FXCollections.observableArrayList(listaNrcs));

        }catch(OperacionesDeDaoExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar NRCs",
            "Hubo un error al recuperar la lista de NRCs de la base de datos");

        }

    }

    private ReferenciaCurso obtenerNrcSeleccionado() {

        ReferenciaCurso nrcSeleccionado = tblListaNRC.getSelectionModel().getSelectedItem();

        if (nrcSeleccionado == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING,"Sin selección",
            "Debe seleccionar un NRC de la tabla para continuar");

        }

        return nrcSeleccionado;

    }
    
    @FXML
    private void irARegistrarNuevaEE(ActionEvent evento){

        ReferenciaCurso nrcSeleccionado = obtenerNrcSeleccionado();

        if (nrcSeleccionado != null) {
        
            FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaNuevaExperienciaEducativa.fxml",
            "Registro de Nueva Experiencia Educativa");

            if (cargador != null) {

                ControladorNuevaExperienciaEducativa controlador = cargador.getController();
                
                controlador.inicializarDatos(nrcSeleccionado);
                
                CerradorVentana.cerrarVentana(evento);

            }

        }

    }

    @FXML
    private void irARegistrarNuevoNRC(ActionEvent evento){

        CargadorVentana.cargarVentana("/fxml/VistaRegistroNuevaReferenciaCurso.fxml", "Registro de un Nuevo NRC");
        CerradorVentana.cerrarVentana(evento);

    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalCoordinador.fxml", "Menú Principal del Coordinador");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
