package spp.utilerias.seleccionactividadentregable;

import javafx.beans.property.SimpleBooleanProperty;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;

public class SeleccionActividadEntregable implements Callback<TableColumn.CellDataFeatures<ActividadReporteFinal, Boolean>, ObservableValue<Boolean>> {

    @Override
    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<ActividadReporteFinal, Boolean> param) {
        
        ActividadReporteFinal actividad = param.getValue();
        SimpleBooleanProperty propiedadBooleana = new SimpleBooleanProperty(actividad.getEsEntregable());

        propiedadBooleana.addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observable, Boolean valorViejo, Boolean valorNuevo) {

                actividad.setEsEntregable(valorNuevo);
                
            }
        });
        
        return propiedadBooleana;
    }
}