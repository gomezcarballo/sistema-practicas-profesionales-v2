/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.EncabezadoReporte;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
/**
 *
 * @author Luz Fernanda H J
 */
public interface IEncabezadoReporteDAO {
    public EncabezadoReporte recuperarDatosReporte(int idPracticante) throws OperacionesDeDaoExcepcion;
    
}

