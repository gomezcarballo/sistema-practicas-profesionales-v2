package spp.utilerias.numerosentablas;

import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;

public class FabricaCeldaNumerica implements Callback<TableColumn<ActividadReporteFinal, String>, TableCell<ActividadReporteFinal, String>> {
    @Override
    public TableCell<ActividadReporteFinal, String> call(TableColumn<ActividadReporteFinal, String> param) {
        return new CeldaNumericaEditable();
    }
}
