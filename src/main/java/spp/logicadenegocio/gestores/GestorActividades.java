/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.PracticaDAO;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionActividad;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorActividades {  
    
    public void ingresarActividad(Actividad actividad)throws ReglaDeNegocioExcepcion{
        
        ValidacionActividad validacion = new ValidacionActividad();
        validacion.sonCamposValidosPorReglaDeNegocio(actividad);
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        
        PracticaDAO actividadDAO = new PracticaDAO();
        
        actividad.setIdProfesor(sesionUsuario.getIdUsuario());
        
        try{
            
            actividadDAO.insertarActividad(actividad);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico de base de datos al registrar una nueva actividad.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo registrar la Actividad por un problema "
                + "interno del sistema. Intente más tarde.");
            
        }
        
    }
    
    public List<Actividad> recuperarActividadesAsignadas()throws ReglaDeNegocioExcepcion{
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        PracticaDAO actividadDAO = new PracticaDAO();
        int idUsuario = sesionUsuario.getIdUsuario();
        
        try{
            
            return actividadDAO.consultarActividadesAsignadas(idUsuario);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("No se pudo consultar las actividades asignadas. ", e);
            
        }
        
        
    }
    
}
