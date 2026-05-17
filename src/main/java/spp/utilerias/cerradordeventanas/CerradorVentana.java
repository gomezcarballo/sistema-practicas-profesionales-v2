/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.cerradordeventanas;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;

/**
 *
 * @author gomes
 */
public class CerradorVentana {
    
    public static void cerrarVentana(ActionEvent evento) {

        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene() .getWindow();
        ventanaActual.close();
        
    }
    
}
