/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de coordinadores.
 *
 * Proporciona los métodos necesarios para registrar, consultar, inactivar,
 * reactivar, verificar y eliminar coordinadores dentro del sistema.
 *
 * @author gomes
 */
public interface ICoordinadorDAO {
    /**
     * Inserta un coordinador en el sistema.
     *
     * @param coordinador Coordinador que se desea registrar.
     * @return {@code true} si el coordinador se registró correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean insertarCoordinador(Coordinador coordinador)throws OperacionesDeDaoExcepcion;
    /**
     * Consulta la lista de coordinadores que se encuentran inactivos.
     *
     * @return Lista de coordinadores inactivos. Si no existen registros,
     * devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Coordinador> consultarCoordinadoresInactivos() throws OperacionesDeDaoExcepcion;
    /**
     * Inactiva un coordinador en el sistema.
     *
     * @return {@code true} si el coordinador fue inactivado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean inactivarCoordinador()throws OperacionesDeDaoExcepcion;
    /**
     * Reactiva un coordinador previamente inactivo.
     *
     * @param idUsuario Identificador del usuario del coordinador.
     * @return {@code true} si el coordinador fue reactivado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean reactivarCoordinador(int idUsuario)throws OperacionesDeDaoExcepcion;
    /**
     * Verifica si existe al menos un coordinador activo en el sistema.
     *
     * @return {@code true} si existe un coordinador activo; {@code false} en
     * caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean existeCoordinadorActivo() throws OperacionesDeDaoExcepcion;
     /**
     * Elimina un coordinador del sistema.
     *
     * @param idUsuario Identificador del usuario del coordinador.
     * @return {@code true} si el coordinador fue eliminado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarCoordinador(int idUsuario) throws OperacionesDeDaoExcepcion;
    
}
