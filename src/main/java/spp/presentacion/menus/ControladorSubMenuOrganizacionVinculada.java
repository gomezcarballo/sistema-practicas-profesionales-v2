/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;


import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;


/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorSubMenuOrganizacionVinculada {
    
    private static final Logger bitacora = Logger.getLogger(ControladorMenu.class.getName());
    
    @FXML
    private void abrirRegistroOrganizacionVinculada() {
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioOrganizacion.fxml", "Registrar Organizacion");
        
    }
    
    @FXML
    private void abrirActualizarOrganizacionVinculada() {
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioOrganizacion.fxml", "Actualizar Organizacion");
        
    }
    
    @FXML
    private void abrirInactivarOrganizacionVinculada() {
        
        CargadorVentana.cargarVentana("/fxml/Vista.fxml", "");
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
