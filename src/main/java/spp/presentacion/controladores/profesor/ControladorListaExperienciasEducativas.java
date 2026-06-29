/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.profesor;

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
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.gestores.GestorProfesores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaExperienciasEducativas {
    
    @FXML 
    private TableView<ExperienciaEducativa> tblListaExperiencias;
    
    @FXML 
    private TableColumn<ExperienciaEducativa, String> colNombre;
    
    @FXML 
    private TableColumn<ExperienciaEducativa, String> colPeriodo;
        
    private GestorProfesores gestorProfesor;
    
    @FXML
    public void initialize() {
        
        this.gestorProfesor = new GestorProfesores();

        tblListaExperiencias.setPlaceholder(new Label("No hay grupos asignados para mostrar"));
        tblListaExperiencias.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        configurarColumnas();
        cargarExperienciasEducativas();        
        
    }
    
    private void configurarColumnas() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreExperienciaEducativa"));
        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));

    }
    
    private void cargarExperienciasEducativas() {

        try {

            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuarioProfesor = sesionUsuario.getIdUsuario();

            List<ExperienciaEducativa> experiencias = gestorProfesor.recuperarExperienciasEducativasPorProfesor(idUsuarioProfesor);

            tblListaExperiencias.getItems().clear();
            tblListaExperiencias.setItems(FXCollections.observableArrayList(experiencias));

        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar grupos",
            "Hubo un error al recuperar sus Experiencias Educativas: " + e.getMessage());

        }

    }
    
    private ExperienciaEducativa obtenerExperienciaSeleccionada() {

        ExperienciaEducativa experienciaSeleccionada = tblListaExperiencias.getSelectionModel().getSelectedItem();

        if (experienciaSeleccionada == null) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar un grupo para ver a sus practicantes");

        }

        return experienciaSeleccionada;

    }
    
    @FXML
    private void abrirListaPracticantes(ActionEvent evento) {

        ExperienciaEducativa experienciaSeleccionada = obtenerExperienciaSeleccionada();

        if (experienciaSeleccionada == null) {
            return;
        }

        FXMLLoader cargadorListaPracticantes = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaListaPracticantesEE.fxml", "Lista de Practicantes");

        if (cargadorListaPracticantes != null) {

            ControladorListaPracticantesEE controlador = cargadorListaPracticantes.getController();
            controlador.inicializarDatos(experienciaSeleccionada);
            CerradorVentana.cerrarVentana(evento);

        }

    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaMenuPrincipalProfesor.fxml", 
        "Menú Principal del Profesor");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
