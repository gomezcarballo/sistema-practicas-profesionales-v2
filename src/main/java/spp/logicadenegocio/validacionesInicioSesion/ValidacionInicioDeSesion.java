/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionesInicioSesion;

import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;


/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionInicioDeSesion {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionInicioDeSesion.class.getName());
    
    public String inicioDeSesion( String identificador ) throws ReglaDeNegocioExcepcion {
        
        sonCamposValidosPorReglaNegocio( identificador );
        String tipoRol = null;
        
        if( identificador.matches("^[z][sS][0-9]{8}$") ) {
            
            tipoRol = "Practicante";
        }
        
        try{
            
            UsuarioDAO usuarioDao = new UsuarioDAO();
            
            tipoRol = usuarioDao.buscarUsuario( identificador );
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("Error: No se pudo recuperar la información. ", e);
        }
            
        return tipoRol;
    }
    
    public void sonCamposValidosPorReglaNegocio( String identificador ) throws ReglaDeNegocioExcepcion {
        
        String PATRON_CORREO_ELECTRONICO = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)"
            + "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";
        
        if( !identificador.matches( "^[z][sS][0-9]{8}$" ) && !identificador.matches( PATRON_CORREO_ELECTRONICO ) ){
            throw new ReglaDeNegocioExcepcion("Identificador no valido. "
            + "Ingresa una matricula o correo institucional");
        }
    }
    
}
