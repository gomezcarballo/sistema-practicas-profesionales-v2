/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.interfacesdao.IUsuarioDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorPerfilUsuario {
    
    private IUsuarioDAO usuarioDAO;
    
    public Usuario recuperarUsuario(int idUsuario) throws ReglaDeNegocioExcepcion {

        try {
            
            usuarioDAO = new UsuarioDAO();
            return usuarioDAO.consultarUsuario(idUsuario);

        } catch (OperacionesDeDaoExcepcion e) {

            throw new ReglaDeNegocioExcepcion("No se pudo recuperar la información del usuario");

        }

    }   
    
}
