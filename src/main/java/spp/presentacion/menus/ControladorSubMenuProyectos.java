/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Label;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.presentacion.controladores.coordinador.ControladorFormularioProyecto;
import spp.presentacion.controladores.coordinador.ControladorListaProyectos;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorSubMenuProyectos {
    
    @FXML
    private Label lblTitulo;

    private Organizacion organizacion;

    public void inicializarDatos(Organizacion organizacion){

        this.organizacion = organizacion;

        lblTitulo.setText("Proyectos de " + organizacion.getNombre());

    }

    @FXML
    private void abrirRegistrarProyecto(){

        FXMLLoader cargadorFormularioProyecto = CargadorVentana.cargarVentanaConControlador(
        "/fxml/VistaFormularioProyecto.fxml", "Registrar Proyecto");

        if(cargadorFormularioProyecto != null){

            ControladorFormularioProyecto controlador = cargadorFormularioProyecto.getController();

            controlador.inicializarOrganizacion(organizacion);

        }

    }

    @FXML
    private void abrirconsultarProyectos(){

        FXMLLoader cargadorListaProyectos = CargadorVentana.cargarVentanaConControlador(
        "/fxml/VistaListaProyectos.fxml","Lista de Proyectos");

        if(cargadorListaProyectos != null){

            ControladorListaProyectos controlador = cargadorListaProyectos.getController();
            controlador.inicializarDatos(organizacion);

        }

    }
    
    @FXML
    private void abrirAsignarProyecto() {
    
        CargadorVentana.cargarVentana("/fxml/VistaAsignacionProyecto.fxml", "Asignar a un Proyecto");
        
    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
