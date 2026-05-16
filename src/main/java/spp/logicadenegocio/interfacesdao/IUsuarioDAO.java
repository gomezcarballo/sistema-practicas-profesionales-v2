/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IUsuarioDAO {
    public int insertarUsuario(Usuario usuario)throws OperacionesDeDaoExcepcion;
    public Usuario consultarUsuario(int idUsuario)throws OperacionesDeDaoExcepcion;
    public boolean eliminarUsuario(int idUsuario)throws OperacionesDeDaoExcepcion;
    public boolean actualizarUsuario(Usuario usuario)throws OperacionesDeDaoExcepcion;
    public UsuarioEncontrado buscarUsuario(String correoInstitucional)throws OperacionesDeDaoExcepcion;
    public boolean existeCorreo(String correo)throws OperacionesDeDaoExcepcion;
}
