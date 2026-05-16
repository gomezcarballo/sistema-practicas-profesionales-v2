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
    public boolean insertarEnvioMensaje(int idMensaje, int idRemitente, int idDestinatario)throws OperacionesDeDaoExcepcion;
}
