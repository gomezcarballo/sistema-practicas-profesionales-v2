/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IEnvioMensajeDAO {
    public boolean insertarEnvioMensaje(int idMensaje, int idUsuario, String destinatario)throws OperacionesDeDaoExcepcion;
    public boolean eliminarEnvioMensaje(int idMensaje, int idUsuario) throws OperacionesDeDaoExcepcion;
    public String consultarDestinatario(int idMensaje, int idUsuario) throws OperacionesDeDaoExcepcion;
}
