/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.interfacesdao.IOrganizacionDAO;
import spp.logicadenegocio.interfacesdao.IProyectoDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorOrganizaciones {
    
    private IOrganizacionDAO organizacionDAO;
    private IProyectoDAO proyectoDAO;

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
