/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.logicadenegocio.validaciones.validacionesiniciosesion.ValidacionInicioDeSesion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
 
/**
 *
 * @author gomes
 */
public class GestorInicioSesion {
    
    public boolean esFormatoValido(String identificador){

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();
        boolean esValido = false;
        esValido = validacion.sonFormatosValidos(identificador);
        return esValido;

    }

    public UsuarioEncontrado autenticarUsuario (String identificador) throws OperacionesDeDaoExcepcion{
         
        UsuarioEncontrado usuarioEncontrado = null;
        
        String patronMatricula = "^[sS][0-9]{8}$";
        
        if( identificador.matches(patronMatricula) ) {
            
            PracticanteDAO practicanteDao = new PracticanteDAO();
            usuarioEncontrado = practicanteDao.buscarPracticante(identificador);
            
            if(usuarioEncontrado != null){
                usuarioEncontrado.setRolUsuarioEncontrado("Practicante");
            }
            
        }else{
            
            UsuarioDAO usuarioDao = new UsuarioDAO();
            usuarioEncontrado = usuarioDao.buscarUsuario(identificador);
            
        }
        
        return usuarioEncontrado;
        
    }
    
    public boolean esContraseñaCorrecta (UsuarioEncontrado usuario, String contraseñaIngresada){

        ValidacionInicioDeSesion validacion = new ValidacionInicioDeSesion();
        boolean esContraseñaValida = false;
        esContraseñaValida = validacion.validarContraseña(contraseñaIngresada, usuario);
        
        return esContraseñaValida;

    }
    public void iniciarSesion(UsuarioEncontrado usuario, String identificador){
        
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();   
        int idUsuarioEncontrado = usuario.getIdUsuarioEncontrado();
        String rolUsuarioEcontrado = usuario.getRolUsuarioEncontrado();
        String hashUsuarioEncontrado = usuario.getHashUsuarioEncontrado();

        sesionUsuario.iniciarSesion(idUsuarioEncontrado,rolUsuarioEcontrado,
        identificador, hashUsuarioEncontrado);
        
    }
    
}
