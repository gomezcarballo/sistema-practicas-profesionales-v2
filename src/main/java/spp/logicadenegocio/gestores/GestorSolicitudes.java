/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.SolicitudDAO;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorSolicitudes {
    
    public void registrarSolicitudes(int idUsuario,List<Proyecto> proyectosSeleccionados)
    throws ReglaDeNegocioExcepcion {

        if(proyectosSeleccionados.isEmpty()) {

            throw new ReglaDeNegocioExcepcion("Debe seleccionar al menos un proyecto");
            
        }

        if(proyectosSeleccionados.size() > 3) {

            throw new ReglaDeNegocioExcepcion("Solo puede seleccionar 3 proyectos");
        }

        try {

            SolicitudDAO solicitudDAO = new SolicitudDAO();

            for(Proyecto proyecto : proyectosSeleccionados) {

                solicitudDAO.guardarSolicitud(idUsuario, proyecto.getIdProyecto());
                
            }

        } catch(OperacionesDeDaoExcepcion e) {

            throw new ReglaDeNegocioExcepcion("No se pudieron registrar las solicitudes");
            
        }
    }
    
}
