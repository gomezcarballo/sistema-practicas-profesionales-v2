/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.cerradordeventanas.CerradorVentana;

/**
 *
 * @author gomes
 */
public class ControladorDetalleProyecto {
    
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtDescripcion;

    @FXML
    private TextField txtNombreResponsable;

    @FXML
    private TextField txtCupoMaximo;
    
    @FXML
    public void initialize() {

        txtNombre.setEditable(false);

        txtDescripcion.setEditable(false);

        txtNombreResponsable.setEditable(false);

        txtCupoMaximo.setEditable(false);
        
    }

    public void cargarProyecto(Proyecto proyecto){

        txtNombre.setText(proyecto.getNombre());
        txtDescripcion.setText(proyecto.getDescripcion());
        txtNombreResponsable.setText(proyecto.getNombreResponsable());
        txtCupoMaximo.setText(String.valueOf(proyecto.getCupoMaximo()));

    }

    @FXML
    public void regresar(ActionEvent evento){

        CerradorVentana.cerrarVentana(evento);

    }
  
}
