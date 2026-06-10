/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.selecciones.seleccionesreportefinal;

import javafx.event.EventHandler;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;

/**
 *
 * @author gomes
 */
public class EdicionObservacionListener implements EventHandler<CellEditEvent<ActividadReporteFinal, String>>{
    
    private TableView<ActividadReporteFinal> tblActividades;

    public EdicionObservacionListener(TableView<ActividadReporteFinal> tblActividades) {
        this.tblActividades = tblActividades;
    }

    @Override
    public void handle(CellEditEvent<ActividadReporteFinal, String> evento) {
        ActividadReporteFinal actividadEditada = evento.getRowValue();
        actividadEditada.setObservaciones(evento.getNewValue());
        tblActividades.refresh();
    }
    
}
