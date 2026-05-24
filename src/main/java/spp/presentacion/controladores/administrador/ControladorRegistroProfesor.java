/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.validaciones.validacionesInsercion.ValidacionProfesor;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorRegistroProfesor {
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtApellidoPaterno;
    
    @FXML
    private TextField txtApellidoMaterno;
    
    @FXML
    private TextField txtCorreo;
    
    @FXML
    private TextField txtNumeroPersonal;
       
    @FXML
    private void leerDatosDelProfesor(){
        
        if(camposValidos()){
            
            String nombre = txtNombre.getText();
            String apellidoPaterno = txtApellidoPaterno.getText();
            String apellidoMaterno = txtApellidoMaterno.getText();
            String correoInstitucional = txtCorreo.getText();
            String numeroPersonal = txtNumeroPersonal.getText();

            Profesor profesor = new Profesor();
            profesor.setNombre(nombre);
            profesor.setApellidoPaterno(apellidoPaterno);
            profesor.setApellidoMaterno(apellidoMaterno);
            profesor.setCorreoInstitucional(correoInstitucional);
            profesor.setNumeroDePersonal(numeroPersonal);

            registrarProfesor(profesor);
        
        }else{
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
    }
    
    @FXML
    private boolean camposValidos(){
        
        boolean sonCamposValidos = true; 
        
        if(txtNombre.getText().isBlank() ||  txtApellidoPaterno.getText().isBlank() ||
            txtCorreo.getText().isBlank() || txtNumeroPersonal.getText().isBlank()){
           
            sonCamposValidos = false; 
            
        }
        
        if(txtApellidoMaterno.getText().isBlank()){
            
            txtApellidoMaterno.setText(null);
            
        }
        
        return sonCamposValidos; 
    }
    
    @FXML 
    private void registrarProfesor(Profesor profesor){
                
        try{
            
            ValidacionProfesor validacion = new ValidacionProfesor();
            validacion.ingresarProfesor(profesor);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
            "Profesor registrado exitosamente");
            
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
            e.getMessage());
            
        }
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
