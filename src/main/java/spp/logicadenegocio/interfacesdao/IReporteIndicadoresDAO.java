/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.IndicadorReporte;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 * Define las operaciones de acceso a datos para la obtención de indicadores
 * utilizados en el reporte de indicadores.
 *
 * Proporciona los métodos necesarios para consultar los indicadores que se
 * muestran en el reporte de indicadores.
 *
 * @author gomes
 */
public interface IReporteIndicadoresDAO {
    /**
     * Obtiene la lista de indicadores utilizados en el reporte de indicadores.
     *
     * @return Lista de indicadores de reporte. Si no existen registros,
     * se devuelve una lista vacía.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public List<IndicadorReporte> obtenerIndicadores() throws OperacionesDeDaoExcepcion;
}
