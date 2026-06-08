package spp.utilerias.numerosentablas;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TextField;
import spp.utilerias.validadorsoloenteros.ValidadorEnteros;

public class EscuchaValidacionNumerica implements ChangeListener<String> {
    private TextField campoTexto;

    public EscuchaValidacionNumerica(TextField campoTexto) {
        this.campoTexto = campoTexto;
    }

    @Override
    public void changed(ObservableValue<? extends String> observable, String valorViejo, String valorNuevo) {
        // Aquí mandamos a llamar tu función reciclada tal como la querías
        ValidadorEnteros.validarSoloNumeros(campoTexto);
    }
}
