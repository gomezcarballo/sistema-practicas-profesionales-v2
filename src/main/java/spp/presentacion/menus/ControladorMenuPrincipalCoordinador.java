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
public class ControladorMenuPrincipalCoordinador {
    
    @FXML
    private Button botonCerrarSesion;
    
    @FXML
    private void abrirSubMenuProyectos() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-SubMenuProyectos.fxml", "Menu de Proyectos");
        
    }
    
    @FXML
    private void abrirSubMenuPracticantes() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-SubMenuPracticantes.fxml", "Menu de Practicantes");
        
    }
    
    @FXML
    private void abrirSubMenuOrganizacionVinculada() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-SubMenuOrganizacionVinculada.fxml", "Menu de Organizaciones Vinculadas");
        
    }
    
    @FXML
    private void abrirReporteIndicadores() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-ReporteIndicadores", "Reporte de Indicadores");
        
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
