/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.menus;


import javafx.event.ActionEvent;
import javafx.fxml.FXML;


/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorSubMenuOrganizacionVinculada extends ControladorMenus{
        
    @FXML
    private void abrirRegistroOrganizacionVinculada() {
        
        abrirVentana("/fxml/VistaFormularioOrganizacion.fxml", "Registrar Organizacion");
        
    }
    
    @FXML
    private void abrirConsultarOrganizaciones(ActionEvent evento){
        
        cambiarVentana( "/fxml/VistaListaOrganizaciones.fxml","Lista de Organizaciones", evento);
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaMenuPrincipalCoordinador.fxml",
        "Menú Principal para Coordinador", evento);
        
    }    
    
}
