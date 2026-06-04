/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.documentos;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorDocumentos {
    
    @FXML 
    private Label lblNombreArchivo;
    
    @FXML 
    private Label lblPesoArchivo;
    
    @FXML 
    private VBox vbPrevisualizacion;
    
    @FXML 
    private Label lblTituloDocumento; 
    
    private File archivoSeleccionado = null;
    //private TipoDocumento tipoDocumento;

    @FXML
    private void elegirDocumento() {
        /* 
        FileChooser explorador = new FileChooser();
        explorador.setTitle("Selecciona el archivo deseado");

        FileChooser.ExtensionFilter filtroPdf = new FileChooser.ExtensionFilter("Documentos PDF (*.pdf)", "*.pdf");
        explorador.getExtensionFilters().add(filtroPdf);

        File archivoTemporal = explorador.showOpenDialog(null);
        
        validarArchivoTemporal(archivoTemporal);
        */
       VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "¡UY!", "Funcionalidad en progreso.");
        
    }
    
    @FXML
    private void validarArchivoTemporal(File archivoTemporal){

        /* 
        if (archivoTemporal != null) {
            
            try {
                
                ValidacionesDocumentos validador = new ValidacionesDocumentos();
                
                validador.archivoValidoPorReglaDeNegocio(archivoTemporal);

                archivoSeleccionado = archivoTemporal;

                actualizarVistaArchivo();

            } catch (ReglaDeNegocioExcepcion e) {
                
                VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Archivo no permitido", e.getMessage());
                
            }
            
        }
            */
    }
    
    @FXML
    private void subirDocumento() {
        /* 
        
        if (archivoSeleccionado != null) {
            
            try {
                
                ValidacionesDocumentos validador = new ValidacionesDocumentos();
                
                validador.guardarDocumento(tipoDocumento, archivoSeleccionado);
        
                VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Éxito", "Documento subido correctamente.");

                 limpiarVista();
                
            } catch (OperacionesDeDaoExcepcion | ProcesamientoSistemaExcepcion e) {
                
                VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error al subir el archivo.", e.getMessage());
                
            } 
            
        }
            */

        VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "¡UY!", "Funcionalidad en progreso.");
    }
    
    public void configurarTipoDocumento(TipoDocumento tipoDocumento){
        /* 
        this.tipoDocumento = tipoDocumento;
        
        lblTituloDocumento.setText("Subir "+tipoDocumento.getDescripcion());
        */
        
    }

    private void limpiarVista() {

        vbPrevisualizacion.setVisible(false);
        archivoSeleccionado = null;
        
    }

    private void actualizarVistaArchivo() {

        lblNombreArchivo.setText(archivoSeleccionado.getName());
        lblPesoArchivo.setText((archivoSeleccionado.length() / 1024) + " KB");

        vbPrevisualizacion.setVisible(true);
        vbPrevisualizacion.setManaged(true);
        
    }
    
    @FXML
    private void cancelar(ActionEvent evento){

        CerradorVentana.cerrarVentana(evento);
     
    }
}
