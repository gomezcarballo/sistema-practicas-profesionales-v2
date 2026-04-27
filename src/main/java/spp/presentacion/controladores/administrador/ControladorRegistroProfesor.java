/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.validacionesInsercion.ValidacionProfesor;

/**
 *
 * @author gomes
 */
public class ControladorRegistroProfesor {
    
    @FXML
    private TextField ingresaNombre;
    
    @FXML
    private TextField ingresaApellidoPaterno;
    
    @FXML
    private TextField ingresaApellidoMaterno;
    
    @FXML
    private TextField ingresaNumeroPersonal;
       
    @FXML
    private Button botonCancelar;
    
    @FXML
    private void leerDatosDelProfesor(){
        
        if(camposValidos()){
            
        String nombre = ingresaNombre.getText();
        String apellidoPaterno = ingresaApellidoPaterno.getText();
        String apellidoMaterno = ingresaApellidoMaterno.getText();
        String numeroPersonal = ingresaNumeroPersonal.getText();
        
        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setApellidoPaterno(apellidoPaterno);
        profesor.setApellidoMaterno(apellidoMaterno);
        profesor.setNumeroDePersonal(numeroPersonal);
        
        registrarProfesor(profesor);
        
        }
    }
    
    @FXML
    private boolean camposValidos(){
        
        boolean sonCamposValidos = true; 
        
        if(ingresaNombre.getText().isBlank() ||  ingresaApellidoPaterno.getText().isBlank() ||
            ingresaNumeroPersonal.getText().isBlank()){
           
            sonCamposValidos = false; 
            
        }
        return sonCamposValidos; 
    }
    
    @FXML 
    private void registrarProfesor(Profesor profesor){
        try{
            
            ValidacionProfesor validacion = new ValidacionProfesor();
            validacion.ingresarProfesor(profesor);
            
        }catch(Exception e){
            System.out.println("no se pudo");
        }
    }
    
}
