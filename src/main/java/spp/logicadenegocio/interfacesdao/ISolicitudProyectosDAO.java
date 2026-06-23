/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de solicitudes de proyectos.
 *
 * Proporciona los métodos necesarios para registrar, consultar y eliminar solicitudes
 * de proyectos realizadas por los usuarios dentro del sistema.
 *
 * @author gomes
 */
public interface ISolicitudProyectosDAO {
    /**
     * Registra una solicitud de proyecto realizada por un usuario.
     *
     * @param idUsuario Identificador del usuario que realiza la solicitud.
     * @param idProyecto Identificador del proyecto solicitado.
     * @return {@code true} si la solicitud se guardó correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean guardarSolicitud(int idUsuario, int idProyecto) throws OperacionesDeDaoExcepcion;
    /**
     * Obtiene la lista de proyectos solicitados por un usuario específico.
     *
     * @param idUsuario Identificador del usuario.
     * @return Lista de proyectos solicitados por el usuario. Si no existen
     * solicitudes, se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Proyecto> obtenerProyectosSolicitados(int idUsuario)throws OperacionesDeDaoExcepcion;
    /**
     * Elimina una solicitud de proyecto realizada por un usuario.
     *
     * @param idUsuario Identificador del usuario que realizó la solicitud.
     * @param idProyecto Identificador del proyecto asociado a la solicitud.
     * @return {@code true} si la solicitud fue eliminada correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarSolicitud(int idUsuario, int idProyecto)throws OperacionesDeDaoExcepcion;
}
