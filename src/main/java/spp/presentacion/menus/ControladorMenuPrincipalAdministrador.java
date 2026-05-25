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
public class ControladorMenuPrincipalAdministrador {
        
    @FXML
    private void abrirRegistroNuevoCoordinador(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioRegistroCoordinador.fxml", "Registro de Coordinador");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    private void abrirRegistroNuevoProfesor(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaFormularioRegistroProfesor.fxml", "Registro de Profesor");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    private void abrirRegistroNuevoAdministrador(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/Vista.fxml", "Registro de Administrador");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    private void abrirReactivarCoordinador(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/Vista", "Reactivar Coordinador");
        CerradorVentana.cerrarVentana(evento);
        
    }
    @FXML
    private void abrirReactivarProfesor(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/Vista", "Reactivar Profesor");
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
