/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.EncabezadoReporte;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
/**
 * Define las operaciones de acceso a datos para la obtención de la información
 * del encabezado de un reporte.
 *
 * Proporciona los métodos necesarios para recuperar los datos que conforman el
 * encabezado de un reporte de un practicante.
 *
 * @author Luz Fernanda H J
 */
public interface IEncabezadoReporteDAO {
    /**
     * Recupera la información necesaria para generar el encabezado del reporte
     * de un practicante.
     *
     * @param idPracticante Identificador del practicante del que se obtendrán
     * los datos del encabezado.
     * @return Objeto {@code EncabezadoReporte} con la información recuperada.
     * @throws OperacionesDeDaoExcepcion Si ocurre un error durante la operación
     * de acceso a datos.
     */
    public EncabezadoReporte recuperarDatosReporte(int idPracticante) throws OperacionesDeDaoExcepcion;  
}

