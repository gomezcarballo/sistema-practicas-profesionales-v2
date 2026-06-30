package spp.presentacion.controladores.coordinador;

import java.io.File;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.logicadenegocio.gestores.GestorDocumentosIniciales;
import spp.logicadenegocio.gestores.GestorNotificaciones;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

public class ControladorAprobacionDocumentosIniciales {

    @FXML 
    private Button btnVerDocumento;

    @FXML
    private Button btnAprobarDocumento;

    @FXML
    private Button btnRechazarDocumento;

    private TipoDocumento tipoDocumento;
    private Practicante practicanteSeleccionado;
    private GestorDocumentosIniciales gestor;
    private Documento documentoActual;

    public void configurarTipoDocumento(TipoDocumento tipoDocumento, Practicante practicanteSeleccionado){

        this.tipoDocumento = tipoDocumento;
        this.practicanteSeleccionado = practicanteSeleccionado;

        cargarDocumento(practicanteSeleccionado, tipoDocumento);

    }

    private Documento cargarDocumento(Practicante practicante, TipoDocumento tipo) {

        gestor = new GestorDocumentosIniciales();
        documentoActual = null;
        
        try {
        
            documentoActual = gestor.obtenerDocumentoInicial(practicante.getIdUsuario(), tipo);
            String estadoDocumentoActual = documentoActual.getEstadoDocumento();

            String estadoEsperado = "Aprobado";

            if(estadoDocumentoActual != null && estadoDocumentoActual.equals(estadoEsperado)){
                bloquearAccionesInterfaz();
                VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Documento aprobado",
                        "Este documento ya ha sido aprobado previamente. Solo puedes consultarlo.");
            }

            estadoEsperado = "Rechazado";

            if(estadoDocumentoActual != null && estadoDocumentoActual.equals(estadoEsperado)){
                bloquearAccionesInterfaz();
                VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Documento rechazado",
                        "Este documento ya ha sido rechazado previamente. Solo puedes consultarlo.");
            }

        } catch (OperacionesDeDaoExcepcion e) {
        
            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error al obtener documento", 
                e.getMessage());

            bloquearTodaLaInterfaz();

        }
            
        return documentoActual;

    }

    @FXML
    private void aprobarDocumento() {

        String estadoDocumentoActualizar = "Aprobado";

        try {

            if (actualizarEstadoDocumento(estadoDocumentoActualizar)) {

                VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Éxito",
                        "Documento se aprobado correctamente.");

                bloquearAccionesInterfaz();

            } else {

                VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error",
                        "No se pudo actualizar el Documento a estado aprobado.");
            }

        }catch (OperacionesDeDaoExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error al actualizar", e.getMessage());

        }

    }

    @FXML
    private void  rechazarDocumento(){

        String estadoDocumentoActualizar = "Rechazado";

        try{

            if(actualizarEstadoDocumento(estadoDocumentoActualizar)) {

                VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Éxito",
                        "Documento rechazado correctamente.");
                bloquearAccionesInterfaz();

                GestorNotificaciones gestorNotificaciones = new GestorNotificaciones();
                gestorNotificaciones.notificarPracticante(practicanteSeleccionado, tipoDocumento );

            }else {

                VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error",
                        "No se pudo rechazar el documento.");
            }

        }catch (OperacionesDeDaoExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error al actualizar", e.getMessage());
        }

    }

    private boolean actualizarEstadoDocumento(String estadoDocumentoActualizar) throws OperacionesDeDaoExcepcion{

        boolean esDocumentoActualizadoCorrectamente = false;
        if (documentoActual != null) {
            gestor = new GestorDocumentosIniciales();
            if (gestor.actualizarEstadoDocumento(documentoActual, estadoDocumentoActualizar)) {
                esDocumentoActualizadoCorrectamente = true;

            }
        }
        return  esDocumentoActualizadoCorrectamente;
    }

    @FXML
    private void verDocumento(){

        File archivo = new File(documentoActual.getRuta());

        if (documentoActual != null) {

            gestor = new GestorDocumentosIniciales();

            try {

                if (archivo.exists()) {

                    gestor.abrirDocumento(documentoActual);

                }else{

                    VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Error",
                            "Error al abrir el documento. La ruta no es valida.");

                }

            } catch (ProcesamientoSistemaExcepcion e) {

                VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error al abrir", e.getMessage());

            }

        }else{

            VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Sin documento",
                    "No hay documento cargado.");

        }
    }

    @FXML
    public void regresar(ActionEvent evento) {

        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador(
                "/fxml/VistaAsignacionExperienciaEducativa.fxml",
                "Asignación Experiencia Educativa"
        );

        if (cargador != null) {

            ControladorAsignacionExperienciaEducativa controlador = cargador.getController();
            controlador.cargarDatosDesdeBD(practicanteSeleccionado);

        }

        CerradorVentana.cerrarVentana(evento);
    }

    private void bloquearTodaLaInterfaz(){

        btnVerDocumento.setDisable(true);
        btnAprobarDocumento.setDisable(true);
        btnRechazarDocumento.setDisable(true);

    }

    private void bloquearAccionesInterfaz(){

        btnAprobarDocumento.setDisable(true);
        btnRechazarDocumento.setDisable(true);

    }

    /*
    @FXML
    private void rechazarDocumento() {

        gestor = new GestorDocumentosIniciales(); 
         
        boolean esArchivoEliminado = false;
        boolean esNotificacionEnviada = false;

        try{

            esArchivoEliminado = gestor.rechazarDocumento(documentoActual, practicanteSeleccionado) ;
            esNotificacionEnviada = gestor.notificarPracticante(practicanteSeleccionado, tipoDocumento);

        }catch(OperacionesDeDaoExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Error rechazar documento", 
            e.getMessage());

        }
        
        if(esArchivoEliminado){

            if(esNotificacionEnviada){

                VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Éxito", 
                        "Documento rechazado y eliminado.");
                 bloquearAccionesInterfaz();

            }else{
                
                VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Éxito", 
                        "Documento rechazado y eliminado.");

                VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Error notificar Practicante", 
                        "Error al notificar al practicante sobre sus documentos.");

            }

        }else{

            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error rechazar documento", 
            "Hubo un error al rechazar el documento. Intentelo más tarde.");

        }

    }
    */

}
