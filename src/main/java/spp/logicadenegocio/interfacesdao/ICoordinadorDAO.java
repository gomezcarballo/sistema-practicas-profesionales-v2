/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Coordinador;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface ICoordinadorDAO {
    public void insertarCoordinador(Coordinador coordinador)throws OperacionesDeDaoExcepcion;
    public Coordinador consultarCoordinador(String numeroDePersonal)throws OperacionesDeDaoExcepcion;
    public boolean eliminarCoordinador(String numeroDePersonal)throws OperacionesDeDaoExcepcion;
    public boolean actualizarCoordinador(Coordinador coordinador)throws OperacionesDeDaoExcepcion;       
}
