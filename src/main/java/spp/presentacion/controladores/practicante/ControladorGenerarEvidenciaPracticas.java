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
    private void abrirReportes(ActionEvent evento){
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioReporte.fxml", "Formulario de Reporte");       
        CerradorVentana.cerrarVentana(evento);
        
    }
    @FXML 
    private void abrirAutoevaluacion(ActionEvent evento){
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioAutoevaluacion.fxml", "Formulario Autoevaluacion");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
        "Menú Principal para Practicante");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
