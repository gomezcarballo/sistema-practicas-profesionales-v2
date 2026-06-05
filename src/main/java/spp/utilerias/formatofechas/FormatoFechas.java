/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.formatofechas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author gomes
 */
public class FormatoFechas {
    
    public static String formatearFechaHora(LocalDateTime fechaHora) {

        if (fechaHora == null) {
            return "";
        }
        
        DateTimeFormatter formatoFechaHora = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                
        return fechaHora.format(formatoFechaHora);
    }
    
    public static String formatearFecha(LocalDate fecha) {

        if (fecha == null) {
            return "";
        }

        DateTimeFormatter formato = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        return fecha.format(formato);
    }   
    
}
