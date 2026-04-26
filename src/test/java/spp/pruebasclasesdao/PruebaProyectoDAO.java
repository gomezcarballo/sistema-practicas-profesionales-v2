/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdao.ProyectoDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class PruebaProyectoDAO {
    
    Organizacion organizacion = new Organizacion();
    
    @Before
    public void recursoInsertarOrganizacionVinculada()throws OperacionesDeDaoExcepcion{
        OrganizacionDAO organizacionDao = new OrganizacionDAO();
        organizacion.setIdOrganizacion(2);
        organizacion.setNombre("Vought");
        organizacion.setDireccion("Torre Vought en Nueva York");
        organizacion.setSector("Privado");
        organizacion.setEsActivo(true);
        
        organizacionDao.insertarOrganizacion(organizacion);
        
    }
    
    @Test
    public void pruebaRegistroProyectoDAOExitosa()throws OperacionesDeDaoExcepcion{
        
        Proyecto proyecto = new Proyecto();
        ProyectoDAO proyectoDao = new ProyectoDAO();
        
        proyecto.setNombre("Proyecto Odessa");
        proyecto.setDescripcion("Crear un superhéroe definitivo");
        proyecto.setCupoMaximo(7);
        proyecto.setNombreResponsable("Godolkin");
        proyecto.setOrganizacion(organizacion);
        proyecto.setEsActivo(true);
        
        boolean registroExitoso = proyectoDao.insertarProyecto(proyecto);
        assertTrue(registroExitoso);
        
    }
    
}
