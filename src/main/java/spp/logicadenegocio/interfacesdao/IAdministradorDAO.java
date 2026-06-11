/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.sql.Connection;
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IAdministradorDAO {
    public boolean insertarAdministrador(Administrador administrador) throws OperacionesDeDaoExcepcion;
    public boolean inactivarAdministrador() throws OperacionesDeDaoExcepcion;
    public Administrador consultarAdministrador(String numeroDePersonal) throws OperacionesDeDaoExcepcion;
    public boolean eliminarAdministrador(int idUsuario) throws OperacionesDeDaoExcepcion;
    public boolean insertarAdministradorConTransaccion(Administrador administrador, Connection conexion) throws OperacionesDeDaoExcepcion;
    public void registrarAdministradorCompleto(Usuario usuario, Administrador administrador) throws OperacionesDeDaoExcepcion;
}
