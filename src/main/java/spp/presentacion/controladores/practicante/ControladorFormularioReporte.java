/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import java.time.LocalDate;
import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.logicadenegocio.validacionesDocumentos.ValidacionesReporteParcial;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanademensajes.VentanaMensaje;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorFormularioReporte {
      
    @FXML
    private TextField txtPeriodoEscolar;
    
    @FXML
    private TextField txtHorasCubiertas;
    
    @FXML
    private TextField txtTiempoPlaneado;
    
    @FXML
    private TextField txtTiempoReal; 
    
    @FXML 
    private DatePicker dpFechaInicio;
    
    @FXML 
    private DatePicker dpFechaTermino;
    
    @FXML
    private ToggleGroup tgTipoReportes;
    
    @FXML
    public void initialize() {
        
        dpFechaInicio.setEditable(false);
        dpFechaTermino.setEditable(false);
        
        UnaryOperator<TextFormatter.Change> filtro = cambioEntero -> {
            if (cambioEntero.getText().matches("[0-9]*")) {
                return cambioEntero;
            }
            return null;
        };

        txtHorasCubiertas.setTextFormatter(new TextFormatter<>(filtro));
        
    }
    
    @FXML
    private void leerDatosDeReporte(){
        
        if(camposValidos()){
            
            String periodoEscolar = txtPeriodoEscolar.getText();
            int horasCubiertas = Integer.parseInt(txtHorasCubiertas.getText());
            int tiempoPlaneado = Integer.parseInt(txtTiempoPlaneado.getText());
            int tiempoReal = Integer.parseInt(txtTiempoReal.getText());
            LocalDate fechaInicio = dpFechaInicio.getValue();
            LocalDate fechaTermino = dpFechaTermino.getValue();
            RadioButton reporteSelecionado = (RadioButton) tgTipoReportes.getSelectedToggle();
            String tipoReporte = reporteSelecionado.getText();
            
            ReporteParcial reporteParcial = new ReporteParcial();
            reporteParcial.setPeriodoEscolar(periodoEscolar);
            reporteParcial.setHorasCubiertas(horasCubiertas);
            reporteParcial.setTiempoPlaneado(tiempoPlaneado);
            reporteParcial.setTiempoReal(tiempoReal);
            reporteParcial.setFechaInicio(fechaInicio);
            reporteParcial.setFechaTermino(fechaTermino);
            reporteParcial.setTipoReporte(tipoReporte);
            
            generarReporte(reporteParcial);
            
        }else {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
        
    }
    
    @FXML
    private boolean camposValidos(){

        boolean sonCamposValidos = true; 

        if(txtPeriodoEscolar.getText().isBlank() || txtHorasCubiertas.getText().isBlank() ||
           dpFechaInicio.getValue() == null || dpFechaTermino.getValue() == null || 
           txtTiempoPlaneado.getText().isBlank() || txtTiempoReal.getText().isBlank() || 
            tgTipoReportes.getSelectedToggle() == null ){
            
            sonCamposValidos = false; 

        }
       
        return sonCamposValidos; 
        
    }
    
    @FXML 
    private void generarReporte(ReporteParcial reporteParcial){
        
        try{
            
            ValidacionesReporteParcial validacion = new ValidacionesReporteParcial();
            validacion.generarReporteParcial(reporteParcial);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Reporte generado", 
            "Reporte generado correctamente.");
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Generar reporte fallido", 
            e.getMessage());
            
        }
        
    }
    
   @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
}
