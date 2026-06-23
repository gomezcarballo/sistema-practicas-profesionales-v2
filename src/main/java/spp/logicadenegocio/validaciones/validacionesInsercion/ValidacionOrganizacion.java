/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Organizacion;

/**
 *
 * @author gomes
 */
public class ValidacionOrganizacion {
        
    private final int LONGITUD_MAXIMA_DIRECCION = 50;
        
    public List<String> validarRegistroOrganizacion(Organizacion organizacion) {
        
        List<String> listaValidaciones = new ArrayList<>();
        
        String nombre = organizacion.getNombre();
        String direccion = organizacion.getDireccion();
        
        ValidacionDatos validacionDatos = new ValidacionDatos();
        
        if (!validacionDatos.esFormatoSoloLetrasValido(nombre)) {
            listaValidaciones.add("El nombre de la organización solo debe contener letras.");
        } else if (!validacionDatos.esLongitudNombreValida(nombre)) {
            listaValidaciones.add("El nombre de la organización excede la longitud maxima de " + validacionDatos.LONGITUD_MAXIMA_NOMBRE + " caracteres.");
        }
        
        if (!esDireccionValida(direccion)) {
            listaValidaciones.add("La dirección excede la longitud maxima de " + LONGITUD_MAXIMA_DIRECCION + " caracteres");
        }
        
        return listaValidaciones;
    }
    
    public boolean esDireccionValida(String direccion) {
        
        boolean esValido = false;
        
        if (direccion != null && direccion.length() <= LONGITUD_MAXIMA_DIRECCION) {
            esValido = true;
        }
        
        return esValido;
    }
    
}
