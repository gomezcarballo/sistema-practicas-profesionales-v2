/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Profesor;


/**
 *
 * @author gomes
 */
public class ValidacionProfesor {
    
    public List<String> validarRegistroProfesor(Profesor profesor) {
        
        ValidacionDatos validacionDatos = new ValidacionDatos();
        List<String> listaValidaciones = new ArrayList<>();
        
        listaValidaciones.addAll(validacionDatos.validarNumeroPersonal(profesor.getNumeroDePersonal()));
        
        listaValidaciones.addAll(validacionDatos.validarNombreCompleto(profesor.getNombre(), 
        profesor.getApellidoPaterno(), profesor.getApellidoMaterno()));
        
        return listaValidaciones;
    }
    
}

