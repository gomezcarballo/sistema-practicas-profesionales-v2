/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.CoordinadorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionCoordinador;
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
public class GestorCoordinadores {
    
    private final int LONGITUD_CONTRASENA = 10;
    
    public List<String> validarCamposCoordinador(Coordinador coordinador) {
        ValidacionCoordinador validacion = new ValidacionCoordinador();
        return validacion.validarRegistroCoordinador(coordinador);
    }

    public void ingresarCoordinador(Coordinador coordinador) throws OperacionesDeDaoExcepcion, ProcesamientoSistemaExcepcion {
        
        String contrasenaPlana = GeneradorContrasena.generarContraseña(LONGITUD_CONTRASENA);        
        Usuario usuarioCoordinador = prepararUsuarioParaRegistro(coordinador, contrasenaPlana);
        
        guardarCoordinadorEnBaseDeDatos(usuarioCoordinador, coordinador);
        enviarContraseñaPorCorreo(usuarioCoordinador.getCorreoInstitucional(), contrasenaPlana);
    
    }

    private Usuario prepararUsuarioParaRegistro(Coordinador coordinador, String contrasenaPlana) {
        
        Usuario usuarioCoordinador = crearUsuarioCoordinador(coordinador);
        String contrasenaHasheada = HasheoContrasena.hashearContraseña(contrasenaPlana);
        usuarioCoordinador.setContraseña(contrasenaHasheada);
        
        return usuarioCoordinador;
    
    }

    private void guardarCoordinadorEnBaseDeDatos(Usuario usuarioCoordinador, Coordinador coordinador) throws OperacionesDeDaoExcepcion {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        CoordinadorDAO coordinadorDAO = new CoordinadorDAO();
        
        int idUsuario = usuarioDAO.insertarUsuario(usuarioCoordinador);
        coordinador.setIdUsuario(idUsuario);
        
        coordinadorDAO.insertarCoordinador(coordinador);
    
    }

    private void enviarContraseñaPorCorreo(String correoDestino, String contrasenaPlana) throws ProcesamientoSistemaExcepcion {
        
        try {
            
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(correoDestino, contrasenaPlana);
            
        } catch(RuntimeException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ProcesamientoSistemaExcepcion("El coordinador se registró, pero no se pudo enviar la contraseña por correo. Por favor contacte al personal.");
        
        }
    
    }
    
    private Usuario crearUsuarioCoordinador(Coordinador coordinador) {
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(coordinador.getNombre());
        usuario.setApellidoPaterno(coordinador.getApellidoPaterno());
        usuario.setApellidoMaterno(coordinador.getApellidoMaterno());
        usuario.setCorreoInstitucional(coordinador.getCorreoInstitucional());
        usuario.setEsActivo(true);
       
        return usuario;
    
    }
    
    public void reemplazarCoordinador(Coordinador coordinador) throws OperacionesDeDaoExcepcion, ProcesamientoSistemaExcepcion {
        
        String contrasenaPlana = GeneradorContrasena.generarContraseña(LONGITUD_CONTRASENA);        
        Usuario usuarioCoordinador = prepararUsuarioParaRegistro(coordinador, contrasenaPlana);
        
        if (verificarCoordinadorActivo()) {
            inactivarCoordinadorActivo();
        }
        
        guardarCoordinadorEnBaseDeDatos(usuarioCoordinador, coordinador);
        enviarContraseñaPorCorreo(usuarioCoordinador.getCorreoInstitucional(), contrasenaPlana);
    
    }
    
    public boolean verificarCoordinadorActivo() throws OperacionesDeDaoExcepcion {
        
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        return coordinadorDao.existeCoordinadorActivo();
    
    }
    
    public List<Coordinador> obtenerCoordinadoresInactivos() throws OperacionesDeDaoExcepcion {
        
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        return coordinadorDao.consultarCoordinadoresInactivos();
    
    }
    
    public void inactivarCoordinadorActivo() throws OperacionesDeDaoExcepcion {
        
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        coordinadorDao.inactivarCoordinador();
    
    }
     
    public void reactivarCoordinadorInactivo(int idUsuario) throws OperacionesDeDaoExcepcion {
        
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        coordinadorDao.reactivarCoordinador(idUsuario);
    
    }
    
    public void reemplazarPorCoordinadorInactivo(int idUsuarioReactivar) throws OperacionesDeDaoExcepcion, 
    ProcesamientoSistemaExcepcion {
        
        if (verificarCoordinadorActivo()) {
            inactivarCoordinadorActivo();
        }

        try {
            
            reactivarCoordinadorInactivo(idUsuarioReactivar);
        
        } catch (OperacionesDeDaoExcepcion e) {
            
            throw new ProcesamientoSistemaExcepcion("Se inactivó al coordinador actual, "
            + "pero falló la reactivación del nuevo. Contacte a soporte técnico.");
        
        }
    
    }
    
}
