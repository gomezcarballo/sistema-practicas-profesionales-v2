/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.validacionesInsercion.ValidacionCoordinador;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorRegistroCoordinador {
    @FXML
    private TextField ingresaNombre;
    
    @FXML
    private TextField ingresaApellidoPaterno;
    
    @FXML
    private TextField ingresaApellidoMaterno;
    
    @FXML 
    private TextField ingresaNumeroPersonal; 
    
    @FXML
    private Button botonCancelar;
    
    @FXML
    private void leerDatosDelCoordinador(){
        
        if (camposValidos()){
            
           String nombre = ingresaNombre.getText();
           String apellidoPaterno = ingresaApellidoPaterno.getText();
           String apellidoMaterno = ingresaApellidoMaterno.getText();
           String numeroPersonal = ingresaNumeroPersonal.getText();
           
           Coordinador coordinador = new Coordinador();
           coordinador.setNombre(nombre);
           coordinador.setApellidoPaterno(apellidoPaterno);
           coordinador.setApellidoMaterno(apellidoMaterno);
           coordinador.setNumeroDePersonal(numeroPersonal);
           
           registrarCoordinador(coordinador);
           
        }else{
            
            Alert alert = new Alert(Alert.AlertType.WARNING);
            alert.setTitle("Datos faltantes");
            alert.setHeaderText(null);
            alert.setContentText("Faltan datos por agregar. Por favor ingreselos.");
            alert.showAndWait();
            
        }
        
    }
    
    @FXML
    private boolean camposValidos(){
        
        boolean sonCamposValidos = true; 
        
        if(ingresaNombre.getText().isBlank() ||  ingresaApellidoPaterno.getText().isBlank() ||
            ingresaNumeroPersonal.getText().isBlank()){
            
            sonCamposValidos = false; 
            
        }
        
        return sonCamposValidos; 
        
    }
    
    @FXML 
    private void registrarCoordinador(Coordinador coordinador){
        
        boolean ingresoExitoso;
        
        try{
            
            ValidacionCoordinador validacion = new ValidacionCoordinador();
            ingresoExitoso = validacion.ingresarCoordinador(coordinador);
            
            if(ingresoExitoso){
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registro exitoso");
                alert.setHeaderText(null);
                alert.setContentText("Coordinador registrado exitosamente");
                alert.showAndWait();
                
            }
            
        }catch(ReglaDeNegocioExcepcion e){
            
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro fallido");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo registrar al Coordinador, intente más tarde");
            alert.showAndWait();
            
        }
        
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }

}
