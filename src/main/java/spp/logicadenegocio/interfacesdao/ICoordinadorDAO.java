/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.sql.Connection;
import java.util.List;
import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface ICoordinadorDAO {
    public boolean insertarCoordinador(Coordinador coordinador)throws OperacionesDeDaoExcepcion;
    public List<Coordinador> consultarCoordinadoresInactivos() throws OperacionesDeDaoExcepcion;
    public boolean inactivarCoordinador()throws OperacionesDeDaoExcepcion;
    public boolean reactivarCoordinador(int idUsuario)throws OperacionesDeDaoExcepcion;
    public boolean existeCoordinadorActivo() throws OperacionesDeDaoExcepcion;
    public boolean eliminarCoordinador(int idUsuario) throws OperacionesDeDaoExcepcion;
    public boolean insertarCoordinadorConTransaccion(Coordinador coordinador, Connection conexion) throws OperacionesDeDaoExcepcion;
    public void registrarCoordinadorCompleto(Usuario usuario, Coordinador coordinador) throws OperacionesDeDaoExcepcion;
    
}
