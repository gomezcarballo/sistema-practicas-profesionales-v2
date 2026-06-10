/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.ventanas.ventanademensajes;

import java.util.Optional;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;

/**
 *
 * @author gomes
 */
public class VentanaMensaje {
    
    private static Alert construirAlerta(AlertType tipo, String titulo, String mensaje){
        
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        return alerta;
    }
    
    public static void mostrarVentanaMensaje(AlertType tipo, String titulo, String mensaje) {
        
        construirAlerta(tipo, titulo, mensaje).showAndWait();
        
    }
    
    public static boolean mostrarConfirmacion(String titulo, String mensaje) {

        Alert alerta = construirAlerta(Alert.AlertType.CONFIRMATION, titulo, mensaje);       
        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;

    }
    
}
