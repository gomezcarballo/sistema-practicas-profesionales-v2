/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.ReportePendienteAgregar;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IReporteDAO {
    public boolean insertarReporte(ReportePendienteAgregar reporte)throws OperacionesDeDaoExcepcion;
    public ReportePendienteAgregar consultarReporte()throws OperacionesDeDaoExcepcion;
}
