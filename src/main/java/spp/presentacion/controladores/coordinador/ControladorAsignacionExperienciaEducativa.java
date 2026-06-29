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
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.gestores.GestorExperienciaEducativa;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;


public class ControladorAsignacionExperienciaEducativa {

    @FXML 
    private TableView<ExperienciaEducativa> tblListaExperiencias;

    @FXML
    private TableColumn<ExperienciaEducativa, String> colNombre;

    @FXML
    private TableColumn<ExperienciaEducativa, String> colPeriodo;
    
    @FXML
    private TableColumn<ExperienciaEducativa, String> colNRC;

    private Practicante practicanteSeleccionado;

    private GestorExperienciaEducativa gestor;

    @FXML
    public void initialize() {

        tblListaExperiencias.setPlaceholder(new Label("No hay experiencias educativas registradas."));

        tblListaExperiencias.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();
        
    }

    public void inicializarDatos(Practicante practicante, List<ExperienciaEducativa> listaExperienciaEducativas) {

        this.practicanteSeleccionado = practicante;

        if(listaExperienciaEducativas != null && !listaExperienciaEducativas.isEmpty()){

            cargarExperienciasEducativas(listaExperienciaEducativas);

        }else{

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Error cargar experiencias educativas",
                "Error mostrar las experiencias educativas. Intentelo más tarde.");
        
        }
        
    }

    private void configurarColumnas() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombreExperienciaEducativa"));
        
        colPeriodo.setCellValueFactory(new PropertyValueFactory<>("periodo"));

        colNRC.setCellValueFactory(new PropertyValueFactory<>("nrc"));

    }

    private void cargarExperienciasEducativas(List<ExperienciaEducativa> listaExperienciaEducativas){

        if(listaExperienciaEducativas != null && !listaExperienciaEducativas.isEmpty()){

            tblListaExperiencias.getItems().clear();

            tblListaExperiencias.setItems(FXCollections.observableArrayList(listaExperienciaEducativas));

        }else{

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Error cargar experiencias educativas",
                "Error mostrar las experiencias educativas. Intentelo más tarde.");
        }
       
    }

    private ExperienciaEducativa obtenerExperienciaSeleccionada(){
        
        ExperienciaEducativa experienciaSeleccionada = tblListaExperiencias.getSelectionModel().getSelectedItem();
        
        if(experienciaSeleccionada == null){

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar una experiencia educativa");

        }

        return experienciaSeleccionada;
    }

    
    @FXML 
    void asignarExperienciaAPracticante(ActionEvent evento){
        
        ExperienciaEducativa experiencia = obtenerExperienciaSeleccionada();
        boolean asignacionExitosa = false;
         
        if(experiencia != null){

            gestor = new GestorExperienciaEducativa();

            try{

                asignacionExitosa = gestor.asignarExperienciaAlPracticante(practicanteSeleccionado, experiencia);

            }catch(OperacionesDeDaoExcepcion e){

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al asignar la EE. ",
                e.getMessage());
                
            }

            if(asignacionExitosa){

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Asignación exitosa",
                "Se a realizado la asignación correctamente.");

            }else{

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Error al asignar",
                 "Intente de nuevo más tarde. Hubo un error al asignar la EE.");

            }
        }
    }

    @FXML
    private void verHorario(){
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Perame",
        "Ahorita queda");
    }

    @FXML
    private void  verPlanActividades(){
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Perame",
        "Ahorita queda");
    }

    @FXML
    private void  verOficioAceptacion(){
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Perame",
        "Ahorita queda");
    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubMenuPracticantes.fxml", 
        "Menu de Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }
}
