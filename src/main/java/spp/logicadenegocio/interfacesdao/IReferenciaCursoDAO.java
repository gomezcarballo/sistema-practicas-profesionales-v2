/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.ReferenciaCurso;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de referencias de cursos.
 *
 * Proporciona los métodos necesarios para registrar y consultar las referencias
 * de cursos (NRC, claves o identificadores de materias) utilizadas en el sistema.
 *
 * @author gomes
 */
public interface IReferenciaCursoDAO {
    /**
     * Inserta una nueva referencia de curso en el sistema.
     *
     * @param referenciaCurso Objeto {@code ReferenciaCurso} con los datos de la
     *                        referencia que se desea registrar.
     * @return El identificador único generado para la referencia insertada (valor positivo).
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la inserción en la base de datos.
     */
    public int insertarReferenciaCurso(ReferenciaCurso referenciaCurso) throws OperacionesDeDaoExcepcion;
    /**
     * Recupera la lista completa de todas las referencias de cursos registradas.
     *
     * @return Lista de objetos {@code ReferenciaCurso} con todas las referencias almacenadas,
     *         o una lista vacía si no existe ninguna.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la consulta a la base de datos.
     */
    public List<ReferenciaCurso> consultarTodasLasReferenciasCursos() throws OperacionesDeDaoExcepcion;
    /**
     * Consulta una referencia de curso a partir de su identificador único.
     *
     * @param idReferenciaCurso Identificador de la referencia de curso que se desea consultar.
     * @return Objeto {@code ReferenciaCurso} con los datos completos si existe,
     *         o {@code null} si no se encuentra ninguna referencia con ese ID.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la consulta a la base de datos.
     */
    public ReferenciaCurso consultarReferenciaCursoPorId(int idReferenciaCurso) throws OperacionesDeDaoExcepcion;
}
