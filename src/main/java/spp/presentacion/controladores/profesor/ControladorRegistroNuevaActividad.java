/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.profesor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.gestores.GestorActividades;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorRegistroNuevaActividad {
    
    @FXML
    private TextField txtTitulo;
    
    @FXML
    private TextArea taDescripcion;
    
    @FXML
    private DatePicker dpFechaLimite;
    
    @FXML
    private ComboBox<String> cbHoraLimite;
    
    @FXML
    private ComboBox<String> cbMinutosLimite;
    
    @FXML
    public void initialize(){
        
        dpFechaLimite.setEditable(false);
        
        int totalHorasDia = 24;
        String[] intervalosMinutos = {"00", "15", "30", "45"};
        
        for (int i = 0; i < totalHorasDia; i++) {
            cbHoraLimite.getItems().add(String.format("%02d", i));
        }

        cbMinutosLimite.getItems().addAll(intervalosMinutos);
        
    }
    
    private LocalDateTime construirFechaLimite(){
        
        LocalDate fecha = dpFechaLimite.getValue();

        int hora = Integer.parseInt(cbHoraLimite.getValue());
        int minuto = Integer.parseInt(cbMinutosLimite.getValue());

        LocalDateTime fechaHora = LocalDateTime.of(fecha, LocalTime.of(hora, minuto));
        
        return fechaHora;
    }
    
    @FXML
    private void leerDatosDeActividad(ActionEvent evento){
        
        if(sonCamposValidos()){
            
            Actividad nuevaActividad = crearActividad();
            
            registrarActividad(nuevaActividad, evento);
            
        }else{
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
                
    }
    
    private Actividad crearActividad(){
        
        String titulo = txtTitulo.getText();
        String descripcion = taDescripcion.getText();
        LocalDateTime fechaLimite = construirFechaLimite();
        
        Actividad actividad = new Actividad();

        actividad.setTitulo(titulo);
        actividad.setDescripcion(descripcion);
        actividad.setFechaLimite(fechaLimite);

        return actividad;
        
    }
    
    @FXML
    private boolean sonCamposValidos(){
        
        boolean sonCamposValidos = true;
        
        if(txtTitulo.getText().isBlank() || taDescripcion.getText().isBlank()||
           dpFechaLimite.getValue() == null || cbHoraLimite.getValue() == null || cbMinutosLimite.getValue() == null){
        
            sonCamposValidos = false;
        
        }
        
        return sonCamposValidos;
    }
    
    @FXML
    private void registrarActividad(Actividad actividad, ActionEvent evento) {
        
        try {
            GestorActividades gestor = new GestorActividades();
           
            if (validarActividad(actividad)) {
                gestor.ingresarActividad(actividad);

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
                "Actividad registrada correctamente");
                
                regresar(evento);
            }
        } catch (OperacionesDeDaoExcepcion e) {
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", e.getMessage());
        }
        
    }

    private boolean validarActividad(Actividad actividad) {
        
        boolean actividadValida = false;
        try {
            
            GestorActividades gestor = new GestorActividades();
            List<String> listaValidaciones = gestor.validarCamposDeActividad(actividad);

            if (listaValidaciones.isEmpty()) {
                
                actividadValida = true;
                
            } else {
                
                VentanaMensaje.mostrarVentanaErrores(listaValidaciones);
            }
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error de validación", e.getMessage());
        
        }
        return actividadValida;
    }

    @FXML
    public void regresar(ActionEvent evento) {
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalProfesor.fxml", "Menu para Profesores");
        CerradorVentana.cerrarVentana(evento);
    }
    
}
