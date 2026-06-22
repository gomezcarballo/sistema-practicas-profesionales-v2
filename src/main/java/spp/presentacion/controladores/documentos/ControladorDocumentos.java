/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.documentos;

import java.io.File;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.FileChooser;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.logicadenegocio.gestores.GestorDocumentos;
import spp.logicadenegocio.validaciones.validacionesdocumentos.ValidacionesDocumentos;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

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
    
    private TipoDocumento tipoDocumento;

    @FXML
    private void elegirDocumento() {
        
        FileChooser explorador = new FileChooser();
        explorador.setTitle("Selecciona el archivo deseado");

        String descipcion = "Documentos PDF (*.pdf)"; 
        String extensionArchivo = "*.pdf";
        FileChooser.ExtensionFilter filtroPdf = new FileChooser.ExtensionFilter(descipcion, extensionArchivo);
        explorador.getExtensionFilters().add(filtroPdf);

        File archivoTemporal = explorador.showOpenDialog(null);
        
        validarArchivoTemporal(archivoTemporal);
        
    }
    
    @FXML
    private void validarArchivoTemporal(File archivoTemporal){

        
        if (archivoTemporal != null) {
            
            ValidacionesDocumentos validador = new ValidacionesDocumentos();
            boolean esArchivoValido = validador.esTamañoValidoArchivo(archivoTemporal);
            
            if(esArchivoValido){
            
                archivoSeleccionado = archivoTemporal;

                actualizarVistaArchivo();

            }else{
                
                VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Archivo no permitido", 
                    "El archivo pesa más de lo permitido, por favor selecciona otro.");
                
            }
            
        }
            
    }
    
    @FXML
    private void subirDocumento() {

        if (archivoSeleccionado != null) {
            
            GestorDocumentos gestor = new GestorDocumentos();
            
            try {
                
                if(gestor.guardarDocumento(tipoDocumento, archivoSeleccionado)){
                    
                    VentanaMensaje.mostrarVentanaMensaje(AlertType.INFORMATION, "Éxito", "Documento subido correctamente.");
                    limpiarVista();

                }else{
                    
                    VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING, "Error", "Hubo un error al subir el documento.");
                    limpiarVista();
                    
                }
        
            } catch (OperacionesDeDaoExcepcion | ProcesamientoSistemaExcepcion e) {
                
                VentanaMensaje.mostrarVentanaMensaje(AlertType.ERROR, "Error al subir el archivo.", e.getMessage());
                
            } 
            
        }

    }
    
    public void configurarTipoDocumento(TipoDocumento tipoDocumento){
        
        this.tipoDocumento = tipoDocumento;
        
        lblTituloDocumento.setText("Subir "+ tipoDocumento.getDescripcion());
        
    }

    private void limpiarVista() {

        vbPrevisualizacion.setVisible(false);
        archivoSeleccionado = null;
        
    }

    private void actualizarVistaArchivo() {

        final int CONVERSION_KILO_BYTES = 1024;
        final String KILOBYTES = "KB";

        lblNombreArchivo.setText(archivoSeleccionado.getName());
        lblPesoArchivo.setText((archivoSeleccionado.length() / CONVERSION_KILO_BYTES) + KILOBYTES);

        vbPrevisualizacion.setVisible(true);
        vbPrevisualizacion.setManaged(true);
        
    }
    
    @FXML
    private void cancelar(ActionEvent evento){

        CerradorVentana.cerrarVentana(evento);
     
    }
    
}
