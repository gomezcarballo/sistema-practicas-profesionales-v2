/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de experiencias educativas.
 *
 * Proporciona los métodos necesarios para registrar, consultar y eliminar
 * experiencias educativas dentro del sistema.
 *
 * @author gomes
 */
public interface IExperienciaEducativaDAO {
    /**
     * Registra una nueva experiencia educativa en el sistema.
     *
     * @param experiencia Objeto {@code ExperienciaEducativa} con los datos de la experiencia a insertar.
     * @return El identificador único generado para la experiencia educativa insertada (valor positivo).
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la inserción en la base de datos.
     */
    public int insertarExperienciaEducativa(ExperienciaEducativa experiencia) throws OperacionesDeDaoExcepcion;
    /**
     * Consulta una experiencia educativa a partir de su identificador único.
     *
     * @param idExperienciaEducativa Identificador de la experiencia educativa que se desea consultar.
     * @return Objeto {@code ExperienciaEducativa} con la información completa si existe,
     *         o {@code null} si no se encuentra ninguna experiencia con ese ID.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la consulta a la base de datos.
     */
    public ExperienciaEducativa consultarExperienciaEducativa(int idExperienciaEducativa) throws OperacionesDeDaoExcepcion;
    /**
     * Cuenta el número de asignaciones activas de experiencias educativas.
     * <p>
     * Una asignación activa se refiere a aquellas que están vigentes o no finalizadas
     * (la definición exacta depende de la regla de negocio aplicada en la consulta).
     *
     * @return Cantidad de asignaciones activas actualmente registradas. Retorna 0 si no hay ninguna.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error al ejecutar la consulta en la base de datos.
     */
    public int consultarAsignacionesActivas() throws OperacionesDeDaoExcepcion;

}
