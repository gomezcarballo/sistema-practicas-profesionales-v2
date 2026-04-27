/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.time.LocalDate;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.validacionesInsercion.ValidacionPracticante;

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
    private ComboBox<String> opcionesGenero;
    
    @FXML
    private DatePicker calendarioFechaNacimiento;
    
    @FXML
    private ComboBox<String> opcionesLenguaIndigena;
             
    @FXML
    private Button botonCancelar;
    
    @FXML
    public void initialize() {
        
        opcionesGenero.setItems(FXCollections.observableArrayList("Masculino", "Femenino", "Otro"));
        opcionesLenguaIndigena.setItems(FXCollections.observableArrayList("Sí", "No"));
        
    }
    
    @FXML
    private void leerDatosDePracticante(){
        
        if(camposValidos()){
            
            String nombre = ingresaNombre.getText();
            String apellidoPaterno = ingresaApellidoPaterno.getText();
            String apellidoMaterno = ingresaApellidoMaterno.getText();
            String matricula = ingresaMatricula.getText();
            String genero = opcionesGenero.getValue();
            LocalDate fechaNacimiento = calendarioFechaNacimiento.getValue();
            String hablaLenguaIndigena = opcionesLenguaIndigena.getValue();
            boolean lenguaIndigena = "sí".equalsIgnoreCase(hablaLenguaIndigena);


            Practicante practicante = new Practicante();
            practicante.setNombre(nombre);
            practicante.setApellidoPaterno(apellidoPaterno);
            practicante.setApellidoMaterno(apellidoMaterno);
            practicante.setMatricula(matricula);
            practicante.setGenero(genero);
            practicante.setFechaNacimiento(fechaNacimiento);
            practicante.setHablaLenguaIndigena(lenguaIndigena);
            
            registrarPracticante(practicante);
        
        }   
        
    }
        
    
    @FXML
    private boolean camposValidos(){

        boolean sonCamposValidos = true; 

        if(ingresaNombre.getText().isBlank() ||  ingresaApellidoPaterno.getText().isBlank() || 
          ingresaApellidoMaterno.getText().isBlank() || ingresaMatricula.getText().isBlank() ||
          calendarioFechaNacimiento.getValue() == null || opcionesLenguaIndigena.getValue() == null){
            
            sonCamposValidos = false; 

        }
        return sonCamposValidos; 
    }
   
   @FXML 
   private void registrarPracticante(Practicante practicante){
       
        try{
            
            ValidacionPracticante validacion = new ValidacionPracticante();
            validacion.ingresarPracticante(practicante);
            
        }catch(Exception e){
            System.out.println("no se pudo");
        }
        
   }
    
}
