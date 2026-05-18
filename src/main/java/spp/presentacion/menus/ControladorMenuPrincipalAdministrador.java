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
public class ControladorMenuPrincipalAdministrador {
        
    @FXML
    private void abrirRegistroNuevoCoordinador() {
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioRegistroCoordinador.fxml", "Registro de Coordinador");
        
    }
    
    @FXML
    private void abrirRegistroNuevoProfesor() {
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioRegistroProfesor.fxml", "Registro de Profesor");
        
    }
    
    @FXML
    private void abrirRegistroNuevoAdministrador() {
        
        CargadorVentana.cargarVentana("/fxml/Vista.fxml", "Registro de Administrador");
        
    }
    
    @FXML
    private void abrirReactivarCoordinador() {
        
        CargadorVentana.cargarVentana("/fxml/Vista", "Reactivar Coordinador");
        
    }
    @FXML
    private void abrirReactivarProfesor() {
        
        CargadorVentana.cargarVentana("/fxml/Vista", "Reactivar Profesor");
        
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
