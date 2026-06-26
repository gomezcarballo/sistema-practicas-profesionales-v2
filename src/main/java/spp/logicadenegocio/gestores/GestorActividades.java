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
        
        int idUsuario = sesionUsuario.getIdUsuario();

        PracticaDAO actividadDAO = new PracticaDAO();

        List<Actividad> listaActividadesAsignadas;
        
        listaActividadesAsignadas = actividadDAO.consultarActividadesAsignadas(idUsuario);
        
        return listaActividadesAsignadas;
    }
    
}
