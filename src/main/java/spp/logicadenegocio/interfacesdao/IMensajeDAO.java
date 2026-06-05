/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IMensajeDAO {
    public int insertarMensaje(Mensaje mensaje)throws OperacionesDeDaoExcepcion;
    public List<Mensaje> consultarMensajesPorDestinatario(int idUsuario)throws OperacionesDeDaoExcepcion;
    public List<Mensaje> consultarMensajesEnviados(int idUsuario) throws OperacionesDeDaoExcepcion;
}
