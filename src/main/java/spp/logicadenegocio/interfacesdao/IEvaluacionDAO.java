/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IEvaluacionDAO {
    public int insertarEvaluacion(Evaluacion evaluacion) throws OperacionesDeDaoExcepcion;
    public Evaluacion consultarEvaluacion(int idEvaluacion) throws OperacionesDeDaoExcepcion;
    public boolean eliminarEvaluacion(int idEvaluacion)throws OperacionesDeDaoExcepcion;
}
