/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorAsignacionProyectos {
    
    public void asignarProyecto( int idProyecto, int idUsuario) throws ReglaDeNegocioExcepcion {

        try {
            
            ProyectoDAO proyectoDAO = new ProyectoDAO();
            proyectoDAO.asignarProyecto(idProyecto, idUsuario);

        } catch (OperacionesDeDaoExcepcion e) {

            throw new ReglaDeNegocioExcepcion("No se pudo asignar el proyecto");
        }
        
    }
    
}
