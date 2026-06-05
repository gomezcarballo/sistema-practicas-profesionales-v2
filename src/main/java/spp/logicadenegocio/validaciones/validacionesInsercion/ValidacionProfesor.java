/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.ProfesorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Profesor;
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
public class ValidacionProfesor {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionProfesor.class.getName());
    
    private static final int LONGITUD_MAXIMA_NUMEROPERSONAL = 5;    
    
    public void ingresarProfesor(Profesor profesor)throws ReglaDeNegocioExcepcion{
        
        sonCamposValidosPorReglaNegocio(profesor);
        
        Usuario usuarioProfesor = crearUsuarioProfesor(profesor);
        
        String contraseñaPlana = GeneradorContrasena.generarContraseña(10);        
        String contraseñaHasheada = HasheoContrasena.hashearContraseña(contraseñaPlana);
        usuarioProfesor.setContraseña(contraseñaHasheada);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        ProfesorDAO profesorDao = new ProfesorDAO();
                
        try{

            int idUsuario = usuarioDao.insertarUsuario(usuarioProfesor);
            
            profesor.setIdUsuario(idUsuario);
            profesorDao.insertarProfesor(profesor);
            
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(usuarioProfesor.getCorreoInstitucional(), contraseñaPlana);
            
        }catch(OperacionesDeDaoExcepcion e){
            
           RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo profesor.", e);          
           throw new ReglaDeNegocioExcepcion("No se pudo registrar al Profesor por un problema "
                + "interno del sistema. Intente más tarde.", e);
            
        }catch(RuntimeException e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);             
            throw new ReglaDeNegocioExcepcion("No se pudo enviar la contraseña por correo electronico.");
            
        }
        
    }
    
    private Usuario crearUsuarioProfesor(Profesor profesor){
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(profesor.getNombre());
        usuario.setApellidoPaterno(profesor.getApellidoPaterno());
        usuario.setApellidoMaterno(profesor.getApellidoMaterno());
        usuario.setCorreoInstitucional(profesor.getCorreoInstitucional());
        usuario.setEsActivo(true);
        
        return usuario;
    }   
    
    public void sonCamposValidosPorReglaNegocio(Profesor profesor) throws ReglaDeNegocioExcepcion {
        
        String numeroPersonal = profesor.getNumeroDePersonal();
        String nombre = profesor.getNombre();
        String apellidoPaterno = profesor.getApellidoPaterno();
        String apellidoMaterno = profesor.getApellidoMaterno();
        
        if(numeroPersonal.length() != LONGITUD_MAXIMA_NUMEROPERSONAL || !numeroPersonal.chars()
        .allMatch(Character::isDigit)){
            
           throw new ReglaDeNegocioExcepcion("Numero de personal no valido. Debe contener" + 
           LONGITUD_MAXIMA_NUMEROPERSONAL +  "digitos.");
           
        }
        
        ValidacionDatos validacionDatosPersonales = new ValidacionDatos();
        
        validacionDatosPersonales.validarNombre(nombre);
        validacionDatosPersonales.validarApellidoPaterno(apellidoPaterno);
        validacionDatosPersonales.validarApellidoMaterno(apellidoMaterno);
        
    }
}
