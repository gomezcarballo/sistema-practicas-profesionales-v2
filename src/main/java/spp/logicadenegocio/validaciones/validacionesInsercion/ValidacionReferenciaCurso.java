/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.ReferenciaCurso;

/**
 *
 * @author gomes
 */
public class ValidacionReferenciaCurso {
    
    private final int LONGITUD_EXACTA_NRC = 5;

    public List<String> validarRegistroNrc(ReferenciaCurso referenciaCurso) {

        List<String> listaValidaciones = new ArrayList<>(); 
        
        String clave = referenciaCurso.getNrc(); 
        String mensajeAlerta;

        if (!esClaveValida(clave)) {
            
            mensajeAlerta = "El NRC es inválido. Debe contener exactamente " + LONGITUD_EXACTA_NRC + " dígitos numéricos.";
            listaValidaciones.add(mensajeAlerta);
        
        }

        return listaValidaciones;
    }
    
    public boolean esClaveValida(String clave) {
        
        boolean esValida = false;
        
        if (clave != null && clave.length() == LONGITUD_EXACTA_NRC) {
            esValida = true; 
        }

        return esValida;
    }

}
