/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.Test;
import static org.junit.Assert.*;
import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaOrganizacionDAO {
    
    @Test
    public void pruebaInsertarOrganizcionDaoExitosa()throws OperacionesDeDaoExcepcion{
        
        Organizacion organizacion = new Organizacion();
        OrganizacionDAO organizacionDao = new OrganizacionDAO();
        
        organizacion.setNombre("Vengadores");
        organizacion.setDireccion("Av. Siempre Viva no 1");
        organizacion.setSector("Privado");
        organizacion.setEsActivo(true);
        
        boolean registroExitoso = organizacionDao.insertarOrganizacion(organizacion);
        assertTrue(registroExitoso);
        
    }
    
}
