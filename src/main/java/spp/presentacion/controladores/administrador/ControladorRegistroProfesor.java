/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ComboBox;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.gestores.GestorProfesores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;


/**
 *
 * @author gomes
 */
public class ControladorRegistroProfesor extends ControladorRegistroPersonal{
    
    @FXML
    private ComboBox<String> cbOpcionesNrc;
    
    @FXML
    public void initialize(){
        
        cbOpcionesNrc.setItems(FXCollections.observableArrayList("17141", "12345"));
        
    }
    
    @FXML
    private boolean esNrcAsignadoValido(){
        
        boolean esNrcValido = true;
        
        if(cbOpcionesNrc.getValue() == null){
            
            esNrcValido = false;
            
        }
        
        return esNrcValido;
        
    }
    
    @FXML
    private void leerDatosDelProfesor(ActionEvent evento){
        
        if(camposValidos() && esNrcAsignadoValido()){
            
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
        String nrcAsignado = cbOpcionesNrc.getValue();

        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setApellidoPaterno(apellidoPaterno);
        profesor.setApellidoMaterno(apellidoMaterno);
        profesor.setCorreoInstitucional(correoInstitucional);
        profesor.setNumeroDePersonal(numeroPersonal);
        profesor.setNrcAsignado(nrcAsignado);
        
        return profesor;
        
    }
    
    @FXML 
    private void registrarProfesor(Profesor profesor, ActionEvent evento) {
        
        try {
            GestorProfesores gestorProfesores = new GestorProfesores();
            
            if (validarProfesor(profesor, gestorProfesores)) {
                
                if (gestorProfesores.hayCupoProfesores()) {
                    
                    gestorProfesores.ingresarProfesor(profesor);
        
                    VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Registro exitoso", 
                    "Profesor registrado exitosamente" ); 
                    
                    regresar(evento);
                
                } else {
                    abrirSeleccionProfesorReemplazo(profesor);
                }
            
            }
        
        } catch (OperacionesDeDaoExcepcion | ProcesamientoSistemaExcepcion e) {
            
            mostrarMensajeErrorRegistro(e.getMessage());           
        
        }
    }
    
    private boolean validarProfesor(Profesor profesor, GestorProfesores gestor) {
        
        boolean esValido = false;
        List<String> listaErrores = gestor.validarCamposProfesor(profesor);
        
        if (listaErrores.isEmpty()) {
            esValido = true;
        } else {
            VentanaMensaje.mostrarVentanaErrores(listaErrores);
        }
        
        return esValido;
    }
    
    
    private void abrirSeleccionProfesorReemplazo(Profesor profesor){
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaListaProfesoresActivos.fxml", "Profesores Activos");

        if(cargador != null){
            
            ControladorListaProfesoresActivos controlador = cargador.getController();
            controlador.inicializarDatos(profesor);

        }
        
    }

    public void registrarProfesorConReemplazo(Profesor profesorNuevo, Profesor profesorAnterior, ActionEvent evento) {
        
        try {
            
            GestorProfesores gestorProfesores = new GestorProfesores();

            gestorProfesores.inactivarProfesor(profesorAnterior.getIdUsuario());
            gestorProfesores.ingresarProfesor(profesorNuevo);

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro exitoso",
            "Profesor registrado exitosamente");

            regresar(evento);

        } catch (OperacionesDeDaoExcepcion | ProcesamientoSistemaExcepcion e) {
            mostrarMensajeErrorRegistro(e.getMessage());
        }
    
    }
    
    private void mostrarMensajeErrorRegistro(String mensaje){
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
        mensaje);
        
    }
    
}
