/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Administrador;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de administradores.
 *
 * Proporciona los métodos necesarios para registrar, consultar, inactivar y
 * eliminar administradores dentro del sistema.
 *
 * @author gomes
 */
public interface IAdministradorDAO {
    /**
     * Inserta un administrador en el sistema.
     *
     * @param administrador Administrador que se desea registrar.
     * @return {@code true} si el administrador se registró correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean insertarAdministrador(Administrador administrador) throws OperacionesDeDaoExcepcion;
    /**
     * Inactiva un administrador en el sistema.
     *
     * @return {@code true} si el administrador fue inactivado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean inactivarAdministrador() throws OperacionesDeDaoExcepcion;
    /**
     * Consulta la información de un administrador a partir de su número de
     * personal.
     *
     * @param numeroDePersonal Número de personal del administrador a consultar.
     * @return Objeto {@code Administrador} con la información encontrada o
     * {@code null} si no existe un administrador con el número de personal
     * proporcionado.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public Administrador consultarAdministrador(String numeroDePersonal) throws OperacionesDeDaoExcepcion;
    /**
     * Elimina un administrador del sistema.
     *
     * @param idUsuario Identificador del usuario asociado al administrador.
     * @return {@code true} si el administrador fue eliminado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarAdministrador(int idUsuario) throws OperacionesDeDaoExcepcion;
}
