/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.cargadordeventanas;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 *
 * @author gomes
 */
public class CargadorVentana {
    
    private static final Logger bitacora = Logger.getLogger(CargadorVentana.class.getName());
    
    public static void cargarVentana(String archivoFXML, String titulo) {
        
        try {
            
            FXMLLoader cargadorFXML = new FXMLLoader(CargadorVentana.class.getResource(archivoFXML));
            Parent raiz = cargadorFXML.load();

            Stage ventana = new Stage();
            ventana.setTitle(titulo);
            ventana.setScene(new Scene(raiz));
            ventana.show();

        } catch (IOException e) {
           
           bitacora.log(Level.SEVERE, "Error al cargar la ventana: " + archivoFXML, e); 
           
        }
        
    }
    
}
