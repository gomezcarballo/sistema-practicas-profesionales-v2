/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de usuarios.
 *
 * Proporciona los métodos necesarios para registrar, consultar, actualizar,
 * eliminar y buscar usuarios dentro del sistema.
 *
 * @author gomes
 */
public interface IUsuarioDAO {
    /**
     * Inserta un usuario en el sistema.
     *
     * @param usuario Usuario que se desea registrar.
     * @return Identificador generado del usuario insertado.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public int insertarUsuario(Usuario usuario)throws OperacionesDeDaoExcepcion;
    /**
     * Consulta un usuario a partir de su identificador.
     *
     * @param idUsuario Identificador del usuario que se desea consultar.
     * @return Objeto {@code Usuario} con la información encontrada o
     * {@code null} si no existe un usuario con el identificador proporcionado.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public Usuario consultarUsuario(int idUsuario)throws OperacionesDeDaoExcepcion;
    /**
     * Elimina un usuario del sistema.
     *
     * @param idUsuario Identificador del usuario que se desea eliminar.
     * @return {@code true} si el usuario fue eliminado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarUsuario(int idUsuario)throws OperacionesDeDaoExcepcion;
    /**
     * Actualiza la contraseña de un usuario.
     *
     * @param idUsuario Identificador del usuario cuya contraseña se desea actualizar.
     * @param nuevaContraseña Nueva contraseña que se asignará al usuario.
     * @return {@code true} si la contraseña fue actualizada correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean actualizarContraseña(int idUsuario, String nuevaContraseña)throws OperacionesDeDaoExcepcion;
    /**
     * Busca un usuario a partir de su correo institucional.
     *
     * @param correoInstitucional Correo institucional del usuario.
     * @return Objeto {@code UsuarioEncontrado} con la información del usuario
     * encontrado o {@code null} si no existe coincidencia.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public UsuarioEncontrado buscarUsuario(String correoInstitucional)throws OperacionesDeDaoExcepcion;
    /**
     * Obtiene el identificador de un usuario a partir de su correo.
     *
     * @param correo Correo electrónico del usuario.
     * @return Identificador del usuario si existe; en caso contrario,
     * puede devolver un valor inválido o indicar ausencia de registro.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public int buscarIdPorCorreo(String correo) throws OperacionesDeDaoExcepcion;
}
