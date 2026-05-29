/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionCoordinador;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorRegistroCoordinador extends ControladorRegistroPersonal{
    
    @FXML
    private void leerDatosDelCoordinador(ActionEvent evento){
        
        if (camposValidos()){
           
           Coordinador coordinador = crearCoordinador();
           
           registrarCoordinador(coordinador, evento);
           
        }else{
            
            mostrarMensajeCamposFaltantes();
            
        }
        
    }
    
    private Coordinador crearCoordinador(){
        
        
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
        
        return coordinador;
        
    }
    
    @FXML 
    private void registrarCoordinador(Coordinador coordinador, ActionEvent evento){
        
        try{
            
            ValidacionCoordinador validacion = new ValidacionCoordinador();
            
            boolean puedeRegistrar = validarCoordinadorActivo(validacion, evento);
            
            if(puedeRegistrar){
                
                ingresarCoordinador(coordinador, validacion, evento);
                
            }
                 
            validacion.ingresarCoordinador(coordinador);
            
        }catch(ReglaDeNegocioExcepcion e){
            
            mostrarMensajeErrorRegistro(e.getMessage());
                        
        }
        
    }
    
    private boolean validarCoordinadorActivo(ValidacionCoordinador validacion, ActionEvent evento) 
    throws ReglaDeNegocioExcepcion{
        
        boolean puedeRegistrar = true;
        
        if(validacion.verificarCoordinadorActivo()){
                
            boolean confirmarInactivacion = VentanaMensaje.mostrarConfirmacion("Coordinador activo", 
            "Ya existe un Coordinador activo. ¿Desea inactivarlo para continuar con el registro?");

            if(confirmarInactivacion){

                validacion.inactivarCoordinadorActivo();

            }else {

                regresar(evento);
                puedeRegistrar = false;

            }

        }
        
        return puedeRegistrar;

    }
    
    private void ingresarCoordinador(Coordinador coordinador, ValidacionCoordinador validacion, 
    ActionEvent evento) throws ReglaDeNegocioExcepcion{
        
        validacion.ingresarCoordinador(coordinador);
        
        VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Registro exitoso", 
        "Coordinador registrado exitosamente");
        
        regresar(evento);
        
    }
    
    private void mostrarMensajeErrorRegistro(String mensaje){
        
        VentanaMensaje.mostrarVentanaMensaje( AlertType.ERROR, "Registro fallido", mensaje );
        
    }

}
