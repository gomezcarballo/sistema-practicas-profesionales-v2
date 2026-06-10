/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.SolicitudProyectosDAO;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorSolicitudesProyectos {
    
    private static final int NUMERO_SOLICITUDES = 3;
    
    public void registrarSolicitudes(List<Proyecto> proyectosSeleccionados)
    throws ReglaDeNegocioExcepcion {   
        
        validarSolicitudes(proyectosSeleccionados);

        try {
            
            PracticanteDAO practicanteDAO = new PracticanteDAO();
            SolicitudProyectosDAO solicitudDAO = new SolicitudProyectosDAO();
            
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuario = sesionUsuario.getIdUsuario();
            
            if (practicanteDAO.tieneProyectoAsignado(idUsuario)) {

                throw new ReglaDeNegocioExcepcion("No puedes solicitar proyectos porque ya tienes uno asignado");
            
            }
            
            for(Proyecto proyecto : proyectosSeleccionados) {

                solicitudDAO.guardarSolicitud(idUsuario, proyecto.getIdProyecto());
                
            }

        } catch(OperacionesDeDaoExcepcion e) {

            throw new ReglaDeNegocioExcepcion("No se pudieron registrar las solicitudes");
            
        }
    }
    
    public List<Proyecto> recuperarProyectosSolicitados(int idUsuario) throws ReglaDeNegocioExcepcion{
       
        try{
           
           SolicitudProyectosDAO solicitudDAO = new SolicitudProyectosDAO();
           return solicitudDAO.obtenerProyectosSolicitados(idUsuario);
           
        }catch(OperacionesDeDaoExcepcion e){
           
           throw new ReglaDeNegocioExcepcion("No se pudieron obtener los proyectos solicitados");
           
        }
   }
    
    private void validarSolicitudes(List<Proyecto> proyectosSeleccionados) throws ReglaDeNegocioExcepcion {

        if (proyectosSeleccionados == null) {
            
            throw new ReglaDeNegocioExcepcion("No hay proyectos seleccionados");
            
        }
        
        if(proyectosSeleccionados.size() != NUMERO_SOLICITUDES) {
            
            throw new ReglaDeNegocioExcepcion("Debe seleccionar exactamente " + NUMERO_SOLICITUDES + "proyectos");
            
        }
        
    }

    
}
