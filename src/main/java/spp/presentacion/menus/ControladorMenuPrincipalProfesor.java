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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.stage.FileChooser;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordesesion.CerradorSesion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalProfesor {

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


                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Subida Exitosa", 
                    "El formato se cargo correctamente en el sistema");

            } catch (Exception e) {
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error", 
                    "No se pudo guardar el archivo en el sistema");
            }
        }
    }
    
    @FXML
    private void abrirSubMenuMensajes(){
        
        CargadorVentana.cargarVentana("/fxml/VistaSubMenuMensajes.fxml", "Mensajes");
        
    }
    
    @FXML
    private void cerrarSesion(ActionEvent evento) {

        CerradorSesion.cerrarSesion(evento);
        
    }
}
