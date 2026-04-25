/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Mensaje;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IMensajeDAO {
    public boolean registrarMensaje(Mensaje mensaje)throws OperacionesDeDaoExcepcion;
    public Mensaje consultarMensaje(String asunto)throws OperacionesDeDaoExcepcion;
}
