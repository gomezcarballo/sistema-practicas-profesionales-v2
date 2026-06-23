/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.profesor;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.gestores.GestorEvaluacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.validadorsoloenteros.ValidadorEnteros;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

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
    private void validarCalificacionFinal(KeyEvent evento) {

        TextField campoTexto = (TextField) evento.getSource();
        ValidadorEnteros.validarSoloNumeros(campoTexto);
     
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
            
            try {
                
                GestorEvaluacion gestor = new GestorEvaluacion();

                if (gestor.evaluacionYaExiste(practicanteSeleccionado)) {
                    
                    VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Ya evaluado", 
                            "Este practicante ya cuenta con una evaluación para este documento.");
                            
                } else {
                    
                    Evaluacion nuevaEvaluacion = crearEvaluacion();
                    
                    if (nuevaEvaluacion != null) {
                        procesarEvaluacion(nuevaEvaluacion, gestor, evento);
                    } else {
                        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Formato incorrecto", 
                        "La calificación ingresada no es un número válido.");
                    }
                    
                }

            } catch (OperacionesDeDaoExcepcion e) {
                
                mostrarMensajeErrorRegistro("Error de base de datos al verificar o registrar la evaluación.");
                
            }
            
        } else {
            
            mostrarMensajeCamposFaltantes();
            
        }
        
    }

    private Evaluacion crearEvaluacion() {
        
        Evaluacion evaluacion = new Evaluacion();
        
        try {
            
            Double calificacionFinal = Double.valueOf(txtCalificacionFinal.getText().trim());
            String observaciones = taObservaciones.getText().trim();
            
            evaluacion.setCalificacionFinal(calificacionFinal);
            evaluacion.setObservaciones(observaciones);
            evaluacion.setPracticante(practicanteSeleccionado);
            evaluacion.setNrc(practicanteSeleccionado.getNrcAsignado());
            
        } catch (NumberFormatException e) {
            
            evaluacion = null;
            
        }

        return evaluacion;
    }

    @FXML
    private boolean sonCamposValidos() {
        
        boolean sonCamposValidos = true;
        
        if (txtCalificacionFinal.getText().isBlank() || taObservaciones.getText().isBlank()) {
            
            sonCamposValidos = false;
        
        }
        
        return sonCamposValidos;
        
    }

    private void procesarEvaluacion(Evaluacion evaluacion, GestorEvaluacion gestor, ActionEvent evento) {
        
        try {
            
            List<String> errores = gestor.validarCamposEvaluacion(evaluacion);
            
            if (errores.isEmpty()) {
                
                ingresarEvaluacion(evaluacion, gestor, evento);
                
            } else {
                
                VentanaMensaje.mostrarVentanaErrores(errores);
                
            }
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            mostrarMensajeErrorRegistro(e.getMessage());
            
        }
        
    }

    private void ingresarEvaluacion(Evaluacion evaluacion, GestorEvaluacion gestor, ActionEvent evento) 
    throws OperacionesDeDaoExcepcion {
        
        gestor.ingresarEvaluacion(evaluacion);
        
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
        
        FXMLLoader cargadorListaDocumentos = CargadorVentana.cargarVentanaConControlador("/fxml/VistaListaDocumentos.fxml", 
        "Documentos del Practicante");
        
        if (cargadorListaDocumentos != null) {

            ControladorListaDocumentos controlador = cargadorListaDocumentos.getController();
            
            controlador.inicializarDatos(this.practicanteSeleccionado);
            
            CerradorVentana.cerrarVentana(evento);
            
        }
        
    }
}
