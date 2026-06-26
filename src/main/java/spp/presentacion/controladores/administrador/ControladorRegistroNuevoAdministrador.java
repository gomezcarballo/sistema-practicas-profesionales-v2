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
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.gestores.GestorAdministradores;
import spp.utilerias.cerradordesesion.CerradorSesion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorRegistroNuevoAdministrador extends ControladorRegistroPersonal{
    
    @FXML
    private void leerDatosDelAdministrador(ActionEvent evento){
        
        if (camposValidos()){
           
           Administrador administrador = crearAdministrador();
           
           registrarAdministrador(administrador, evento);
           
        }else{
            
            mostrarMensajeCamposFaltantes();
            
        }
        
    }
    
    private Administrador crearAdministrador(){      
        
        String nombre = txtNombre.getText();
        String apellidoPaterno = txtApellidoPaterno.getText();
        String apellidoMaterno = txtApellidoMaterno.getText();
        String correoInstitucional = txtCorreo.getText();
        String numeroPersonal = txtNumeroPersonal.getText();

        Administrador administrador = new Administrador();
        administrador.setNombre(nombre);
        administrador.setApellidoPaterno(apellidoPaterno);
        administrador.setApellidoMaterno(apellidoMaterno);
        administrador.setCorreoInstitucional(correoInstitucional);
        administrador.setNumeroDePersonal(numeroPersonal);
        
        return administrador;
        
    }
    
    private boolean validarAdministrador(Administrador administrador) {
        
        boolean esValido = false;
        
        GestorAdministradores gestor = new GestorAdministradores();
        
        List<String> listaErrores = gestor.validarCamposAdministrador(administrador);
        
        if (listaErrores.isEmpty()) {
            esValido = true;
        } else {
            VentanaMensaje.mostrarVentanaErrores(listaErrores);
        }
        
        return esValido;
    }
    
    private void registrarAdministrador(Administrador administrador, ActionEvent evento) {
        
        try {
            
            if (validarAdministrador(administrador)) {
                
                boolean continuarRegistro = confirmarReemplazoAdministrador();
                
                if (continuarRegistro) {
                    
                    GestorAdministradores gestorAdministradores = new GestorAdministradores();
                    
                    int idAdministradorActual = SesionUsuario.getInstancia().getIdUsuario();
                    
                    gestorAdministradores.reemplazarAdministrador(administrador, idAdministradorActual);

                    VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro exitoso", 
                    "Administrador registrado exitosamente");

                    CerradorSesion.cerrarSesion(evento);
                
                }
            
            }
        } catch (OperacionesDeDaoExcepcion | ProcesamientoSistemaExcepcion e) {
            
            mostrarMensajeErrorRegistro(e.getMessage());                        
        
        }
    }
    
    private boolean confirmarReemplazoAdministrador(){
        
        boolean continuarRegistro = true;
        
        continuarRegistro = VentanaMensaje.mostrarConfirmacion("Administrador activo", 
        "Esta acción implica inactivar a este usuario. ¿Desea inactivar continuar con el registro?. "
        + "Esta acción no se puede deshacer.");

        return continuarRegistro;

    }
    
    private void mostrarMensajeErrorRegistro(String mensaje){
        
        VentanaMensaje.mostrarVentanaMensaje( AlertType.ERROR, "Registro fallido", mensaje );
        
    }
    
}
