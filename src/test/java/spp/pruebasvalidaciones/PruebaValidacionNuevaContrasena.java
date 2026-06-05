/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import org.junit.After;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.CredencialContraseña;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.validaciones.validacionnuevacontraseña.ValidacionNuevaContraseña;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author gomes
 */
public class PruebaValidacionNuevaContrasena {
    
    @Before
    public void prepararSesion() {

        String hash = HasheoContrasena.hashearContraseña("Password123");

        SesionUsuario.getInstancia().iniciarSesion(1, "Practicante", "S12345678", hash);
        
    }

    @After
    public void limpiarSesion() {

        SesionUsuario.getInstancia().cerrarSesion();
        
    }

    private CredencialContraseña crearCredencialesValidas() {

        CredencialContraseña credenciales = new CredencialContraseña();

        credenciales.setContraseñaActual("Password123");
        credenciales.setContraseñaNueva("NuevaClave123");
        credenciales.setContraseñaConfirmada("NuevaClave123");

        return credenciales;
        
    }

    @Test
    public void pruebaContraseñaActualIncorrecta() {

        CredencialContraseña credenciales = crearCredencialesValidas();

        credenciales.setContraseñaActual("Incorrecta");

        Usuario usuario = new Usuario();

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();

        try {

            validacion.cambiarContraseña(credenciales, usuario);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMensajeContraseñaActualIncorrecta() {

        CredencialContraseña credenciales = crearCredencialesValidas();

        credenciales.setContraseñaActual("Incorrecta");

        Usuario usuario = new Usuario();

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();

        try {

            validacion.cambiarContraseña(credenciales, usuario);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("La contraseña actual no es correcta", e.getMessage());
            
        }
        
    }

    @Test
    public void pruebaContraseñasNoCoinciden() {

        CredencialContraseña credenciales = crearCredencialesValidas();

        credenciales.setContraseñaConfirmada("OtraClave123");

        Usuario usuario = new Usuario();

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();

        try {

            validacion.cambiarContraseña(credenciales, usuario);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMensajeContraseñasNoCoinciden() {

        CredencialContraseña credenciales = crearCredencialesValidas();

        credenciales.setContraseñaConfirmada("OtraClave123");

        Usuario usuario = new Usuario();

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();

        try {

            validacion.cambiarContraseña(credenciales, usuario);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals( "Las contraseñas no coinciden", e.getMessage());
            
        }
        
    }

    @Test
    public void pruebaLongitudMenorAlMinimo() {

        CredencialContraseña credenciales = crearCredencialesValidas();

        credenciales.setContraseñaNueva("12345");
        credenciales.setContraseñaConfirmada("12345");

        Usuario usuario = new Usuario();

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();

        try {

            validacion.cambiarContraseña(credenciales, usuario);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaMensajeLongitudMenorAlMinimo() {

        CredencialContraseña credenciales = crearCredencialesValidas();

        credenciales.setContraseñaNueva("12345");
        credenciales.setContraseñaConfirmada("12345");

        Usuario usuario = new Usuario();

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();

        try {

            validacion.cambiarContraseña(credenciales, usuario);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertEquals("La nueva contraseña debe tener al menos10caracteres", e.getMessage());
            
        }
        
    }

    @Test
    public void pruebaLongitudNueveCaracteres() {

        CredencialContraseña credenciales = crearCredencialesValidas();

        credenciales.setContraseñaNueva("123456789");
        credenciales.setContraseñaConfirmada("123456789");

        Usuario usuario = new Usuario();

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();

        try {

            validacion.cambiarContraseña(credenciales, usuario);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }

    @Test
    public void pruebaContraseñasTotalmenteDiferentes() {

        CredencialContraseña credenciales = crearCredencialesValidas();

        credenciales.setContraseñaNueva("NuevaClave123");
        credenciales.setContraseñaConfirmada("ClaveDiferente456");

        Usuario usuario = new Usuario();

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();

        try {

            validacion.cambiarContraseña(credenciales, usuario);
            fail();

        } catch (ReglaDeNegocioExcepcion e) {

            assertTrue(true);
            
        }
        
    }
    
}
