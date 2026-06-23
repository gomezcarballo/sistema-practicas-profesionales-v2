/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de prácticas y
 * actividades asociadas.
 *
 * Proporciona los métodos necesarios para registrar, consultar, actualizar,
 * eliminar y consultar actividades asignadas a usuarios dentro del sistema.
 *
 * @author gomes
 */
public interface IPracticaDAO {
    /**
     * Inserta una actividad en el sistema.
     *
     * @param actividad Actividad que se desea registrar.
     * @return {@code true} si la actividad se registró correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean insertarActividad(Actividad actividad) throws OperacionesDeDaoExcepcion;
    /**
     * Consulta una actividad a partir de su título.
     *
     * @param titulo Título de la actividad que se desea consultar.
     * @return Objeto {@code Actividad} con la información encontrada o
     * {@code null} si no existe una actividad con el título proporcionado.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public Actividad consultarActividad(String titulo) throws OperacionesDeDaoExcepcion;
    /**
     * Elimina una actividad del sistema.
     *
     * @param titulo Título de la actividad que se desea eliminar.
     * @return {@code true} si la actividad fue eliminada correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarActividad(String titulo)throws OperacionesDeDaoExcepcion;
    /**
     * Actualiza la información de una actividad existente.
     *
     * @param actividad Actividad con los datos actualizados.
     * @return {@code true} si la actividad fue actualizada correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean actualizarActividad(Actividad actividad)throws OperacionesDeDaoExcepcion;
    /**
     * Consulta las actividades asignadas a un usuario específico.
     *
     * @param idUsuario Identificador del usuario.
     * @return Lista de actividades asignadas al usuario. Si no existen registros,
     * se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Actividad> consultarActividadesAsignadas(int idUsuario) throws OperacionesDeDaoExcepcion;
}
