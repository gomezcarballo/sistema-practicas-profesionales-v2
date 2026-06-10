/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface ISolicitudProyectosDAO {
    public boolean guardarSolicitud(int idUsuario, int idProyecto) throws OperacionesDeDaoExcepcion;
    public List<Proyecto> obtenerProyectosSolicitados(int idUsuario)throws OperacionesDeDaoExcepcion;
    public boolean eliminarSolicitud(int idUsuario, int idProyecto)throws OperacionesDeDaoExcepcion;
}
