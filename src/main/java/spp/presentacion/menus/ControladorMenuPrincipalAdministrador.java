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
public class ControladorMenuPrincipalAdministrador {
    
    @FXML
    private Button botonCerrarSesion;
    
    @FXML
    private void abrirRegistroNuevoCoordinador() {
        
        CargadorVentana.cargarVentana("/fxml/GUI-FormularioRegistroCoordinador.fxml", "Registro de Coordinador");
        
    }
    
    @FXML
    private void abrirRegistroNuevoProfesor() {
        
        CargadorVentana.cargarVentana("/fxml/GUI-FormularioRegistroProfesor.fxml", "Registro de Profesor");
        
    }
    
    @FXML
    private void abrirRegistroNuevoAdministrador() {
        
        CargadorVentana.cargarVentana("/fxml/GUI-.fxml", "Registro de Administrador");
        
    }
    
    @FXML
    private void abrirReactivarCoordinador() {
        
        CargadorVentana.cargarVentana("/fxml/GUI-", "Reactivar Coordinador");
        
    }
    @FXML
    private void abrirReactivarProfesor() {
        
        CargadorVentana.cargarVentana("/fxml/GUI-", "Reactivar Profesor");
        
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
