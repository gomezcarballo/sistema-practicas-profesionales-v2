/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.ReporteIndicadoresDAO;
import spp.logicadenegocio.clasesdto.IndicadorReporte;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorReporteIndicadores {
    
    public List<IndicadorReporte> obtenerIndicadores()throws ReglaDeNegocioExcepcion {

    try {
        
        ReporteIndicadoresDAO reporteIndicadoresDAO = new ReporteIndicadoresDAO();
        return reporteIndicadoresDAO.obtenerIndicadores();

    } catch (OperacionesDeDaoExcepcion e) {

        throw new ReglaDeNegocioExcepcion("No pudo obtener los indicadores", e);
        
    }
}
    
}
