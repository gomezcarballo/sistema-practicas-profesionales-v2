package spp.presentacion.controladores.practicante;

import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.logicadenegocio.gestores.GestorEvidenciasPracticas;
import spp.presentacion.controladores.documentos.ControladorDocumentos;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorSubirEvidenciaPracticas {
    
    @FXML 
    private Button btnHorario;
    
    @FXML 
    private Button btnPlanActividades;
    
    @FXML 
    private Button btnOficioAceptacion;

    @FXML 
    private Button btnReporteParcial;
    
    @FXML 
    private Button btnReporteMensual;
    
    @FXML 
    private Button btnReporteFinal;
    
    @FXML 
    private Button btnBitacoraPSP;
    
    @FXML 
    private Button btnAutoevaluacion;
    
    public void initialize() {
        
        aplicarReglasDeNegocio();
        
    }

    public void refrescarPermisos() {

        aplicarReglasDeNegocio();

    }
    
    private void aplicarReglasDeNegocio() {
        
        try {
            
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idPracticante = sesionUsuario.getIdUsuario();
            GestorEvidenciasPracticas gestorEvidencias = new GestorEvidenciasPracticas();

            validarDocumentosIniciales(idPracticante, gestorEvidencias);
            validarDocumentosSeguimiento(idPracticante, gestorEvidencias);
            validarEvaluacion(idPracticante, gestorEvidencias);

        } catch (OperacionesDeDaoExcepcion e) {
            
            bloquearTodaLaInterfaz();
            RegistroErrores.registrarError(Level.SEVERE, "Fallo al validar permisos de evidencias", e);
            VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Error verificar documentación", e.getMessage());
                    
        }
        
    }

    private void validarDocumentosIniciales(int idPracticante, GestorEvidenciasPracticas gestorEvidencias) 
    throws OperacionesDeDaoExcepcion {
        
        if (gestorEvidencias.yaSubioHorario(idPracticante)) {
            btnHorario.setDisable(true);
        }
        
        if (gestorEvidencias.yaSubioPlanActividades(idPracticante)) {
            btnPlanActividades.setDisable(true);
        }
        
        if (gestorEvidencias.yaSubioOficioAceptacion(idPracticante)) {
            btnOficioAceptacion.setDisable(true);
        }
        
    }

    private void validarDocumentosSeguimiento(int idPracticante, GestorEvidenciasPracticas gestorEvidencias) 
    throws OperacionesDeDaoExcepcion {
        
        if (!gestorEvidencias.puedeSubirDocumentosSeguimiento(idPracticante)) {
            
            bloquearBotonesSeguimiento();
            
        } else {
            
            if (gestorEvidencias.yaSubioLimitesParciales(idPracticante)) {
                btnReporteParcial.setDisable(true);
            }
            
            if (gestorEvidencias.yaSubioLimitesMensuales(idPracticante)) {
                btnReporteMensual.setDisable(true);
            }
            
            if (!gestorEvidencias.puedeSubirReporteFinal(idPracticante) || 
                 gestorEvidencias.yaSubioReporteFinal(idPracticante)) {
                 
                btnReporteFinal.setDisable(true);
                
            }
            
            if (gestorEvidencias.yaSubioBitacoraPSP(idPracticante)) {
                btnBitacoraPSP.setDisable(true);
            }
            
        }
        
    }

    private void validarEvaluacion(int idPracticante, GestorEvidenciasPracticas gestorEvidencias) 
    throws OperacionesDeDaoExcepcion {
        
        if (!gestorEvidencias.puedeSubirAutoevaluacion(idPracticante) || 
             gestorEvidencias.yaSubioAutoevaluacion(idPracticante)) {
            
            btnAutoevaluacion.setDisable(true);
            
        }
        
    }

    private void bloquearTodaLaInterfaz() {
        
        btnHorario.setDisable(true);
        btnPlanActividades.setDisable(true);
        btnOficioAceptacion.setDisable(true);
        bloquearBotonesSeguimiento();
        btnAutoevaluacion.setDisable(true);
        
    }

    private void bloquearBotonesSeguimiento() {
        
        btnReporteParcial.setDisable(true);
        btnReporteMensual.setDisable(true);
        btnReporteFinal.setDisable(true);
        btnBitacoraPSP.setDisable(true);
        
    }
    
    private void abrirDocumento(TipoDocumento tipoDocumento){
        
       FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubidaDocumentos.fxml",
        "Subir Documento");
        
        if(cargador != null){
            
            ControladorDocumentos controlador = cargador.getController();
            controlador.configurarTipoDocumento(tipoDocumento);
            controlador.setControladorEvidencia(this);
        } 
        
    }
    
    @FXML
    private void agregarHorario() {
        abrirDocumento(TipoDocumento.HORARIO);  
    }

    @FXML
    private void agregarPlanActividades() {
        abrirDocumento(TipoDocumento.PLAN_ACTIVIDADES);
    }

    @FXML
    private void agregarOficioAceptacion() {
        abrirDocumento(TipoDocumento.OFICIO_ACEPTACION);
    }

    @FXML 
    private void agregarReporteParcial(){
        abrirDocumento(TipoDocumento.REPORTE_PARCIAL);
    }

    @FXML
    private void agregarReporteMensual() {
        abrirDocumento(TipoDocumento.REPORTE_MENSUAL);
    }

    @FXML
    private void agregarReporteFinal() {
        abrirDocumento(TipoDocumento.REPORTE_FINAL);
    }

    @FXML
    private void agregarBitacoraPSP() {
        abrirDocumento(TipoDocumento.BITACORA_PSP);
    }


    @FXML
    private void agregarAutoevaluacion() {
        abrirDocumento(TipoDocumento.AUTOEVALUACION);
    }

    
    @FXML
    private void cancelar(ActionEvent evento){
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
        "Menú Principal para Practicante");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
