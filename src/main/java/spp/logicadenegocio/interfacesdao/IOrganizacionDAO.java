/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de organizaciones.
 *
 * Proporciona los métodos necesarios para registrar, consultar, actualizar,
 * inactivar, listar y eliminar organizaciones dentro del sistema.
 *
 * @author gomes
 */
public interface IOrganizacionDAO {
    /**
     * Inserta una organización en el sistema.
     *
     * @param organizacion Organización que se desea registrar.
     * @return {@code true} si la organización se registró correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean insertarOrganizacion(Organizacion organizacion) throws OperacionesDeDaoExcepcion;
    /**
     * Consulta una organización a partir de su nombre.
     *
     * @param nombre Nombre de la organización que se desea consultar.
     * @return Objeto {@code Organizacion} con la información encontrada o
     * {@code null} si no existe una organización con el nombre proporcionado.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public Organizacion consultarOrganizacion(String nombre)throws OperacionesDeDaoExcepcion;
    /**
     * Inactiva una organización dentro del sistema.
     *
     * @param idOrganizacion Identificador de la organización a inactivar.
     * @return {@code true} si la organización fue inactivada correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean inactivarOrganizacion(int idOrganizacion)throws OperacionesDeDaoExcepcion;
    /**
     * Actualiza la información de una organización existente.
     *
     * @param organizacion Organización con los datos actualizados.
     * @return {@code true} si la organización fue actualizada correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean actualizarOrganizacion(Organizacion organizacion)throws OperacionesDeDaoExcepcion;
    /**
     * Obtiene la lista de organizaciones activas en el sistema.
     *
     * @return Lista de organizaciones activas. Si no existen registros,
     * se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Organizacion>obtenerOrganizacionesActivas() throws OperacionesDeDaoExcepcion;
    /**
     * Elimina una organización del sistema.
     *
     * @param nombre Nombre de la organización que se desea eliminar.
     * @return {@code true} si la organización fue eliminada correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarOrganizacion(String nombre) throws OperacionesDeDaoExcepcion;
}
