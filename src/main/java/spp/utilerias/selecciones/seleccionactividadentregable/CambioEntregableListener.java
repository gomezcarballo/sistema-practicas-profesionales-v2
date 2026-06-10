/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.selecciones.seleccionactividadentregable;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;

/**
 *
 * @author gomes
 */
public class CambioEntregableListener implements ChangeListener<Boolean>{
    
    private ActividadReporteFinal actividad;

    public CambioEntregableListener(ActividadReporteFinal actividad) {
        this.actividad = actividad;
    }

    @Override
    public void changed(ObservableValue<? extends Boolean> observable, Boolean valorViejo, Boolean valorNuevo) {
        actividad.setEsEntregable(valorNuevo);
    }
    
}
