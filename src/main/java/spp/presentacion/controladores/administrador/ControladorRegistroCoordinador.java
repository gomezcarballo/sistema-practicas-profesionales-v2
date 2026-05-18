/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.validacionesInsercion.ValidacionCoordinador;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorRegistroCoordinador {
    
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
    private void leerDatosDelCoordinador(){
        
        if (camposValidos()){
            
           String nombre = txtNombre.getText();
           String apellidoPaterno = txtApellidoPaterno.getText();
           String apellidoMaterno = txtApellidoMaterno.getText();
           String correoInstitucional = txtCorreo.getText();
           String numeroPersonal = txtNumeroPersonal.getText();
           
           Coordinador coordinador = new Coordinador();
           coordinador.setNombre(nombre);
           coordinador.setApellidoPaterno(apellidoPaterno);
           coordinador.setApellidoMaterno(apellidoMaterno);
           coordinador.setCorreoInstitucional(correoInstitucional);
           coordinador.setNumeroDePersonal(numeroPersonal);
           
           registrarCoordinador(coordinador);
           
        }else{
            
            VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Datos faltantes", 
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
        
        if(txtApellidoMaterno.getText().isBlank()){
            
            txtApellidoMaterno.setText(null);
            
        }
        
        return sonCamposValidos; 
        
    }
    
    @FXML 
    private void registrarCoordinador(Coordinador coordinador){
        
        try{
            
            ValidacionCoordinador validacion = new ValidacionCoordinador();
            validacion.ingresarCoordinador(coordinador);
            
            VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Registro exitoso", 
            "Coordinador registrado exitosamente");
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Registro fallido", 
            e.getMessage());
            
        }
        
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }

}
