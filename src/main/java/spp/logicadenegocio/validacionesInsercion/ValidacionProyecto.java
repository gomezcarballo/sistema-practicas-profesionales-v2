/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionesInsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionProyecto {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionProyecto.class.getName());
    
    private static final int LONGITUD_MAXIMA_NOMBRE = 50;
    
    private static final int LONGITUD_MAXIMA_DESCRIPCION = 100;
    
    private static final int LONGITUD_MAXIMA_RESPONSABLE = 50;

    public void ingresarProyecto(Proyecto proyecto)throws ReglaDeNegocioExcepcion {
        
        sonCamposValidosPorReglaNegocio(proyecto);
        
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        proyecto.setEsActivo(true);
        
        try{
            
            proyectoDAO.insertarProyecto(proyecto);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo proyecto.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo registrar el Proyecto por un problema "
                + "interno del sistema. Intente más tarde.");
            
        }
        
    }
    
    public void actualizarProyecto(Proyecto proyecto)throws ReglaDeNegocioExcepcion {

        sonCamposValidosPorReglaNegocio(proyecto);

        ProyectoDAO proyectoDAO = new ProyectoDAO();

        try {

            proyectoDAO.actualizarProyecto(proyecto);

        } catch (OperacionesDeDaoExcepcion e) {

            bitacora.log(Level.SEVERE,"Fallo crítico de base de datos al actualizar un proyecto.",e);
            throw new ReglaDeNegocioExcepcion("No se pudo actualizar el proyecto por un problema "
            + "interno del sistema. Intente más tarde");

        }

    }
    
    public void sonCamposValidosPorReglaNegocio(Proyecto proyecto) throws ReglaDeNegocioExcepcion{
        
        String nombre = proyecto.getNombre();
        String descripcion = proyecto.getDescripcion();
        String nombreResponsable = proyecto.getNombreResponsable();
        
        if(!(nombre.matches("^[\\p{L} ]+$"))){
            
            throw new ReglaDeNegocioExcepcion("El nombre solo debe contener letras.");
            
        }
        
        if(!(nombreResponsable.matches("^[\\p{L} ]+$"))){
            
            throw new ReglaDeNegocioExcepcion("El nombre del Responsable solo debe contener letras.");
            
        }
        
        if(nombre.length() > LONGITUD_MAXIMA_NOMBRE){
            
            throw new ReglaDeNegocioExcepcion("El nombre excede la longitud maxima de" + 
            LONGITUD_MAXIMA_NOMBRE + "caracteres");
            
        }
        
        if(descripcion.length() > LONGITUD_MAXIMA_DESCRIPCION){
            
            throw new ReglaDeNegocioExcepcion("La descripción excede la longitud maxima de" + 
            LONGITUD_MAXIMA_DESCRIPCION + "caracteres");
            
        }
        
        if(nombreResponsable.length() > LONGITUD_MAXIMA_RESPONSABLE){
            
            throw new ReglaDeNegocioExcepcion("El nombre del Responsable excede la longitud maxima de" + 
            LONGITUD_MAXIMA_RESPONSABLE + "caracteres");
            
        }

        
    }
    
}
