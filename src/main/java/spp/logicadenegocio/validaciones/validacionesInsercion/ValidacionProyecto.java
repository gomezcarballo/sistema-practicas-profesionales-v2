/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Proyecto;

/**
 *
 * @author gomes
 */
public class ValidacionProyecto {
    
    private final int LONGITUD_MAXIMA_OBJETIVO_GENERAL = 300;
    private final int LONGITUD_MAXIMA_METODOLOGIA = 200;      
    private final int LONGITUD_MAXIMA_CONTACTO_RESPONSABLE = 50;
    private final int CANTIDAD_MAXIMA_CUPO = 50;
    
    public List<String> validarRegistroProyecto(Proyecto proyecto) {
        
        List<String> listaValidaciones = new ArrayList<>();
        ValidacionDatos validacionDatos = new ValidacionDatos();
                
        String nombre = proyecto.getNombre();
        String nombreResponsable = proyecto.getNombreResponsable();
        String contactoResponsable = proyecto.getContactoResponsable();
        String objetivoGeneral = proyecto.getObjetivoGeneral();
        String metodologia = proyecto.getMetodologia();
        int cupoMaximo = proyecto.getCupoMaximo();
        
        if (!validacionDatos.esFormatoSoloLetrasValido(nombre)) {
            listaValidaciones.add("El nombre del proyecto solo debe contener letras.");
        } else if (!validacionDatos.esLongitudNombreValida(nombre)) {
            listaValidaciones.add("El nombre del proyecto excede la longitud maxima de " + 
            validacionDatos.LONGITUD_MAXIMA_NOMBRE + " caracteres");
        }
        
        if (!validacionDatos.esFormatoSoloLetrasValido(nombreResponsable)) {
            listaValidaciones.add("El nombre del responsable solo debe contener letras.");
        } else if (!validacionDatos.esLongitudNombreValida(nombreResponsable)) {
            listaValidaciones.add("El nombre del responsable excede la longitud maxima de " + 
            validacionDatos.LONGITUD_MAXIMA_NOMBRE + " caracteres");
        }
        
        if (!esObjetivoGeneralValido(objetivoGeneral)) {
            listaValidaciones.add("El objetivo general excede la longitud maxima de " + 
            LONGITUD_MAXIMA_OBJETIVO_GENERAL + " caracteres");
        }
        
        if (!esMetodologiaValida(metodologia)) {
            listaValidaciones.add("La metodología excede la longitud maxima de " + 
            LONGITUD_MAXIMA_METODOLOGIA + " caracteres");
        }
        
        if (!esContactoResponsableValido(contactoResponsable)) {
            listaValidaciones.add("El contacto del responsable excede la longitud maxima de " + 
            LONGITUD_MAXIMA_CONTACTO_RESPONSABLE + " caracteres");
        }
        
        if (!esCupoMaximoValido(cupoMaximo)) {
            listaValidaciones.add("El cupo excede el numero maximo de " + 
            CANTIDAD_MAXIMA_CUPO + " lugares permitidos");
        }
        
        return listaValidaciones;
    }
    
    public boolean esObjetivoGeneralValido(String objetivoGeneral) {
        
        boolean esValido = false;
        
        if (objetivoGeneral != null && objetivoGeneral.length() <= LONGITUD_MAXIMA_OBJETIVO_GENERAL) {
            esValido = true;
        }
        
        return esValido;
    }
    
    public boolean esMetodologiaValida(String metodologia) {
        
        boolean esValido = false;
        
        if (metodologia != null && metodologia.length() <= LONGITUD_MAXIMA_METODOLOGIA) {
            esValido = true;
        }
        
        return esValido;
    }
    
    public boolean esContactoResponsableValido(String contactoResponsable) {
        
        boolean esValido = false;
        
        if (contactoResponsable != null && contactoResponsable.length() <= LONGITUD_MAXIMA_CONTACTO_RESPONSABLE) {
            esValido = true;
        }
        
        return esValido;
    }
    
    public boolean esCupoMaximoValido(int cupoMaximo) {
        
        boolean esValido = false;
        
        if (cupoMaximo < CANTIDAD_MAXIMA_CUPO) {
            esValido = true;
        }
        
        return esValido;
    }
        
}
