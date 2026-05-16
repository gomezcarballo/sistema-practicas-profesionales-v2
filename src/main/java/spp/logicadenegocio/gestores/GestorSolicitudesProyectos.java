/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.SolicitudDAO;
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
            
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuario = sesionUsuario.getIdUsuario();
            
            SolicitudDAO solicitudDAO = new SolicitudDAO();

            for(Proyecto proyecto : proyectosSeleccionados) {

                solicitudDAO.guardarSolicitud(idUsuario, proyecto.getIdProyecto());
                
            }

        } catch(OperacionesDeDaoExcepcion e) {

            throw new ReglaDeNegocioExcepcion("No se pudieron registrar las solicitudes");
            
        }
    }
    
    private void validarSolicitudes(List<Proyecto> proyectosSeleccionados) throws ReglaDeNegocioExcepcion {

        if(proyectosSeleccionados.size() != NUMERO_SOLICITUDES) {
            throw new ReglaDeNegocioExcepcion("Debe seleccionar exactamente 3 proyectos");
        }
        
    }

    
}
