/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.utilerias.formatofechas.ValidadorFechas;

/**
 *
 * @author gomes
 */
public class ValidacionActividad {
    
    private final int MAXIMO_CARACTERES_TITULO = 50;
    private final int MAXIMO_CARACTERES_DESCRIPCION = 100;

    public List<String> validarRegistroActividad(Actividad actividad) {

        List<String> listaValidaciones = new ArrayList<>(); 
        String titulo = actividad.getTitulo();
        String descripcion = actividad.getDescripcion();
        LocalDateTime fechaLimite = actividad.getFechaLimite();
        String mensajeAlerta;

        if (!esTamañoTituloValido(titulo)) {
            
            mensajeAlerta = "El título excede la longitud máxima de " + MAXIMO_CARACTERES_TITULO + " caracteres";
            listaValidaciones.add(mensajeAlerta);
        
        }

        if (!esTamañoDescripcionValido(descripcion)) {
            
            mensajeAlerta = "La descripción excede la longitud máxima de " + MAXIMO_CARACTERES_DESCRIPCION + " caracteres";
            listaValidaciones.add(mensajeAlerta);
        
        }
        
        if (!ValidadorFechas.esFechaPasada(fechaLimite)) {
            listaValidaciones.add("La fecha y hora límite no pueden ser fechas pasadas.");
        }

        return listaValidaciones;
    }
    
    public boolean esTamañoTituloValido(String titulo) {
        
        boolean esTamañoValido = false;
        
        if (titulo != null && titulo.length() <= MAXIMO_CARACTERES_TITULO) {
            esTamañoValido = true; 
        }

        return esTamañoValido;
    }

    public boolean esTamañoDescripcionValido(String descripcion) {
        
        boolean esTamañoValido = false;
        
        if (descripcion != null && descripcion.length() <= MAXIMO_CARACTERES_DESCRIPCION) {
            esTamañoValido = true;
        }

        return esTamañoValido;
    }             
    
}
