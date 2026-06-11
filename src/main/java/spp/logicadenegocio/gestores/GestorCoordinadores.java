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
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.contrasenas.generadordecontrasenas.GeneradorContrasena;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author gomes
 */
public class GestorCoordinadores {
    
    public void ingresarCoordinador(Coordinador coordinador) throws ReglaDeNegocioExcepcion {
        
        validarCoordinador(coordinador);
        
        String contrasenaPlana = GeneradorContrasena.generarContraseña(10);        
        Usuario usuarioCoordinador = prepararUsuarioParaRegistro(coordinador, contrasenaPlana);
        
        guardarCoordinadorEnBaseDeDatos(usuarioCoordinador, coordinador);
        
        enviarContraseñaPorCorreo(usuarioCoordinador.getCorreoInstitucional(), contrasenaPlana);
        
    }

    private void validarCoordinador(Coordinador coordinador) throws ReglaDeNegocioExcepcion {
        
        ValidacionCoordinador validacion = new ValidacionCoordinador();
        validacion.sonCamposValidosPorReglaNegocio(coordinador);
        
    }

    private Usuario prepararUsuarioParaRegistro(Coordinador coordinador, String contrasenaPlana) {
        
        Usuario usuarioCoordinador = crearUsuarioCoordinador(coordinador);
        String contrasenaHasheada = HasheoContrasena.hashearContraseña(contrasenaPlana);
        usuarioCoordinador.setContraseña(contrasenaHasheada);
        
        return usuarioCoordinador;
        
    }

    private void guardarCoordinadorEnBaseDeDatos(Usuario usuarioCoordinador, Coordinador coordinador) throws ReglaDeNegocioExcepcion {
        
        try {
            
            CoordinadorDAO coordinadorDao = new CoordinadorDAO();
            coordinadorDao.registrarCoordinadorCompleto(usuarioCoordinador, coordinador);
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico al registrar un nuevo coordinador.", e);  
            throw new ReglaDeNegocioExcepcion("No se pudo registrar al Coordinador por un problema interno del sistema. Intente más tarde.", e);
        
        }
        
    }

    private void enviarContraseñaPorCorreo(String correoDestino, String contrasenaPlana) throws ReglaDeNegocioExcepcion {
        
        try {
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(correoDestino, contrasenaPlana);
            
        } catch(RuntimeException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ReglaDeNegocioExcepcion("El coordinador se registró, pero no se pudo enviar la contraseña por correo. Por favor contacte al personal.");
        
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
    
    public void reemplazarCoordinador(Coordinador coordinador)throws ReglaDeNegocioExcepcion{

        if(verificarCoordinadorActivo()){

            inactivarCoordinadorActivo();

        }

        ingresarCoordinador(coordinador);

    }
    
    public boolean verificarCoordinadorActivo()throws ReglaDeNegocioExcepcion {

        CoordinadorDAO coordinadorDao = new CoordinadorDAO();

        try{

            return coordinadorDao.existeCoordinadorActivo();

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion( "No se pudo verificar si existe un coordinador activo.");

        }

    }
    
    public List<Coordinador> obtenerCoordinadoresInactivos()throws ReglaDeNegocioExcepcion{
        
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        try{
            
            return coordinadorDao.consultarCoordinadoresInactivos();
            
        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudieron recuperar los coordinadores inactivos.");

        }
        
    }
    
    public void inactivarCoordinadorActivo()throws ReglaDeNegocioExcepcion {

        CoordinadorDAO coordinadorDao = new CoordinadorDAO();

        try{

            coordinadorDao.inactivarCoordinador();

        }catch(OperacionesDeDaoExcepcion e){

            throw new ReglaDeNegocioExcepcion("No se pudo inactivar el coordinador actual.");

        }

    }
     
    public void reactivarCoordinadorInactivo(int idUsuario)throws ReglaDeNegocioExcepcion{
        
        CoordinadorDAO coordinadorDao = new CoordinadorDAO();
        
        try{
            
            coordinadorDao.reactivarCoordinador(idUsuario);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("No se pudo reactivar al coordinador");
            
        }
        
    }
    
}
