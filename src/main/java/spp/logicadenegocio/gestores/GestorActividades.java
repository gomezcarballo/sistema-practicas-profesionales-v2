/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.ActividadDAO;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorActividades {
    
    public List<Actividad> recuperarActividadesAsignadas()throws ReglaDeNegocioExcepcion{
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        ActividadDAO actividadDAO = new ActividadDAO();
        int idUsuario = sesionUsuario.getIdUsuario();
        
        try{
            
            return actividadDAO.consultarActividadesAsignadas(idUsuario);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("No se pudo consultar las actividades asignadas. ", e);
            
        }
        
        
    }
    
}
