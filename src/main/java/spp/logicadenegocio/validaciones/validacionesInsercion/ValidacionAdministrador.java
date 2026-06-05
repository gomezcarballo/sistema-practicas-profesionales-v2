/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.AdministradorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.enviodecorreo.EnvioCorreo;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.generadordecontrasenas.GeneradorContrasena;
import spp.utilerias.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author gomes
 */
public class ValidacionAdministrador {
        
    public void ingresarAdministrador(Administrador administrador)throws ReglaDeNegocioExcepcion{
        
        sonCamposValidosPorReglaNegocio(administrador);
        
        Usuario usuarioAdministrador = crearUsuarioAdministrador(administrador);

        String contraseñaPlana = GeneradorContrasena.generarContraseña(10);        
        String contraseñaHasheada = HasheoContrasena.hashearContraseña(contraseñaPlana);
        usuarioAdministrador.setContraseña(contraseñaHasheada);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        AdministradorDAO administradorDAO = new AdministradorDAO();
        
        try{
            
            int idUsuario = usuarioDao.insertarUsuario(usuarioAdministrador);
            
            administrador.setIdUsuario(idUsuario);
            administradorDAO.insertarAdministrador(administrador);
           
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(usuarioAdministrador.getCorreoInstitucional(), contraseñaPlana);
            
            
        }catch(OperacionesDeDaoExcepcion e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo administrador.", e);  
            throw new ReglaDeNegocioExcepcion("No se pudo registrar al Administrador por un problema "
                + "interno del sistema. Intente más tarde.", e);
            
        }catch(RuntimeException e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo enviar la contraseña por correo electronico.");
            
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
    
    public void sonCamposValidosPorReglaNegocio(Administrador administrador) throws ReglaDeNegocioExcepcion {
        
        String numeroPersonal = administrador.getNumeroDePersonal();
        String nombre = administrador.getNombre();
        String apellidoPaterno = administrador.getApellidoPaterno();
        String apellidoMaterno = administrador.getApellidoMaterno();
        
        int longitudMaximaNumeroPersonal = 5;
        
        if( numeroPersonal.length() != longitudMaximaNumeroPersonal || !numeroPersonal.chars()
           .allMatch(Character::isDigit) ){
            
           throw new ReglaDeNegocioExcepcion("Numero de personal no valido. Debe contener" + 
           longitudMaximaNumeroPersonal + "digitos.");
           
        }
        
        ValidacionDatos validacionDatosPersonales = new ValidacionDatos();
        
        validacionDatosPersonales.validarNombre(nombre);
        validacionDatosPersonales.validarApellidoPaterno(apellidoPaterno);
        validacionDatosPersonales.validarApellidoMaterno(apellidoMaterno);
        
    }
    
}
