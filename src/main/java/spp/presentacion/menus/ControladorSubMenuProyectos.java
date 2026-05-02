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
public class ControladorSubMenuProyectos {
    
    private static final Logger bitacora = Logger.getLogger(ControladorMenu.class.getName());
    
    @FXML
    private void abrirRegistroProyecto() {
    
        cargarVentana("/fxml/GUI-FormularioRegistroProyecto.fxml", "Registrar Proyecto");
        
    }
    
    @FXML
    private void abrirAsignarProyecto() {
    
        cargarVentana("/fxml/GUI-.fxml", "");
        
    }
    
    @FXML
    private void abrirInactivarProyecto() {
    
        cargarVentana("/fxml/GUI-.fxml", "");
        
    }
    
    @FXML
    private void abrirActualizarProyecto() {
    
        cargarVentana("/fxml/GUI-.fxml", "");
        
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
