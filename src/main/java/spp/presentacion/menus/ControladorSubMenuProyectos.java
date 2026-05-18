/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorSubMenuProyectos {
    
    @FXML
    private void abrirRegistroProyecto() {
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioRegistroProyecto.fxml", "Registrar Proyecto");
        
    }
    
    @FXML
    private void abrirAsignarProyecto() {
    
        CargadorVentana.cargarVentana("/fxml/VistaAsignacionProyecto.fxml", "Asignar a un Proyecto");
        
    }
    
    @FXML
    private void abrirInactivarProyecto() {
        
        CargadorVentana.cargarVentana("/fxml/.fxml", "");
        
    }
    
    @FXML
    private void abrirActualizarProyecto() {
        
        CargadorVentana.cargarVentana("/fxml/VistaActualizacionProyecto.fxml", "Actualizar Proyecto");
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
