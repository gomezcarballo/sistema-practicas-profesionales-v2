    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordesesion.CerradorSesion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalCoordinador {
       
    @FXML
    private void abrirSubMenuProyectos() {
        
        CargadorVentana.cargarVentana("/fxml/VistaSubMenuProyectos.fxml", "Menu de Proyectos");
        
    }
    
    @FXML
    private void abrirSubMenuPracticantes() {
        
        CargadorVentana.cargarVentana("/fxml/VistaSubMenuPracticantes.fxml", "Menu de Practicantes");
        
    }
    
    @FXML
    private void abrirSubMenuOrganizacionVinculada() {
        
        CargadorVentana.cargarVentana("/fxml/VistaSubMenuOrganizacionVinculada.fxml", "Menu de Organizaciones Vinculadas");
        
    }
    
    @FXML
    private void abrirReporteIndicadores() {
        
        CargadorVentana.cargarVentana("/fxml/VistaReporteIndicadores", "Reporte de Indicadores");
        
    }
    
    @FXML
    private void abrirSubMenuMensajes(){
        
        CargadorVentana.cargarVentana("/fxml/VistaSubMenuMensajes.fxml", "Mensajes");
        
    }
    
    @FXML
    private void cerrarSesion(ActionEvent evento) {

        CerradorSesion.cerrarSesion(evento);
        
    }
    
}
