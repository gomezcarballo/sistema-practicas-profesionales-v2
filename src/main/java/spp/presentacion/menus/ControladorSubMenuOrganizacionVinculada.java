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
public class ControladorSubMenuOrganizacionVinculada {
        
    @FXML
    private void abrirRegistroOrganizacionVinculada() {
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioOrganizacion.fxml", "Registrar Organizacion");
        
    }
    
    @FXML
    private void abrirConsultarOrganizaciones(ActionEvent evento){
        
        CargadorVentana.cargarVentana( "/fxml/VistaListaOrganizaciones.fxml","Lista de Organizaciones");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalCoordinador.fxml",
        "Menú Principal para Coordinador");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    
}
