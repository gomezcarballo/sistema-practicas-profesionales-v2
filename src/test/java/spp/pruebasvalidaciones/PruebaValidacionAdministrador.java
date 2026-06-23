/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;


import java.util.List;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionAdministrador;


/**
 *
 * @author gomes
 */
public class PruebaValidacionAdministrador {
     
    
    private ValidacionAdministrador validacion;
    
    @Before
    public void configurar() {
        validacion = new ValidacionAdministrador();
    }
    
    private Administrador crearAdministradorValido() {

        Administrador administrador = new Administrador();

        administrador.setNumeroDePersonal("12345");
        administrador.setNombre("Juan");
        administrador.setApellidoPaterno("Perez");
        administrador.setApellidoMaterno("Lopez");
        administrador.setCorreoInstitucional("juan.perez@uv.mx"); 

        return administrador;
        
    }
    
    @Test
    public void pruebaCamposValidosAdministrador() {

        Administrador administrador = crearAdministradorValido();

        List<String> errores = validacion.validarRegistroAdministrador(administrador);
        
        assertTrue("La lista de errores debería estar vacía para un administrador válido", 
            errores.isEmpty());
        
    }
    
    @Test
    public void pruebaNumeroPersonalLongitudCorrecta() {

        Administrador administrador = crearAdministradorValido();
        administrador.setNumeroDePersonal("99999");

        List<String> errores = validacion.validarRegistroAdministrador(administrador);
        
        assertTrue("La lista de errores debería estar vacía con un numero de personal válido", 
            errores.isEmpty());
        
    }
    
    @Test
    public void pruebaNumeroPersonalMenorLongitud() {

        Administrador administrador = crearAdministradorValido();
        administrador.setNumeroDePersonal("1234");

        List<String> errores = validacion.validarRegistroAdministrador(administrador);
        
        assertFalse("La lista de errores NO debería estar vacía con un numero corto", 
            errores.isEmpty());
        
    }
    
    @Test
    public void pruebaNumeroPersonalMayorLongitud() {

        Administrador administrador = crearAdministradorValido();
        administrador.setNumeroDePersonal("123456");

        List<String> errores = validacion.validarRegistroAdministrador(administrador);
        
        assertFalse("La lista de errores NO debería estar vacía con un numero largo", 
            errores.isEmpty());
        
    }
    
    @Test
    public void pruebaNumeroPersonalConLetras() {

        Administrador administrador = crearAdministradorValido();
        administrador.setNumeroDePersonal("12A45");

        List<String> errores = validacion.validarRegistroAdministrador(administrador);
        
        assertFalse("La lista de errores NO debería estar vacía con letras intercaladas", 
            errores.isEmpty());
        
    }
    
    @Test
    public void pruebaNumeroPersonalSoloLetras() {

        Administrador administrador = crearAdministradorValido();
        administrador.setNumeroDePersonal("ABCDE");

        List<String> errores = validacion.validarRegistroAdministrador(administrador);
        
        assertFalse("La lista de errores NO debería estar vacía con solo letras", 
            errores.isEmpty());
        
    }
    
    @Test
    public void pruebaNumeroPersonalConSimbolos() {

        Administrador administrador = crearAdministradorValido();
        administrador.setNumeroDePersonal("12-45");

        List<String> errores = validacion.validarRegistroAdministrador(administrador);
        
        assertFalse("La lista de errores NO debería estar vacía con símbolos", 
            errores.isEmpty());
        
    }
    
    @Test
    public void pruebaNumeroPersonalVacio() {

        Administrador administrador = crearAdministradorValido();
        administrador.setNumeroDePersonal("");

        List<String> errores = validacion.validarRegistroAdministrador(administrador);
        
        assertFalse("La lista de errores NO debería estar vacía con un numero en blanco", 
            errores.isEmpty());
        
    }
    
    @Test
    public void pruebaNumeroPersonalConEspacios() {

        Administrador administrador = crearAdministradorValido();
        administrador.setNumeroDePersonal("12 45");

        List<String> errores = validacion.validarRegistroAdministrador(administrador);
        
        assertFalse("La lista de errores NO debería estar vacía con espacios", 
            errores.isEmpty());
        
    }
    
}
