/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.hasheodecontrasenas;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author gomes
 */
public class HasheoContrasena {
    
    public static String hashearContraseña(String contraseñaPlana) {
        return BCrypt.hashpw(contraseñaPlana, BCrypt.gensalt());
    }

    public static boolean verificarContraseña(String contraseñaPlana, String hashGuardado) {
        return BCrypt.checkpw(contraseñaPlana, hashGuardado);
    }
    
}
