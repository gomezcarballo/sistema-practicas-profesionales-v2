/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import javafx.fxml.FXML;
import spp.utilerias.cargadordeventanas.CargadorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorGenerarEvidenciaPracticas {
    
    
    @FXML 
    private void abrirReportes(){
        
        CargadorVentana.cargarVentana("/fxml/GUI-FormularioReporte.fxml", "Formulario de Reporte");       

    }
    
}
