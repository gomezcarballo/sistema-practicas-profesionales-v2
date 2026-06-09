/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import spp.logicadenegocio.clasesdto.Practicante;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionPracticante {
    
    public void sonCamposValidosPorReglaNegocio(Practicante practicante) throws ReglaDeNegocioExcepcion {
        
        String matricula = practicante.getMatricula();
        String nombre = practicante.getNombre();
        String apellidoPaterno = practicante.getApellidoPaterno();
        String apellidoMaterno = practicante.getApellidoMaterno();
        
        if(!matricula.matches("^[sS][0-9]{8}$")){
            
           throw new ReglaDeNegocioExcepcion("Matricula no valida. Debe comenzar con S seguido de 8 números.");
           
        }
        
        ValidacionDatos validacionDatosPersonales = new ValidacionDatos();
        
        validacionDatosPersonales.validarNombre(nombre);
        validacionDatosPersonales.validarApellidoPaterno(apellidoPaterno);
        validacionDatosPersonales.validarApellidoMaterno(apellidoMaterno);
        
    }
    
}
