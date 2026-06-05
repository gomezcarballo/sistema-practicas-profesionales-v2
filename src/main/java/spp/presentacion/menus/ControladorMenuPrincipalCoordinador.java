    /*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import spp.utilerias.cerradordesesion.CerradorSesion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalCoordinador extends ControladorMenus{
       
    @FXML
    private void abrirSubMenuPracticantes(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaSubMenuPracticantes.fxml", "Menu de Practicantes", evento);
        
    }
    
    @FXML
    private void abrirSubMenuOrganizacionVinculada(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaSubMenuOrganizacionVinculada.fxml", "Menu de Organizaciones Vinculadas", evento);
        
    }
    
    @FXML
    private void abrirAsignarProyecto(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaSolicitudesPracticantes.fxml", "Solicitudes de Proyectos", evento);
        
    }
    
    @FXML
    private void abrirReporteIndicadores(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaReporteIndicadores.fxml", "Reporte de Indicadores", evento);
        
    }
    
    @FXML
    private void abrirSubMenuMensajes(){
        
        abrirVentana("/fxml/VistaSubMenuMensajes.fxml", "Mensajes");
        
    }
    
    @FXML
    private void abrirVerPerfil(){
        
        abrirVentana("/fxml/VistaPerfilUsuario.fxml", "Mensajes");
        
    }
    
    @FXML
    private void cerrarSesion(ActionEvent evento) {

        CerradorSesion.cerrarSesion(evento);
        
    }
    
}
