/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorAsignacionProyectos {
    public void asignarProyecto( int idProyecto, int idUsuario) throws ReglaDeNegocioExcepcion {

        try {
            
            PracticanteDAO practicanteDAO = new PracticanteDAO();
            practicanteDAO.asignarProyecto(idProyecto, idUsuario);

        } catch (OperacionesDeDaoExcepcion e) {

            throw new ReglaDeNegocioExcepcion("No se pudo asignar el proyecto");
        }
        
    }
    
}
