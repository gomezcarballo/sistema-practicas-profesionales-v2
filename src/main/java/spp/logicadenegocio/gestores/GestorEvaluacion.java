/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.EvaluacionDAO;
import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionEvaluacion;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorEvaluacion {
    
    public void ingresarEvaluacion(Evaluacion evaluacion)throws ReglaDeNegocioExcepcion{
        
        ValidacionEvaluacion validacion = new ValidacionEvaluacion();
        validacion.sonCamposValidosPorReglasDeNegocio(evaluacion);
        
        EvaluacionDAO evaluacionDAO = new EvaluacionDAO();
        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        evaluacion.setIdProfesor(sesionUsuario.getIdUsuario());
        
        try{
            
            evaluacionDAO.insertarEvaluacion(evaluacion);
            
        }catch(OperacionesDeDaoExcepcion e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico de base de datos al calificar un documento.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo registrar la calificacion por un problema "
                + "interno del sistema. Intente más tarde.");
            
        }
        
    }
    
}
