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
    
    public void ingresarOrganizacion(Organizacion organizacion)throws ReglaDeNegocioExcepcion{
        
        sonCamposValidosPorReglaNegocio(organizacion);
        
        OrganizacionDAO organizacionDao = new OrganizacionDAO();
        organizacion.setEsActivo(true);
        
        try{
            
            organizacionDao.insertarOrganizacion(organizacion);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar una nueva organización.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo registrar la Organización por un problema "
                + "interno del sistema. Intente más tarde.");
            
        }catch(RuntimeException e){
             bitacora.log(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo enviar la contraseña por correo electronico.");
        }
    }
    
    public void sonCamposValidosPorReglaNegocio(Organizacion organizacion) throws ReglaDeNegocioExcepcion {
        
        String nombre = organizacion.getNombre();
        String direccion = organizacion.getDireccion();
        
        if( !(nombre.matches("^[\\p{L} ]+$") ) ){
            throw new ReglaDeNegocioExcepcion("El nombre solo debe contener letras.");
        }
        
        if( nombre.length() > 50 ){
            throw new ReglaDeNegocioExcepcion("El nombre excede la longitud maxima de 50 caracteres");
        }
        if( !(direccion.matches("^[\\p{L} ]+$") ) ){
            throw new ReglaDeNegocioExcepcion("La dirección solo debe contener letras.");
        }
        
        if( nombre.length() > 50 ){
            throw new ReglaDeNegocioExcepcion("La dirección excede la longitud maxima de 50 caracteres");
        }
        
    }
}
