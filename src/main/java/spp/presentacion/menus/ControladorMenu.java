/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import java.io.IOException;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gomes
 */
public class ControladorMenu {
    
    @FXML
    private void abrirRegistrarPracticante() {
    
        cargarVentana("/fxml/GUI-FormularioRegistroPracticante.fxml", "Registro de Practicante");
        
    }   
    
    @FXML
    private void abrirRegistrarProfesor() {
    
        cargarVentana("/fxml/GuiFormularioRegistroProfesor.fxml", "Registro de Profesores");
        
    }
    
    @FXML
    private void abrirRegistrarCoordinador() {
    
        cargarVentana("/fxml/GUI-FormularioRegistroCoordinador.fxml", "Registro de Coordinador");
        
    }
    
    @FXML
    private void abrirRegistrarOrganizacion() {
    
        cargarVentana("/fxml/GUI-FormularioRegistroOrganizacion.fxml", "Registro de Organizacion");
        
    }
    
    
    private void cargarVentana(String Archivofxml, String titulo) {
        
        try {
            
            FXMLLoader loader = new FXMLLoader(getClass().getResource(Archivofxml));
            Parent root = loader.load();

            Stage ventana = new Stage();
            ventana.setTitle(titulo);
            ventana.setScene(new Scene(root));
            ventana.show();

        } catch (IOException e) {
           System.out.println("Error cargando FXML: " + Archivofxml);
            e.printStackTrace();
        }
        
    }

    
}
