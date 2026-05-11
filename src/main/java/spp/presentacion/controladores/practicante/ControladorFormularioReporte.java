/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import java.time.LocalDate;
import java.util.function.UnaryOperator;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.DatePicker;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import javafx.scene.control.ToggleGroup;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.logicadenegocio.validacionesReportes.ValidacionesReporteParcial;
import spp.utilerias.ventanademensajes.VentanaMensaje;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorFormularioReporte {
    
    @FXML
    private TextField ingresaNRC; 
     
    @FXML
    private TextField ingresaPeriodoEscolar;
    
    @FXML
    private TextField ingresaHorasCubiertas;
    
    @FXML
    private TextField ingresaTiempoPlaneado;
    
    @FXML
    private TextField ingresaTiempoReal; 
    
    @FXML 
    private DatePicker calendarioFechaInicio;
    
    @FXML 
    private DatePicker calendarioFechaTermino;
    
    @FXML 
    private RadioButton botonRadioReporteParcial;
    
    @FXML
    private RadioButton botonRadioReporteMensual;
    
    @FXML
    private ToggleGroup tipoReportes;
    
    @FXML
    public void initialize() {
        
        calendarioFechaInicio.setEditable(false);
        calendarioFechaTermino.setEditable(false);
        
        UnaryOperator<TextFormatter.Change> filtro = cambioEntero -> {
            if (cambioEntero.getText().matches("[0-9]*")) {
                return cambioEntero;
            }
            return null;
        };

        ingresaHorasCubiertas.setTextFormatter(new TextFormatter<>(filtro));
        
    }
    
    @FXML
    private void leerDatosDeReporte(){
        
        if(camposValidos()){
            
            String nrc = ingresaNRC.getText();
            String periodoEscolar = ingresaPeriodoEscolar.getText();
            int horasCubiertas = Integer.parseInt(ingresaHorasCubiertas.getText());
            int tiempoPlaneado = Integer.parseInt(ingresaTiempoPlaneado.getText());
            int tiempoReal = Integer.parseInt(ingresaTiempoReal.getText());
            LocalDate fechaInicio = calendarioFechaInicio.getValue();
            LocalDate fechaTermino = calendarioFechaTermino.getValue();
            RadioButton reporteSelecionado = (RadioButton) tipoReportes.getSelectedToggle();
            String tipoReporte = reporteSelecionado.getText();
            
            ReporteParcial reporteParcial = new ReporteParcial();
            reporteParcial.setNrc(nrc);
            reporteParcial.setPeriodoEscolar(periodoEscolar);
            reporteParcial.setHorasCubiertas(horasCubiertas);
            reporteParcial.setTiempoPlaneado(tiempoPlaneado);
            reporteParcial.setTiempoReal(tiempoReal);
            reporteParcial.setFechaInicio(fechaInicio);
            reporteParcial.setFechaTermino(fechaTermino);
            reporteParcial.setTipoReporte(tipoReporte);
            
            generarReporte(reporteParcial);
            
        }else {
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
        
    }
    
    @FXML
    private boolean camposValidos(){

        boolean sonCamposValidos = true; 

        if(ingresaNRC.getText().isBlank() ||  ingresaPeriodoEscolar.getText().isBlank() || 
            ingresaHorasCubiertas.getText().isBlank() ||calendarioFechaInicio.getValue() == null || 
            calendarioFechaTermino.getValue() == null || ingresaTiempoPlaneado.getText().isBlank() || 
            ingresaTiempoReal.getText().isBlank() || tipoReportes.getSelectedToggle() == null){
            
            sonCamposValidos = false; 

        }
        
        return sonCamposValidos; 
        
    }
    
    @FXML 
    private void generarReporte(ReporteParcial reporteParcial){
        
        
        try{
            
            ValidacionesReporteParcial validacion = new ValidacionesReporteParcial();
            validacion.generarReporteParcial(reporteParcial);
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Reporte generado", 
            "Reporte generado correctamente.");
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Generar reporte fallido", 
            e.getMessage());
            
        }
        
    }
}
