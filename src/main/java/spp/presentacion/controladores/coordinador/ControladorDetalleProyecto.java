/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.cargadordeventanas.CargadorVentana;
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
    
    private Organizacion organizacion;
    
    @FXML
    public void initialize() {

        txtNombre.setEditable(false);

        txtDescripcion.setEditable(false);

        txtNombreResponsable.setEditable(false);

        txtCupoMaximo.setEditable(false);
        
    }

    public void cargarProyecto(Proyecto proyecto, Organizacion organizacion){
        
        this.organizacion = organizacion;

        txtNombre.setText(proyecto.getNombre());
        txtDescripcion.setText(proyecto.getDescripcion());
        txtNombreResponsable.setText(proyecto.getNombreResponsable());
        txtCupoMaximo.setText(String.valueOf(proyecto.getCupoMaximo()));

    }

    @FXML
    public void regresar(ActionEvent evento){
        
        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaListaProyectos.fxml",
        "Lista de Proyectos");

        if(cargador != null){

            ControladorListaProyectos controlador = cargador.getController();

            controlador.inicializarDatos(organizacion);

            CerradorVentana.cerrarVentana(evento);
        }

    }
  
}
