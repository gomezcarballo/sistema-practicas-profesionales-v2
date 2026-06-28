/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.logicadenegocio.gestores.GestorEvidenciasPracticas;
import spp.presentacion.controladores.documentos.ControladorDocumentos;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorSubirEvidenciaPracticas {
    
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
    
    private void aplicarReglasDeNegocio() {
        
        try {
            
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idPracticante = sesionUsuario.getIdUsuario();

            GestorEvidenciasPracticas gestorEvidencias = new GestorEvidenciasPracticas();

            if (!gestorEvidencias.puedeSubirDocumentosSeguimiento(idPracticante)) {
                
                bloquearBotonesSeguimiento();
                
            }

            if (!gestorEvidencias.puedeSubirAutoevaluacion(idPracticante)) {
                
                btnAutoevaluacion.setDisable(true);
                
            }

        } catch (OperacionesDeDaoExcepcion e) {
            
            bloquearBotonesSeguimiento();
            btnAutoevaluacion.setDisable(true);
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo al validar permisos de evidencias", e);
            
        }
        
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
