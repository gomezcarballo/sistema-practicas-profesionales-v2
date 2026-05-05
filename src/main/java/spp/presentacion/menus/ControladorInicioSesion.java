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
            String contraseñaIngresada = ingresaContrasena.getText();
            iniciarSesion(identificador, contraseñaIngresada);
            
        }else{
            
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Por favor, ingresa un correo electronico institucional o una matricula");
            
        }
    }
    
    @FXML
    private boolean sonCamposValidos(){
        
        boolean sonCamposValidos = true; 
        
        if( ( ingresaCorreoMatricula.getText() == null) || ingresaCorreoMatricula.getText().isBlank() ||
              ingresaContrasena.getText() == null || ingresaCorreoMatricula.getText().isBlank()){
            
            sonCamposValidos = false; 
        }
        
        return sonCamposValidos; 
    }
    
    @FXML
    private void iniciarSesion(String identificador, String contraseñaIngresada) {
        
        try{
            
            ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();
            
            String tipoRol =  validacion.autenticarUsuario(identificador, contraseñaIngresada);
            
            CargadorVentana cargadorVentana = new CargadorVentana();
            
            if( tipoRol.equals("Administrador") ){
                cargadorVentana.cargarVentana("/fxml/GUI-MenuPrincipalAdministrador.fxml","Menú Principal para Administrador");
            }
            
            if( tipoRol.equals("Profesor") ){
                 cargadorVentana.cargarVentana("/fxml/GUI-MenuPrincipalProfesor.fxml","Menú Principal para Profesores");
            }
            
            if( tipoRol.equals("Coordinador") ){
                cargadorVentana.cargarVentana("/fxml/GUI-MenuPrincipalCoordinador.fxml","Menú Principal para Coordinador");
            }
            
            if( tipoRol.equals("Practicante")){
                cargadorVentana.cargarVentana("/fxml/GUI-MenuPrincipalPracticante.fxml","Menú Principal para Practicante");
            }

        }catch(ReglaDeNegocioExcepcion e){

            String causa = e.getMessage();
            VentanaMensaje ventanaMensaje = new VentanaMensaje();
            ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Inicio de sesión fallido", 
            causa);
            
        }
    }

}
