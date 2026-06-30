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
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.enums.TipoDocumento;
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

    private GestorExperienciaEducativa gestorExperiencia;

    @FXML
    public void initialize() {

        tblListaExperiencias.setPlaceholder(new Label("No hay experiencias educativas registradas."));

        tblListaExperiencias.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);

        configurarColumnas();
        
    }

    public void cargarDatosDesdeBD(Practicante practicante) {

        this.practicanteSeleccionado = practicante;

        gestorExperiencia = new GestorExperienciaEducativa();

        try {

            List<ExperienciaEducativa> lista = gestorExperiencia.buscarExperienciasEducativasActivas();
            cargarExperienciasEducativas(lista);

        } catch (OperacionesDeDaoExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al cargar experiencias", 
                e.getMessage());

        }

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

        //
        ExperienciaEducativa experiencia = obtenerExperienciaSeleccionada();
        boolean asignacionExitosa = false;
         
        if(experiencia != null){

            gestorExperiencia = new GestorExperienciaEducativa();

            try{

                asignacionExitosa = gestorExperiencia.asignarPracticanteAEE(practicanteSeleccionado, experiencia);

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
/*
    private boolean sonDocumentosAprobados(){

        boolean sonDocumentosAprobados = false;
        GestorDocumentosIniciales gestorDocumentosIniciales= new GestorDocumentosIniciales();

    }
*/
    @FXML
    private void verDocumento(TipoDocumento tipoDocumento){

        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaAprobacionDocumentosIniciales.fxml",
        "Aprobación Documentos Iniciales");
        
        if(cargador != null){
            
            ControladorAprobacionDocumentosIniciales controlador = cargador.getController();
            controlador.configurarTipoDocumento(tipoDocumento, practicanteSeleccionado);
        } 
    }

    @FXML
    private void verHorario(ActionEvent evento){

        abrirAprobacionDocumento(TipoDocumento.HORARIO, evento);
    }

    @FXML
    private  void  verPlanActividades(ActionEvent evento){
        abrirAprobacionDocumento(TipoDocumento.PLAN_ACTIVIDADES, evento);
    }

    @FXML
    private void  verOficioAceptacion(ActionEvent evento){
        abrirAprobacionDocumento(TipoDocumento.OFICIO_ACEPTACION, evento);
    }

    private void abrirAprobacionDocumento(TipoDocumento tipo, ActionEvent evento) {

        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador(
            "/fxml/VistaAprobacionDocumentosIniciales.fxml",
            "Aprobación Documentos Iniciales"
        );

        if (cargador != null) {

            ControladorAprobacionDocumentosIniciales controlador = cargador.getController();
            controlador.configurarTipoDocumento(tipo, practicanteSeleccionado);
            CerradorVentana.cerrarVentana(evento);

        }
    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaListaPracticantesParaAsignacionEE.fxml", 
        "Lista Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }
}
