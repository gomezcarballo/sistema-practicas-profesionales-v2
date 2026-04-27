/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.validacionesInsercion.ValidacionProfesor;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

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
        
        }else{
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos faltantes");
            alert.setHeaderText(null);
            alert.setContentText("Faltan datos por agregar. Por favor ingreselos.");
            alert.showAndWait();
            
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
        
        boolean ingresoExitoso;
        
        try{
            
            ValidacionProfesor validacion = new ValidacionProfesor();
            ingresoExitoso = validacion.ingresarProfesor(profesor);
            
            if(ingresoExitoso){
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registro exitoso");
                alert.setHeaderText(null);
                alert.setContentText("Profesor registrado exitosamente");
                alert.showAndWait();
                
            }
            
        }catch(ReglaDeNegocioExcepcion e){
           
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro fallido");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo registrar al Profesor, intente más tarde");
            alert.showAndWait();
            
        }
    }
    
}
