/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import java.util.List;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IPracticanteDAO {
    public boolean insertarPracticante(Practicante practicante)throws OperacionesDeDaoExcepcion;
    public List<Practicante> consultarPracticantes()throws OperacionesDeDaoExcepcion;
    public boolean inactivarPracticante(int idUsuario)throws OperacionesDeDaoExcepcion;
    public List<Practicante> consultarPracticantesConSolicitudes() throws OperacionesDeDaoExcepcion;
    public UsuarioEncontrado buscarPracticante(String matricula)throws OperacionesDeDaoExcepcion;
}
