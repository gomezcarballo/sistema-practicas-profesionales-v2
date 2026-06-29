package spp.presentacion.controladores.practicante;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public abstract class ControladorBaseListaPracticantes {
    
    @FXML 
    protected TableView<Practicante> tblListaPracticantes;
    
    @FXML 
    protected TableColumn<Practicante, String> colNombre;
    
    @FXML 
    protected TableColumn<Practicante, String> colApellidoPaterno;
    
    @FXML 
    protected TableColumn<Practicante, String> colApellidoMaterno;
    
    @FXML 
    protected TableColumn<Practicante, String> colMatricula; 

    @FXML
    public void initialize() {
        
        tblListaPracticantes.setPlaceholder(new Label("No hay practicantes disponibles"));
        
        tblListaPracticantes.getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
        
        configurarColumnas();
                        
    }

    private void configurarColumnas() {

        colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        
        colApellidoPaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoPaterno"));
        
        colApellidoMaterno.setCellValueFactory(new PropertyValueFactory<>("apellidoMaterno"));
        
        colMatricula.setCellValueFactory(new PropertyValueFactory<>("matricula"));
        
    }

    protected Practicante obtenerPracticanteSeleccionado() {
        
        Practicante practicanteSeleccionado = tblListaPracticantes.getSelectionModel().getSelectedItem();
        
        if (practicanteSeleccionado == null) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Sin selección",
            "Debe seleccionar un practicante.");
        
        }
        return practicanteSeleccionado;
    }

    protected abstract void cargarDatosEspecificos();

}
    

