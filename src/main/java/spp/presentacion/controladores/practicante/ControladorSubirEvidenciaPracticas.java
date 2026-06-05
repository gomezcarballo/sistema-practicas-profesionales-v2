/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.presentacion.documentos.ControladorDocumentos;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorSubirEvidenciaPracticas {
    
    private void abrirDocumento(TipoDocumento tipoDocumento){
        
       FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubidaDocumentos.fxml",
        "Subir Documento");
        
        if(cargador != null){
            
            ControladorDocumentos controlador = cargador.getController();
            controlador.configurarTipoDocumento(tipoDocumento);
            
        } 
        
    }
    
    @FXML 
    private void agregarReporteParcial(){
        
        abrirDocumento(TipoDocumento.REPORTE_PARCIAL);
        
    }
    
    @FXML
    private void agregarHorario() {

        abrirDocumento(TipoDocumento.HORARIO);  
        
    }
    
    
    @FXML
    private void agregarActividad() {
        
        abrirDocumento(TipoDocumento.ACTIVIDAD);
    }
    
    @FXML
    private void agregarReporteMensual() {
        
        abrirDocumento(TipoDocumento.REPORTE_MENSUAL);
        
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
    private void agregarPlanActividades() {
        
        abrirDocumento(TipoDocumento.AUTOEVALUACION);
        
    }
    
    @FXML
    private void cancelar(ActionEvent evento){
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
        "Menú Principal para Practicante");
        CerradorVentana.cerrarVentana(evento);
        
    }
}
