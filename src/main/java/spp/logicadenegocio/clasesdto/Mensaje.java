/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

import java.time.LocalDateTime;
/**
 *
 * @author Luz Fernanda H J
 */
public class Mensaje {
    
    private int idMensaje; 
    private String asunto; 
    private String cuerpo;
    private LocalDateTime fecha;
    private String correoRemitente;
    private String correoDestinatario;
    
    public Mensaje(){
    }

    public Mensaje(int idMensaje, String asunto, String cuerpo, LocalDateTime fecha, 
           String correoDestinatario, String correoRemitente) {
        
        this.idMensaje = idMensaje;
        this.asunto = asunto;
        this.cuerpo = cuerpo;
        this.fecha = fecha;
        this.correoRemitente = correoRemitente;
        this.correoDestinatario = correoDestinatario;
        
    }

    public int getIdMensaje() {
        return idMensaje;
    }

    public void setIdMensaje(int idMensaje) {
        this.idMensaje = idMensaje;
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public void setCuerpo(String cuerpo) {
        this.cuerpo = cuerpo;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
    } 

    public String getCorreoRemitente() {
        return correoRemitente;
    }

    public void setCorreoRemitente(String correoRemitente) {
        this.correoRemitente = correoRemitente;
    }    

    public String getCorreoDestinatario() {
        return correoDestinatario;
    }

    public void setCorreoDestinatario(String correoDestinatario) {
        this.correoDestinatario = correoDestinatario;
    }
  
}
