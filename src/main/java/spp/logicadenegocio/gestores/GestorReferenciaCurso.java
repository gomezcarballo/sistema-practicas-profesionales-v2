/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdao.ReferenciaCursoDAO;
import spp.logicadenegocio.clasesdto.ReferenciaCurso;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionReferenciaCurso;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorReferenciaCurso {
    
    public List<String> validarCamposDeNrc(ReferenciaCurso referenciaCurso) throws OperacionesDeDaoExcepcion {
        
        ValidacionReferenciaCurso validacion = new ValidacionReferenciaCurso();
        
        List<String> listaValidaciones = new ArrayList<>();    

        listaValidaciones = validacion.validarRegistroNrc(referenciaCurso);
        
        return listaValidaciones;
        
    }

    public boolean ingresarNRC(ReferenciaCurso referenciaCurso) throws OperacionesDeDaoExcepcion {
        
        ReferenciaCursoDAO nrcDAO = new ReferenciaCursoDAO();
        
        boolean registroExitoso = false;
        
        nrcDAO.insertarReferenciaCurso(referenciaCurso);
        
        registroExitoso = true;
 
        return registroExitoso;
    
    }
    
    public List<ReferenciaCurso> consultarTodosLosNRC() throws OperacionesDeDaoExcepcion {
        
        ReferenciaCursoDAO nrcDAO = new ReferenciaCursoDAO();

        List<ReferenciaCurso> listaNrcs;
        
        listaNrcs = nrcDAO.consultarTodasLasReferenciasCursos();
        
        return listaNrcs;
        
    }
    
}
