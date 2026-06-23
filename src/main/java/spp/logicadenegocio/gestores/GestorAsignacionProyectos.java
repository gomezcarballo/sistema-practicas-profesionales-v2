/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorAsignacionProyectos {
    
    public List<String> asignarProyecto(int idProyecto, int idUsuario) throws OperacionesDeDaoExcepcion {
        
        List<String> listaErrores = new ArrayList<>();
        
        PracticanteDAO practicanteDAO = new PracticanteDAO();
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        
        if (practicanteDAO.tieneProyectoAsignado(idUsuario)) {
            
            listaErrores.add("El practicante ya tiene un proyecto asignado.");
            
        } else if (!proyectoDAO.disminuirCupoProyecto(idProyecto)) {
            
            listaErrores.add("El proyecto ya no tiene cupo disponible.");
            
        } else if (!proyectoDAO.asignarProyecto(idProyecto, idUsuario)) {
            
            listaErrores.add("No es posible asignar el proyecto, intentelo de nuevo.");
            
        }
        
        return listaErrores;
        
    }
    
}
