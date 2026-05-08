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
    public Proyecto consultarProyecto(String nombre)throws OperacionesDeDaoExcepcion;
    public boolean eliminarProyecto(String nombre)throws OperacionesDeDaoExcepcion;
    public boolean actualizarProyecto(Proyecto proyecto)throws OperacionesDeDaoExcepcion;
    public List<Proyecto> obtenerProyectosActivos(int idOrganizacion)throws OperacionesDeDaoExcepcion;
}
