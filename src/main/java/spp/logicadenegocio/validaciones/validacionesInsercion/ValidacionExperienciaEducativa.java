/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;

/**
 *
 * @author gomes
 */
public class ValidacionExperienciaEducativa {
    
    private final int MAXIMO_CARACTERES_NOMBRE = 50;
    private final int MAXIMO_CARACTERES_PERIODO = 50;
    private final int CUPO_MINIMO = 1;
    private final int CUPO_MAXIMO = 30;

    public List<String> validarRegistroEE(ExperienciaEducativa experienciaEducativa) {

        List<String> listaValidaciones = new ArrayList<>(); 
        String nombre = experienciaEducativa.getNombreExperienciaEducativa();
        String periodo = experienciaEducativa.getPeriodo();
        int cupo = experienciaEducativa.getCupo();
        
        String mensajeAlerta;

        if (!esNombreValido(nombre)) {
            
            mensajeAlerta = "El nombre es inválido o excede la longitud máxima de " + MAXIMO_CARACTERES_NOMBRE + " caracteres";
            listaValidaciones.add(mensajeAlerta);
        
        }

        if (!esPeriodoValido(periodo)) {
            
            mensajeAlerta = "El periodo es inválido o excede la longitud máxima de " + MAXIMO_CARACTERES_PERIODO + " caracteres";
            listaValidaciones.add(mensajeAlerta);
        
        }
        
        if (!esCupoValido(cupo)) {

            mensajeAlerta = "El cupo debe ser un número entre " + CUPO_MINIMO + " y " + CUPO_MAXIMO + " alumnos";
            listaValidaciones.add(mensajeAlerta);
        
        }

        return listaValidaciones;
    }
    
    public boolean esNombreValido(String nombre) {
        
        boolean esValido = false;
        
        if (nombre != null && !nombre.isBlank() && nombre.length() <= MAXIMO_CARACTERES_NOMBRE) {
            esValido = true; 
        }

        return esValido;
    }

    public boolean esPeriodoValido(String periodo) {
        
        boolean esValido = false;
        
        if (periodo != null && !periodo.isBlank() && periodo.length() <= MAXIMO_CARACTERES_PERIODO) {
            esValido = true;
        }

        return esValido;
    } 
    
    public boolean esCupoValido(int cupo) {
        
        boolean esValido = false;
        
        if (cupo >= CUPO_MINIMO && cupo <= CUPO_MAXIMO) {
            esValido = true;
        }

        return esValido;
    }
    
}
