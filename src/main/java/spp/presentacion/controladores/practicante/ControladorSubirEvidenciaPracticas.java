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
    
    @FXML 
    private void agregarReporteParcial(){
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubidaDocumentos.fxml",
        "Subir Documento");
        
        if(cargador != null){
            
            ControladorDocumentos controlador = cargador.getController();
            controlador.configurarTipoDocumento(TipoDocumento.REPORTE_PARCIAL);
            
        }
    }
    
    @FXML
    private void agregarHorario() {
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubidaDocumentos.fxml",
        "Subir Documento");
        
        if(cargador != null){
            
            ControladorDocumentos controlador = cargador.getController();
            controlador.configurarTipoDocumento(TipoDocumento.HORARIO);
            
        }
    }
    
    
    @FXML
    private void agregarActividad() {
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubidaDocumentos.fxml",
        "Subir Documento");
        
        if(cargador != null){
            
            ControladorDocumentos controlador = cargador.getController();
            controlador.configurarTipoDocumento(TipoDocumento.ACTIVIDAD);
            
        }
    }
    
    @FXML
    private void agregarReporteMensual() {
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubidaDocumentos.fxml",
        "Subir Documento");
        
        if(cargador != null){
            
            ControladorDocumentos controlador = cargador.getController();
            controlador.configurarTipoDocumento(TipoDocumento.REPORTE_MENSUAL);
            
        }
    }
    
    @FXML
    private void agregarBitacoraPSP() {
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubidaDocumentos.fxml",
        "Subir Documento");
        
        if(cargador != null){
            
            ControladorDocumentos controlador = cargador.getController();
            controlador.configurarTipoDocumento(TipoDocumento.BITACORA_PSP);
            
        }
    }
    
    @FXML
    private void agregarAutoevaluacion() {
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubidaDocumentos.fxml",
        "Subir Documento");
        
        if(cargador != null){
            
            ControladorDocumentos controlador = cargador.getController();
            controlador.configurarTipoDocumento(TipoDocumento.AUTOEVALUACION);
            
        }
        
    }
    
    @FXML
    private void agregarPlanActividades() {
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubidaDocumentos.fxml",
        "Subir Documento");
        
        if(cargador != null){
            
            ControladorDocumentos controlador = cargador.getController();
            controlador.configurarTipoDocumento(TipoDocumento.PLAN_ACTIVIDADES);
            
        }
        
    }
    
    @FXML
    private void cancelar(ActionEvent evento){
        CerradorVentana.cerrarVentana(evento);
    }
}
