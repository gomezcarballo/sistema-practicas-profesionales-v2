/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de practicantes.
 *
 * Proporciona los métodos necesarios para registrar, consultar, inactivar,
 * verificar asignaciones, buscar y eliminar practicantes dentro del sistema.
 *
 * @author gomes
 */
public interface IPracticanteDAO {
    /**
     * Inserta un practicante en el sistema.
     *
     * @param practicante Practicante que se desea registrar.
     * @return {@code true} si el practicante se registró correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean insertarPracticante(Practicante practicante)throws OperacionesDeDaoExcepcion;
    /**
     * Consulta todos los practicantes registrados en el sistema.
     *
     * @return Lista de practicantes. Si no existen registros, se devuelve una
     * lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Practicante> consultarPracticantes()throws OperacionesDeDaoExcepcion;
    /**
     * Inactiva un practicante en el sistema.
     *
     * @param idUsuario Identificador del usuario asociado al practicante.
     * @return {@code true} si el practicante fue inactivado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean inactivarPracticante(int idUsuario)throws OperacionesDeDaoExcepcion;
    /**
     * Verifica si un practicante tiene un proyecto asignado.
     *
     * @param idPracticante Identificador del practicante.
     * @return {@code true} si el practicante tiene un proyecto asignado;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean tieneProyectoAsignado(int idPracticante)throws OperacionesDeDaoExcepcion;
    /**
     * Consulta los practicantes que tienen solicitudes registradas.
     *
     * @return Lista de practicantes con solicitudes. Si no existen registros,
     * se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Practicante> consultarPracticantesConSolicitudes() throws OperacionesDeDaoExcepcion;
    /**
     * Busca un practicante a partir de su matrícula.
     *
     * @param matricula Matrícula del practicante que se desea buscar.
     * @return Objeto {@code UsuarioEncontrado} con la información del
     * practicante encontrado o {@code null} si no existe coincidencia.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public UsuarioEncontrado buscarPracticante(String matricula)throws OperacionesDeDaoExcepcion;
    /**
     * Elimina un practicante del sistema.
     *
     * @param idUsuario Identificador del usuario asociado al practicante.
     * @return {@code true} si el practicante fue eliminado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarPracticante(int idUsuario) throws OperacionesDeDaoExcepcion;

    /**
     * Consulta todos los practicantes que pueden ser asginados a una experiencia educativa
     * 
     * @return Lista de practicantes activos y con docuementos iniciales entregados. Si no existe 
     * ningun registro, retorna una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Practicante> consultarPracticantesParaAsignacionEE() throws OperacionesDeDaoExcepcion;
}
