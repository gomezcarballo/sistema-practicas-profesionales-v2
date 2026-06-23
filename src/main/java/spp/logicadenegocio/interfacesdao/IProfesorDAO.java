/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de profesores.
 *
 * Proporciona los métodos necesarios para registrar, consultar, contar,
 * inactivar, reactivar y eliminar profesores dentro del sistema.
 *
 * @author gomes
 */
public interface IProfesorDAO {
    /**
     * Inserta un profesor en el sistema.
     *
     * @param profesor Profesor que se desea registrar.
     * @return {@code true} si el profesor se registró correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean insertarProfesor(Profesor profesor)throws OperacionesDeDaoExcepcion;
    /**
     * Consulta todos los profesores activos en el sistema.
     *
     * @return Lista de profesores activos. Si no existen registros,
     * se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Profesor> consultarProfesoresActivos()throws OperacionesDeDaoExcepcion;
    /**
     * Obtiene la cantidad total de profesores activos en el sistema.
     *
     * @return Número de profesores activos registrados.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public int obtenerCantidadProfesoresActivos()throws OperacionesDeDaoExcepcion;
    /**
     * Consulta todos los profesores inactivos en el sistema.
     *
     * @return Lista de profesores inactivos. Si no existen registros,
     * se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Profesor> consultarProfesoresInactivos() throws OperacionesDeDaoExcepcion;
    /**
     * Inactiva un profesor en el sistema.
     *
     * @param idUsuario Identificador del usuario asociado al profesor.
     * @return {@code true} si el profesor fue inactivado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean inactivarProfesor(int idUsuario)throws OperacionesDeDaoExcepcion;
    /**
     * Reactiva un profesor previamente inactivo.
     *
     * @param idUsuario Identificador del usuario asociado al profesor.
     * @return {@code true} si el profesor fue reactivado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean reactivarProfesor(int idUsuario) throws OperacionesDeDaoExcepcion;   
    /**
     * Elimina un profesor del sistema.
     *
     * @param idUsuario Identificador del usuario asociado al profesor.
     * @return {@code true} si el profesor fue eliminado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarProfesor(int idUsuario) throws OperacionesDeDaoExcepcion;
}
