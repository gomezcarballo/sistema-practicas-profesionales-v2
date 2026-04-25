/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package spp.pruebasclasesdao;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.junit.Test;
import static org.junit.Assert.*;
import spp.logicadenegocio.clasesdao.MensajeDAO;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaMensajeDAO {
    
    @Test
    public void pruebaInsertarUsuarioDaoExitosa()throws OperacionesDeDaoExcepcion{
        
        Mensaje mensaje = new Mensaje();
        MensajeDAO mensajeDao = new MensajeDAO();
        
        mensaje.setAsunto("Prorroga de Actividad");
        mensaje.setCuerpo("Estudiantes, les abriré la actividad a los que no la subieron");
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        LocalDateTime fechaFormateada = LocalDateTime.parse("25-10-2004 14:30", formatoFecha);
        mensaje.setFecha(fechaFormateada); 
        
        boolean registroExitoso = mensajeDao.registrarMensaje(mensaje);
        assertTrue(registroExitoso);
        
    }
    
}
