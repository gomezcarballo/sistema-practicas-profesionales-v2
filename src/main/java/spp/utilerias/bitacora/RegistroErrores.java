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
import java.util.logging.Level;

/**
 *
 * @author gomes
 */

public class RegistroErrores {

    private static final String DIRECTORIO_LOGS = "logs/";

    public static void registrarError(Level nivel, String mensaje, Exception excepcion) {
        
        String fechaActual = LocalDate.now().toString();
        String nombreArchivo = "log_" + fechaActual + ".txt";
        Path ruta = Paths.get(DIRECTORIO_LOGS, nombreArchivo);
        
        try {
            
            Files.createDirectories(Paths.get(DIRECTORIO_LOGS));
            
            try (FileWriter escritor = new FileWriter(ruta.toFile(), true)) {
                
                escritor.write(construirEntradaLog(nivel, mensaje, excepcion));
                
            }

        } catch (IOException e) {
            
            System.err.println("Error crítico: No se pudo escribir en el archivo de log. " + e.getMessage());
            
        }
        
    }

    private static String construirEntradaLog(Level nivel, String mensaje, Exception excepcion) {
        
        String entradaLog = "[" + LocalDateTime.now() + "] [" + nivel.getName() + "] " + mensaje;

        if (excepcion != null) {
            
            entradaLog += " - Detalles: " + excepcion.getMessage();
            
        }

        return entradaLog + "\n";
        
    }
    
}
