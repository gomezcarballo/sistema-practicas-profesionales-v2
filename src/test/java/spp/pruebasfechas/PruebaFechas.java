/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasfechas;

import java.time.LocalDate;
import java.time.LocalDateTime;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;
import org.junit.Test;
import spp.utilerias.formatofechas.ConvertidorFechaHoraLocal;
import spp.utilerias.formatofechas.FormatoFechas;

/**
 *
 * @author gomes
 */
public class PruebaFechas {
    
    @Test
    public void pruebaFormatearFechaHoraValida() {

        LocalDateTime fechaHora = LocalDateTime.of(2026, 6, 4, 14, 30);

        String resultado = FormatoFechas.formatearFechaHora(fechaHora);

        assertEquals("04-06-2026 14:30", resultado);

    }

    @Test
    public void pruebaFormatearFechaHoraNula() {

        String resultado = FormatoFechas.formatearFechaHora(null);

        assertEquals("", resultado);

    }

    @Test
    public void pruebaFormatearFechaValida() {

        LocalDate fecha = LocalDate.of(2026, 6, 4);

        String resultado = FormatoFechas.formatearFecha(fecha);

        assertEquals("04-06-2026", resultado);

    }

    @Test
    public void pruebaFormatearFechaNula() {

        String resultado = FormatoFechas.formatearFecha(null);

        assertEquals("", resultado);

    }

    @Test
    public void pruebaFormatearFechaConCeros() {

        LocalDate fecha = LocalDate.of(2026, 1, 5);

        String resultado = FormatoFechas.formatearFecha(fecha);

        assertEquals("05-01-2026", resultado);

    }

    @Test
    public void pruebaToStringFechaHoraValida() {

        ConvertidorFechaHoraLocal convertidor = new ConvertidorFechaHoraLocal();

        LocalDateTime fechaHora = LocalDateTime.of(2026, 6, 4, 14, 30);

        String resultado = convertidor.toString(fechaHora);

        assertEquals("04-06-2026 14:30", resultado);

    }

    @Test
    public void pruebaToStringFechaHoraNula() {

        ConvertidorFechaHoraLocal convertidor = new ConvertidorFechaHoraLocal();

        String resultado = convertidor.toString(null);

        assertEquals("", resultado);

    }

    @Test
    public void pruebaFromStringValido() {

        ConvertidorFechaHoraLocal convertidor = new ConvertidorFechaHoraLocal();

        LocalDateTime resultado = convertidor.fromString("2026-06-04T14:30");

        assertEquals(LocalDateTime.of(2026, 6, 4, 14, 30), resultado);

    }

    @Test
    public void pruebaFromStringConSegundos() {

        ConvertidorFechaHoraLocal convertidor = new ConvertidorFechaHoraLocal();

        LocalDateTime resultado = convertidor.fromString("2026-06-04T14:30:15");

        assertEquals( LocalDateTime.of(2026, 6, 4, 14, 30, 15), resultado);

    }

    @Test
    public void pruebaFromStringInvalido() {

        ConvertidorFechaHoraLocal convertidor = new ConvertidorFechaHoraLocal();

        try {

            convertidor.fromString("hola");

            fail();

        } catch (Exception e) {

            assertTrue(true);

        }

    }
    
}
