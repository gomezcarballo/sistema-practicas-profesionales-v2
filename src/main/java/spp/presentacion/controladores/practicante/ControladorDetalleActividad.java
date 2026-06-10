/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.formatofechas.FormatoFechas;

/**
 *
 * @author gomes
 */
public class ControladorDetalleActividad {
    
    @FXML
    private TextField txtTitulo;

    @FXML
    private TextArea taDescripcion;

    @FXML
    private TextField txtFechaLimite;

    
    
    @FXML
    public void initialize() {

        txtTitulo.setEditable(false);
        
        taDescripcion.setEditable(false);
        
        txtFechaLimite.setEditable(false);

    }
    
    public void cargarActividad(Actividad actividad) {

        txtTitulo.setText(actividad.getTitulo());

        taDescripcion.setText(actividad.getDescripcion());
        
        txtFechaLimite.setText(FormatoFechas.formatearFechaHora(actividad.getFechaLimite()));
                
    }
    
    
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaListaActividades.fxml", "Lista de Actividades");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    
    
}
