/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.EvaluacionDAO;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionEvaluacion;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorEvaluacion {
    
    public List<String> validarCamposEvaluacion(Evaluacion evaluacion) throws OperacionesDeDaoExcepcion {
        
        ValidacionEvaluacion validacion = new ValidacionEvaluacion();
        return validacion.validarRegistroEvaluacion(evaluacion);
        
    }
    
    public void ingresarEvaluacion(Evaluacion evaluacion) throws OperacionesDeDaoExcepcion {
        
        EvaluacionDAO evaluacionDAO = new EvaluacionDAO();
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        evaluacion.setIdProfesor(sesionUsuario.getIdUsuario());

        evaluacionDAO.insertarEvaluacion(evaluacion);

        
    }
    
    public boolean evaluacionYaExiste(Practicante practicante, Documento documento) throws OperacionesDeDaoExcepcion {
        EvaluacionDAO dao = new EvaluacionDAO(); 
        
        return dao.existeEvaluacion(practicante.getIdUsuario(), documento.getIdDocumento());
    }
    
}
