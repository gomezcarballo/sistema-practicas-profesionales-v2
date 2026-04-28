/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.validacionesInsercion.ValidacionProfesor;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

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
    private TextField ingresaCorreo;
    
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
        String correoInstitucional = ingresaCorreo.getText();
        String numeroPersonal = ingresaNumeroPersonal.getText();
        
        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setApellidoPaterno(apellidoPaterno);
        profesor.setApellidoMaterno(apellidoMaterno);
        profesor.setCorreoInstitucional(correoInstitucional);
        profesor.setNumeroDePersonal(numeroPersonal);
        
        registrarProfesor(profesor);
        
        }else{
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
    }
    
    @FXML
    private boolean camposValidos(){
        
        boolean sonCamposValidos = true; 
        
        if(ingresaNombre.getText().isBlank() ||  ingresaApellidoPaterno.getText().isBlank() ||
            ingresaCorreo.getText().isBlank() || ingresaNumeroPersonal.getText().isBlank()){
           
            sonCamposValidos = false; 
            
        }
        
        if(ingresaApellidoMaterno.getText().isBlank()){
            
            ingresaApellidoMaterno.setText(null);
            
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
                
                VentanaMensaje ventanaMensaje = new VentanaMensaje();
                ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
                "Profesor registrado exitosamente");
                                
            }
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
            "No se pudo registrar al Profesor, intente más tarde");
            
        }
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
