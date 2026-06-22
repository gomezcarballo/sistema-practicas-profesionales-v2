/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de proyectos.
 *
 * Proporciona los métodos necesarios para registrar, consultar, actualizar,
 * asignar, desasignar, inactivar y eliminar proyectos dentro del sistema,
 * así como la consulta de proyectos por organización y estado.
 *
 * @author gomes
 */
public interface IProyectoDAO {
    /**
     * Inserta un proyecto en el sistema.
     *
     * @param proyecto Proyecto que se desea registrar.
     * @return {@code true} si el proyecto se registró correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean insertarProyecto(Proyecto proyecto)throws OperacionesDeDaoExcepcion;
    /**
     * Consulta un proyecto a partir de su nombre.
     *
     * @param nombre Nombre del proyecto que se desea consultar.
     * @return Objeto {@code Proyecto} con la información encontrada o
     * {@code null} si no existe un proyecto con el nombre proporcionado.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public Proyecto consultarProyecto(String nombre)throws OperacionesDeDaoExcepcion;
    /**
     * Disminuye el cupo disponible de un proyecto.
     *
     * @param idProyecto Identificador del proyecto.
     * @return {@code true} si el cupo fue disminuido correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean disminuirCupoProyecto(int idProyecto)throws OperacionesDeDaoExcepcion;
    /**
     * Inactiva un proyecto en el sistema.
     *
     * @param idProyecto Identificador del proyecto que se desea inactivar.
     * @return {@code true} si el proyecto fue inactivado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean inactivarProyecto(int idProyecto)throws OperacionesDeDaoExcepcion;
    /**
     * Inactiva todos los proyectos pertenecientes a una organización.
     *
     * @param idOrganizacion Identificador de la organización.
     * @return {@code true} si los proyectos fueron inactivados correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean inactivarProyectosDeOrganizacion(int idOrganizacion)throws OperacionesDeDaoExcepcion;
    /**
     * Actualiza la información de un proyecto existente.
     *
     * @param proyecto Proyecto con los datos actualizados.
     * @return {@code true} si el proyecto fue actualizado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean actualizarProyecto(Proyecto proyecto)throws OperacionesDeDaoExcepcion;
    /**
     * Asigna un proyecto a un usuario.
     *
     * @param idProyecto Identificador del proyecto.
     * @param idUsuario Identificador del usuario.
     * @return {@code true} si la asignación fue realizada correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean asignarProyecto(int idProyecto, int idUsuario) throws OperacionesDeDaoExcepcion;
    /**
     * Obtiene la lista de proyectos activos en el sistema.
     *
     * @return Lista de proyectos activos. Si no existen registros,
     * se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */

    public List<Proyecto> obtenerProyectosActivos()throws OperacionesDeDaoExcepcion;
    /**
     * Obtiene la lista de proyectos activos de una organización.
     *
     * @param idOrganizacion Identificador de la organización.
     * @return Lista de proyectos activos de la organización. Si no existen
     * registros, se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Proyecto> obtenerProyectosActivosPorOrganizacion(int idOrganizacion)throws OperacionesDeDaoExcepcion;
    /**
     * Elimina un proyecto del sistema.
     *
     * @param nombre Nombre del proyecto que se desea eliminar.
     * @return {@code true} si el proyecto fue eliminado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarProyecto(String nombre) throws OperacionesDeDaoExcepcion;
    /**
     * Desasigna un usuario de su proyecto actual.
     *
     * @param idUsuario Identificador del usuario.
     * @return {@code true} si la desasignación fue realizada correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean desasignarProyecto(int idUsuario)throws OperacionesDeDaoExcepcion;
}
