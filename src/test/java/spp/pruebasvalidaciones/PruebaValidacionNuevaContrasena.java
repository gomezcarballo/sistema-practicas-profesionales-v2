/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasvalidaciones;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdto.CredencialContraseña;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.validaciones.validacionnuevacontraseña.ValidacionNuevaContraseña;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;

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
    public void pruebaCredencialesValidas() {

        CredencialContraseña credenciales = crearCredencialesValidas();
        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();
        
        List<String> errores = validacion.validarCambioContraseña(credenciales);

        assertTrue("La lista de errores debería estar vacía para credenciales válidas", errores.isEmpty());

    }

    @Test
    public void pruebaContraseñaActualIncorrecta() {

        CredencialContraseña credenciales = crearCredencialesValidas();
        credenciales.setContraseñaActual("Incorrecta");

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();
        List<String> errores = validacion.validarCambioContraseña(credenciales);

        assertFalse("La lista NO debería estar vacía si la contraseña actual es incorrecta", errores.isEmpty());
        
    }

    @Test
    public void pruebaContraseñasNoCoinciden() {

        CredencialContraseña credenciales = crearCredencialesValidas();
        credenciales.setContraseñaConfirmada("OtraClave123");

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();
        List<String> errores = validacion.validarCambioContraseña(credenciales);

        assertFalse("La lista NO debería estar vacía si las contraseñas nuevas no coinciden", errores.isEmpty());
        
    }

    @Test
    public void pruebaLongitudMenorAlMinimo() {

        CredencialContraseña credenciales = crearCredencialesValidas();
        credenciales.setContraseñaNueva("12345");
        credenciales.setContraseñaConfirmada("12345");

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();
        List<String> errores = validacion.validarCambioContraseña(credenciales);

        assertFalse("La lista NO debería estar vacía si la contraseña es muy corta", errores.isEmpty());
        
    }

    @Test
    public void pruebaLongitudNueveCaracteres() {

        CredencialContraseña credenciales = crearCredencialesValidas();
        credenciales.setContraseñaNueva("123456789");
        credenciales.setContraseñaConfirmada("123456789");

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();
        List<String> errores = validacion.validarCambioContraseña(credenciales);

        assertFalse("La lista NO debería estar vacía si la contraseña tiene 9 caracteres", errores.isEmpty());
        
    }

    @Test
    public void pruebaContraseñasTotalmenteDiferentes() {

        CredencialContraseña credenciales = crearCredencialesValidas();
        credenciales.setContraseñaNueva("NuevaClave123");
        credenciales.setContraseñaConfirmada("ClaveDiferente456");

        ValidacionNuevaContraseña validacion = new ValidacionNuevaContraseña();
        List<String> errores = validacion.validarCambioContraseña(credenciales);

        assertFalse("La lista NO debería estar vacía si las contraseñas son totalmente distintas", errores.isEmpty());
        
    }
    
}
