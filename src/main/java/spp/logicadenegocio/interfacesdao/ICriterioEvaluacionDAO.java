/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.CriterioEvaluacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface ICriterioEvaluacionDAO {
    public void insertarCriterioEvaluacion(CriterioEvaluacion criterio) throws OperacionesDeDaoExcepcion;
    public CriterioEvaluacion consultarCriterioEvaluacion(String nombreCriterio) throws OperacionesDeDaoExcepcion;
}
