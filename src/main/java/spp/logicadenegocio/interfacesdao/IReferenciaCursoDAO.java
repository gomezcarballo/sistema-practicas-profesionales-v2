/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.ReferenciaCurso;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IReferenciaCursoDAO {
    public int insertarReferenciaCurso(ReferenciaCurso referenciaCurso) throws OperacionesDeDaoExcepcion;
    public List<ReferenciaCurso> consultarTodasLasReferenciasCursos() throws OperacionesDeDaoExcepcion;
    public ReferenciaCurso consultarReferenciaCursoPorId(int idReferenciaCurso) throws OperacionesDeDaoExcepcion;
}
