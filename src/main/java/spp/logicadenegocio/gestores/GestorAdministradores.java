/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.AdministradorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionAdministrador;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.enviodecorreo.EnvioCorreo;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.contrasenas.generadordecontrasenas.GeneradorContrasena;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author gomes
 */
public class GestorAdministradores {
    
    public void ingresarAdministrador(Administrador administrador) throws ReglaDeNegocioExcepcion {
        
        validarAdministrador(administrador);
        int longitudContrasena = 10; 
        String contrasenaPlana = GeneradorContrasena.generarContraseña(longitudContrasena);        
        Usuario usuarioAdministrador = prepararUsuarioParaRegistro(administrador, contrasenaPlana);
        
        guardarAdministradorEnBaseDeDatos(usuarioAdministrador, administrador);
        
        enviarContraseñaPorCorreo(usuarioAdministrador.getCorreoInstitucional(), contrasenaPlana);
        
    }


    private void validarAdministrador(Administrador administrador) throws ReglaDeNegocioExcepcion {
        
        ValidacionAdministrador validacion = new ValidacionAdministrador();
        validacion.sonCamposValidosPorReglaNegocio(administrador);
        
    }

    private Usuario prepararUsuarioParaRegistro(Administrador administrador, String contrasenaPlana) {
        
        Usuario usuarioAdministrador = crearUsuarioAdministrador(administrador);
        String contrasenaHasheada = HasheoContrasena.hashearContraseña(contrasenaPlana);
        usuarioAdministrador.setContraseña(contrasenaHasheada);
        
        return usuarioAdministrador;
        
    }

    private void guardarAdministradorEnBaseDeDatos(Usuario usuarioAdministrador, Administrador administrador) throws ReglaDeNegocioExcepcion {
        
        try {
  
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            AdministradorDAO administradorDAO = new AdministradorDAO();
            
            int idUsuario = usuarioDAO.insertarUsuario(usuarioAdministrador);
            administrador.setIdUsuario(idUsuario);
            
            administradorDAO.insertarAdministrador(administrador);
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico al registrar un nuevo administrador.", e);  
            throw new ReglaDeNegocioExcepcion(e.getMessage());
        
        }
    }

    private void enviarContraseñaPorCorreo(String correoDestino, String contrasenaPlana) throws ReglaDeNegocioExcepcion {
        
        try {
            
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(correoDestino, contrasenaPlana);
            
        } catch(RuntimeException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ReglaDeNegocioExcepcion("El administrador se registró, pero no se pudo enviar la contraseña por correo. Por favor contacte al personal");
       
        }
        
    }
    
    public void reemplazarAdministrador(Administrador administrador)throws ReglaDeNegocioExcepcion{
        
        ingresarAdministrador(administrador);

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
    
    private Usuario crearUsuarioAdministrador(Administrador administrador){
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(administrador.getNombre());
        usuario.setApellidoPaterno(administrador.getApellidoPaterno());
        usuario.setApellidoMaterno(administrador.getApellidoMaterno());
        usuario.setCorreoInstitucional(administrador.getCorreoInstitucional());
        usuario.setEsActivo(true);
        
        return usuario;
    }
    
}
