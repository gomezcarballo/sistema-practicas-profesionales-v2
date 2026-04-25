/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Organizacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IOrganizacionDAO {
    public boolean registrarOrganizacion(Organizacion organizacion) throws OperacionesDeDaoExcepcion;
    public Organizacion consultarOrganizacion(String nombre)throws OperacionesDeDaoExcepcion;
    public boolean eliminarOrganizacion(String nombre)throws OperacionesDeDaoExcepcion;
    public boolean actualizarOrganizacion(Organizacion organizacion)throws OperacionesDeDaoExcepcion;
}
