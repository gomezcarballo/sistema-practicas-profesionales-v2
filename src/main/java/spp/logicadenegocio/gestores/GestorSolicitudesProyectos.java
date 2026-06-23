/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.SolicitudProyectosDAO;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorSolicitudesProyectos {
    
    private final int NUMERO_SOLICITUDES = 3;
    
    public List<String> registrarSolicitudes(List<Proyecto> proyectosSeleccionados) throws OperacionesDeDaoExcepcion {   
        
        List<String> listaErrores = validarSolicitudes(proyectosSeleccionados);
        
        if (listaErrores.isEmpty()) {
            
            PracticanteDAO practicanteDAO = new PracticanteDAO();
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuario = sesionUsuario.getIdUsuario();
            
            if (practicanteDAO.tieneProyectoAsignado(idUsuario)) {
                
                listaErrores.add("No puedes solicitar proyectos porque ya tienes uno asignado.");
                
            } else {
                
                SolicitudProyectosDAO solicitudDAO = new SolicitudProyectosDAO();
                
                for (Proyecto proyecto : proyectosSeleccionados) {
                    
                    solicitudDAO.guardarSolicitud(idUsuario, proyecto.getIdProyecto());
                    
                }
                
            }
            
        }
        
        return listaErrores; 
        
    }
    
    public List<Proyecto> recuperarProyectosSolicitados(int idUsuario) throws OperacionesDeDaoExcepcion {
        
        SolicitudProyectosDAO solicitudDAO = new SolicitudProyectosDAO();
        
        return solicitudDAO.obtenerProyectosSolicitados(idUsuario);
        
    }
    
    private List<String> validarSolicitudes(List<Proyecto> proyectosSeleccionados) {
        
        List<String> listaErrores = new ArrayList<>();

        if (proyectosSeleccionados == null || proyectosSeleccionados.isEmpty()) {
            
            listaErrores.add("No hay proyectos seleccionados.");
            
        } else if (proyectosSeleccionados.size() != NUMERO_SOLICITUDES) {
            
            listaErrores.add("Debe seleccionar exactamente " + NUMERO_SOLICITUDES + " proyectos.");
            
        }
        
        return listaErrores;
        
    }

    public int contarSolicitudesPorPracticante(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        SolicitudProyectosDAO solicitudesDAO = new SolicitudProyectosDAO();
        
        return solicitudesDAO.obtenerConteoSolicitudes(idPracticante);
        
    }
    
}
