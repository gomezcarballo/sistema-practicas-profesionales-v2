/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.gestores.GestorCoordinadores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

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
    private void registrarCoordinador(Coordinador coordinador, ActionEvent evento) {
        
        try {
            
            if (validarCoordinador(coordinador)) {
                
                GestorCoordinadores gestorCoordinadores = new GestorCoordinadores();
                boolean continuarRegistro = confirmarReemplazoCoordinador(gestorCoordinadores);
                
                if (continuarRegistro) {
                    gestorCoordinadores.reemplazarCoordinador(coordinador);
                    
                    VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro exitoso",
                    "Coordinador registrado exitosamente");
                    
                    regresar(evento);
                
                }
            
            }
       
        } catch (OperacionesDeDaoExcepcion | ProcesamientoSistemaExcepcion e) {
            
            mostrarMensajeErrorRegistro(e.getMessage());
        
        }
    }
    
    private boolean validarCoordinador(Coordinador coordinador) {
        
        boolean esValido = false;
        
        GestorCoordinadores gestor = new GestorCoordinadores();
        
        List<String> listaErrores = gestor.validarCamposCoordinador(coordinador);
        
        if (listaErrores.isEmpty()) {
            esValido = true;
        } else {
            VentanaMensaje.mostrarVentanaErrores(listaErrores);
        }
        
        return esValido;
   
    }
    
    private boolean confirmarReemplazoCoordinador(GestorCoordinadores gestorCoordinadores) throws OperacionesDeDaoExcepcion {
        
        boolean continuarRegistro = true;
        
        if (gestorCoordinadores.verificarCoordinadorActivo()) {
            
            continuarRegistro = VentanaMensaje.mostrarConfirmacion("Coordinador activo", 
            "Ya existe un Coordinador activo. ¿Desea inactivarlo para continuar con el registro?");
        
        }
        
        return continuarRegistro;
    }

    private void mostrarMensajeErrorRegistro(String mensaje){
        
        VentanaMensaje.mostrarVentanaMensaje( AlertType.ERROR, "Registro fallido", mensaje );
        
    }

}
