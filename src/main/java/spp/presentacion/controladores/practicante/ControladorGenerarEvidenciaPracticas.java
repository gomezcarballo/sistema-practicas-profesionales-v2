/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import spp.presentacion.menus.ControladorMenus;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorGenerarEvidenciaPracticas extends ControladorMenus{
    
    
    @FXML 
    private void abrirReporteParcial(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaFormularioReporte.fxml", "Formulario de Reporte Parcial", evento);       
        
    }

    @FXML 
    private void abrirReporteFinal(ActionEvent evento){
        
        mostrarMensaje();      
        
    }

    private void mostrarMensaje(){
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "¡UY!", 
        "Funcionalidad en progreso...");           
        
    }

    @FXML 
    private void abrirAutoevaluacion(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaFormularioAutoevaluacion.fxml", "Formulario Autoevaluacion", evento);
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
        "Menú Principal para Practicante", evento);
        
    }
    
}
