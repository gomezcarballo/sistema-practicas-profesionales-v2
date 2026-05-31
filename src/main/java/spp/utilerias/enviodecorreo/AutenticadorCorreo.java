/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.enviodecorreo;

import jakarta.mail.Authenticator;
import jakarta.mail.PasswordAuthentication;

/**
 *
 * @author gomes
 */
public class AutenticadorCorreo extends Authenticator{
    
    private final String correoRemitente;
    private final String contrasenaRemitente;

    public AutenticadorCorreo(String correoRemitente, String contrasenaRemitente) {

        this.correoRemitente = correoRemitente;
        this.contrasenaRemitente = contrasenaRemitente;
        
    }

    @Override
    protected PasswordAuthentication getPasswordAuthentication() {

        return new PasswordAuthentication(correoRemitente, contrasenaRemitente);

    }
    
}
