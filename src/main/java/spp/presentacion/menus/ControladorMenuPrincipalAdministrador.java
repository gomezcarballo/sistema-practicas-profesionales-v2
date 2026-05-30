/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import spp.utilerias.cerradordesesion.CerradorSesion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalAdministrador extends ControladorMenus {
        
    @FXML
    private void abrirRegistroNuevoCoordinador(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaFormularioRegistroCoordinador.fxml", "Registro de Coordinador", evento);
        
    }
    
    @FXML
    private void abrirRegistroNuevoProfesor(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaFormularioRegistroProfesor.fxml", "Registro de Profesor", evento);
        
    }
    
    @FXML
    private void abrirRegistroNuevoAdministrador(ActionEvent evento) {
        
        cambiarVentana("/fxml/Vista.fxml", "Registro de Administrador", evento);
        
    }
    
    @FXML
    private void abrirReactivarCoordinador(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaReactivacionCoordinador.fxml", "Reactivar Coordinador", evento);
        
    }
    @FXML
    private void abrirReactivarProfesor(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaReactivacionProfesor", "Reactivar Profesor", evento);
        
    }
    
    @FXML
    private void abrirSubMenuMensajes(){
        
        abrirVentana("/fxml/VistaSubMenuMensajes.fxml", "Mensajes");
        
    }
    
    @FXML
    private void abrirVerPerfil(){
        
        abrirVentana("/fxml/VistaPerfilUsuario.fxml", "Mensajes");
        
    }
    
    @FXML
    private void cerrarSesion(ActionEvent evento) {

        CerradorSesion.cerrarSesion(evento);
        
    }
    
}
