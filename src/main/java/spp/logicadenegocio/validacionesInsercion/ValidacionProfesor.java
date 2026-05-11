/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validacionesInsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.ProfesorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.enviodecorreo.EnvioCorreo;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.generadordecontrasenas.GeneradorContrasena;
import spp.utilerias.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author gomes
 */
public class ValidacionProfesor {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionProfesor.class.getName());
    
    public boolean ingresarProfesor(Profesor profesor)throws ReglaDeNegocioExcepcion{
        
        sonCamposValidosPorReglaNegocio(profesor);
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(profesor.getNombre());
        usuario.setApellidoPaterno(profesor.getApellidoPaterno());
        usuario.setApellidoMaterno(profesor.getApellidoMaterno());
        usuario.setCorreoInstitucional(profesor.getCorreoInstitucional());
        usuario.setEsActivo(true);
        
        String contraseñaPlana = GeneradorContrasena.generarContraseña(10);        
        String contraseñaHasheada = HasheoContrasena.hashearContraseña(contraseñaPlana);
        usuario.setContraseña(contraseñaHasheada);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        ProfesorDAO profesorDao = new ProfesorDAO();
        
        boolean registroExitoso;
        
        try{

            int idUsuario = usuarioDao.insertarUsuario(usuario);
            
            profesor.setIdUsuario(idUsuario);
            registroExitoso = profesorDao.insertarProfesor(profesor);
            
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(usuario.getCorreoInstitucional(), contraseñaPlana);
            
        }catch(OperacionesDeDaoExcepcion e){
            
           bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo profesor.", e);
           
           throw new ReglaDeNegocioExcepcion("No se pudo registrar al Profesor por un problema "
                + "interno del sistema. Intente más tarde.", e);
            
        }
        return registroExitoso;
    }
    
    public void sonCamposValidosPorReglaNegocio(Profesor profesor) throws ReglaDeNegocioExcepcion {
        
        String numeroPersonal = profesor.getNumeroDePersonal();
        String nombre = profesor.getNombre();
        String apellidoPaterno = profesor.getApellidoPaterno();
        String apellidoMaterno = profesor.getApellidoMaterno();
        
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
