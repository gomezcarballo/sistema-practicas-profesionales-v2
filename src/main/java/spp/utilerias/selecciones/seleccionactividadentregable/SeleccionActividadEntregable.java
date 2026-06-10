package spp.utilerias.selecciones.seleccionactividadentregable;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;

public class SeleccionActividadEntregable implements Callback<TableColumn.CellDataFeatures<ActividadReporteFinal, Boolean>, ObservableValue<Boolean>> {

    @Override
    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ActividadReporteFinal, Boolean> param) {
        
        ActividadReporteFinal actividad = param.getValue();
        SimpleBooleanProperty propiedadBooleana = new SimpleBooleanProperty(actividad.getEsEntregable());

        propiedadBooleana.addListener(new CambioEntregableListener(actividad));
        
        return propiedadBooleana;
    }
}