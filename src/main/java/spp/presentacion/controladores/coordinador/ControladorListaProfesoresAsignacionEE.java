/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.ArrayList;
import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.gestores.GestorExperienciaEducativa;
import spp.logicadenegocio.gestores.GestorProfesores;
import spp.presentacion.controladores.administrador.ControladorBaseListaProfesores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaProfesoresAsignacionEE extends ControladorBaseListaProfesores{
    
    private GestorProfesores gestorProfesor;
    private GestorExperienciaEducativa gestorEE;

    @FXML
    @Override
    public void initialize() {

        gestorProfesor = new GestorProfesores();
        super.initialize();
        cargarDatosEspecificos();
        
    }
    
    @Override
    protected void cargarDatosEspecificos() {

        try {

            List<Profesor> profesores = gestorProfesor.recuperarProfesoresParaAsignacionEE();
            tblListaProfesores.getItems().clear();
            tblListaProfesores.setItems(FXCollections.observableArrayList(profesores));

        } catch (OperacionesDeDaoExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar profesores",
            e.getMessage());

        }

    }

    @FXML
    private void asignarExperienciaEducativa(ActionEvent evento) {

        List<ExperienciaEducativa> listaExperienciasSinProfesor = verificarExperienciasEducativasSinProfesor();

        if (listaExperienciasSinProfesor != null && !listaExperienciasSinProfesor.isEmpty()) {

            Profesor profesorSeleccionado = obtenerProfesorSeleccionado();

            if (profesorSeleccionado != null) {

                FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador( 
                    "/fxml/VistaAsignacionProfesorAEE.fxml", "Asignacion Profesor a Experiencia Educativa");
                    
                if (cargador != null) {

                    ControladorAsignacionProfesorAEE controlador = cargador.getController();
                    controlador.inicializarDatos(profesorSeleccionado, listaExperienciasSinProfesor);
                    CerradorVentana.cerrarVentana(evento);

                }
            }
            
        } else {

             VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "No hay grupos disponibles",
                "No existen Experiencias Educativas sin profesor asignado. Por favor, registre un nuevo grupo primero.");

        }

    }

    private List<ExperienciaEducativa> verificarExperienciasEducativasSinProfesor() {

        List<ExperienciaEducativa> listaExperienciasLibres = new ArrayList<>();
        gestorEE = new GestorExperienciaEducativa();

        try {
            
            listaExperienciasLibres = gestorEE.buscarExperienciasEducativasSinProfesor();

        } catch (OperacionesDeDaoExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar grupos",
            e.getMessage());
            
        }

        return listaExperienciasLibres;
        
    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaMenuPrincipalCoordinador.fxml", 
        "Menu de Coordinador");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
