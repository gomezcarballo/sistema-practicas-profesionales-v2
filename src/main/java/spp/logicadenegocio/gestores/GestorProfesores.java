/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.ProfesorDAO;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorProfesores {
    
    public boolean hayCupoProfesores()throws ReglaDeNegocioExcepcion {
        
        ProfesorDAO profesorDao = new ProfesorDAO();
        int cantidadMaximaProfesores = 2;

        try{

            return profesorDao.obtenerCantidadProfesoresActivos() < cantidadMaximaProfesores;

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudo verificar la disponibilidad de profesores.");

        }

    }
    
    public List<Profesor> obtenerProfesoresActivos()throws ReglaDeNegocioExcepcion {

        ProfesorDAO profesorDao = new ProfesorDAO();

        try{

            return profesorDao.consultarProfesoresActivos();

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudo obtener la lista de profesores activos.");

        }

    }
    
    public List<Profesor> obtenerProfesoresInactivos()throws ReglaDeNegocioExcepcion{
        
        ProfesorDAO profesorDao = new ProfesorDAO();
        try{
            
            return profesorDao.consultarProfesoresInactivos();
            
        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudieron recuperar los Profesores inactivos.");

        }
        
    }
    
    public void inactivarProfesor(int idUsuario)throws ReglaDeNegocioExcepcion {

        ProfesorDAO profesorDao = new ProfesorDAO();

        try{

            profesorDao.inactivarProfesor(idUsuario);

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("Error al inactivar al profesor.");

        }

    }
    
    public void reactivarProfesorInactivo(int idUsuario)throws ReglaDeNegocioExcepcion{
        
        ProfesorDAO profesorDao = new ProfesorDAO();
        
        try{
            
            profesorDao.reactivarProfesor(idUsuario);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("No se pudo reactivar al profesor");
            
        }
        
    }
    
}
