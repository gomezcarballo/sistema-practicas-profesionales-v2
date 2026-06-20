package spp.presentacion.controladores;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;

public class ControladorErroresValidacion {

    @FXML
    private ListView<String> lvErrores;

    public void recibirListaErrores(List<String> listaValidaciones) {
        
        ObservableList<String> listaObservable = FXCollections.observableArrayList(listaValidaciones);
        
        lvErrores.setItems(listaObservable);
        
    }

    @FXML
    private void aceptar(ActionEvent evento) {
        CerradorVentana.cerrarVentana(evento);
    }

}
  