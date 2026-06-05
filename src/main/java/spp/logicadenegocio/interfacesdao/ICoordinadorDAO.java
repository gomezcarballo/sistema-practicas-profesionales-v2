/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface ICoordinadorDAO {
    public void insertarCoordinador(Coordinador coordinador)throws OperacionesDeDaoExcepcion;
    public List<Coordinador> consultarCoordinadoresInactivos() throws OperacionesDeDaoExcepcion;
    public boolean inactivarCoordinador()throws OperacionesDeDaoExcepcion;
    public boolean reactivarCoordinador(int idUsuario)throws OperacionesDeDaoExcepcion;
    public boolean existeCoordinadorActivo() throws OperacionesDeDaoExcepcion;
}
