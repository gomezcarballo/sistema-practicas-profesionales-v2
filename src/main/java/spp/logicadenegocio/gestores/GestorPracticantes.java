/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.interfacesdao.IPracticanteDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorPracticantes {
    
    private IPracticanteDAO practicanteDAO;
    
    public List<Practicante> recuperarPracticantesActivos() throws ReglaDeNegocioExcepcion{
        
        try{
               practicanteDAO = new PracticanteDAO();
               return practicanteDAO.consultarPracticantes();

           }catch(OperacionesDeDaoExcepcion e){

               throw new ReglaDeNegocioExcepcion("No se pudieron obtener los practicantes activos");

        }
        
    }
    
    public List<Practicante> obtenerPracticantesConSolicitudes() throws ReglaDeNegocioExcepcion {
    
        try{
            
            practicanteDAO = new PracticanteDAO();
            return practicanteDAO.consultarPracticantesConSolicitudes();
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("No se pudieron obtener los practicantes con solicitudes");
            
        }
    
    }
    
    public void inactivarPracticante(int idPracticante)throws ReglaDeNegocioExcepcion {
       
       try{
          
            practicanteDAO = new PracticanteDAO();
            practicanteDAO.inactivarPracticante(idPracticante);  
          
       }catch(OperacionesDeDaoExcepcion e){
           
           throw new ReglaDeNegocioExcepcion("No se pudo inactivar el practicante");
           
       }
   }
    
}
