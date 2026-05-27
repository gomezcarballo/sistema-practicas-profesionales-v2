/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.validaciones.validacionesInsercion.ValidacionCoordinador;
import spp.utilerias.cargadordeventanas.CargadorVentana;
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
    private void leerDatosDelCoordinador(ActionEvent evento){
        
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
           
           registrarCoordinador(coordinador, evento);
           
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
            
            txtApellidoMaterno.setText("");
            
        }
        
        return sonCamposValidos; 
        
    }
    
    @FXML 
    private void registrarCoordinador(Coordinador coordinador, ActionEvent evento){
        
        ValidacionCoordinador validacion = new ValidacionCoordinador();
        
        try{
            
            if(validacion.verificarCoordinadorActivo()){
                
                boolean confirmarInactivacion = VentanaMensaje.mostrarConfirmacion("Coordinador activo", 
                "Ya existe un Coordinador activo. ¿Desea inactivarlo para continuar con el registro?");
                
                if(confirmarInactivacion){
                    
                    validacion.inactivarCoordinadorActivo();
                    
                }else {
                    
                    cancelar(evento);
                    return;
                    
                }
                
            }
       
            validacion.ingresarCoordinador(coordinador);
            
            VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Registro exitoso", 
            "Coordinador registrado exitosamente");
            
            CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalAdministrador.fxml", 
            "Menu Principal para Administrador");
            CerradorVentana.cerrarVentana(evento);
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Registro fallido", 
            e.getMessage());
                        
        }
        
    }
   
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalAdministrador.fxml", 
        "Menu Principal para Administrador");
        CerradorVentana.cerrarVentana(evento);
        
    }

}
