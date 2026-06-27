/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdao.ExperienciaEducativaDAO;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionExperienciaEducativa;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorExperienciasEducativas {
    
    public List<String> validarCamposDeEE(ExperienciaEducativa experienciaEducativa) throws OperacionesDeDaoExcepcion {
        
        ValidacionExperienciaEducativa validacion = new ValidacionExperienciaEducativa();
        
        List<String> listaValidaciones = new ArrayList<>();    

        listaValidaciones = validacion.validarRegistroEE(experienciaEducativa);
        
        return listaValidaciones;
        
    }

    public boolean ingresarExperienciaEducativa(ExperienciaEducativa experienciaEducativa) throws OperacionesDeDaoExcepcion {
        
        ExperienciaEducativaDAO experienciaEducativaDAO = new ExperienciaEducativaDAO();
        
        boolean registroExitoso = false;
                
        experienciaEducativaDAO.insertarExperienciaEducativa(experienciaEducativa);
        
        registroExitoso = true;
 
        return registroExitoso;
    
    }

}
