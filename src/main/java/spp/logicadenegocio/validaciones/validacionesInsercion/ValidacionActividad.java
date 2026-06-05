/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.ActividadDAO;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionActividad {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionActividad.class.getName());
    
    public void ingresarActividad(Actividad actividad)throws ReglaDeNegocioExcepcion{
        
        sonCamposValidosPorReglaDeNegocio(actividad);
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        
        ActividadDAO actividadDAO = new ActividadDAO();
        
        actividad.setIdProfesor(sesionUsuario.getIdUsuario());
        
        try{
            
            actividadDAO.insertarActividad(actividad);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar una nueva actividad.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo registrar la Actividad por un problema "
                + "interno del sistema. Intente más tarde.");
            
        }
    }

    public void sonCamposValidosPorReglaDeNegocio(Actividad actividad) throws ReglaDeNegocioExcepcion{
        
        String titulo = actividad.getTitulo();
        String descripcion = actividad.getDescripcion();
        
        int longitudMaximaTitulo = 50;
        int longitudMaximaDescripcion = 100;
        
        if(titulo.length() > longitudMaximaTitulo){
            
            throw new ReglaDeNegocioExcepcion("El título excede la longitud maxima de " + 
            longitudMaximaTitulo + " caracteres");
            
        }   
        
        if(descripcion.length() > longitudMaximaDescripcion){
            
            throw new ReglaDeNegocioExcepcion("La descripción excede la longitud maxima de " + 
            longitudMaximaDescripcion + " caracteres");
            
        } 
        
    }
    
}
