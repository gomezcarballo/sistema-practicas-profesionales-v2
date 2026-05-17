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
public class ControladorMenuPrincipalPracticante {
    
    @FXML
    private Button botonCerrarSesion;
    
    @FXML
    private void abrirGenerarEvidenciaPracticas(){
        
        CargadorVentana.cargarVentana("/fxml/GUI-GenerarEvidenciaPracticas.fxml", "Generar Evidencias de Practicas");
        
    }
        
    @FXML
    private void abrirAnadirEvidenciaPracticas(){
       
    }

    @FXML
    private void abrirSolicitarProyecto() {
        
        CargadorVentana.cargarVentana("/fxml/GUI-ListaProyectos.fxml", "Solicitar proyecto");
        
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
