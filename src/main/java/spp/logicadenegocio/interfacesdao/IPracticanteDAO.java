/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package spp.logicadenegocio.interfacesdao;

import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public interface IPracticanteDAO {
    public boolean insertarPracticante(Practicante practicante)throws OperacionesDeDaoExcepcion;
    public Practicante consultarPracticante(String matricula)throws OperacionesDeDaoExcepcion;
    public boolean eliminarPracticante(String matricula)throws OperacionesDeDaoExcepcion;
    public boolean actualizarPracticante(Practicante practicante)throws OperacionesDeDaoExcepcion;
    public UsuarioEncontrado buscarPracticante(String matricula)throws OperacionesDeDaoExcepcion;
}
