/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.formatofechas;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author gomes
 */
public class ValidadorFechas {
    
    public static boolean esFechaFutura(LocalDate fecha) {
        boolean esFutura = false;
        if (fecha != null) {
            LocalDate hoy = LocalDate.now();
            if (!fecha.isAfter(hoy)) {
                esFutura = true;
            }
        }
        return esFutura;
    }


    public static boolean esFechaPasada(LocalDateTime fechaHora) {
        boolean esPasada = false;
        if (fechaHora != null) {
            LocalDateTime ahora = LocalDateTime.now();
            if (!fechaHora.isBefore(ahora)) {
                esPasada = true;
            }
        }
        return esPasada;
    }
    
}
