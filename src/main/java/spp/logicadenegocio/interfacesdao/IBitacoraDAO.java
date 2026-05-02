/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Bitacora;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IBitacoraDAO {
    public boolean insertarBitacora(Bitacora bitacora)throws OperacionesDeDaoExcepcion;
    public Bitacora consultarBitacora(int idBitacora)throws OperacionesDeDaoExcepcion;
}
