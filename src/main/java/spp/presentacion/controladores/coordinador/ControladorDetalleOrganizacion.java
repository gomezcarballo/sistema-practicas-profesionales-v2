/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.utilerias.cerradordeventanas.CerradorVentana;


/**
 *
 * @author gomes
 */
public class ControladorDetalleOrganizacion {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDireccion;

    @FXML
    private TextField txtSector;

    public void cargarOrganizacion(Organizacion organizacion){

        txtNombre.setText(organizacion.getNombre());
        txtDireccion.setText(organizacion.getDireccion());
        txtSector.setText(organizacion.getSector());

    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }    
    
}
