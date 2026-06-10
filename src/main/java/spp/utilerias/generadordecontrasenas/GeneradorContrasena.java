/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.generadordecontrasenas;

import java.security.SecureRandom;
/**
 *
 * @author gomes
 */


public class GeneradorContrasena {

    private static final String CARACTERES =  "ABCDEFGHJKMNPQRSTUVWXYZabcdefghijkmnpqrstuvwxyz23456789@#$%";

    private static final SecureRandom ALEATORIO = new SecureRandom();

    public static String generarContraseña(int longitud) {
        
        StringBuilder contraseña = new StringBuilder(longitud);

        for (int i = 0; i < longitud; i++) {
            int indice = ALEATORIO.nextInt(CARACTERES.length());
            contraseña.append(CARACTERES.charAt(indice));
        }

        return contraseña.toString();
    }
    
}