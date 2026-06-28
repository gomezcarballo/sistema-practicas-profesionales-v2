/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IExperienciaEducativaDAO {
    public int insertarExperienciaEducativa(ExperienciaEducativa experiencia) throws OperacionesDeDaoExcepcion;
    public ExperienciaEducativa consultarExperienciaEducativa(int idExperienciaEducativa) throws OperacionesDeDaoExcepcion;
    public int consultarAsignacionesActivas() throws OperacionesDeDaoExcepcion;

}
