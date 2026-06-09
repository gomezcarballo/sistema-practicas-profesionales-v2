/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.interfacesdao.IProyectoDAO;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionProyecto;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorProyectos {
   
    public void ingresarProyecto(Proyecto proyecto)throws ReglaDeNegocioExcepcion {
        
        ValidacionProyecto validacion = new ValidacionProyecto();
        validacion.sonCamposValidosPorReglaNegocio(proyecto);
        
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

        ValidacionProyecto validacion = new ValidacionProyecto();
        validacion.sonCamposValidosPorReglaNegocio(proyecto);

        ProyectoDAO proyectoDAO = new ProyectoDAO();

        try {

            proyectoDAO.actualizarProyecto(proyecto);

        } catch (OperacionesDeDaoExcepcion e) {

            RegistroErrores.registrarError(Level.SEVERE,"Fallo crítico de base de datos al actualizar un proyecto.",e);
            throw new ReglaDeNegocioExcepcion("No se pudo actualizar el proyecto por un problema "
            + "interno del sistema. Intente más tarde");

        }

    } 
   
   private IProyectoDAO proyectoDAO;
   
   public List<Proyecto> recuperarProyectosActivos() throws ReglaDeNegocioExcepcion{
       
       try{
           
           proyectoDAO = new ProyectoDAO();
           return proyectoDAO.obtenerProyectosActivos();
           
       }catch(OperacionesDeDaoExcepcion e){
           
           throw new ReglaDeNegocioExcepcion("No se pudieron obtener los proyectos activos");
           
       }
   }
   
   public List<Proyecto> recuperarProyectosActivosPorOrganizacion(int idOrganizacion) throws ReglaDeNegocioExcepcion{
       
       try{
           
           proyectoDAO = new ProyectoDAO();
           return proyectoDAO.obtenerProyectosActivosPorOrganizacion(idOrganizacion);
           
       }catch(OperacionesDeDaoExcepcion e){
           
           throw new ReglaDeNegocioExcepcion("No se pudieron obtener los proyectos activos");
           
       }
   }
   
   public void inactivarProyecto(int idProyecto)throws ReglaDeNegocioExcepcion {
       
       try{
          
            proyectoDAO = new ProyectoDAO();
            proyectoDAO.inactivarProyecto(idProyecto);  
          
       }catch(OperacionesDeDaoExcepcion e){
           
           throw new ReglaDeNegocioExcepcion("No se pudo inactivar el proyecto");
           
       }
   }
   
}
