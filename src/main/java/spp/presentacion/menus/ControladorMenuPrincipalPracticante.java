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
public class ControladorMenuPrincipalPracticante extends ControladorMenus{
    
    @FXML
    private void abrirGenerarEvidenciaPracticas(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaGenerarEvidenciaPracticas.fxml", "Generar Evidencias de Practicas", evento);
        
    }
        
    @FXML
    private void abrirAnadirEvidenciaPracticas(ActionEvent evento){
        
       cambiarVentana("/fxml/VistaSubirEvidenciaPracticas.fxml", "Subir Evidencias de Practicas", evento);
       
    }
    

    @FXML
    private void abrirSolicitarProyecto(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaSolicitudProyectos.fxml", "Solicitar proyecto", evento);
        
    }
    
    @FXML
    private void abrirListaActividades(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaListaActividades.fxml", "Lista de Actividades", evento);
        
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
