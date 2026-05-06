/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionesInsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.CoordinadorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.enviodecorreo.EnvioCorreo;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.generadordecontrasenas.GeneradorContrasena;
import spp.utilerias.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionCoordinador {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionCoordinador.class.getName());

    public void ingresarCoordinador(Coordinador coordinador) throws ReglaDeNegocioExcepcion{
        
        sonCamposValidosPorReglaNegocio(coordinador);
        
        
        Usuario usuario = new Usuario();
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        
        usuario.setNombre(coordinador.getNombre());
        usuario.setApellidoPaterno(coordinador.getApellidoPaterno());
        usuario.setApellidoMaterno(coordinador.getApellidoMaterno());
        usuario.setCorreoInstitucional(coordinador.getCorreoInstitucional());
        usuario.setEsActivo(true);
        
        String contraseñaPlana = GeneradorContrasena.generarContraseña(10);        
        String contraseñaHasheada = HasheoContrasena.hashearContraseña(contraseñaPlana);
        usuario.setContraseña(contraseñaHasheada);

        try{
            
            int idUsuario = usuarioDao.insertarUsuario(usuario);
            
            coordinador.setIdUsuario(idUsuario);
            coordinadorDao.insertarCoordinador(coordinador);
            
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(usuario.getCorreoInstitucional(), contraseñaPlana);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo coordinador.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo registrar al Coordinador por un problema "
                + "interno del sistema. Intente más tarde.");
            
        }catch(RuntimeException e){
             bitacora.log(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo enviar la contraseña por correo electronico.");
        }
    }
    
    public void sonCamposValidosPorReglaNegocio(Coordinador coordinador) throws ReglaDeNegocioExcepcion {
        
        String numeroPersonal = coordinador.getNumeroDePersonal();
        String nombre = coordinador.getNombre();
        String apellidoPaterno = coordinador.getApellidoPaterno();
        String apellidoMaterno = coordinador.getApellidoMaterno();
        
        if( numeroPersonal.length() != 5 || !numeroPersonal.chars().allMatch(Character::isDigit) ){
           throw new ReglaDeNegocioExcepcion("Numero de personal no valido. Debe contener 5 digitos.");
        }
        
        if( !(nombre.matches("^[\\p{L} ]+$") ) ){
            throw new ReglaDeNegocioExcepcion("El nombre solo debe contener letras.");
        }
        
        if( nombre.length() > 50 ){
            throw new ReglaDeNegocioExcepcion("El nombre excede la longitud maxima de 50 caracteres");
        }
        
        if( !(apellidoPaterno.matches("^[\\p{L} ]+$") ) ){
            throw new ReglaDeNegocioExcepcion("El apellido paterno solo debe contener letras.");
        }
        
        if( apellidoPaterno.length() > 30 ){
            throw new ReglaDeNegocioExcepcion("El apellido paterno excede la longitud maxima de 30 caracteres.");
        }
        
        if( apellidoMaterno != null && !(apellidoMaterno.matches("^[\\p{L} ]+$") ) ){
            throw new ReglaDeNegocioExcepcion("El apellido materno solo debe contener letras.");
        }
        
        if ( apellidoMaterno != null && apellidoMaterno.length() > 30 ) {
            throw new ReglaDeNegocioExcepcion("El apellido materno excede la longitud máxima de 30 caracteres.");
        }
        
    }
}
