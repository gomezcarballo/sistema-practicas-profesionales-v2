/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.CoordinadorDAO;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionCoordinador;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorCoordinadores {
    
    public void reemplazarCoordinador(Coordinador coordinador)throws ReglaDeNegocioExcepcion{

        if(verificarCoordinadorActivo()){

            inactivarCoordinadorActivo();

        }

        ValidacionCoordinador validacion = new ValidacionCoordinador();

        validacion.ingresarCoordinador(coordinador);

    }
    
    public boolean verificarCoordinadorActivo()throws ReglaDeNegocioExcepcion {

        CoordinadorDAO coordinadorDao = new CoordinadorDAO();

        try{

            return coordinadorDao.existeCoordinadorActivo();

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion( "No se pudo verificar si existe un coordinador activo.");

        }

    }
    
    public List<Coordinador> obtenerCoordinadoresInactivos()throws ReglaDeNegocioExcepcion{
        
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        try{
            
            return coordinadorDao.consultarCoordinadoresInactivos();
            
        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudieron recuperar los coordinadores inactivos.");

        }
        
    }
    
    public void inactivarCoordinadorActivo()throws ReglaDeNegocioExcepcion {

        CoordinadorDAO coordinadorDao = new CoordinadorDAO();

        try{

            coordinadorDao.inactivarCoordinador();

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudo inactivar el coordinador actual.");

        }

    }
     
    public void reactivarCoordinadorInactivo(int idUsuario)throws ReglaDeNegocioExcepcion{
        
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        
        try{
            
            coordinadorDao.reactivarCoordinador(idUsuario);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("No se pudo reactivar al coordinador");
            
        }
        
    }
    
}
