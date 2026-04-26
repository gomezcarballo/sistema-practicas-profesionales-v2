/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IParticipacionDAO {
    public boolean insertarParticipacion(int idPracticante, int idProyecto) throws OperacionesDeDaoExcepcion;
    public boolean eliminarParticipacion(int idPracticante, int idProyecto) throws OperacionesDeDaoExcepcion;
    public List<Integer> consultarProyectosPorPracticante(int idPracticante) throws OperacionesDeDaoExcepcion;
    public List<Integer> consultarPracticantesPorProyecto(int idProyecto) throws OperacionesDeDaoExcepcion;
}
