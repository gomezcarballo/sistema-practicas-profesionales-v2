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
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.presentacion.menus.ControladorSubMenuProyectos;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanademensajes.VentanaMensaje;


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


    private Organizacion organizacion;

    public void cargarOrganizacion(Organizacion organizacion){

        this.organizacion = organizacion;

        txtNombre.setText(organizacion.getNombre());
        txtDireccion.setText(organizacion.getDireccion());
        txtSector.setText(organizacion.getSector());

    }

    @FXML
    private void abrirActualizarOrganizacion(){

        FXMLLoader cargadorFormulario = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaFormularioOrganizacion.fxml","Actualizar Organización");

        if(cargadorFormulario != null){

            ControladorFormularioOrganizacion controlador = cargadorFormulario.getController();
            controlador.inicializarDatos(organizacion);

        }

    }   
    
    @FXML
    private void verMenuProyectos(){

        FXMLLoader cargadorMenuProyectos = CargadorVentana.cargarVentanaConControlador
        ("/fxml/VistaSubMenuProyectos.fxml", "Menú de Proyectos de" + organizacion.getNombre());

        if(cargadorMenuProyectos != null){

            ControladorSubMenuProyectos controlador = cargadorMenuProyectos.getController();
            controlador.inicializarDatos(organizacion);

        }
    
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
       public void abrirInactivarOrganizacion(ActionEvent evento) {

          VentanaMensaje.mostrarVentanaMensaje( Alert.AlertType.INFORMATION, "!UY!",
            "Funcionalidad no disponible. Intente en la proxima entrega");

        }
    }
