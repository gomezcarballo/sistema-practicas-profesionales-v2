/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import spp.logicadenegocio.clasesdto.Organizacion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionOrganizacion {
        
    public void sonCamposValidosPorReglaNegocio(Organizacion organizacion) throws ReglaDeNegocioExcepcion {
        
        String nombre = organizacion.getNombre();
        String direccion = organizacion.getDireccion();
        
        ValidacionDatos validacionDatos = new ValidacionDatos();
        
        validacionDatos.validarNombre(nombre);
        
        int longitudMaximaDireccion = 50;
        
        if( direccion.length() > longitudMaximaDireccion){
            
            throw new ReglaDeNegocioExcepcion("La dirección excede la longitud maxima de " + 
            longitudMaximaDireccion + " caracteres");
            
        }
        
    }
}
