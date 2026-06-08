/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesinsercion;

import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.CoordinadorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.bitacora.RegistroErrores;
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
        
    private static final int LONGITUD_MAXIMA_NUMEROPERSONAL = 5;

    public void ingresarCoordinador(Coordinador coordinador) throws ReglaDeNegocioExcepcion{
        
        sonCamposValidosPorReglaNegocio(coordinador);       
        
        Usuario usuarioCoordinador = crearUsuarioCoordinador(coordinador);
        
        String contraseñaPlana = GeneradorContrasena.generarContraseña(10);        
        String contraseñaHasheada = HasheoContrasena.hashearContraseña(contraseñaPlana);
        usuarioCoordinador.setContraseña(contraseñaHasheada);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();

        try{
            
            int idUsuario = usuarioDao.insertarUsuario(usuarioCoordinador);
            
            coordinador.setIdUsuario(idUsuario);
            coordinadorDao.insertarCoordinador(coordinador);
            
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(usuarioCoordinador.getCorreoInstitucional(), contraseñaPlana);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo coordinador.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo registrar al Coordinador por un problema "
                + "interno del sistema. Intente más tarde.");
            
        }catch(RuntimeException e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo enviar la contraseña por correo electronico.");
            
        }
    }
    
    private Usuario crearUsuarioCoordinador(Coordinador coordinador){
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(coordinador.getNombre());
        usuario.setApellidoPaterno(coordinador.getApellidoPaterno());
        usuario.setApellidoMaterno(coordinador.getApellidoMaterno());
        usuario.setCorreoInstitucional(coordinador.getCorreoInstitucional());
        usuario.setEsActivo(true);
      
        return usuario;
    }
    
    public void sonCamposValidosPorReglaNegocio(Coordinador coordinador) throws ReglaDeNegocioExcepcion {
        
        String numeroPersonal = coordinador.getNumeroDePersonal();
        String nombre = coordinador.getNombre();
        String apellidoPaterno = coordinador.getApellidoPaterno();
        String apellidoMaterno = coordinador.getApellidoMaterno();
        
        if( numeroPersonal.length() != LONGITUD_MAXIMA_NUMEROPERSONAL || !numeroPersonal.chars()
           .allMatch(Character::isDigit) ){
            
           throw new ReglaDeNegocioExcepcion("Numero de personal no valido. Debe contener" + 
           LONGITUD_MAXIMA_NUMEROPERSONAL + "digitos.");
           
        }
        
        ValidacionDatos validacionDatosPersonales = new ValidacionDatos();
        
        validacionDatosPersonales.validarNombre(nombre);
        validacionDatosPersonales.validarApellidoPaterno(apellidoPaterno);
        validacionDatosPersonales.validarApellidoMaterno(apellidoMaterno);
        
    }
    
}
