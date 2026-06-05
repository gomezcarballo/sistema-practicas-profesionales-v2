/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasgeneradorcontrasenas;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Test;
import spp.utilerias.generadordecontrasenas.GeneradorContrasena;

/**
 *
 * @author gomes
 */
public class PruebaGeneradorContrasenas {
    
    @Test
    public void pruebaGenerarContraseñaLongitudDiez() {

        String contraseña = GeneradorContrasena.generarContraseña(10);

        assertEquals(10, contraseña.length());

    }

    @Test
    public void pruebaGenerarContraseñaLongitudVeinte() {

        String contraseña = GeneradorContrasena.generarContraseña(20);

        assertEquals(20, contraseña.length());

    }

    @Test
    public void pruebaGenerarContraseñaLongitudUno() {

        String contraseña = GeneradorContrasena.generarContraseña(1);

        assertEquals(1, contraseña.length());

    }


    @Test
    public void pruebaGenerarContraseñaLongitudCero() {

        String contraseña = GeneradorContrasena.generarContraseña(0);

        assertEquals(0, contraseña.length());

    }

    @Test
    public void pruebaGenerarContraseñaNoEsNull() {

        String contraseña = GeneradorContrasena.generarContraseña(10);

        assertTrue(contraseña != null);

    }

    @Test
    public void pruebaGenerarContraseñaNoVacia() {

        String contraseña = GeneradorContrasena.generarContraseña(10);

        assertFalse(contraseña.isEmpty());

    }

    @Test
    public void pruebaGenerarMultiplesContraseñasMismaLongitud() {

        boolean longitudCorrecta = true;

        for (int i = 0; i < 20; i++) {

            String contraseña = GeneradorContrasena.generarContraseña(15);

            if (contraseña.length() != 15) {
                longitudCorrecta = false;
            }

        }

        assertTrue(longitudCorrecta);

    }

    @Test
    public void pruebaCaracteresPermitidos() {

        String caracteresPermitidos =
                "ABCDEFGHJKMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789@#$%";

        String contraseña = GeneradorContrasena.generarContraseña(50);

        boolean contieneSoloPermitidos = true;

        for (char caracter : contraseña.toCharArray()) {

            if (!caracteresPermitidos.contains(String.valueOf(caracter))) {

                contieneSoloPermitidos = false;

            }

        }

        assertTrue(contieneSoloPermitidos);

    }

    @Test
    public void pruebaGenerarContraseñaLongitudCien() {

        String contraseña = GeneradorContrasena.generarContraseña(100);

        assertEquals(100, contraseña.length());

    }
    
}
