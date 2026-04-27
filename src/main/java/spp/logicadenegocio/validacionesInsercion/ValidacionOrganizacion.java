/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionesInsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.OrganizacionDAO;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionOrganizacion {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionOrganizacion.class.getName());
    
    public boolean ingresarOrganizacion(Organizacion organizacion)throws ReglaDeNegocioExcepcion{
        
        OrganizacionDAO organizacionDao = new OrganizacionDAO();
        organizacion.setEsActivo(true);
        boolean registroExitoso;
        
        try{
            
            registroExitoso = organizacionDao.insertarOrganizacion(organizacion);
            
            
        }catch(OperacionesDeDaoExcepcion e){
            
            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar una nueva organización.", e);
            
            throw new ReglaDeNegocioExcepcion("",e);
            
        }
        return registroExitoso;
    }
    
}
