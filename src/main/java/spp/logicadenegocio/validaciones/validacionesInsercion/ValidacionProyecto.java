/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionProyecto {
    
    public void sonCamposValidosPorReglaNegocio(Proyecto proyecto) throws ReglaDeNegocioExcepcion{
        
        int longitudMaximaObjetivoGeneral = 300;
        int longitudMaximaMetodologia = 200;      
        int longitudMaximaContactoResponsable = 50;
        int cantidadMaximaCupo = 50;
                
        String nombre = proyecto.getNombre();
        String nombreResponsable = proyecto.getNombreResponsable();
        String contactoResponsable = proyecto.getContactoResponsable();
        String objetivoGeneral = proyecto.getObjetivoGeneral();
        String metodologia = proyecto.getMetodologia();
        int cupoMaximo = proyecto.getCupoMaximo();
        
        ValidacionDatos validacionDatos = new ValidacionDatos();
        
        validacionDatos.validarNombre(nombre);
        
        validacionDatos.validarNombre(nombreResponsable);
        
        if(objetivoGeneral.length() > longitudMaximaObjetivoGeneral){
            
            throw new ReglaDeNegocioExcepcion("El objetivo general excede la longitud maxima de " + 
            longitudMaximaObjetivoGeneral + " caracteres");
            
        }   
        
        if(metodologia.length() > longitudMaximaMetodologia){
            
            throw new ReglaDeNegocioExcepcion("La metodología excede la longitud maxima de " + 
            longitudMaximaMetodologia + " caracteres");
            
        }
        
        if(contactoResponsable.length() > longitudMaximaContactoResponsable){
            
            throw new ReglaDeNegocioExcepcion("El contacto del responsable excede la longitud maxima de " + 
            longitudMaximaContactoResponsable + " caracteres");
            
        }
        
        if(cupoMaximo >= cantidadMaximaCupo){
            
            throw new ReglaDeNegocioExcepcion("El cupo excede el numero maximo de " + 
            cantidadMaximaCupo + " lugares permitidos");
            
        }
        
    }
}
