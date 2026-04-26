/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Profesor;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IProfesorDAO {
    public boolean insertarProfesor(Profesor profesor)throws OperacionesDeDaoExcepcion;
    public Profesor consultarProfesor(String numeroDePersonal)throws OperacionesDeDaoExcepcion;
    public boolean eliminarProfesor(String numeroDePersonal)throws OperacionesDeDaoExcepcion;
    public boolean actualizarProfesor(Profesor profesor)throws OperacionesDeDaoExcepcion;
}
