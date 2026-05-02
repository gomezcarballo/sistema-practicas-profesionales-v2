/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalAdministrador {
    
    private static final Logger bitacora = Logger.getLogger(ControladorMenu.class.getName());
    
    @FXML
    private void abrirRegistroNuevoCoordinador() {
    
        cargarVentana("/fxml/GUI-FormularioRegistroCoordinador.fxml", "Registro de Coordinador");
        
    }
    
    @FXML
    private void abrirRegistroNuevoProfesor() {
    
        cargarVentana("/fxml/GUI-FormularioRegistroProfesor.fxml", "Registro de Profesor");
        
    }
    
    @FXML
    private void abrirRegistroNuevoAdministrador() {
    
        cargarVentana("/fxml/GUI-FormularioRegistroAdministrador.fxml", "Registr de Administrador");
        
    }
    
    @FXML
    private void abrirReactivarCoordinador() {
    
        cargarVentana("/fxml/GUI-", "Reactivar Coordinador");
        
    }
    @FXML
    private void abrirReactivarProfesor() {
    
        cargarVentana("/fxml/GUI-", "Reactivar Profesor");
        
    }
    
    
    private void cargarVentana(String archivoFXML, String titulo) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(archivoFXML));
            Parent root = loader.load();

            Stage ventana = new Stage();
            ventana.setTitle(titulo);
            ventana.setScene(new Scene(root));
            ventana.show();

        } catch (IOException e) {
           
           bitacora.log(Level.SEVERE, "Error al cargar la ventana: " + archivoFXML, e); 
           
        }
        
    }
    
}
