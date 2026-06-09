/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.interfacesdao.IOrganizacionDAO;
import spp.logicadenegocio.interfacesdao.IProyectoDAO;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionOrganizacion;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorOrganizaciones {
    
    private IOrganizacionDAO organizacionDAO;
    private IProyectoDAO proyectoDAO;

    public void ingresarOrganizacion(Organizacion organizacion)throws ReglaDeNegocioExcepcion{
        
        ValidacionOrganizacion validacion = new ValidacionOrganizacion();
        validacion.sonCamposValidosPorReglaNegocio(organizacion);
        
        OrganizacionDAO organizacionDao = new OrganizacionDAO();
        organizacion.setEsActivo(true);
        
        try{
            
            organizacionDao.insertarOrganizacion(organizacion);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico de base de datos al registrar una nueva organización.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo registrar la Organización por un problema "
                + "interno del sistema. Intente más tarde.");
            
        }
        
    }
    
    public void actualizarOrganizacion(Organizacion organizacion)throws ReglaDeNegocioExcepcion{
        
        ValidacionOrganizacion validacion = new ValidacionOrganizacion();
        validacion.sonCamposValidosPorReglaNegocio(organizacion);

        OrganizacionDAO organizacionDao = new OrganizacionDAO();

        try{

            organizacionDao.actualizarOrganizacion(organizacion);

        }catch(OperacionesDeDaoExcepcion e){

            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico de base de datos al actualizar una organización.",e);
            throw new ReglaDeNegocioExcepcion("No se pudo actualizar la Organización por un problema "
            + "interno del sistema. Intente más tarde.");

        }
        
    }
    
    public List<Organizacion> recuperarOrganizacionesActivas() throws ReglaDeNegocioExcepcion{

        try{
            
            organizacionDAO = new OrganizacionDAO();
            return organizacionDAO.obtenerOrganizacionesActivas();

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudieron obtener las organizaciones activas");
            
        }

    }

    public void inactivarOrganizacion(int idOrganizacion)throws ReglaDeNegocioExcepcion{
        
        try{
            
            organizacionDAO = new OrganizacionDAO();
            proyectoDAO = new ProyectoDAO();
            organizacionDAO.inactivarOrganizacion(idOrganizacion);
            proyectoDAO.inactivarProyectosDeOrganizacion(idOrganizacion);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("No se pudo inactivar la organización");
        }
    }
    
}
