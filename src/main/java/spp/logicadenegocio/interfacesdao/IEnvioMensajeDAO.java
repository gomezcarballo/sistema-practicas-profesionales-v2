/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la gestión del envío de
 * mensajes entre usuarios.
 *
 * Proporciona los métodos necesarios para registrar y eliminar los envíos de
 * mensajes dentro del sistema.
 *
 * @author gomes
 */
public interface IEnvioMensajeDAO {
    /**
     * Registra el envío de un mensaje entre un remitente y un destinatario.
     *
     * @param idMensaje Identificador del mensaje enviado.
     * @param idRemitente Identificador del usuario que envía el mensaje.
     * @param idDestinatario Identificador del usuario que recibe el mensaje.
     * @return {@code true} si el envío del mensaje se registró correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean insertarEnvioMensaje(int idMensaje, int idRemitente, int idDestinatario)throws OperacionesDeDaoExcepcion;
    /**
     * Elimina el registro de envío de un mensaje entre un remitente y un
     * destinatario.
     *
     * @param idMensaje Identificador del mensaje.
     * @param idRemitente Identificador del usuario que envió el mensaje.
     * @param idDestinatario Identificador del usuario destinatario del mensaje.
     * @return {@code true} si el registro fue eliminado correctamente;
     * {@code false} en caso contrario.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public boolean eliminarEnvioMensaje(int idMensaje,int idRemitente,int idDestinatario)throws OperacionesDeDaoExcepcion;
}
