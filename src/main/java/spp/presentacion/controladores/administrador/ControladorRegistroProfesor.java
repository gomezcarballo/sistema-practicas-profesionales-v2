/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Profesor;

/**
 *
 * @author gomes
 */
public class ControladorRegistroProfesor {
    
    @FXML
    private TextField ingresaNombre;
    
    @FXML
    private TextField ingresaApellidoPaterno;
    
    @FXML
    private TextField ingresaApellidoMaterno;
    
    @FXML
    private TextField ingresaNoPersonal;
       
    @FXML
    private Button botonCancelar;
    
    @FXML
    private void leerProfesor(){
        
        String nombre = ingresaNombre.getText();
        String apellidoPaterno = ingresaApellidoPaterno.getText();
        String apellidoMaterno = ingresaApellidoMaterno.getText();
        String noPersonal = ingresaNoPersonal.getText();
        
        Profesor profesor = new Profesor();
        profesor.setNombre(nombre);
        profesor.setApellidoPaterno(apellidoPaterno);
        profesor.setApellidoMaterno(apellidoMaterno);
        profesor.setNumeroDePersonal(noPersonal);
       
    }
    
}
