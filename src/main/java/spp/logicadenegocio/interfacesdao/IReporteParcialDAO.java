/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
/**
 *
 * @author Luz Fernanda H J
 */
public interface IReporteParcialDAO {
    public Documento generarReporteParcial(ReporteParcial reporteParcial) throws OperacionesDeDaoExcepcion ;
}

