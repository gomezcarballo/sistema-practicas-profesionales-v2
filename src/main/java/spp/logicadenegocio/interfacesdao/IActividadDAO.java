/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Actividad;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IActividadDAO {
    public boolean registrarActividad(Actividad actividad) throws OperacionesDeDaoExcepcion;
    public Actividad consultarActividad(String titulo) throws OperacionesDeDaoExcepcion;
    public boolean eliminarActividad(String titulo)throws OperacionesDeDaoExcepcion;
    public boolean actualizarActividad(Actividad actividad)throws OperacionesDeDaoExcepcion;
}
