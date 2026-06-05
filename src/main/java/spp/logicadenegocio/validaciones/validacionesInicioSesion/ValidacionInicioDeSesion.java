/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesiniciosesion;

import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.hasheodecontrasenas.HasheoContrasena;


/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionInicioDeSesion {
    
    public String autenticarUsuario (String identificador , String contraseñaIngresada) throws ReglaDeNegocioExcepcion {
        
        sonCamposValidosPorReglaNegocio(identificador);
        
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

            validarContraseña(contraseñaIngresada, usuario);
            
            iniciarSesion(usuario, identificador);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion(e);
            
        }
            
        return tipoRol;
        
    }
    
    private void validarContraseña(String contraseñaIngresada, UsuarioEncontrado usuario)throws ReglaDeNegocioExcepcion{
        
        boolean esContraseñaCorrecta = HasheoContrasena.verificarContraseña(contraseñaIngresada,
        usuario.getHashUsuarioEncontrado());
            
        if (!esContraseñaCorrecta) {
            throw new ReglaDeNegocioExcepcion("La contraseña no es correcta");
        }
    }
    
    private void iniciarSesion(UsuarioEncontrado usuario, String identificador){
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();            
        sesionUsuario.iniciarSesion(usuario.getIdUsuarioEncontrado(),usuario.getRolUsuarioEncontrado(),
        identificador, usuario.getHashUsuarioEncontrado());
        
    }
    
    public void sonCamposValidosPorReglaNegocio( String identificador ) throws ReglaDeNegocioExcepcion {
        
        String PATRON_CORREO_ELECTRONICO = "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9-]+)"
            + "*@[A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[_A-Za-z0-9-]+)";
        
        if( !identificador.matches( "^[sS][0-9]{8}$" ) && !identificador.matches( PATRON_CORREO_ELECTRONICO ) ){
            throw new ReglaDeNegocioExcepcion("Identificador no valido. "
            + "Ingresa una matricula o correo institucional");
        }
        
    }
    
}
