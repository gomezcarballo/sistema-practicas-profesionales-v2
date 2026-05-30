/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.perfilusuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import spp.logicadenegocio.clasesdto.CredencialContraseña;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.validaciones.validacionnuevacontraseña.ValidacionNuevaContraseña;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorCambioContraseña {
    
    @FXML
    private PasswordField pfContraseñaActual;

    @FXML
    private PasswordField pfNuevaContraseña;

    @FXML
    private PasswordField pfContraseñaConfirmada;

    private Usuario usuario;

    public void cargarUsuario(Usuario usuario) {

        this.usuario = usuario;

    }
    
    @FXML
    private void leerNuevaContraseña(ActionEvent evento){
        
        if(sonCamposValidos()){
            
            CredencialContraseña credencialContraseña = crearCredenciales();
            
            cambiarContraseña(credencialContraseña, usuario, evento);
        
        }else{
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }  
    }
    
    private CredencialContraseña crearCredenciales(){
        
        String contraseñaActual = pfContraseñaActual.getText();
        String nuevaContraseña = pfNuevaContraseña.getText();
        String contraseñaConfirmada = pfContraseñaConfirmada.getText();

        CredencialContraseña credencialContraseña = new CredencialContraseña();
        credencialContraseña.setContraseñaActual(contraseñaActual);
        credencialContraseña.setContraseñaNueva(nuevaContraseña);
        credencialContraseña.setContraseñaConfirmada(contraseñaConfirmada);
        
        return credencialContraseña;
    }
    
    private boolean sonCamposValidos(){
            
        boolean sonCamposValidos = true;
        
        if(pfContraseñaActual.getText().isBlank() || pfNuevaContraseña.getText().isBlank() ||
           pfContraseñaConfirmada.getText().isBlank()){
            
            sonCamposValidos = false;
            
        }
        return sonCamposValidos;
        
    }
    
    @FXML
    private void cambiarContraseña(CredencialContraseña credenciales, Usuario usuario, ActionEvent evento) {

        try {

            ValidacionNuevaContraseña validacionNuevaContraseña = new ValidacionNuevaContraseña();
            validacionNuevaContraseña.cambiarContraseña(credenciales, usuario);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Contraseña actualizada",
            "La contraseña se actualizó correctamente");
            
            regresarPerfil(evento);

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Error al cambiar contraseña",
            e.getMessage());

        }

    }

    @FXML
    private void regresarPerfil(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaPerfilUsuario.fxml","Mi Perfil");
        CerradorVentana.cerrarVentana(evento);

    }

    
}
