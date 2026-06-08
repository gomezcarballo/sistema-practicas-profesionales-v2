/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import spp.logicadenegocio.clasesdto.Autoevaluacion;

/**
 *
 * @author gomes
 */
public class ValidacionAutoevaluacion {
    
    public boolean sonValoresValidos(Autoevaluacion autoevaluacion) {
        
        int[] valores = obtenerArregloValores(autoevaluacion);
        
        for (int valor : valores) {
            
            if (valor < 1 || valor > 5) {
                
                return false; 
                
            }
            
        }
        
        return true;
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
