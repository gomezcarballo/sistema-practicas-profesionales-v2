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
import spp.utilerias.contrasenas.generadordecontrasenas.GeneradorContrasena;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

/**
 *
 * @author gomes
 */
public class GestorProfesores {
    
    public List<String> validarCamposProfesor(Profesor profesor) {
        
        ValidacionProfesor validacion = new ValidacionProfesor();
        return validacion.validarRegistroProfesor(profesor);
    
    }
    
    public void ingresarProfesor(Profesor profesor) throws OperacionesDeDaoExcepcion, ProcesamientoSistemaExcepcion {
                
        String contrasenaPlana = GeneradorContrasena.generarContraseña(10);        
        Usuario usuarioProfesor = prepararUsuarioParaRegistro(profesor, contrasenaPlana);
        
        guardarProfesorEnBaseDeDatos(usuarioProfesor, profesor);
        
        enviarContraseñaPorCorreo(usuarioProfesor.getCorreoInstitucional(), contrasenaPlana);
        
    }
    
    private Usuario prepararUsuarioParaRegistro(Profesor profesor, String contrasenaPlana) {
        
        Usuario usuarioProfesor = crearUsuarioProfesor(profesor);
        String contrasenaHasheada = HasheoContrasena.hashearContraseña(contrasenaPlana);
        usuarioProfesor.setContraseña(contrasenaHasheada);
        return usuarioProfesor;
        
    }

    private void guardarProfesorEnBaseDeDatos(Usuario usuarioProfesor, Profesor profesor) throws OperacionesDeDaoExcepcion {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        ProfesorDAO profesorDAO = new ProfesorDAO();
        
        int idUsuario = usuarioDAO.insertarUsuario(usuarioProfesor);
        profesor.setIdUsuario(idUsuario);
        
        profesorDAO.insertarProfesor(profesor);
   
    }

    private void enviarContraseñaPorCorreo(String correoDestino, String contrasenaPlana) throws ProcesamientoSistemaExcepcion {
        
        try {
            
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(correoDestino, contrasenaPlana);
            
        } catch(RuntimeException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ProcesamientoSistemaExcepcion("El profesor se registró, pero no se pudo enviar la contraseña por correo. Por favor contacte al personal.");
        
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
    
    public boolean hayCupoProfesores() throws OperacionesDeDaoExcepcion {
        
        int cantidadMaximaProfesores = 2;
        ProfesorDAO profesorDao = new ProfesorDAO();
        return profesorDao.obtenerCantidadProfesoresActivos() < cantidadMaximaProfesores;
    
    }
    
    public List<Profesor> obtenerProfesoresActivos() throws OperacionesDeDaoExcepcion {
        
        ProfesorDAO profesorDao = new ProfesorDAO();
        return profesorDao.consultarProfesoresActivos();
   
    }
    
    public List<Profesor> obtenerProfesoresInactivos() throws OperacionesDeDaoExcepcion {
        
        ProfesorDAO profesorDao = new ProfesorDAO();
        return profesorDao.consultarProfesoresInactivos();
    
    }
    
    public void inactivarProfesor(int idUsuario) throws OperacionesDeDaoExcepcion {
        
        ProfesorDAO profesorDao = new ProfesorDAO();
        profesorDao.inactivarProfesor(idUsuario);
    
    }
    
    public void reactivarProfesorInactivo(int idUsuario) throws OperacionesDeDaoExcepcion {
        
        ProfesorDAO profesorDao = new ProfesorDAO();
        profesorDao.reactivarProfesor(idUsuario);
    
    }
    
}
