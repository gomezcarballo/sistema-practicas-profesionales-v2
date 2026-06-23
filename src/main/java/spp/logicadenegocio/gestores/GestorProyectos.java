/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionProyecto;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorProyectos {
   
    public List<String> validarCamposProyecto(Proyecto proyecto) {
        
        ValidacionProyecto validacion = new ValidacionProyecto();
        return validacion.validarRegistroProyecto(proyecto);
    
    }
   
    public void ingresarProyecto(Proyecto proyecto) throws OperacionesDeDaoExcepcion {
        
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        proyecto.setEsActivo(true);
        proyectoDAO.insertarProyecto(proyecto);

    }
    
    public void actualizarProyecto(Proyecto proyecto) throws OperacionesDeDaoExcepcion {
        
        ProyectoDAO proyectoDAO = new ProyectoDAO();

        proyectoDAO.actualizarProyecto(proyecto);

    } 
   
    public List<Proyecto> recuperarProyectosActivos() throws OperacionesDeDaoExcepcion {
        
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        return proyectoDAO.obtenerProyectosActivos();
    
    }
   
    public List<Proyecto> recuperarProyectosActivosPorOrganizacion(int idOrganizacion) throws OperacionesDeDaoExcepcion {
        
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        return proyectoDAO.obtenerProyectosActivosPorOrganizacion(idOrganizacion);
   
    }
   
    public void inactivarProyecto(int idProyecto) throws OperacionesDeDaoExcepcion {
        
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        proyectoDAO.inactivarProyecto(idProyecto);  
    
    }
   
}
