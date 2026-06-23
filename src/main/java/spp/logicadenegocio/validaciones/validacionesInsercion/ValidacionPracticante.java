/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Practicante;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionPracticante {
    
    private final String PATRON_MATRICULA = "^[sS][0-9]{8}$";
    
    public List<String> validarRegistroPracticante(Practicante practicante) {
        
        List<String> listaValidaciones = new ArrayList<>();
        ValidacionDatos validacionDatos = new ValidacionDatos();
        
        String matricula = practicante.getMatricula();
        
        if (!esMatriculaValida(matricula)) {
            listaValidaciones.add("Matricula no valida. Debe comenzar con S seguido de 8 números.");
        }
        
        listaValidaciones.addAll(validacionDatos.validarNombreCompleto(practicante.getNombre(), 
        practicante.getApellidoPaterno(), practicante.getApellidoMaterno()));
        
        return listaValidaciones;
    }
    
    public boolean esMatriculaValida(String matricula) {
        
        boolean esValido = false;
        
        if (matricula != null && matricula.matches(PATRON_MATRICULA)) {
            esValido = true;
        }
        
        return esValido;
    }
    
}
