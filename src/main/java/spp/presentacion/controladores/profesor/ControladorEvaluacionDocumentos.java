/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.profesor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionEvaluacion;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorEvaluacionDocumentos {
    

    @FXML
    private TextField txtCalificacionFinal;

    @FXML
    private TextArea taObservaciones;

    private Documento documentoSeleccionado;
    
    private Practicante practicanteSeleccionado;

    @FXML
    public void initialize() {
        
    }

    public void inicializarDatos(Documento documento, Practicante practicante) {
        
        this.documentoSeleccionado = documento;
        this.practicanteSeleccionado = practicante;
        
    }

    @FXML
    private void abrirDocumento(ActionEvent evento) {
        
        if (documentoSeleccionado != null && documentoSeleccionado.getRuta() != null) {
            
            try {
                
                File archivoPdf = new File(documentoSeleccionado.getRuta());
                
                if (archivoPdf.exists() && Desktop.isDesktopSupported()) {
                    
                    Desktop.getDesktop().open(archivoPdf);
                    
                } else {
                
                    VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error de Archivo", 
                    "No se pudo encontrar el archivo en la ruta especificada.");
                    
                }
            } catch (IOException e) {
                
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error del Sistema", 
                "Ocurrió un problema al intentar abrir el documento.");
                
            }
            
        }
        
    }

    @FXML
    private void registrarEvaluacion(ActionEvent evento) {
        
        if (sonCamposValidos()) {
            
            Evaluacion nuevaEvaluacion = crearEvaluacion();
            procesarEvaluacion(nuevaEvaluacion, evento);
            
        } else {
            
            mostrarMensajeCamposFaltantes();
            
        }
        
    }

    private Evaluacion crearEvaluacion() {
        
        Double calificacionFinal = Double.parseDouble(txtCalificacionFinal.getText());
        String observaciones = taObservaciones.getText();
        
        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setCalificacionFinal(calificacionFinal);
        evaluacion.setObservaciones(observaciones);
        evaluacion.setPracticante(practicanteSeleccionado);

        return evaluacion;
    }

    @FXML
    private boolean sonCamposValidos() {
        
        boolean validos = true;
        
        if (txtCalificacionFinal.getText().isBlank() || taObservaciones.getText().isBlank()) {
            
            validos = false;
        
        }
        
        return validos;
    }

    private void procesarEvaluacion(Evaluacion evaluacion, ActionEvent evento) {
        
        try {
            
            ValidacionEvaluacion validacion = new ValidacionEvaluacion();
            ingresarEvaluacion(evaluacion, validacion, evento);
            
        } catch (ReglaDeNegocioExcepcion e) {
            
            mostrarMensajeErrorRegistro(e.getMessage());
            
        }
        
    }

    private void ingresarEvaluacion(Evaluacion evaluacion, ValidacionEvaluacion validacion, ActionEvent evento) 
    throws ReglaDeNegocioExcepcion {
        
        validacion.ingresarEvaluacion(evaluacion);
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Evaluación Exitosa", 
        "La evaluación se ha registrado correctamente.");
        
        cancelar(evento);
        
    }

    private void mostrarMensajeErrorRegistro(String mensaje) {
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al Evaluar", mensaje);
        
    }

    private void mostrarMensajeCamposFaltantes() {
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
        "Faltan datos por agregar. Por favor ingrese la calificación y observaciones.");
        
    }

    @FXML
    public void cancelar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaListaDocumentos.fxml", "Documentos del Practicante");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
