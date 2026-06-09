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
import spp.logicadenegocio.gestores.GestorPracticantes;
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
    private ComboBox<String> cbOpcionesNrc;
             
    @FXML
    public void initialize() {
        
        cbOpcionesGenero.setItems(FXCollections.observableArrayList("Masculino", "Femenino", "Prefiero no decirlo"));
        cbOpcionesLenguaIndigena.setItems(FXCollections.observableArrayList("Sí", "No"));
        cbOpcionesNrc.setItems(FXCollections.observableArrayList("17141", "12345"));
        dpFechaNacimiento.setEditable(false);
        
    }
    
    @FXML
    private void leerDatosDePracticante(ActionEvent evento){
        
        if(sonCamposValidos()){
            
            Practicante practicante = crearPracticante();
            
            registrarPracticante(practicante, evento);
        
        }else {
            
            mostrarMensajeCamposFaltantes();
 
        }
        
    }
    
    private Practicante crearPracticante(){
        
        String nombre = txtNombre.getText();
        String apellidoPaterno = txtApellidoPaterno.getText();
        String apellidoMaterno = txtApellidoMaterno.getText();
        String correoInstitucional = txtCorreo.getText();
        String matricula = txtMatricula.getText();
        String genero = cbOpcionesGenero.getValue();
        LocalDate fechaNacimiento = dpFechaNacimiento.getValue();
        String hablaLenguaIndigena = cbOpcionesLenguaIndigena.getValue();
        String nrcAsignado = cbOpcionesNrc.getValue();
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
        practicante.setNrcAsignado(nrcAsignado);
        
        return practicante;
        
    }
        
    
    @FXML
    private boolean sonCamposValidos(){

        boolean sonCamposValidos = true; 

        if(txtNombre.getText().isBlank() ||  txtApellidoPaterno.getText().isBlank() || 
            txtMatricula.getText().isBlank() ||dpFechaNacimiento.getValue() == null ||
            txtCorreo.getText().isBlank() || cbOpcionesLenguaIndigena.getValue() == null ||
            cbOpcionesNrc.getValue() == null){
            
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
            
            GestorPracticantes gestor = new GestorPracticantes();
            
            ingresarPracticante(practicante, gestor, evento);
            
        }catch(ReglaDeNegocioExcepcion e){
            
            mostrarMensajeErrorRegistro(e.getMessage());

        }
        
    }
    
    private void ingresarPracticante(Practicante practicante, GestorPracticantes gestor, ActionEvent evento)
    throws ReglaDeNegocioExcepcion{
        
        gestor.ingresarPracticante(practicante);
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
        "Practicante registrado correctamente");
            
        regresar(evento);
        
    }
    
    private void mostrarMensajeErrorRegistro(String mensaje){
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
        mensaje);
        
    }
    
    private void mostrarMensajeCamposFaltantes(){
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
        "Faltan datos por agregar. Por favor ingreselos");
            
        
    }
   
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaSubMenuPracticantes.fxml", "Menu de Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
