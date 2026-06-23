/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.CredencialContraseña;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author gomes
 */
public class GestorCambioContraseña {
    
    public void actualizarContraseña(Usuario usuario, CredencialContraseña credenciales)throws OperacionesDeDaoExcepcion {
         
        String contraseñaNuevaHasheada = HasheoContrasena.hashearContraseña(credenciales.getContraseñaNueva());
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        usuarioDAO.actualizarContraseña(usuario.getIdUsuario(), contraseñaNuevaHasheada);

        SesionUsuario.getInstancia().setHashContrasena(contraseñaNuevaHasheada);

    }
    
}
