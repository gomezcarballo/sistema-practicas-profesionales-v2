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
public interface IProyectoDAO {
    public boolean insertarProyecto(Proyecto proyecto)throws OperacionesDeDaoExcepcion;
    public boolean disminuirCupoProyecto(int idProyecto)throws OperacionesDeDaoExcepcion;
    public boolean inactivarProyecto(int idProyecto)throws OperacionesDeDaoExcepcion;
    public boolean inactivarProyectosDeOrganizacion(int idOrganizacion)throws OperacionesDeDaoExcepcion;
    public boolean actualizarProyecto(Proyecto proyecto)throws OperacionesDeDaoExcepcion;
    public void asignarProyecto(int idProyecto, int idUsuario) throws OperacionesDeDaoExcepcion;
    public List<Proyecto> obtenerProyectosActivos()throws OperacionesDeDaoExcepcion;
    public List<Proyecto> obtenerProyectosActivosPorOrganizacion(int idOrganizacion)throws OperacionesDeDaoExcepcion;
}
