/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.validaciones.validacionesInsercion.ValidacionPracticante;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorRegistroPracticante {
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtMatricula;
    
    @FXML
    private TextField txtApellidoPaterno;
    
    @FXML
    private TextField txtApellidoMaterno;
    
    @FXML
    private TextField txtCorreo;
    
    @FXML
    private ComboBox<String> cbOpcionesGenero;
    
    @FXML
    private DatePicker dpFechaNacimiento;
    
    @FXML
    private ComboBox<String> cbOpcionesLenguaIndigena;
             
    @FXML
    public void initialize() {
        
        cbOpcionesGenero.setItems(FXCollections.observableArrayList("Masculino", "Femenino", "Prefiero no decirlo"));
        cbOpcionesLenguaIndigena.setItems(FXCollections.observableArrayList("Sí", "No"));
        dpFechaNacimiento.setEditable(false);
        
    }
    
    @FXML
    private void leerDatosDePracticante(ActionEvent evento){
        
        if(sonCamposValidos()){
            
            String nombre = txtNombre.getText();
            String apellidoPaterno = txtApellidoPaterno.getText();
            String apellidoMaterno = txtApellidoMaterno.getText();
            String correoInstitucional = txtCorreo.getText();
            String matricula = txtMatricula.getText();
            String genero = cbOpcionesGenero.getValue();
            LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
            String hablaLenguaIndigena = cbOpcionesLenguaIndigena.getValue();
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
            
            registrarPracticante(practicante, evento);
        
        }else {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
        
    }
        
    
    @FXML
    private boolean sonCamposValidos(){

        boolean sonCamposValidos = true; 

        if(txtNombre.getText().isBlank() ||  txtApellidoPaterno.getText().isBlank() || 
            txtMatricula.getText().isBlank() ||dpFechaNacimiento.getValue() == null ||
            txtCorreo.getText().isBlank() || cbOpcionesLenguaIndigena.getValue() == null){
            
            sonCamposValidos = false; 

        }
        
        if(txtApellidoMaterno.getText().isBlank()){
            
            txtApellidoMaterno.setText("");
            
        }
        
        return sonCamposValidos; 
        
    }
   
    @FXML 
    private void registrarPracticante(Practicante practicante, ActionEvent evento){
       
        try{
            
            ValidacionPracticante validacion = new ValidacionPracticante();
            validacion.ingresarPracticante(practicante);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
            "Practicante registrado correctamente");
            
            CargadorVentana.cargarVentana("/fxml/VistaSubMenuPracticantes.fxml", "Menu de Practicantes");
            CerradorVentana.cerrarVentana(evento);
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
            e.getMessage());

        }
        
    }
   
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaSubMenuPracticantes.fxml", "Menu de Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
