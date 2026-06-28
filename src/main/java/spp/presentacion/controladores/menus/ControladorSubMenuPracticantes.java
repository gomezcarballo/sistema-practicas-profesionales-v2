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
public class ControladorSubMenuPracticantes extends ControladorMenus{
    
    @FXML
    private void abrirRegistroPracticante(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaFormularioRegistroPracticante.fxml", "Registrar Practicante", evento);
        
    }
    
    @FXML
    private void abrirConsultarPracticantes(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaListaPracticantes.fxml", "Lista de Practicantes", evento);
        
    }

    @FXML
    private void abrirListaPracticantesAsignacionEE(ActionEvent evento){

        cambiarVentana("/fxml/VistaListaPracticantesParaAsignacionEE.fxml", "Lista Practicantes", evento);

    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaMenuPrincipalCoordinador.fxml",
        "Menú Principal para Coordinador", evento);
        
    }

}
