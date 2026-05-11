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
public class ControladorMenuPrincipalPracticante {
   
    @FXML
    private void abrirGenerarEvidenciaPracticas(){
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-GenerarEvidenciaPracticas.fxml", "Generar Evidencias de Practicas");
        
    }
   @FXML
   private void abrirAnadirEvidenciaPracticas(){
       
   }
    
    @FXML
    private Button botonCerrarSesion;
    
    @FXML
    private void abrirSolicitarProyecto() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-ListaProyectos.fxml", "Solicitar proyecto");
        
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
