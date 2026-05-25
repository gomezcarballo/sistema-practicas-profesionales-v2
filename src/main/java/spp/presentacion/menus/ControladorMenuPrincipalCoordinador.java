    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordesesion.CerradorSesion;
import spp.utilerias.cerradordeventanas.CerradorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalCoordinador {
       
    @FXML
    private void abrirSubMenuPracticantes(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaSubMenuPracticantes.fxml", "Menu de Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    private void abrirSubMenuOrganizacionVinculada(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaSubMenuOrganizacionVinculada.fxml", "Menu de Organizaciones Vinculadas");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    private void abrirReporteIndicadores(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaReporteIndicadores", "Reporte de Indicadores");
        CerradorVentana.cerrarVentana(evento);
        
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
