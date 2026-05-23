/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorGenerarEvidenciaPracticas {
    
    
    @FXML 
    private void abrirReportes(){
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioReporte.fxml", "Formulario de Reporte");       

    }
    @FXML 
    private void abrirAutoevaluacion(){
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioAutoevaluacion.fxml", "Formulario Autoevaluacion");
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
