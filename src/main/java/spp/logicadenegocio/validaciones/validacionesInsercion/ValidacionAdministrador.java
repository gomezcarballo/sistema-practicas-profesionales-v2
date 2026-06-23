/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;


import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Administrador;

/**
 *
 * @author gomes
 */
public class ValidacionAdministrador {

    public List<String> validarRegistroAdministrador(Administrador administrador) {
        
        ValidacionDatos validacionDatos = new ValidacionDatos();
        List<String> listaValidaciones = new ArrayList<>();
        
        listaValidaciones.addAll(validacionDatos.validarNumeroPersonal(administrador.getNumeroDePersonal()));
        
        listaValidaciones.addAll(validacionDatos.validarCorreo(administrador.getCorreoInstitucional()));
        
        listaValidaciones.addAll(validacionDatos.validarNombreCompleto(administrador.getNombre(), 
        administrador.getApellidoPaterno(), administrador.getApellidoMaterno()));
        
        return listaValidaciones;
    }
    
}
