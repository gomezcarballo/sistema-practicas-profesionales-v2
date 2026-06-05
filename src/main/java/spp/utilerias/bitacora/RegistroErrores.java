/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.bitacora;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 *
 * @author gomes
 */

public class RegistroErrores {

    private static final String DIRECTORIO_LOGS = "logs/";

    public static void registrarError(Exception excepcion) {
        
        String fechaActual = LocalDate.now().toString();
        String nombreArchivo = "log_" + fechaActual + ".txt";
        Path rutaArchivo = Paths.get(DIRECTORIO_LOGS + nombreArchivo);

        try {
            Files.createDirectories(Paths.get(DIRECTORIO_LOGS));

            try (FileWriter escritor = new FileWriter(rutaArchivo.toFile(), true)) {
                escritor.write(construirEntradaLog(excepcion));
            }

        } catch (IOException excepcionIO) {
            
            excepcionIO.printStackTrace();
            
        }
    }

    private static String construirEntradaLog(Exception excepcion) {
        
        return "[" + LocalDateTime.now() + "] " +
                excepcion.getClass().getSimpleName() + ": " +
                excepcion.getMessage() + "\n";
    }
    
}
