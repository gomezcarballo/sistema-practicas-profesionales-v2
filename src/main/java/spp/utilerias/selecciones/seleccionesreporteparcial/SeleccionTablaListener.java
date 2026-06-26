/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.selecciones.seleccionesreporteparcial;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import spp.logicadenegocio.clasesdto.ActividadReporteParcial;
import spp.presentacion.controladores.practicante.ControladorFormularioReporte;

/**
 *
 * @author gomes
 */
public class SeleccionTablaListener implements ChangeListener<ActividadReporteParcial>{
    
    private ControladorFormularioReporte controlador;

    public SeleccionTablaListener(ControladorFormularioReporte controlador) {
        this.controlador = controlador;
    }

    @Override
    public void changed(ObservableValue<? extends ActividadReporteParcial> observable, 
                      ActividadReporteParcial actividadAnterior, 
                        ActividadReporteParcial actividadNueva) {
        /*
        if (actividadNueva != null) {
            controlador.cargarActividadEnFormulario(actividadNueva);
        }*/
    }
    
}
