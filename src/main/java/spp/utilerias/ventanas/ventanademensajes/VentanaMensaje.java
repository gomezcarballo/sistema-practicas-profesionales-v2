package spp.utilerias.ventanas.ventanademensajes;

import java.util.List;
import java.util.Optional;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonType;
import spp.presentacion.controladores.ControladorErroresValidacion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;

/**
 *
 * @author gomes
 */
public class VentanaMensaje {
    
    private static Alert construirAlerta(AlertType tipo, String titulo, String mensaje){
        
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(mensaje);
        return alerta;

    }
    
    public static void mostrarVentanaMensaje(AlertType tipo, String titulo, String mensaje) {
        
        construirAlerta(tipo, titulo, mensaje).showAndWait();
        
    }
    
    public static boolean mostrarConfirmacion(String titulo, String mensaje) {

        Alert alerta = construirAlerta(Alert.AlertType.CONFIRMATION, titulo, mensaje);       
        Optional<ButtonType> resultado = alerta.showAndWait();
        return resultado.isPresent() && resultado.get() == ButtonType.OK;

    }

    public static void mostrarVentanaErrores(List<String> listaValidaciones) {
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador(
            "/fxml/VistaListaErroresValidaciones.fxml", 
            "Detalles de Validación"
        );
        
        if (cargador != null) {
            ControladorErroresValidacion controladorDestino = cargador.getController();
            
            controladorDestino.recibirListaErrores(listaValidaciones);
        }
    }
    
}
