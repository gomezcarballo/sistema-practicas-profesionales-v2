/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión de mensajes.
 *
 * Proporciona los métodos necesarios para registrar, consultar y eliminar
 * mensajes dentro del sistema, tanto recibidos como enviados por un usuario.
 *
 * @author gomes
 */
public interface IMensajeDAO {
    /**
     * Inserta un mensaje en el sistema.
     *
     * @param mensaje Mensaje que se desea registrar.
     * @return Identificador generado del mensaje insertado.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public int insertarMensaje(Mensaje mensaje)throws OperacionesDeDaoExcepcion;
    /**
     * Consulta los mensajes recibidos por un usuario específico.
     *
     * @param idUsuario Identificador del usuario destinatario.
     * @return Lista de mensajes recibidos por el usuario. Si no existen
     * mensajes, se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Mensaje> consultarMensajesPorDestinatario(int idUsuario)throws OperacionesDeDaoExcepcion;
    /**
     * Consulta los mensajes enviados por un usuario específico.
     *
     * @param idUsuario Identificador del usuario remitente.
     * @return Lista de mensajes enviados por el usuario. Si no existen
     * mensajes, se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<Mensaje> consultarMensajesEnviados(int idUsuario) throws OperacionesDeDaoExcepcion;
    /**
     * Elimina un mensaje del sistema.
     *
     * @param idMensaje Identificador del mensaje que se desea eliminar.
     * @return {@code true} si el mensaje fue eliminado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarMensaje(int idMensaje) throws OperacionesDeDaoExcepcion;
}
