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
public class ControladorSubMenuPracticantes {
    
    @FXML
    private void abrirRegistroPracticante(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioRegistroPracticante.fxml", "Registrar Practicante");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    private void abrirConsultarPracticantes(ActionEvent evento){
        
        CargadorVentana.cargarVentana("/fxml/VistaListaPracticantes.fxml", "Lista de Practicantes");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalCoordinador.fxml",
        "Menú Principal para Coordinador");
        CerradorVentana.cerrarVentana(evento);
        
    }

}
