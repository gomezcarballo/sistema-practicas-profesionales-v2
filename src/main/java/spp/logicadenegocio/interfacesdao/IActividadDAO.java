/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IActividadDAO {
    public boolean insertarActividad(Actividad actividad) throws OperacionesDeDaoExcepcion;
    public Actividad consultarActividad(String titulo) throws OperacionesDeDaoExcepcion;
    public boolean eliminarActividad(String titulo)throws OperacionesDeDaoExcepcion;
    public boolean actualizarActividad(Actividad actividad)throws OperacionesDeDaoExcepcion;
    public List<Actividad> consultarActividadesAsignadas(int idUsuario) throws OperacionesDeDaoExcepcion;
}
