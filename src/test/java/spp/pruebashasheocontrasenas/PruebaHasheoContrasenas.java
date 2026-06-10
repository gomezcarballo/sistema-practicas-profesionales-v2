/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebashasheocontrasenas;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author gomes
 */
public class PruebaHasheoContrasenas {
    
    @Test
    public void pruebaHashearContraseñaGeneraHash() {

        String hash = HasheoContrasena.hashearContraseña("Password123");

        assertTrue(hash != null);

    }

    @Test
    public void pruebaHashEsDiferenteContraseñaOriginal() {

        String contraseña = "Password123";

        String hash = HasheoContrasena.hashearContraseña(contraseña);

        assertFalse(contraseña.equals(hash));

    }

    @Test
    public void pruebaVerificarContraseñaCorrecta() {

        String hash = HasheoContrasena.hashearContraseña("Password123");

        boolean resultado = HasheoContrasena.verificarContraseña("Password123", hash);

        assertTrue(resultado);

    }

    @Test
    public void pruebaVerificarContraseñaIncorrecta() {

        String hash = HasheoContrasena.hashearContraseña("Password123");

        boolean resultado = HasheoContrasena.verificarContraseña("OtraPassword", hash);

        assertFalse(resultado);

    }

    @Test
    public void pruebaHashesDiferentesParaMismaContraseña() {

        String hash1 = HasheoContrasena.hashearContraseña("Password123");

        String hash2 = HasheoContrasena.hashearContraseña("Password123");

        assertFalse(hash1.equals(hash2));

    }

    @Test
    public void pruebaAmbosHashesValidanContraseña() {

        String hash1 = HasheoContrasena.hashearContraseña("Password123");

        String hash2 = HasheoContrasena.hashearContraseña("Password123");

        boolean resultado = HasheoContrasena.verificarContraseña("Password123", hash1)
        && HasheoContrasena.verificarContraseña("Password123", hash2);

        assertTrue(resultado);

    }

    @Test
    public void pruebaHashearContraseñaVacia() {

        String hash = HasheoContrasena.hashearContraseña("");

        boolean resultado =  HasheoContrasena.verificarContraseña("", hash);

        assertTrue(resultado);

    }

    @Test
    public void pruebaContraseñaEsCaseSensitive() {

        String hash = HasheoContrasena.hashearContraseña("Password123");

        boolean resultado = HasheoContrasena.verificarContraseña("password123", hash);

        assertFalse(resultado);

    }
    
}
