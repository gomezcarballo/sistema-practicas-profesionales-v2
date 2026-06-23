/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author gomes
 */
public class ValidacionDatos {
                    
    public final int LONGITUD_MAXIMA_NOMBRE = 50;
    public final int LONGITUD_MAXIMA_APELLIDO_PATERNO = 30;
    public final int LONGITUD_MAXIMA_APELLIDO_MATERNO = 30;
    public final int LONGITUD_MAXIMA_NUMERO_PERSONAL = 5;
    private final String PATRON_SOLO_LETRAS = "^[\\p{L} ]+$";
    
    public List<String> validarNombreCompleto(String nombre, String apellidoPaterno, String apellidoMaterno) {
        
        List<String> listaValidaciones = new ArrayList<>();

        if (!esFormatoSoloLetrasValido(nombre)) {
            listaValidaciones.add("El nombre solo debe contener letras.");
        } else if (!esLongitudNombreValida(nombre)) {
            listaValidaciones.add("El nombre excede la longitud maxima de " + 
            LONGITUD_MAXIMA_NOMBRE + " caracteres.");
        }

        if (!esFormatoSoloLetrasValido(apellidoPaterno)) {
            listaValidaciones.add("El apellido paterno solo debe contener letras.");
        } else if (!esLongitudApellidoPaternoValida(apellidoPaterno)) {
            listaValidaciones.add("El apellido paterno excede la longitud maxima de " + 
            LONGITUD_MAXIMA_APELLIDO_PATERNO + " caracteres.");
        }

        if (!esFormatoApellidoMaternoValido(apellidoMaterno)) {
            listaValidaciones.add("El apellido materno solo debe contener letras.");
        } else if (!esLongitudApellidoMaternoValida(apellidoMaterno)) {
            listaValidaciones.add("El apellido materno excede la longitud máxima de " + 
            LONGITUD_MAXIMA_APELLIDO_MATERNO + " caracteres.");
        }

        return listaValidaciones;
    }

    public List<String> validarNumeroPersonal(String numeroPersonal) {
        
        List<String> listaValidaciones = new ArrayList<>();
        
        if (!esNumeroPersonalValido(numeroPersonal)) {
            listaValidaciones.add("Numero de personal no valido. Debe contener " + LONGITUD_MAXIMA_NUMERO_PERSONAL + " digitos.");
        }
        
        return listaValidaciones;
    }
    
    public boolean esNumeroPersonalValido(String numeroPersonal) {
        
        boolean esValido = false;
        if (numeroPersonal != null && 
            numeroPersonal.length() == LONGITUD_MAXIMA_NUMERO_PERSONAL && 
            numeroPersonal.chars().allMatch(Character::isDigit)) {
            esValido = true;
        }
       
        return esValido;
    }
    
    public boolean esFormatoSoloLetrasValido(String texto) {
        
        boolean esValido = false;
        
        if (texto != null && texto.matches(PATRON_SOLO_LETRAS)) {
            esValido = true;
        }
        
        return esValido;
    }

    public boolean esLongitudNombreValida(String nombre) {
        
        boolean esValido = false;
        
        if (nombre != null && nombre.length() <= LONGITUD_MAXIMA_NOMBRE) {
            esValido = true;
        }
        
        return esValido;
    }

    public boolean esLongitudApellidoPaternoValida(String apellidoPaterno) {
        
        boolean esValido = false;
        
        if (apellidoPaterno != null && apellidoPaterno.length() <= LONGITUD_MAXIMA_APELLIDO_PATERNO) {
            esValido = true;
        }
        
        return esValido;
    }

    public boolean esLongitudApellidoMaternoValida(String apellidoMaterno) {
        
        boolean esValido = true; 
        
        if (apellidoMaterno != null && !apellidoMaterno.isBlank()) {
            if (apellidoMaterno.length() > LONGITUD_MAXIMA_APELLIDO_MATERNO) {
                esValido = false;
            }
        }
        
        return esValido;
    }

    public boolean esFormatoApellidoMaternoValido(String apellidoMaterno) {
        
        boolean esValido = true; 
        
        if (apellidoMaterno != null && !apellidoMaterno.isBlank()) {
            if (!apellidoMaterno.matches(PATRON_SOLO_LETRAS)) {
                esValido = false;
            }
        }
        
        return esValido;
    }
    
}
