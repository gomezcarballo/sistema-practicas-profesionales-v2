/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IOrganizacionDAO {
    public boolean insertarOrganizacion(Organizacion organizacion) throws OperacionesDeDaoExcepcion;
    public Organizacion consultarOrganizacion(String nombre)throws OperacionesDeDaoExcepcion;
    public boolean inactivarOrganizacion(int idOrganizacion)throws OperacionesDeDaoExcepcion;
    public boolean actualizarOrganizacion(Organizacion organizacion)throws OperacionesDeDaoExcepcion;
    public List<Organizacion>obtenerOrganizacionesActivas() throws OperacionesDeDaoExcepcion;
}
