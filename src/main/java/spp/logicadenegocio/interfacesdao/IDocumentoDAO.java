/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Documento;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IDocumentoDAO {
    public boolean insertarDocumento(Documento documento)throws OperacionesDeDaoExcepcion;
    public Documento consultarDocumento(String nombre)throws OperacionesDeDaoExcepcion;
}
