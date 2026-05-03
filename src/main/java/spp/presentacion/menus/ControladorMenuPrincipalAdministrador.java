/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import java.util.logging.Logger;
import javafx.fxml.FXML;
import spp.utilerias.cargadordeventanas.CargadorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalAdministrador {
    
    private static final Logger bitacora = Logger.getLogger(ControladorMenu.class.getName());
    
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
        cargadorVentana.cargarVentana("/fxml/GUI-FormularioRegistroAdministrador.fxml", "Registr de Administrador");
        
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
   
}
