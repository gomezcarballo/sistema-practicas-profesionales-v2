/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.ProfesorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionProfesor;
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
public class GestorProfesores {
    
    public void ingresarProfesor(Profesor profesor)throws ReglaDeNegocioExcepcion{
        
        ValidacionProfesor validacion = new ValidacionProfesor();
        validacion.sonCamposValidosPorReglaNegocio(profesor);
        
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
    
    public List<Profesor> obtenerProfesoresInactivos()throws ReglaDeNegocioExcepcion{
        
        ProfesorDAO profesorDao = new ProfesorDAO();
        try{
            
            return profesorDao.consultarProfesoresInactivos();
            
        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudieron recuperar los Profesores inactivos.");

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
    
    public void reactivarProfesorInactivo(int idUsuario)throws ReglaDeNegocioExcepcion{
        
        ProfesorDAO profesorDao = new ProfesorDAO();
        
        try{
            
            profesorDao.reactivarProfesor(idUsuario);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("No se pudo reactivar al profesor");
            
        }
        
    }
    
}
