/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Coordinador;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionCoordinador {
        
    public List<String> validarRegistroCoordinador(Coordinador coordinador) {
        
        ValidacionDatos validacionDatos = new ValidacionDatos();
        List<String> listaValidaciones = new ArrayList<>();
        
        listaValidaciones.addAll(validacionDatos.validarNumeroPersonal(coordinador.getNumeroDePersonal()));
        
        listaValidaciones.addAll(validacionDatos.validarNombreCompleto(
        coordinador.getNombre(),  coordinador.getApellidoPaterno(), 
        coordinador.getApellidoMaterno()));
        
        return listaValidaciones;
    }
    
}
