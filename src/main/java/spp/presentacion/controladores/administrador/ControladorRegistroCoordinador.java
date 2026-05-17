/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
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
            
            VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Datos faltantes", 
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
        
        if(ingresaApellidoMaterno.getText().isBlank()){
            
            ingresaApellidoMaterno.setText(null);
            
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
