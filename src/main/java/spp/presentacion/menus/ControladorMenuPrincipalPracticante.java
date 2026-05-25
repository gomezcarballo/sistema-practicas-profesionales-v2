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
public class ControladorMenuPrincipalPracticante {
    
    @FXML
    private void abrirGenerarEvidenciaPracticas(ActionEvent evento){
        
        CargadorVentana.cargarVentana("/fxml/VistaGenerarEvidenciaPracticas.fxml", "Generar Evidencias de Practicas");
        CerradorVentana.cerrarVentana(evento);
        
    }
        
    @FXML
    private void abrirAnadirEvidenciaPracticas(ActionEvent evento){
        
       CargadorVentana.cargarVentana("/fxml/VistaSubirEvidenciaPracticas.fxml", "Subir Evidencias de Practicas");
       CerradorVentana.cerrarVentana(evento);
       
    }
    

    @FXML
    private void abrirSolicitarProyecto(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaSolicitudProyectos.fxml", "Solicitar proyecto");
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
