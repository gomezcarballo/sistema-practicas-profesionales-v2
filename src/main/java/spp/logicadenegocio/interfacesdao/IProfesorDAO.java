/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IProfesorDAO {
    public boolean insertarProfesor(Profesor profesor)throws OperacionesDeDaoExcepcion;
    public List<Profesor> consultarProfesoresActivos()throws OperacionesDeDaoExcepcion;
    public int obtenerCantidadProfesoresActivos()throws OperacionesDeDaoExcepcion;
    public List<Profesor> consultarProfesoresInactivos() throws OperacionesDeDaoExcepcion;
    public boolean inactivarProfesor(int idUsuario)throws OperacionesDeDaoExcepcion;
    public boolean reactivarProfesor(int idUsuario) throws OperacionesDeDaoExcepcion;
}
