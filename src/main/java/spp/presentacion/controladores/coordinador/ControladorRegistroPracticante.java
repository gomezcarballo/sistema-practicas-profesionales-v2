/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.validacionesInsercion.ValidacionPracticante;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorRegistroPracticante {
    
    @FXML
    private TextField ingresaNombre;
    
    @FXML
    private TextField ingresaMatricula;
    
    @FXML
    private TextField ingresaApellidoPaterno;
    
    @FXML
    private TextField ingresaApellidoMaterno;
    
    @FXML
    private TextField ingresaCorreo;
    
    @FXML
    private ComboBox<String> opcionesGenero;
    
    @FXML
    private DatePicker calendarioFechaNacimiento;
    
    @FXML
    private ComboBox<String> opcionesLenguaIndigena;
             
    @FXML
    private Button botonCancelar;
    
    @FXML
    public void initialize() {
        
        opcionesGenero.setItems(FXCollections.observableArrayList("Masculino", "Femenino", "Prefiero no decirlo"));
        opcionesLenguaIndigena.setItems(FXCollections.observableArrayList("Sí", "No"));
        calendarioFechaNacimiento.setEditable(false);
        
    }
    
    @FXML
    private void leerDatosDePracticante(){
        
        if(sonCamposValidos()){
            
            String nombre = ingresaNombre.getText();
            String apellidoPaterno = ingresaApellidoPaterno.getText();
            String apellidoMaterno = ingresaApellidoMaterno.getText();
            String correoInstitucional = ingresaCorreo.getText();
            String matricula = ingresaMatricula.getText();
            String genero = opcionesGenero.getValue();
            LocalDate fechaNacimiento = calendarioFechaNacimiento.getValue();
            String hablaLenguaIndigena = opcionesLenguaIndigena.getValue();
            boolean lenguaIndigena = "sí".equalsIgnoreCase(hablaLenguaIndigena);


            Practicante practicante = new Practicante();
            practicante.setNombre(nombre);
            practicante.setApellidoPaterno(apellidoPaterno);      
            practicante.setApellidoMaterno(apellidoMaterno);
            practicante.setCorreoInstitucional(correoInstitucional);
            practicante.setMatricula(matricula);
            practicante.setGenero(genero);
            practicante.setFechaNacimiento(fechaNacimiento);
            practicante.setHablaLenguaIndigena(lenguaIndigena);
            
            registrarPracticante(practicante);
        
        }else {
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
        
    }
        
    
    @FXML
    private boolean sonCamposValidos(){

        boolean sonCamposValidos = true; 

        if(ingresaNombre.getText().isBlank() ||  ingresaApellidoPaterno.getText().isBlank() || 
            ingresaMatricula.getText().isBlank() ||calendarioFechaNacimiento.getValue() == null ||
            ingresaCorreo.getText().isBlank() || opcionesLenguaIndigena.getValue() == null){
            
            sonCamposValidos = false; 

        }
        
        if(ingresaApellidoMaterno.getText().isBlank()){
            
            ingresaApellidoMaterno.setText(null);
            
        }
        
        return sonCamposValidos; 
        
    }
   
   @FXML 
   private void registrarPracticante(Practicante practicante){
       
        try{
            
            ValidacionPracticante validacion = new ValidacionPracticante();
            validacion.ingresarPracticante(practicante);
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
            "Practicante registrado correctamente");
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
            e.getMessage());
            
        }
        
   }
   
   @FXML
    public void cancelar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
