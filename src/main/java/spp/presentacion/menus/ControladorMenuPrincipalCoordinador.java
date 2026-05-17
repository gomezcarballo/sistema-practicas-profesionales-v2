    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordesesion.CerradorSesion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalCoordinador {
    
    @FXML
    private Button botonCerrarSesion;
    
    @FXML
    private void abrirSubMenuProyectos() {
        
        CargadorVentana.cargarVentana("/fxml/GUI-SubMenuProyectos.fxml", "Menu de Proyectos");
        
    }
    
    @FXML
    private void abrirSubMenuPracticantes() {
        
        CargadorVentana.cargarVentana("/fxml/GUI-SubMenuPracticantes.fxml", "Menu de Practicantes");
        
    }
    
    @FXML
    private void abrirSubMenuOrganizacionVinculada() {
        
        CargadorVentana.cargarVentana("/fxml/GUI-SubMenuOrganizacionVinculada.fxml", "Menu de Organizaciones Vinculadas");
        
    }
    
    @FXML
    private void abrirReporteIndicadores() {
        
        CargadorVentana.cargarVentana("/fxml/GUI-ReporteIndicadores", "Reporte de Indicadores");
        
    }
    
    @FXML
    private void abrirSubMenuMensajes(){
        
        CargadorVentana.cargarVentana("/fxml/GUI-SubMenuMensajes.fxml", "Mensajes");
        
    }
    
    @FXML
    private void cerrarSesion(ActionEvent evento) {

        CerradorSesion.cerrarSesion(evento);
        
    }
    
}
