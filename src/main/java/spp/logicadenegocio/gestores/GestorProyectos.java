/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdao.SolicitudDAO;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.interfacesdao.IProyectoDAO;
import spp.logicadenegocio.interfacesdao.ISolicitudDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorProyectos {
    
   private IProyectoDAO proyectoDAO;
   private ISolicitudDAO solicitudDAO;
    
   public List<Proyecto> recuperarProyectosActivos(int idOrganizacion) throws ReglaDeNegocioExcepcion{
       
       try{
           
           proyectoDAO = new ProyectoDAO();
           return proyectoDAO.obtenerProyectosActivos(idOrganizacion);
           
       }catch(OperacionesDeDaoExcepcion e){
           
           throw new ReglaDeNegocioExcepcion("No se pudieron obtener los proyectos activos");
           
       }
   }
   
   public List<Proyecto> recuperarProyectosSolicitados(int idUsuario) throws ReglaDeNegocioExcepcion{
       
       try{
           
           solicitudDAO = new SolicitudDAO();
           return solicitudDAO.obtenerProyectosSolicitados(idUsuario);
           
       }catch(OperacionesDeDaoExcepcion e){
           
           throw new ReglaDeNegocioExcepcion("No se pudieron obtener los proyectos solicitados");
           
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
