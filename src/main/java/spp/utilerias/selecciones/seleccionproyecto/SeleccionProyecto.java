/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.selecciones.seleccionproyecto;

import javafx.beans.value.ObservableValue;
import javafx.scene.control.TableColumn;
import javafx.util.Callback;
import spp.logicadenegocio.clasesdto.Proyecto;

/**
 *
 * @author gomes
 */
public class SeleccionProyecto implements Callback<TableColumn.CellDataFeatures<Proyecto, Boolean>,
ObservableValue<Boolean>> {
   
    @Override
    public ObservableValue<Boolean> call(TableColumn.CellDataFeatures<Proyecto, Boolean> celda) {

        return celda.getValue().propiedadEsSeleccionado();
        
    }
    
}
