package spp.utilerias.numerosentablas;

import javafx.scene.control.TextField;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.converter.DefaultStringConverter;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;

public class CeldaNumericaEditable extends TextFieldTableCell<ActividadReporteFinal, String>{
    public CeldaNumericaEditable() {
        super(new DefaultStringConverter());
    }

    @Override
    public void startEdit() {
        super.startEdit(); 
        if (getGraphic() instanceof TextField) {
            TextField campoTexto = (TextField) getGraphic();
            
            campoTexto.textProperty().addListener(new EscuchaValidacionNumerica(campoTexto));
        }
    }
}

