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
public class ControladorMenuPrincipalPracticante {
    
    @FXML
    private void abrirGenerarEvidenciaPracticas(){
        
        CargadorVentana.cargarVentana("/fxml/VistaGenerarEvidenciaPracticas.fxml", "Generar Evidencias de Practicas");
        
    }
        
    @FXML
    private void abrirAnadirEvidenciaPracticas(){
        
       CargadorVentana.cargarVentana("/fxml/VistaSubirEvidenciaPracticas.fxml", "Subir Evidencias de Practicas");
    }
    

    @FXML
    private void abrirSolicitarProyecto() {
        
        CargadorVentana.cargarVentana("/fxml/VistaSolicitudProyectos.fxml", "Solicitar proyecto");
        
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
