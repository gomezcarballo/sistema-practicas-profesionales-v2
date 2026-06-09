/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.logicadenegocio.validaciones.validacionesiniciosesion.ValidacionInicioDeSesion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorInicioSesion {
    
    public String autenticarUsuario (String identificador , String contraseñaIngresada) throws ReglaDeNegocioExcepcion {
        
        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();
        validacion.sonCamposValidosPorReglaNegocio(identificador);
        
        String tipoRol = null;
        
        try{
            
            UsuarioEncontrado usuario = null;
            
            if( identificador.matches("^[sS][0-9]{8}$") ) {
                
                PracticanteDAO practicanteDao = new PracticanteDAO();
                usuario = practicanteDao.buscarPracticante(identificador);
                
                if(usuario == null){
                    throw new ReglaDeNegocioExcepcion("Practicante no encontrado");                    
                }
                
                tipoRol = "Practicante";
                
            }else{
                
                UsuarioDAO usuarioDao = new UsuarioDAO();
                usuario = usuarioDao.buscarUsuario(identificador);
                
                if(usuario == null){
                    throw new ReglaDeNegocioExcepcion("Usuario no encontrado");                    
                }
                
                tipoRol = usuario.getRolUsuarioEncontrado();
                
            }

            validacion.validarContraseña(contraseñaIngresada, usuario);
            
            iniciarSesion(usuario, identificador);
            
        }catch(OperacionesDeDaoExcepcion e){
           
            throw new ReglaDeNegocioExcepcion(e);
            
        }
            
        return tipoRol;
        
    }
    
    private void iniciarSesion(UsuarioEncontrado usuario, String identificador){
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();            
        sesionUsuario.iniciarSesion(usuario.getIdUsuarioEncontrado(),usuario.getRolUsuarioEncontrado(),
        identificador, usuario.getHashUsuarioEncontrado());
        
    }
    
}
