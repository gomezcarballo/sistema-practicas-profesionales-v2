/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalProfesor {
    
    
    @FXML
    private Button botonCerrarSesion;
    
    @FXML
    private void agregarFormatoPresentacion() {
        FileChooser exploradorArchivos = new FileChooser();
        exploradorArchivos.setTitle("Selecciona el formato de presentación");

        File archivoSeleccionado = exploradorArchivos.showOpenDialog(null);

        if (archivoSeleccionado != null) {
            try {
                String idUsuario = String.valueOf(SesionUsuario.getInstancia().getIdUsuario());

                String rutaProyecto = System.getProperty("user.dir");

                Path rutaCarpetaFinal = Paths.get(rutaProyecto, "Documentos", "FormatoPresentacion", idUsuario);

                Files.createDirectories(rutaCarpetaFinal);

                Path destinoCompleto = rutaCarpetaFinal.resolve(archivoSeleccionado.getName());

                Files.copy(archivoSeleccionado.toPath(), destinoCompleto, StandardCopyOption.REPLACE_EXISTING);

                VentanaMensaje ventanaMensaje = new VentanaMensaje();
                ventanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Subida Exitosa", 
                    "El formato se cargo correctamente en el sistema");

            } catch (Exception e) {
                new VentanaMensaje().mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error", 
                    "No se pudo guardar el archivo en el sistema");
            }
        }
    }
    @FXML
    private void cerrarSesion() {

        SesionUsuario.getInstancia().cerrarSesion();

        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-InicioSesion.fxml", "Inicio de sesión");

        Stage escenarioActual = (Stage) botonCerrarSesion.getScene().getWindow();

        escenarioActual.close();
        
    }
}
