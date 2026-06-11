/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.gestores.GestorPracticantes;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.cerradordesesion.CerradorSesion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalPracticante extends ControladorMenus{
    
    @FXML
    private Button btnGenerarReportes;
    
    @FXML
    private Button btnSubirEvidenciaPracticas;
    
    @FXML
    private Button btnVerActividades;
            
    public void initialize() {
        
        try {
            
            aplicarRestricciones();
            
        } catch (ReglaDeNegocioExcepcion e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo al cargar restricciones", e);
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error de Sistema", 
            "No se pudo verificar tu estado de proyecto. Contacta al coordinador.");
        
        }
        
    }
    
    private void aplicarRestricciones() throws ReglaDeNegocioExcepcion {
        
        GestorPracticantes gestor = new GestorPracticantes();
    
        int idUsuarioActual = SesionUsuario.getInstancia().getIdUsuario(); 
        
        boolean tieneProyecto = gestor.verificarAsignacionProyecto(idUsuarioActual);
        
        if (!tieneProyecto) {
            btnGenerarReportes.setDisable(true);
            btnSubirEvidenciaPracticas.setDisable(true);
            btnVerActividades.setDisable(true);
            
        }
        
    }
    
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
        
        abrirVentana("/fxml/VistaPerfilUsuario.fxml", "Perfil de Usuario");
        
    }
    
    @FXML
    private void cerrarSesion(ActionEvent evento) {

        CerradorSesion.cerrarSesion(evento);
        
    }
    
}
