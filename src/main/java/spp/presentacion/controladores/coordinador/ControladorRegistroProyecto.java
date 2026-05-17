/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.validacionesInsercion.ValidacionProyecto;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorRegistroProyecto {

    @FXML
    private TextField ingresaNombre;
    
    @FXML
    private TextField ingresaDescripcion;
    
    @FXML
    private TextField ingresaNombreResponsable;
    
    @FXML
    private TextField ingresaCupoMaximo;
    
    @FXML
    public void initialize() {

        UnaryOperator<TextFormatter.Change> filtro = cambioEntero -> {
            if (cambioEntero.getText().matches("[0-9]*")) {
                return cambioEntero;
            }
            return null;
        };

        ingresaCupoMaximo.setTextFormatter(new TextFormatter<>(filtro));
    }

    
    @FXML
    private void leerDatosDeProyecto(){
        
        if( sonCamposValidos() ){
            
            String nombre = ingresaNombre.getText();
            String descripcion = ingresaDescripcion.getText();
            String nombreResponsable = ingresaNombreResponsable.getText();
            int cupoMaximo = Integer.parseInt(ingresaCupoMaximo.getText());


            Proyecto proyecto = new Proyecto();
            proyecto.setNombre(nombre);
            proyecto.setDescripcion(descripcion);
            proyecto.setNombreResponsable(nombreResponsable);
            proyecto.setCupoMaximo(cupoMaximo);
            
            registrarProyecto(proyecto);
        
        }else {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
        
        
        
    }  
    
    @FXML
    private boolean sonCamposValidos(){

        boolean sonCamposValidos = true; 

        if(ingresaNombre.getText().isBlank() ||  ingresaDescripcion.getText().isBlank() || 
            ingresaNombreResponsable.getText().isBlank() ||ingresaCupoMaximo.getText() == null){
            
            sonCamposValidos = false; 

        }
        
        return sonCamposValidos; 
        
    }   
    
    @FXML 
    private void registrarProyecto(Proyecto proyecto){
       
        try{
            
            ValidacionProyecto validacion = new ValidacionProyecto();
            validacion.ingresarProyecto(proyecto);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
            "Proyecto registrado correctamente");
            
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
