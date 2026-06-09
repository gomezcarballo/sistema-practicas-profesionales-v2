/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spp.logicadenegocio.gestores.GestorInicioSesion;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorInicioSesion {
    
    @FXML
    private TextField txtCorreoMatricula;
    
    @FXML
    private PasswordField pfContrasena;
    
    @FXML
    private Button btnIngresar;
    
    @FXML
    private void leerDatos(){
        
        if(sonCamposValidos()){
            
            String identificador = txtCorreoMatricula.getText();
            String contraseñaIngresada = pfContrasena.getText();
            iniciarSesion(identificador, contraseñaIngresada);
            
        }else{
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Por favor, ingresa un correo electronico institucional o una matricula");
            
        }
        
    }
    
    private boolean sonCamposValidos(){
        
        boolean sonCamposValidos = true; 
        
        if( ( txtCorreoMatricula.getText() == null) || txtCorreoMatricula.getText().isBlank() ||
              pfContrasena.getText() == null || pfContrasena.getText().isBlank()){
            
            sonCamposValidos = false; 
        }
        
        return sonCamposValidos; 
    }
    
    private void iniciarSesion(String identificador, String contraseñaIngresada) {
        
        try{
            
            GestorInicioSesion gestor = new GestorInicioSesion();
            
            String tipoRol =  gestor.autenticarUsuario(identificador, contraseñaIngresada);
                        
            abrirMenuSegunRol(tipoRol);
            
            Stage escenarioActual = (Stage) btnIngresar.getScene().getWindow();
            escenarioActual.close();

        }catch(ReglaDeNegocioExcepcion e){
            
            String causa = e.getMessage();
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Inicio de sesión fallido", 
            causa);
            
        }
    }
    
    private void abrirMenuSegunRol(String tipoRol){
        
        if(tipoRol.equals("Administrador")){
            CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalAdministrador.fxml",
            "Menú Principal para Administrador");
        }

        if(tipoRol.equals("Profesor")){
            CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalProfesor.fxml",
            "Menú Principal para Profesores");
        }

        if(tipoRol.equals("Coordinador")){
            CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalCoordinador.fxml",
            "Menú Principal para Coordinador");
        }

        if(tipoRol.equals("Practicante")){
            CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
            "Menú Principal para Practicante");
        }
        
    }

}
