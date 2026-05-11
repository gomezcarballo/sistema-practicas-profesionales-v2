/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.cargadordeventanas.CargadorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalAdministrador {
    
    @FXML
    private Button botonCerrarSesion;
    
    @FXML
    private void abrirRegistroNuevoCoordinador() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-FormularioRegistroCoordinador.fxml", "Registro de Coordinador");
        
    }
    
    @FXML
    private void abrirRegistroNuevoProfesor() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-FormularioRegistroProfesor.fxml", "Registro de Profesor");
        
    }
    
    @FXML
    private void abrirRegistroNuevoAdministrador() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-.fxml", "Registro de Administrador");
        
    }
    
    @FXML
    private void abrirReactivarCoordinador() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-", "Reactivar Coordinador");
        
    }
    @FXML
    private void abrirReactivarProfesor() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-", "Reactivar Profesor");
        
    }
    
    @FXML
    private void cerrarSesion() {

        SesionUsuario.getInstancia().cerrarSesion();

        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-InicioSesion.fxml", "Inicio de sesión");

        Stage escenarioActual = (Stage) botonCerrarSesion.getScene().getWindow();

        escenarioActual.close();
        
    }
    
}
