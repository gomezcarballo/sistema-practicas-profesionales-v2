/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.gestores.GestorCoordinadores;
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
            
            GestorCoordinadores gestorCoordinadores = new GestorCoordinadores();
            
            boolean continuarRegistro = confirmarReemplazoCoordinador(gestorCoordinadores);
            
            if(continuarRegistro){
                
                gestorCoordinadores.reemplazarCoordinador(coordinador);
                
                VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Registro exitoso",
                "Coordinador registrado exitosamente");
                
                regresar(evento);
                
            }
                             
        }catch(ReglaDeNegocioExcepcion e){
            
            mostrarMensajeErrorRegistro(e.getMessage());
                        
        }
        
    }
    
    private boolean confirmarReemplazoCoordinador(GestorCoordinadores gestorCoordinadores) 
    throws ReglaDeNegocioExcepcion{
        
        boolean continuarRegistro = true;
        
        if(gestorCoordinadores.verificarCoordinadorActivo()){
                
            continuarRegistro = VentanaMensaje.mostrarConfirmacion("Coordinador activo", 
            "Ya existe un Coordinador activo. ¿Desea inactivarlo para continuar con el registro?");

        }
        
        return continuarRegistro;

    }

    private void mostrarMensajeErrorRegistro(String mensaje){
        
        VentanaMensaje.mostrarVentanaMensaje( AlertType.ERROR, "Registro fallido", mensaje );
        
    }

}
