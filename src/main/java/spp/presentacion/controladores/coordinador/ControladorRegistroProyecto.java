/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 *
 * @author gomes
 */
public class ControladorRegistroProyecto {

    @FXML
    private TextField ingresaNombre;
    
    @FXML
    private TextField ingresaDescripcion;
    
    @FXML
    private TextField ingresaNombreResponsable;
    
    @FXML
    private TextField ingresaCupoMaximo;
    
    @FXML
    private void leerDatosDeProyecto(){
        
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        Stage ventanaActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();
        ventanaActual.close();
        
    }
    
}
