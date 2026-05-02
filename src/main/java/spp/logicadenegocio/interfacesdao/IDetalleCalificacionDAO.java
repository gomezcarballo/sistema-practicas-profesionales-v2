/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.DetalleCalificacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IDetalleCalificacionDAO {
    public void insertarDetalleCalificacion(DetalleCalificacion detalleCalificacion) throws OperacionesDeDaoExcepcion;
    public DetalleCalificacion consultarDetalleCalificacion(int idDetalleCalificacion) throws OperacionesDeDaoExcepcion;
}
