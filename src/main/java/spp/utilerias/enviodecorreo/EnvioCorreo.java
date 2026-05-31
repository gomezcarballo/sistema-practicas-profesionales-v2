/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.enviodecorreo;

import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

import jakarta.mail.Authenticator;
import jakarta.mail.Message;
import jakarta.mail.MessagingException;
import jakarta.mail.PasswordAuthentication;
import jakarta.mail.Session;
import jakarta.mail.Transport;

import jakarta.mail.internet.InternetAddress;
import jakarta.mail.internet.MimeMessage;
import java.io.IOException;


/**
 *
 * @author gomes
 */
public class EnvioCorreo {
    
    private Properties propiedades;
    private String correoRemitente;
    private String contrasenaRemitente;

    public EnvioCorreo() {
        cargarConfiguracion();
    }

    private void cargarConfiguracion() {
        
        try {
            propiedades = new Properties();

            InputStream entrada = getClass().getClassLoader().getResourceAsStream("correospp.properties");

            propiedades.load(entrada);

            correoRemitente = propiedades.getProperty("correospp.usuario");
            
            contrasenaRemitente = propiedades.getProperty("correospp.contrasena");

        } catch (IOException e) {
            
            throw new RuntimeException("Error cargando configuracion", e);
            
        }
        
    }

    private Session conectarGmail() {

        Authenticator autenticador = new AutenticadorCorreo(correoRemitente, contrasenaRemitente);

        return Session.getInstance(propiedades, autenticador);

    }

    public boolean enviarContraseña(String correoDestino, String contrasena) {
        
        try {
            
            Session sesion = conectarGmail();

            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(new InternetAddress(correoRemitente));
            mensaje.setRecipients(Message.RecipientType.TO, InternetAddress.parse(correoDestino));

            mensaje.setSubject("Acceso a tu cuenta");

            String contenido = """
                <h2>Cuenta creada</h2>
                <p>Su contraseña es:</p>
                <b>%s</b>
                <p>Por favor, cambie su contraseña al iniciar sesión por primera vez.</p>
                <p>Bienvenido al Sistema de Practicas Profesionales.</p>
                """.formatted(contrasena);

            mensaje.setContent(contenido, "text/html");
            mensaje.setSentDate(new Date());

            Transport.send(mensaje);

            return true;

        } catch (MessagingException e) {
            
            throw new RuntimeException("No se pudo enviar el correo", e);
            
        }
        
    }
    
}
