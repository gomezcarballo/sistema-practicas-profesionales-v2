/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import spp.logicadenegocio.validacionesInicioSesion.ValidacionInicioDeSesion;
import spp.utilerias.cargadordeventanas.CargadorVentana;
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
    
    @FXML
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
            
            CargadorVentana cargadorVentana = new CargadorVentana();
            
            if(tipoRol == "Administrador"){
                cargadorVentana.cargarVentana("/fxml/GUI-MenuPrincipalAdministrador.fxml","Menu Principal para Administrador");
            }
            
            if(tipoRol == "Profesor"){
                 
            }
            
            if(tipoRol == "Coordinador"){
                cargadorVentana.cargarVentana("/fxml/GUI-MenuPrincipalCoordinador.fxml","Menu Principal para Coordinador");
            }
            
            if(tipoRol == "Practicante"){
                
            }

        }catch(ReglaDeNegocioExcepcion e){
        
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Inicio de sesión fallido", 
            e.getMessage());
            
        }
    }

}
