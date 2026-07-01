package spp.presentacion.controladores.coordinador;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;

import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.logicadenegocio.gestores.GestorDocumentosIniciales;
import spp.logicadenegocio.gestores.GestorExperienciaEducativa;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;


public class ControladorAsignacionExperienciaEducativa extends ControladorBaseAsignacionEE{

    @FXML
    private Button btnAsignar;

    private Practicante practicanteSeleccionado;

    private GestorExperienciaEducativa gestorExperiencia;


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

    @FXML 
    void asignarExperienciaAPracticante(ActionEvent evento){

        if(tieneDocumentosInicialesAprobados()){

            ExperienciaEducativa experiencia = obtenerExperienciaSeleccionada();
            boolean asignacionExitosa = false;

            if(experiencia != null){

                gestorExperiencia = new GestorExperienciaEducativa();

                try{

                    asignacionExitosa = gestorExperiencia.asignarPracticanteAExperiencia(practicanteSeleccionado, experiencia);

                }catch(OperacionesDeDaoExcepcion e){

                    VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al asignar la EE. ",
                            e.getMessage());

                }

                if(asignacionExitosa){

                    VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Asignación exitosa",
                            "Se a realizado la asignación correctamente.");
                    regresar(evento);

                }else{

                    VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Error al asignar",
                            "Intente de nuevo más tarde. Hubo un error al asignar la EE.");

                }
            }

        }else {
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "¡Advertencia!",
                    "Los documentos iniciales deben estar validados, para asignar al Practicante.");
            btnAsignar.setDisable(true);
        }
    }

    private boolean tieneDocumentosInicialesAprobados(){

        boolean documentosInicialesAprobados = true;

        GestorDocumentosIniciales gestorDocumentosIniciales = new GestorDocumentosIniciales();
        int idPracticante = practicanteSeleccionado.getIdUsuario();

        try{

            documentosInicialesAprobados = gestorDocumentosIniciales.verificarDocumentosInicialesAprobados(idPracticante);

        }catch (OperacionesDeDaoExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al verifica documentos", e.getMessage());

        }
        return  documentosInicialesAprobados;

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
