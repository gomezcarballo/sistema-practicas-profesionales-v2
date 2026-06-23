/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.ReporteIndicadoresDAO;
import spp.logicadenegocio.clasesdto.IndicadorReporte;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorReporteIndicadores {
    
    public List<IndicadorReporte> obtenerIndicadores() throws OperacionesDeDaoExcepcion {

        ReporteIndicadoresDAO reporteIndicadoresDAO = new ReporteIndicadoresDAO();
        
        return reporteIndicadoresDAO.obtenerIndicadores();

    }
    
}
