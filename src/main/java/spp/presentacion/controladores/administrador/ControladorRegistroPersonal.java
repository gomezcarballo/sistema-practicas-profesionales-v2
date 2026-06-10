/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.administrador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.TextField;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorRegistroPersonal {
    
    @FXML
    protected TextField txtNombre;

    @FXML
    protected TextField txtApellidoPaterno;

    @FXML
    protected TextField txtApellidoMaterno;

    @FXML
    protected TextField txtCorreo;

    @FXML
    protected TextField txtNumeroPersonal;

    protected boolean camposValidos(){

        boolean sonCamposValidos = true;

        if(txtNombre.getText().isBlank() || txtApellidoPaterno.getText().isBlank() ||
           txtCorreo.getText().isBlank() || txtNumeroPersonal.getText().isBlank()){

            sonCamposValidos = false;

        }

        if(txtApellidoMaterno.getText().isBlank()){

            txtApellidoMaterno.setText("");

        }

        return sonCamposValidos;

    }

    protected void mostrarMensajeCamposFaltantes(){

        VentanaMensaje.mostrarVentanaMensaje(AlertType.WARNING,"Datos faltantes",
        "Faltan datos por agregar. Por favor ingreselos");

    }

    @FXML
    protected void regresar(ActionEvent evento){

        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalAdministrador.fxml",
        "Menu Principal para Administrador");

        CerradorVentana.cerrarVentana(evento);

    }
    
}
