/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import spp.logicadenegocio.clasesdao.AdministradorDAO;
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionAdministrador;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorAdministradores {
    
    public void reemplazarAdministrador(Administrador administrador)throws ReglaDeNegocioExcepcion{
        
        ValidacionAdministrador validacion = new ValidacionAdministrador();
        
        validacion.ingresarAdministrador(administrador);

        inactivarAdministradorActivo();

    }
    
    public void inactivarAdministradorActivo()throws ReglaDeNegocioExcepcion {

        AdministradorDAO administradorDao = new AdministradorDAO();

        try{

            administradorDao.inactivarAdministrador();

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudo inactivar el administrador actual.");

        }

    }
    
}
