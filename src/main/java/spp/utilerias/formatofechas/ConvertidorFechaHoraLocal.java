/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.formatofechas;

import java.time.LocalDateTime;
import javafx.util.StringConverter;

/**
 *
 * @author gomes
 */
public class ConvertidorFechaHoraLocal extends StringConverter<LocalDateTime> {
    
    @Override
    public String toString(LocalDateTime fechaHora) {
        return FormatoFechas.formatearFechaHora(fechaHora);
    }

    @Override
    public LocalDateTime fromString(String texto) {
        return LocalDateTime.parse(texto);
    }
    
}
