/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionesInsercion;

import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionOrganizacion {
    
    public String registrarOrganizacion(Organizacion organizacion){
        
        OrganizacionDAO organizacionDao = new OrganizacionDAO();
        organizacion.setEsActivo(true);
        
        try{
            
            organizacionDao.insertarOrganizacion(organizacion);
            return "Organización registrado correctamente";
            
        }catch(OperacionesDeDaoExcepcion e){
            
            return "No se pudo registrar. Intente más tarde.";
        }
        
    }
}
