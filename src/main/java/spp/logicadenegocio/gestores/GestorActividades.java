/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdao.PracticaDAO;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionActividad;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorActividades {  
    
    public List<String> validarCamposDeActividad(Actividad actividad) throws OperacionesDeDaoExcepcion {
        
        ValidacionActividad validacion = new ValidacionActividad();
        
        List<String> listaValidaciones = new ArrayList<>();    

        listaValidaciones = validacion.validarRegistroActividad(actividad);
        
        return listaValidaciones;
    }

    public boolean ingresarActividad(Actividad actividad) throws OperacionesDeDaoExcepcion {
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        
        PracticaDAO actividadDAO = new PracticaDAO();
        
        boolean registroExitoso = false;
        
        actividad.setIdProfesor(sesionUsuario.getIdUsuario());
        
        actividadDAO.insertarActividad(actividad);
        
        registroExitoso = true;
 
        return registroExitoso;
    
    }
    
    public List<Actividad> consultarActividadesAsignadas() throws OperacionesDeDaoExcepcion {
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        
        PracticaDAO actividadDAO = new PracticaDAO();
        
        int idUsuario = sesionUsuario.getIdUsuario();
        
        return actividadDAO.consultarActividadesAsignadas(idUsuario);
        
    }
    
}
