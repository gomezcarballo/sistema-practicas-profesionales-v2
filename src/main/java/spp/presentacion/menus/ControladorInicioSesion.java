/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spp.logicadenegocio.validacionesInicioSesion.ValidacionInicioDeSesion;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorInicioSesion {
    
    @FXML
    private TextField ingresaCorreoMatricula;
    
    @FXML
    private TextField ingresaContrasena;
    
    @FXML
    private Button botonIngresar;
    
    @FXML
    private void leerDatos(){
        
        if(sonCamposValidos()){
            
            String identificador = ingresaCorreoMatricula.getText();
            String contraseñaIngresada = ingresaContrasena.getText();
            iniciarSesion(identificador, contraseñaIngresada);
            
        }else{
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
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
                        
            if( tipoRol.equals("Administrador") ){
                CargadorVentana.cargarVentana("/fxml/GUI-MenuPrincipalAdministrador.fxml",
                    "Menú Principal para Administrador");
            }
            
            if( tipoRol.equals("Profesor") ){
                 CargadorVentana.cargarVentana("/fxml/GUI-MenuPrincipalProfesor.fxml",
                    "Menú Principal para Profesores");
            }
            
            if( tipoRol.equals("Coordinador") ){
                CargadorVentana.cargarVentana("/fxml/GUI-MenuPrincipalCoordinador.fxml",
                    "Menú Principal para Coordinador");
            }
            
            if( tipoRol.equals("Practicante")){
                CargadorVentana.cargarVentana("/fxml/GUI-MenuPrincipalPracticante.fxml",
                    "Menú Principal para Practicante");
            }
            
            Stage escenarioActual = (Stage) botonIngresar.getScene().getWindow();
            escenarioActual.close();

        }catch(ReglaDeNegocioExcepcion e){

            String causa = e.getMessage();
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Inicio de sesión fallido", 
            causa);
            
        }
    }

}
