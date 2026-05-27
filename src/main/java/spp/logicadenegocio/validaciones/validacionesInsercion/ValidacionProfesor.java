/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesInsercion;

import java.util.List;
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
    
    private static final int LONGITUD_MAXIMA_NUMEROPERSONAL = 5;
    private static final int LONGITUD_MAXIMA_NOMBRE = 50;
    private static final int LONGITUD_MAXIMA_APELLIDO = 30;
    
    
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
            
        }catch(RuntimeException e){
            
             bitacora.log(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);             
            throw new ReglaDeNegocioExcepcion("No se pudo enviar la contraseña por correo electronico.");
            
        }
        return registroExitoso;
    }
    
    public boolean hayCupoProfesores()throws ReglaDeNegocioExcepcion {
        
        ProfesorDAO profesorDao = new ProfesorDAO();
        int cantidadMaximaProfesores = 2;

        try{

            return profesorDao.obtenerCantidadProfesoresActivos() < cantidadMaximaProfesores;

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudo verificar la disponibilidad de profesores.");

        }

    }
    
    public List<Profesor> obtenerProfesoresActivos()throws ReglaDeNegocioExcepcion {

        ProfesorDAO profesorDao = new ProfesorDAO();

        try{

            return profesorDao.consultarProfesoresActivos();

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudo obtener la lista de profesores activos.");

        }

    }
    
    public void inactivarProfesor(int idUsuario)throws ReglaDeNegocioExcepcion {

        ProfesorDAO profesorDao = new ProfesorDAO();

        try{

            profesorDao.inactivarProfesor(idUsuario);

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("Error al inactivar al profesor.");

        }

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
        
        if(!(nombre.matches("^[\\p{L} ]+$"))){
            
            throw new ReglaDeNegocioExcepcion("El nombre solo debe contener letras.");
            
        }
        
        if(nombre.length() > LONGITUD_MAXIMA_NOMBRE){
            
            throw new ReglaDeNegocioExcepcion("El nombre excede la longitud maxima de" + 
            LONGITUD_MAXIMA_NOMBRE + "caracteres");
            
        }
        
        if(!(apellidoPaterno.matches("^[\\p{L} ]+$"))){
            
            throw new ReglaDeNegocioExcepcion("El apellido paterno solo debe contener letras.");
            
        }
        
        if(apellidoPaterno.length() > LONGITUD_MAXIMA_APELLIDO){
            
            throw new ReglaDeNegocioExcepcion("El apellido paterno excede la longitud maxima de" + 
            LONGITUD_MAXIMA_APELLIDO +"caracteres.");
            
        }
        
        if(apellidoMaterno != null && !(apellidoMaterno.matches("^[\\p{L} ]+$"))){
            
            throw new ReglaDeNegocioExcepcion("El apellido materno solo debe contener letras.");
            
        }
        
        if (apellidoMaterno != null && apellidoMaterno.length() > LONGITUD_MAXIMA_APELLIDO) {
            
            throw new ReglaDeNegocioExcepcion("El apellido materno excede la longitud máxima de" + 
            LONGITUD_MAXIMA_APELLIDO +"caracteres.");
            
        }
        
    }
}
