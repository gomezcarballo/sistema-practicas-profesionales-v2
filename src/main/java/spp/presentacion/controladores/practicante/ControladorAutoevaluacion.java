/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorAutoevaluacion {

    @FXML
    private TextArea taObjetivos; 
    
    @FXML 
    private TextArea taAprendizaje;
    
    @FXML
    private TextArea taMejora;
    
    @FXML
    private TextArea taSentimientos;
    
    @FXML
    private TextArea taExperiencia; 
    
    @FXML
    private TextArea taDesafiosAbordados; 
    
    @FXML
    private TextArea taAplicaiconConocimiento;
            
    
    
    @FXML
    private void leerDatosDeAutoevaluacion(){
        
        if(camposValidos()){
            
            generarAutoevaluacion();
            
        }else {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
        
    }

    @FXML
    private boolean camposValidos(){

        boolean sonCamposValidos = true; 
        return sonCamposValidos; 
        
    }
    
    @FXML 
    private void generarAutoevaluacion(){
        VentanaMensaje.mostrarVentanaMensaje( Alert.AlertType.INFORMATION, "!UY!",
            "Funcionalidad no disponible. Intente en la proxima entrega");

    }
    
   @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
}
