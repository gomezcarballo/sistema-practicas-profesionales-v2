/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spp.logicadenegocio.validacionesInicioSesion.ValidacionInicioDeSesion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorInicioSesion {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionInicioDeSesion.class.getName());
    
    @FXML
    private TextField ingresaCorreoMatricula;
    
    @FXML
    private TextField ingresaContrasena;
    
    private void leerDatos(){
        if(sonCamposValidos()){
            
            String identificador = ingresaCorreoMatricula.getText();
            iniciarSesion(identificador);
            
        }else{
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Por favor, ingresa un correo electronico institucional o una matricula");
            
        }
    }
    
    @FXML
    private boolean sonCamposValidos(){
        
        boolean sonCamposValidos = true; 
        
        if( ( ingresaCorreoMatricula.getText() == null) || ingresaCorreoMatricula.getText().isBlank()){
            
            sonCamposValidos = false; 
        }
        
        return sonCamposValidos; 
    }
    
    @FXML
    private void iniciarSesion(String identificador) {
        
        try{
            
            ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();
            String tipoRol =  validacion.inicioDeSesion(identificador); 
            
            if(tipoRol == "Administrador"){
                cargarVentana("/fxml/GUI-MenuPrincipalAdministrado.fxml","Menu Principal para Administrador");
            }
            
            if(tipoRol == "Profesor"){
                 
            }
            
            if(tipoRol == "Coordinador"){
                cargarVentana("/fxml/GUI-MenuPrincipalCoordinador.fxml","Menu Principal para Coordinador");
            }
            
            if(tipoRol == "Practicante"){
                
            }

        }catch(ReglaDeNegocioExcepcion e){
        
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
            e.getMessage());
            
        }
    }
    
    private void cargarVentana(String archivoFXML, String titulo) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(archivoFXML));
            Parent root = loader.load();

            Stage ventana = new Stage();
            ventana.setTitle(titulo);
            ventana.setScene(new Scene(root));
            ventana.show();

        } catch (IOException e) {
           
           bitacora.log(Level.SEVERE, "Error al cargar la ventana: " + archivoFXML, e); 
           
        }
        
    }
}
