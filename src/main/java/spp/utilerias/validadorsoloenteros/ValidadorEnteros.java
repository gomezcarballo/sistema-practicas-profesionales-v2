/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.validadorsoloenteros;

import javafx.scene.control.TextField;

/**
 *
 * @author gomes
 */
public class ValidadorEnteros {
    
    public static void validarSoloNumeros(TextField campoTexto) {

        String texto = campoTexto.getText();

        if (!texto.matches("[0-9]*")) {

            texto = texto.replaceAll("[^0-9]", "");

            campoTexto.setText(texto);

            campoTexto.positionCaret(texto.length());
            
        }
        
    }
    
}
