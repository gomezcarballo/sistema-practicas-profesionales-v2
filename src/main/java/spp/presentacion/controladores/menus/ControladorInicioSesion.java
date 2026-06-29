/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.logicadenegocio.gestores.GestorInicioSesion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;
 
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
            
            String identificador = txtCorreoMatricula.getText().trim();
            String contraseñaIngresada = pfContrasena.getText().trim();
            autorizarSesion(identificador, contraseñaIngresada);
            
        }else{
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Por favor, ingresa todos los campos solicitados.");
            
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
    
    private void autorizarSesion(String identificador, String contraseñaIngresada) {
        
        GestorInicioSesion gestor = new GestorInicioSesion();
        boolean esIdentificadorValido = gestor.esFormatoValido(identificador);
        
        if(esIdentificadorValido){

            UsuarioEncontrado usuarioEncontrado = null;

            try{
            
                usuarioEncontrado = gestor.autenticarUsuario(identificador);

                if(usuarioEncontrado == null){ 

                    VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Usuario no encontrado", 
                    "El usuario no se encuentra registrado o esta inactivado.");

                }else{

                    boolean esContraseñaCorrecta = verificarContraseña(usuarioEncontrado, contraseñaIngresada);

                    if(esContraseñaCorrecta){

                        iniciarSesion(usuarioEncontrado, identificador);

                    }
                }

            }catch(OperacionesDeDaoExcepcion e){
                
                String causa = e.getMessage();
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Inicio de sesión fallido", 
                causa);
                
            }
        }else{

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Identificador invalido", 
            "Por favor ingresar un correo institucional o una matricula.");

        }
        
    }
    private boolean verificarContraseña(UsuarioEncontrado usuarioEncontrado, String contraseñaIngresada){
        
        boolean esContraseñaCorrecta = false;
        GestorInicioSesion gestor = new GestorInicioSesion();
        esContraseñaCorrecta = gestor.esContraseñaCorrecta(usuarioEncontrado, contraseñaIngresada);

        if(esContraseñaCorrecta){

            esContraseñaCorrecta = true;

        }else{

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Contraseña incorrecta", 
            "La contraseña es incorrecta. Por favor ingresela de nuevo.");

        }

        return esContraseñaCorrecta;
    }

    private void iniciarSesion(UsuarioEncontrado usuarioEncontrado, String identificador){

        GestorInicioSesion gestor = new GestorInicioSesion();
        String rolUsuario = usuarioEncontrado.getRolUsuarioEncontrado();    
        gestor.iniciarSesion(usuarioEncontrado, identificador);     

        abrirMenuSegunRol(rolUsuario);
        
        Stage escenarioActual = (Stage) btnIngresar.getScene().getWindow();
        escenarioActual.close();
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
