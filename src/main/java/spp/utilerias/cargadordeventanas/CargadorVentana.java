/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.cargadordeventanas;

import java.io.IOException;
import java.util.logging.Level;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import spp.utilerias.bitacora.RegistroErrores;

/**
 *
 * @author gomes
 */
public class CargadorVentana {
        
    public static void cargarVentana(String archivoFXML, String titulo) {
        
        cargarVentanaConControlador(archivoFXML, titulo);
        
    }
    
    public static FXMLLoader cargarVentanaConControlador(String archivoFXML, String titulo) {
        
        FXMLLoader cargadorFXML = null;
        
        try {

            cargadorFXML = new FXMLLoader(CargadorVentana.class.getResource(archivoFXML));

            Parent raiz = cargadorFXML.load();

            Stage ventana = new Stage();
            ventana.setTitle(titulo);
            ventana.setScene(new Scene(raiz));
            ventana.show();

        } catch(IOException e) {

            RegistroErrores.registrarError(Level.SEVERE, "Error al cargar la ventana: " + archivoFXML, e);
               
        }
        
    return cargadorFXML;
    }
    
}
