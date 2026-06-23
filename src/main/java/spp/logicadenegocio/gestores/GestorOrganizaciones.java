/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionOrganizacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorOrganizaciones {
    
    public List<String> validarCamposOrganizacion(Organizacion organizacion) {
        
        ValidacionOrganizacion validacion = new ValidacionOrganizacion();
        return validacion.validarRegistroOrganizacion(organizacion);
    
    }

    public void ingresarOrganizacion(Organizacion organizacion) throws OperacionesDeDaoExcepcion {
        
        OrganizacionDAO organizacionDao = new OrganizacionDAO();
        organizacion.setEsActivo(true);
        organizacionDao.insertarOrganizacion(organizacion);

    
    }
    
    public void actualizarOrganizacion(Organizacion organizacion) throws OperacionesDeDaoExcepcion {
        
        OrganizacionDAO organizacionDao = new OrganizacionDAO();

        organizacionDao.actualizarOrganizacion(organizacion);
    
    }
    
    public List<Organizacion> recuperarOrganizacionesActivas() throws OperacionesDeDaoExcepcion {
        
        OrganizacionDAO organizacionDAO = new OrganizacionDAO();
        return organizacionDAO.obtenerOrganizacionesActivas();
    
    }

    public void inactivarOrganizacion(int idOrganizacion) throws OperacionesDeDaoExcepcion {
        
        OrganizacionDAO organizacionDAO = new OrganizacionDAO();
        ProyectoDAO proyectoDAO = new ProyectoDAO();
        
        organizacionDAO.inactivarOrganizacion(idOrganizacion);
        proyectoDAO.inactivarProyectosDeOrganizacion(idOrganizacion);
    
    }
    
}
