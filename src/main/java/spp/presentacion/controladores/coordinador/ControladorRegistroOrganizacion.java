/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.validacionesInsercion.ValidacionOrganizacion;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorRegistroOrganizacion {
    
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
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");

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
        
        try{
            
            ValidacionOrganizacion validacion = new ValidacionOrganizacion();
            validacion.ingresarOrganizacion(organizacion);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
            "Organización registrada correctamente");
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
            e.getMessage());
            
        }
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
