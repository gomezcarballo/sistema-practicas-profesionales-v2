/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdao.EvaluacionDAO;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.ProfesorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaEvaluacionDAO {
    
    private EvaluacionDAO evaluacionDAO;
    private UsuarioDAO usuarioDAO;
    private ProfesorDAO profesorDAO;
    private PracticanteDAO practicanteDAO; 

    private int idUsuarioProfesor;
    private int idUsuarioPracticante;
    private int idEvaluacionPrueba;

    private static int secuencia = (int) (System.currentTimeMillis() % 10000);

    @Before
    public void inicializarDatosPrueba() throws OperacionesDeDaoExcepcion {
        
        evaluacionDAO = new EvaluacionDAO();
        usuarioDAO = new UsuarioDAO();
        profesorDAO = new ProfesorDAO();
        practicanteDAO = new PracticanteDAO(); 

        secuencia++;

        Usuario usuarioProfesor = new Usuario();
        usuarioProfesor.setNombre("Prof");
        usuarioProfesor.setApellidoPaterno("Eval");
        usuarioProfesor.setApellidoMaterno("Test");
        usuarioProfesor.setCorreoInstitucional("prof_eval" + secuencia + "@uv.mx");
        usuarioProfesor.setContraseña("123");
        usuarioProfesor.setEsActivo(true);
        idUsuarioProfesor = usuarioDAO.insertarUsuario(usuarioProfesor);

        Profesor profesor = new Profesor();
        profesor.setIdUsuario(idUsuarioProfesor);
        profesor.setNumeroDePersonal("PE" + secuencia);
        profesor.setNrcAsignado("NRC01");
        profesorDAO.insertarProfesor(profesor);

        Usuario usuarioPracticante = new Usuario();
        usuarioPracticante.setNombre("Prac");
        usuarioPracticante.setApellidoPaterno("Eval");
        usuarioPracticante.setApellidoMaterno("Test");
        usuarioPracticante.setCorreoInstitucional("prac_eval" + secuencia + "@uv.mx");
        usuarioPracticante.setContraseña("123");
        usuarioPracticante.setEsActivo(true);
        idUsuarioPracticante = usuarioDAO.insertarUsuario(usuarioPracticante);

        Practicante practicante = new Practicante();
        practicante.setIdUsuario(idUsuarioPracticante);
        practicante.setMatricula("S" + secuencia); 
        practicante.setFechaNacimiento(java.time.LocalDate.now());
        practicante.setNrcAsignado("NRC01");
        practicanteDAO.insertarPracticante(practicante);

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setNrc("NRC01");
        evaluacion.setPeriodo("FEB-JUL 26");
        evaluacion.setCalificacionFinal(9.5);
        evaluacion.setProfesor(profesor);
        evaluacion.setPracticante(practicante);
        evaluacion.setObservaciones("Esta es una observacion");

        idEvaluacionPrueba = evaluacionDAO.insertarEvaluacion(evaluacion);
    }

    @After
    public void eliminarDatosPrueba() throws OperacionesDeDaoExcepcion {
  
        evaluacionDAO.eliminarEvaluacion(idEvaluacionPrueba);

        practicanteDAO.eliminarPracticante(idUsuarioPracticante);

        profesorDAO.eliminarProfesor(idUsuarioProfesor);

        usuarioDAO.eliminarUsuario(idUsuarioPracticante);

        usuarioDAO.eliminarUsuario(idUsuarioProfesor);
    
    }


    @Test
    public void pruebaInsertarEvaluacionExitoso() throws OperacionesDeDaoExcepcion {
        
        Evaluacion nuevaEvaluacion = new Evaluacion();
        nuevaEvaluacion.setNrc("NRC01");
        nuevaEvaluacion.setPeriodo("AGO-DIC 26");
        nuevaEvaluacion.setCalificacionFinal(10.0);
        
        Profesor profesor = new Profesor();
        profesor.setIdUsuario(idUsuarioProfesor);
        nuevaEvaluacion.setProfesor(profesor);
        
        Practicante practicante = new Practicante();
        practicante.setIdUsuario(idUsuarioPracticante);
        nuevaEvaluacion.setPracticante(practicante);

        int idGenerado = evaluacionDAO.insertarEvaluacion(nuevaEvaluacion);

        evaluacionDAO.eliminarEvaluacion(idGenerado);

        assertTrue(idGenerado > 0);
        
    }

    @Test
    public void pruebaConsultarEvaluacionExistenteNoNula() throws OperacionesDeDaoExcepcion {
        
        Evaluacion resultado = evaluacionDAO.consultarEvaluacion(idEvaluacionPrueba);
        assertNotNull(resultado);
        
    }

    @Test
    public void pruebaEliminarEvaluacionExitoso() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = evaluacionDAO.eliminarEvaluacion(idEvaluacionPrueba);
        assertTrue(resultado);
        
    }

    @Test
    public void pruebaConsultarEvaluacionNoExistente() throws OperacionesDeDaoExcepcion {
        
        Evaluacion resultado = evaluacionDAO.consultarEvaluacion(999999);
        assertNull(resultado);
        
    }

    @Test
    public void pruebaEliminarEvaluacionNoExistente() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = evaluacionDAO.eliminarEvaluacion(999999);
        assertFalse(resultado);
        
    }
    
}
