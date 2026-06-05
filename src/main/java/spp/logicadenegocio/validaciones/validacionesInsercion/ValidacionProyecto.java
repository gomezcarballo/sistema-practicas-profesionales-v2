/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionProyecto {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionProyecto.class.getName());
        
    private static final int LONGITUD_MAXIMA_OBJETIVO_GENERAL = 300;
    
    private static final int LONGITUD_MAXIMA_METODOLOGIA = 200;
    
    private static final int LONGITUD_MAXIMA_CONTACTO_RESPONSABLE = 50;
        
    private static final int CANTIDAD_MAXIMA_CUPO = 50;

    public void ingresarProyecto(Proyecto proyecto)throws ReglaDeNegocioExcepcion {
        
        sonCamposValidosPorReglaNegocio(proyecto);
        
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        proyecto.setEsActivo(true);
        
        try{
            
            proyectoDAO.insertarProyecto(proyecto);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo proyecto.", e);
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

            RegistroErrores.registrarError(Level.SEVERE,"Fallo crítico de base de datos al actualizar un proyecto.",e);
            throw new ReglaDeNegocioExcepcion("No se pudo actualizar el proyecto por un problema "
            + "interno del sistema. Intente más tarde");

        }

    }
    
    public void sonCamposValidosPorReglaNegocio(Proyecto proyecto) throws ReglaDeNegocioExcepcion{
        
        String nombre = proyecto.getNombre();
        String nombreResponsable = proyecto.getNombreResponsable();
        String contactoResponsable = proyecto.getContactoResponsable();
        String objetivoGeneral = proyecto.getObjetivoGeneral();
        String metodologia = proyecto.getMetodologia();
        int cupoMaximo = proyecto.getCupoMaximo();
        
        ValidacionDatos validacionDatos = new ValidacionDatos();
        
        validacionDatos.validarNombre(nombre);
        
        validacionDatos.validarNombre(nombreResponsable);
        
        if(objetivoGeneral.length() > LONGITUD_MAXIMA_OBJETIVO_GENERAL){
            
            throw new ReglaDeNegocioExcepcion("El objetivo general excede la longitud maxima de " + 
            LONGITUD_MAXIMA_OBJETIVO_GENERAL + " caracteres");
            
        }   
        
        if(metodologia.length() > LONGITUD_MAXIMA_METODOLOGIA){
            
            throw new ReglaDeNegocioExcepcion("La metodología excede la longitud maxima de " + 
            LONGITUD_MAXIMA_METODOLOGIA + " caracteres");
            
        }
        
        if(contactoResponsable.length() > LONGITUD_MAXIMA_CONTACTO_RESPONSABLE){
            
            throw new ReglaDeNegocioExcepcion("El contacto del responsable excede la longitud maxima de " + 
            LONGITUD_MAXIMA_CONTACTO_RESPONSABLE + " caracteres");
            
        }
        
        if(cupoMaximo >= CANTIDAD_MAXIMA_CUPO){
            
            throw new ReglaDeNegocioExcepcion("El cupo excede el numero maximo de " + 
            CANTIDAD_MAXIMA_CUPO + " lugares permitidos");
            
        }
        
    }
}
