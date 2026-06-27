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
    
    public static boolean verificarMaximoDigitos(String cupoMaximo){
        
        boolean esMenorDeDigitosMaximos = true;
        
        int digitosMaximos = 10;      
        
        if(cupoMaximo.length() > digitosMaximos ){
            
            esMenorDeDigitosMaximos = false;
            
        }
        
        return esMenorDeDigitosMaximos;
        
    }
    
}
