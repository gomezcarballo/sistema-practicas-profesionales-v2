/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import java.time.LocalDateTime;
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
import javafx.scene.control.cell.TextFieldTableCell;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.gestores.GestorActividades;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.formatofechas.ConvertidorFechaHoraLocal;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaActividades {
    
    @FXML
    private TableView<Actividad> tblListaActividades;

    @FXML
    private TableColumn<Actividad, String> colTitulo;

    @FXML
    private TableColumn<Actividad, LocalDateTime> colFechaLimite;
    
    @FXML
    public void initialize(){

        tblListaActividades.setPlaceholder(new Label("No hay actividades pendientes"));

        tblListaActividades.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();
        
        configurarFecha();
        
        cargarActividades();
        
    }

    private void configurarColumnas(){

        colTitulo.setCellValueFactory(new PropertyValueFactory<>("titulo"));

    }
    
    private void configurarFecha(){
        
        colFechaLimite.setCellValueFactory(new PropertyValueFactory<>("fechaLimite"));

        colFechaLimite.setCellFactory(TextFieldTableCell.forTableColumn(new ConvertidorFechaHoraLocal()));
            
    }

    private void cargarActividades(){

        try{
            
            GestorActividades gestorActividades = new GestorActividades();
            List<Actividad> actividades = gestorActividades.recuperarActividadesAsignadas();

            tblListaActividades.getItems().clear();

            tblListaActividades.setItems(FXCollections.observableArrayList(actividades));

        }catch(ReglaDeNegocioExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje( Alert.AlertType.ERROR, "Error al recuperar actividades",
            "Hubo un error al recuperar las actividades");

        }

    }

    private Actividad obtenerActividadSeleccionada() {

        Actividad actividadSeleccionada = tblListaActividades.getSelectionModel().getSelectedItem();

        if (actividadSeleccionada == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING,"Sin selección",
            "Debe seleccionar una actividad");

        }

        return actividadSeleccionada;

    }
    
    @FXML
    private void abrirDetalleActividad(ActionEvent evento){

        Actividad actividadSeleccionada = obtenerActividadSeleccionada();

        if (actividadSeleccionada != null) {
        
            FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaDetalleActividad.fxml",
            "Detalle de la Actividad");

            if (cargador != null) {

                ControladorDetalleActividad controlador = cargador.getController();
                controlador.cargarActividad(actividadSeleccionada);
                CerradorVentana.cerrarVentana(evento);

            }

        }

    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalPracticante.fxml", "Menú para Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
