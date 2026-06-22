/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de evaluaciones.
 *
 * Proporciona los métodos necesarios para registrar, consultar y eliminar
 * evaluaciones dentro del sistema.
 *
 * @author gomes
 */
public interface IEvaluacionDAO {
    /**
     * Inserta una evaluación en el sistema.
     *
     * @param evaluacion Evaluación que se desea registrar.
     * @return Identificador generado de la evaluación insertada.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public int insertarEvaluacion(Evaluacion evaluacion) throws OperacionesDeDaoExcepcion;
    /**
     * Consulta una evaluación a partir de su identificador.
     *
     * @param idEvaluacion Identificador de la evaluación a consultar.
     * @return Objeto {@code Evaluacion} con la información encontrada o
     * {@code null} si no existe una evaluación con el identificador
     * proporcionado.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public Evaluacion consultarEvaluacion(int idEvaluacion) throws OperacionesDeDaoExcepcion;
    /**
     * Elimina una evaluación del sistema.
     *
     * @param idEvaluacion Identificador de la evaluación que se desea eliminar.
     * @return {@code true} si la evaluación fue eliminada correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarEvaluacion(int idEvaluacion)throws OperacionesDeDaoExcepcion;
}
