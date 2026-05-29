/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionProfesor;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorRegistroProfesor extends ControladorRegistroPersonal{
    
    @FXML
    private void leerDatosDelProfesor(ActionEvent evento){
        
        if(camposValidos()){
            
            Profesor profesor = crearProfesor();

            registrarProfesor(profesor, evento);
        
        }else{
            
            mostrarMensajeCamposFaltantes();

        }
    }
    
    private Profesor crearProfesor(){
        
        String nombre = txtNombre.getText();
        String apellidoPaterno = txtApellidoPaterno.getText();
        String apellidoMaterno = txtApellidoMaterno.getText();
        String correoInstitucional = txtCorreo.getText();
        String numeroPersonal = txtNumeroPersonal.getText();

        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setApellidoPaterno(apellidoPaterno);
        profesor.setApellidoMaterno(apellidoMaterno);
        profesor.setCorreoInstitucional(correoInstitucional);
        profesor.setNumeroDePersonal(numeroPersonal);
        
        return profesor;
        
    }
    
    @FXML 
    private void registrarProfesor(Profesor profesor, ActionEvent evento){
                
        try{
            
            ValidacionProfesor validacion = new ValidacionProfesor();
            
            if(validacion.hayCupoProfesores()){
                
                ingresarProfesor(profesor, validacion, evento);
                
                
            }else{
                
                abrirVentanaProfesores(profesor);
      
            }
            
        }catch(ReglaDeNegocioExcepcion e){
            
            mostrarMensajeErrorRegistro(e.getMessage());
                       
        }
    }
    
    private void ingresarProfesor(Profesor profesor, ValidacionProfesor validacion, ActionEvent evento) throws ReglaDeNegocioExcepcion{
        
        validacion.ingresarProfesor(profesor);
        
        VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Registro exitoso", "Profesor registrado exitosamente" ); 
        regresar(evento);
        
    }
    
    private void abrirVentanaProfesores(Profesor profesor){
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaListaProfesoresActivos.fxml", "Profesores Activos");

        if(cargador != null){
            ControladorListaProfesoresActivos controlador = cargador.getController();
            controlador.inicializarDatos(profesor);

        }
        
    }
    
    private void mostrarMensajeErrorRegistro(String mensaje){
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
        mensaje);
        
    }
    
    public void registrarProfesorConReemplazo(Profesor profesorNuevo, Profesor profesorAnterior, ActionEvent evento){

        try{

            ValidacionProfesor validacion = new ValidacionProfesor();

            validacion.inactivarProfesor(profesorAnterior.getIdUsuario());

            validacion.ingresarProfesor(profesorNuevo);

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro exitoso",
            "Profesor registrado exitosamente");

            regresar(evento);

        }catch(ReglaDeNegocioExcepcion e){

            mostrarMensajeErrorRegistro(e.getMessage());

        }

    }
    
}
