/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.validacionesInsercion.ValidacionCoordinador;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorRegistroCoordinador {
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
    private void leerDatosDelCoordinador(){
        
        if (camposValidos()){
            
           String nombre = ingresaNombre.getText();
           String apellidoPaterno = ingresaApellidoPaterno.getText();
           String apellidoMaterno = ingresaApellidoMaterno.getText();
           String correoInstitucional = ingresaCorreo.getText();
           String numeroPersonal = ingresaNumeroPersonal.getText();
           
           Coordinador coordinador = new Coordinador();
           coordinador.setNombre(nombre);
           coordinador.setApellidoPaterno(apellidoPaterno);
           coordinador.setApellidoMaterno(apellidoMaterno);
           coordinador.setCorreoInstitucional(correoInstitucional);
           coordinador.setNumeroDePersonal(numeroPersonal);
           
           registrarCoordinador(coordinador);
           
        }else{
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Datos faltantes", 
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
    private void registrarCoordinador(Coordinador coordinador){
        
        boolean ingresoExitoso;
        
        try{
            
            ValidacionCoordinador validacion = new ValidacionCoordinador();
            ingresoExitoso = validacion.ingresarCoordinador(coordinador);
            
            if(ingresoExitoso){
                
                VentanaMensaje ventanaMensaje = new VentanaMensaje();
                ventanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Registro exitoso", 
                "Coordinador registrado exitosamente");                
                
            }
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Registro fallido", 
            "No se pudo registrar al Coordinador, intente más tarde");
            
        }
        
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }

}
