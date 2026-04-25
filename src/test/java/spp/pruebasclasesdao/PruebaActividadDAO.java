/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import spp.logicadenegocio.clasesdao.ActividadDAO;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;


/**
 *
 * @author Luz Fernanda H J
 */
public class PruebaActividadDAO {
    
    Actividad actividadInsertada = new Actividad();
    ActividadDAO actividadDao = new ActividadDAO();
    Profesor profesor = new Profesor();
    
    @Test 
    public void pruebaRegistrarActividadDAOExitosa() throws OperacionesDeDaoExcepcion{
               
        actividadInsertada.setTitulo("Actividad 1");
        actividadInsertada.setDescripcion("Actividad para la definición de estándar");
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime fechaFormateada = LocalDateTime.parse("25-10-2023 14:30", formatoFecha);
        
        actividadInsertada.setFechaLimite(fechaFormateada);
        profesor.setIdUsuario(2);
        actividadInsertada.setProfesor(profesor);
        
        boolean registroExitoso = actividadDao.registrarActividad(actividadInsertada);
        assertTrue("Registro de actividad exitoso es: ", registroExitoso);
        
    }
    
    @Test
    public void pruebaConsultarActividadExitosa() throws OperacionesDeDaoExcepcion{
        
        Actividad actividadResultante = actividadDao.consultarActividad("Actividad 1");
        actividadInsertada.setIdActividad(actividadResultante.getIdActividad());
        assertEquals(actividadResultante, actividadInsertada);
        
    } 
}
