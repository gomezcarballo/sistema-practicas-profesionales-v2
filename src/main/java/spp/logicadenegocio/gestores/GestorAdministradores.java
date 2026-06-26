/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.AdministradorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionAdministrador;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.enviodecorreo.EnvioCorreo;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.contrasenas.generadordecontrasenas.GeneradorContrasena;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

/**
 *
 * @author gomes
 */
public class GestorAdministradores {
    
    
    public List<String> validarCamposAdministrador(Administrador administrador) {
        
        ValidacionAdministrador validacion = new ValidacionAdministrador();
        return validacion.validarRegistroAdministrador(administrador);
    
    }

    public void ingresarAdministrador(Administrador administrador) throws OperacionesDeDaoExcepcion, ProcesamientoSistemaExcepcion {
        
        int longitudContraseña = 10;
        String contrasenaPlana = GeneradorContrasena.generarContraseña(longitudContraseña);        
        Usuario usuarioAdministrador = prepararUsuarioParaRegistro(administrador, contrasenaPlana);
        
        guardarAdministradorEnBaseDeDatos(usuarioAdministrador, administrador);
        enviarContraseñaPorCorreo(usuarioAdministrador.getCorreoInstitucional(), contrasenaPlana);
    
    }

    private Usuario prepararUsuarioParaRegistro(Administrador administrador, String contrasenaPlana) {
        
        Usuario usuarioAdministrador = crearUsuarioAdministrador(administrador);
        String contrasenaHasheada = HasheoContrasena.hashearContraseña(contrasenaPlana);
        usuarioAdministrador.setContraseña(contrasenaHasheada);
        
        return usuarioAdministrador;
    
    }

    private void guardarAdministradorEnBaseDeDatos(Usuario usuarioAdministrador, Administrador administrador) throws OperacionesDeDaoExcepcion {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        AdministradorDAO administradorDAO = new AdministradorDAO();

        int idUsuario = usuarioDAO.insertarUsuario(usuarioAdministrador);
        administrador.setIdUsuario(idUsuario);

        administradorDAO.insertarAdministrador(administrador);

    }

    private void enviarContraseñaPorCorreo(String correoDestino, String contrasenaPlana) throws ProcesamientoSistemaExcepcion {
        
        try {
            
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(correoDestino, contrasenaPlana);
            
        } catch (RuntimeException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envío de la contraseña por correo electrónico.", e);
            throw new ProcesamientoSistemaExcepcion("El administrador se registró, pero no se pudo enviar la contraseña por correo. Por favor contacte a soporte técnico.");
        
        }
   
    }
    
    public void reemplazarAdministrador(Administrador administrador, int idAdministradorActual) throws OperacionesDeDaoExcepcion, ProcesamientoSistemaExcepcion {
        
        ingresarAdministrador(administrador);    
        inactivarAdministradorActivo(idAdministradorActual);
    
    }
    
    public void inactivarAdministradorActivo(int idAdministradorActual) throws OperacionesDeDaoExcepcion {
        
        AdministradorDAO administradorDao = new AdministradorDAO();
        administradorDao.inactivarAdministrador(idAdministradorActual);

    }
    
    private Usuario crearUsuarioAdministrador(Administrador administrador) {
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(administrador.getNombre());
        usuario.setApellidoPaterno(administrador.getApellidoPaterno());
        usuario.setApellidoMaterno(administrador.getApellidoMaterno());
        usuario.setCorreoInstitucional(administrador.getCorreoInstitucional());
        usuario.setEsActivo(true);

        return usuario;
    
    }
    
}
