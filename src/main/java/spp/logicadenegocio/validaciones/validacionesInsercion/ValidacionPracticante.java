/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

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
    
    public void ingresarPracticante(Practicante practicante)throws ReglaDeNegocioExcepcion{
        
        sonCamposValidosPorReglaNegocio(practicante);
        
        Usuario usuarioPracticante = crearUsuarioPracticante(practicante);

        String contraseñaPlana = GeneradorContrasena.generarContraseña(10);        
        String contraseñaHasheada = HasheoContrasena.hashearContraseña(contraseñaPlana);
        usuarioPracticante.setContraseña(contraseñaHasheada);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        PracticanteDAO practicanteDao = new PracticanteDAO();
        
        try{
            
            int idUsuario = usuarioDao.insertarUsuario(usuarioPracticante);
            
            practicante.setIdUsuario(idUsuario);
            practicanteDao.insertarPracticante(practicante);
           
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(usuarioPracticante.getCorreoInstitucional(), contraseñaPlana);
            
            
        }catch(OperacionesDeDaoExcepcion e){
            
            bitacora.log(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo practicante.", e);  
            throw new ReglaDeNegocioExcepcion("No se pudo registrar al Practicante por un problema "
                + "interno del sistema. Intente más tarde.", e);
            
        }catch(RuntimeException e){
            
             bitacora.log(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo enviar la contraseña por correo electronico.");
            
        }
        
    }
    
    private Usuario crearUsuarioPracticante(Practicante practicante){
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(practicante.getNombre());
        usuario.setApellidoPaterno(practicante.getApellidoPaterno());
        usuario.setApellidoMaterno(practicante.getApellidoMaterno());
        usuario.setCorreoInstitucional(practicante.getCorreoInstitucional());
        usuario.setEsActivo(true);
        
        return usuario;
    }
    
    public void sonCamposValidosPorReglaNegocio(Practicante practicante) throws ReglaDeNegocioExcepcion {
        
        String matricula = practicante.getMatricula();
        String nombre = practicante.getNombre();
        String apellidoPaterno = practicante.getApellidoPaterno();
        String apellidoMaterno = practicante.getApellidoMaterno();
        
        if(!matricula.matches("^[sS][0-9]{8}$")){
            
           throw new ReglaDeNegocioExcepcion("Matricula no valida. Debe comenzar con S seguido de 8 números.");
           
        }
        
        ValidacionDatos validacionDatosPersonales = new ValidacionDatos();
        
        validacionDatosPersonales.validarNombre(nombre);
        validacionDatosPersonales.validarApellidoPaterno(apellidoPaterno);
        validacionDatosPersonales.validarApellidoMaterno(apellidoMaterno);
        
    }
    
}
