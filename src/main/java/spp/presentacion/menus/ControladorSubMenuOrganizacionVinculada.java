/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;


import java.util.logging.Logger;
import javafx.fxml.FXML;
import spp.utilerias.cargadordeventanas.CargadorVentana;


/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorSubMenuOrganizacionVinculada {
    
    private static final Logger bitacora = Logger.getLogger(ControladorMenu.class.getName());
    
    @FXML
    private void abrirRegistroOrganizacionVinculada() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-FormularioRegistroOrganizacion.fxml", "Registrar Organizacion");
        
    }
    
    @FXML
    private void abrirActualizarOrganizacionVinculada() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-.fxml", "");
        
    }
    
    @FXML
    private void abrirInactivarOrganizacionVinculada() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-.fxml", "");
        
    }
    
}
