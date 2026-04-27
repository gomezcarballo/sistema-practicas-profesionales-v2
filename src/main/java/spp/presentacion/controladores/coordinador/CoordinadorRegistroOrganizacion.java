/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.validacionesInsercion.ValidacionOrganizacion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class CoordinadorRegistroOrganizacion {
    
    @FXML
    private TextField ingresaNombre;
    
    @FXML
    private TextField ingresaDireccion;
    
    @FXML
    private ComboBox<String> opcionesSector;
    
    @FXML
    public void initialize() {
        
        opcionesSector.setItems(FXCollections.observableArrayList("Público", "Privado", "Social"));
        
    }
       
    @FXML
    private Button botonCancelar;
    
    @FXML
    private void leerDatosDeOrganizacion(){
        
        if(camposValidos()){
            
        String nombre = ingresaNombre.getText();
        String direccion = ingresaDireccion.getText();
        String sector = opcionesSector.getValue();
        
        Organizacion organizacion = new Organizacion();
        organizacion.setNombre(nombre);
        organizacion.setDireccion(direccion);
        organizacion.setSector(sector);
        
        registrarOrganizacion(organizacion);
        
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
        
        if(ingresaNombre.getText().isBlank() ||  ingresaDireccion.getText().isBlank() || 
            opcionesSector.getValue() == null){
           
            sonCamposValidos = false; 
            
        }
        return sonCamposValidos; 
    }
    
    @FXML 
    private void registrarOrganizacion(Organizacion organizacion){
        
        boolean ingresoExitoso;
        
        try{
            
            ValidacionOrganizacion validacion = new ValidacionOrganizacion();
            ingresoExitoso = validacion.ingresarOrganizacion(organizacion);
            
            if(ingresoExitoso){
                
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Registro Exitoso");
                alert.setHeaderText(null);
                alert.setContentText("Organización registrada correctamente");
                alert.showAndWait();
                
            }
            
        }catch(ReglaDeNegocioExcepcion e){
            
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Registro fallido");
            alert.setHeaderText(null);
            alert.setContentText("No se pudo registrar a la Organización, intente más tarde");
            alert.showAndWait();
            
        }
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
