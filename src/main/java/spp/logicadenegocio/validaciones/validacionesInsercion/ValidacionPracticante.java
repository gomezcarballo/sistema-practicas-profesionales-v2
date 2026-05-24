/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesInsercion;

import java.util.logging.Level;
import java.util.logging.Logger;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Practicante;
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
public class ValidacionPracticante {
    
    private static final Logger bitacora = Logger.getLogger(ValidacionPracticante.class.getName());
    
    private static final int LONGITUD_MAXIMA_NOMBRE = 50;
    private static final int LONGITUD_MAXIMA_APELLIDO = 30;
    
    public boolean ingresarPracticante(Practicante practicante)throws ReglaDeNegocioExcepcion{
        
        sonCamposValidosPorReglaNegocio(practicante);
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(practicante.getNombre());
        usuario.setApellidoPaterno(practicante.getApellidoPaterno());
        usuario.setApellidoMaterno(practicante.getApellidoMaterno());
        usuario.setCorreoInstitucional(practicante.getCorreoInstitucional());
        usuario.setEsActivo(true);
        
        String contraseñaPlana = GeneradorContrasena.generarContraseña(10);        
        String contraseñaHasheada = HasheoContrasena.hashearContraseña(contraseñaPlana);
        usuario.setContraseña(contraseñaHasheada);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        PracticanteDAO practicanteDao = new PracticanteDAO();
        boolean registroExitoso;
        
        try{
            
            int idUsuario = usuarioDao.insertarUsuario(usuario);
            
            practicante.setIdUsuario(idUsuario);
            registroExitoso = practicanteDao.insertarPracticante(practicante);
           
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(usuario.getCorreoInstitucional(), contraseñaPlana);
            
            
        }catch(OperacionesDeDaoExcepcion e){
            
            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo practicante.", e);  
            throw new ReglaDeNegocioExcepcion("No se pudo registrar al Practicante por un problema "
                + "interno del sistema. Intente más tarde.", e);
            
        }catch(RuntimeException e){
            
             bitacora.log(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo enviar la contraseña por correo electronico.");
            
        }
        
        return registroExitoso;
    }
    
    public void sonCamposValidosPorReglaNegocio(Practicante practicante) throws ReglaDeNegocioExcepcion {
        
        String matricula = practicante.getMatricula();
        String nombre = practicante.getNombre();
        String apellidoPaterno = practicante.getApellidoPaterno();
        String apellidoMaterno = practicante.getApellidoMaterno();
        
        if(!matricula.matches("^[sS][0-9]{8}$")){
            
           throw new ReglaDeNegocioExcepcion("Matricula no valida. Debe comenzar con S seguido de 8 números.");
           
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
            LONGITUD_MAXIMA_APELLIDO + "caracteres.");
            
        }
        
        if(apellidoMaterno != null && !(apellidoMaterno.matches("^[\\p{L} ]+$"))){
            
            throw new ReglaDeNegocioExcepcion("El apellido materno solo debe contener letras.");
            
        }
        
        if (apellidoMaterno != null && apellidoMaterno.length() > LONGITUD_MAXIMA_APELLIDO) {
            
            throw new ReglaDeNegocioExcepcion("El apellido materno excede la longitud máxima de" +
            LONGITUD_MAXIMA_APELLIDO + "caracteres.");
            
        }
        
    }
}
