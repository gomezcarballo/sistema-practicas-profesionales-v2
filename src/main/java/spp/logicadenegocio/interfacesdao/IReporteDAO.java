/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Reporte;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IReporteDAO {
    public boolean registarReporte(Reporte reporte)throws OperacionesDeDaoExcepcion;
    public Reporte consultarReporte()throws OperacionesDeDaoExcepcion;
}
