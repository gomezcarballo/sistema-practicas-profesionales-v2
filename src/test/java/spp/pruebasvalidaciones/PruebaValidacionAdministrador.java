/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;
/* 
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionAdministrador;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
*/
/**
 *
 * @author gomes
 */
public class PruebaValidacionAdministrador {
    /* 
    private Administrador crearAdministradorValido() {

        Administrador administrador = new Administrador();

        administrador.setNumeroDePersonal("12345");
        administrador.setNombre("Juan");
        administrador.setApellidoPaterno("Perez");
        administrador.setApellidoMaterno("Lopez");

        return administrador;
        
    }
    
    @Test
    public void pruebaCamposValidosAdiministrador() {

        Administrador administrador = crearAdministradorValido();

        ValidacionAdministrador validacion = new ValidacionAdministrador();

        try {

            validacion.sonCamposValidosPorReglaNegocio(administrador);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    }
    
    @Test
    public void pruebaNumeroPersonalLongitudCorrecta() {

        Administrador administrador = crearAdministradorValido();

        administrador.setNumeroDePersonal("99999");

        ValidacionAdministrador validacion = new ValidacionAdministrador();

        try {

            validacion.sonCamposValidosPorReglaNegocio(administrador);

        } catch (ReglaDeNegocioExcepcion e) {

            fail();
        }
        
    }
    
    @Test
    public void pruebaNumeroPersonalMenorLongitud() {

        Administrador administrador = crearAdministradorValido();

        administrador.setNumeroDePersonal("1234");

        ValidacionAdministrador validacion = new ValidacionAdministrador();

        try {

            validacion.sonCamposValidosPorReglaNegocio(administrador);

            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaNumeroPersonalMayorLongitud() {

        Administrador administrador = crearAdministradorValido();

        administrador.setNumeroDePersonal("123456");

        ValidacionAdministrador validacion = new ValidacionAdministrador();

        try {

            validacion.sonCamposValidosPorReglaNegocio(administrador);

            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaNumeroPersonalConLetras() {

        Administrador administrador = crearAdministradorValido();

        administrador.setNumeroDePersonal("12A45");

        ValidacionAdministrador validacion = new ValidacionAdministrador();

        try {

            validacion.sonCamposValidosPorReglaNegocio(administrador);

            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaNumeroPersonalSoloLetras() {

        Administrador administrador = crearAdministradorValido();

        administrador.setNumeroDePersonal("ABCDE");

        ValidacionAdministrador validacion = new ValidacionAdministrador();

        try {

            validacion.sonCamposValidosPorReglaNegocio(administrador);

            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaNumeroPersonalConSimbolos() {

        Administrador administrador = crearAdministradorValido();

        administrador.setNumeroDePersonal("12-45");

        ValidacionAdministrador validacion = new ValidacionAdministrador();

        try {

            validacion.sonCamposValidosPorReglaNegocio(administrador);

            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaNumeroPersonalVacio() {

        Administrador administrador = crearAdministradorValido();

        administrador.setNumeroDePersonal("");

        ValidacionAdministrador validacion = new ValidacionAdministrador();

        try {

            validacion.sonCamposValidosPorReglaNegocio(administrador);

            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
    @Test
    public void pruebaMensajeNumeroPersonalInvalido() {

        Administrador administrador = crearAdministradorValido();

        administrador.setNumeroDePersonal("1234");

        ValidacionAdministrador validacion = new ValidacionAdministrador();

        try {

            validacion.sonCamposValidosPorReglaNegocio(administrador);

            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("Numero de personal no valido. Debe contener5digitos.", e.getMessage());
            
        }
        
    }
    
    @Test
    public void pruebaNumeroPersonalConEspacios() {

        Administrador administrador = crearAdministradorValido();

        administrador.setNumeroDePersonal("12 45");

        ValidacionAdministrador validacion = new ValidacionAdministrador();

        try {

            validacion.sonCamposValidosPorReglaNegocio(administrador);

            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    */
}
