/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Administrador;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IAdministradorDAO {
    public void insertarAdministrador(Administrador administrador) throws OperacionesDeDaoExcepcion;
    public boolean inactivarAdministrador() throws OperacionesDeDaoExcepcion;
    public Administrador consultarAdministrador(String numeroDePersonal) throws OperacionesDeDaoExcepcion;
    public boolean eliminarAdministrador(int idUsuario) throws OperacionesDeDaoExcepcion;
}
