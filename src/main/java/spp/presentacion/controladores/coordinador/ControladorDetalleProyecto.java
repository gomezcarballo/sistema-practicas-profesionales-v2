/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanademensajes.VentanaMensaje;

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

    private Proyecto proyecto;

    public void cargarProyecto(Proyecto proyecto){

        this.proyecto = proyecto;

        txtNombre.setText(proyecto.getNombre());
        txtDescripcion.setText(proyecto.getDescripcion());
        txtNombreResponsable.setText(proyecto.getNombreResponsable());
        txtCupoMaximo.setText(String.valueOf(proyecto.getCupoMaximo()));

    }

    @FXML
    private void abrirActualizarProyecto(){

        FXMLLoader cargadorFormulario = CargadorVentana.cargarVentanaConControlador(
        "/fxml/VistaFormularioProyecto.fxml", "Actualizar Proyecto");

        if(cargadorFormulario != null){

            ControladorFormularioProyecto controlador = cargadorFormulario.getController();
            controlador.inicializarDatos(proyecto);

        }

    }

    @FXML
    public void regresar(ActionEvent evento){

        CerradorVentana.cerrarVentana(evento);

    }
    
    @FXML
    public void abrirInactivarProyecto(ActionEvent evento){

        VentanaMensaje.mostrarVentanaMensaje( Alert.AlertType.INFORMATION, "!UY!",
        "Funcionalidad no disponible. Intente en la proxima entrega");


    }
    
}
