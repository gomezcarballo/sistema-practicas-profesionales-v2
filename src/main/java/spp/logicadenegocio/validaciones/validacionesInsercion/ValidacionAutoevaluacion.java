/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Autoevaluacion;

/**
 *
 * @author gomes
 */
public class ValidacionAutoevaluacion {

    public List<String> validarValoresAutoevaluacion(Autoevaluacion autoevaluacion) {
        
        int valorMinimoPuntuacion = 1;
        int valorMaximoPuntuacion = 5;
        
        List<String> listaErrores = new ArrayList<>();
        int[] valores = obtenerArregloValores(autoevaluacion);
        
        for (int valor : valores) {
            
            if (valor < valorMinimoPuntuacion || valor > valorMaximoPuntuacion) {
                listaErrores.add("Asegúrese de que todas las calificaciones estén entre " + valorMinimoPuntuacion + " y " + valorMaximoPuntuacion + ".");
                break; 
            }
            
        }
        
        return listaErrores;
        
    }
    
    public int calcularPuntuacionFinal(Autoevaluacion autoevaluacion) {
        
        int puntuacionFinal = 0;
        
        int[] valores = obtenerArregloValores(autoevaluacion);
        
        for (int valor : valores) {
            
            puntuacionFinal += valor;
            
        }
        
        return puntuacionFinal;
        
    }
    
    private int[] obtenerArregloValores(Autoevaluacion autoevaluacion) {
        
        return new int[]{
            autoevaluacion.getValorPrimeraAfirmacion(),
            autoevaluacion.getValorSegundaAfirmacion(),
            autoevaluacion.getValorTerceraAfirmacion(),
            autoevaluacion.getValorCuartaAfirmacion(),
            autoevaluacion.getValorQuintaAfirmacion(),
            autoevaluacion.getValorSextaAfirmacion(),
            autoevaluacion.getValorSeptimaAfirmacion(),
            autoevaluacion.getValorOctavaAfirmacion(),
            autoevaluacion.getValorNovenaAfirmacion(),
            autoevaluacion.getValorDecimaAfirmacion()
        };
        
    }
    
}
