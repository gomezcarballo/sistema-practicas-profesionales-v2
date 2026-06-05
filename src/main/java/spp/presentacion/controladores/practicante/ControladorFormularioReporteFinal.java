package spp.presentacion.controladores.practicante;


import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.input.KeyEvent;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanademensajes.VentanaMensaje;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ControladorFormularioReporteFinal {

    @FXML TableView<ActividadReporteFinal> tblActividades;
    @FXML TableColumn<ActividadReporteFinal, String> colActividad;
    @FXML TableColumn colAvance;
    @FXML TableColumn<ActividadReporteFinal, String> colObservaciones;
    
    @FXML
    public void validarNumeros(KeyEvent evento) {}

     @FXML
    public void leerDatosDeActividad(ActionEvent evento) {}

    @FXML
    public boolean sonCamposDeActividadValidos() {

        return false;
        

    }
    
    @FXML
    public void generarReporte(ActionEvent evento) {
        
        
        mostrarMensaje();
        
    }

    private void mostrarMensaje() {
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "!UY¡", 
        "En progreso.");
        
    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaGenerarEvidenciaPracticas.fxml", 
        "Generar Evidencias de Practicas");
        CerradorVentana.cerrarVentana(evento);
        
    }

}
