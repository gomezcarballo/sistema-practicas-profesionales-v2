/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package spp.pruebasclasesdao;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import org.junit.After;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaPracticanteDAO {
    
    Usuario usuario = new Usuario();
    int idUsuario;
    
    @Before
    public void recursoInsertarUsuario()throws OperacionesDeDaoExcepcion{
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        usuario.setNombre("Brian Arturo");
        usuario.setApellidos("Morales Juarez");
        usuario.setContraseña("password");
        usuario.setEsActivo(true);
        idUsuario = usuarioDao.registrarUsuario(usuario);
        
    }
    
    @Test
    public void pruebaInsertarPracticanteDaoExitoso()throws OperacionesDeDaoExcepcion{
       
        Practicante practicante = new Practicante();
        PracticanteDAO practicanteDao = new PracticanteDAO();
        
        practicante.setIdUsuario(usuario.getIdUsuario());
        practicante.setMatricula("zS2401");
        practicante.setGenero("masculino");
        practicante.setHablaLenguaIndigena(false);
        DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate fechaFormateada = LocalDate.parse("25-10-2004", formatoFecha);

        Date fecha = java.sql.Date.valueOf(fechaFormateada);

        practicante.setFechaNacimiento(fecha);

        boolean registroExitoso = practicanteDao.registrarPracticante(practicante);
        assertTrue(registroExitoso);
        
    }
    
    @After
    public void eliminarUsuario()throws OperacionesDeDaoExcepcion{
        if(idUsuario < 0){
            
           PracticanteDAO practicanteDao = new PracticanteDAO();
           UsuarioDAO usuarioDao = new UsuarioDAO();
           practicanteDao.eliminarPracticante("zS2401");
           usuarioDao.eliminarUsuario(idUsuario);
           System.out.println("Usuario y practicante de prueba eliminados");
           
        }
    }
    
}
