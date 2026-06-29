/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.profesor;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.gestores.GestorProfesores;
import spp.presentacion.controladores.practicante.ControladorBaseListaPracticantes;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorListaPracticantesEE extends ControladorBaseListaPracticantes{
    
    @FXML 
    private Label lblTituloPracticantes;

    private ExperienciaEducativa experienciaRecibida;
    private GestorProfesores gestorProfesor;

    
    public void inicializarDatos(ExperienciaEducativa experienciaEducativa) {
        
        this.gestorProfesor = new GestorProfesores();
        this.experienciaRecibida = experienciaEducativa;
        lblTituloPracticantes.setText("Alumnos de: " + experienciaRecibida.getNombreExperienciaEducativa());
        
        cargarDatosEspecificos();
        
    }

    @Override
    protected void cargarDatosEspecificos() {

        try {

            List<Practicante> alumnos = gestorProfesor.recuperarPracticantesPorExperienciaEducativa(experienciaRecibida.getIdExperienciaEducativa());

            tblListaPracticantes.getItems().clear();
            tblListaPracticantes.setItems(FXCollections.observableArrayList(alumnos));

        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar alumnos",
            "Hubo un error al recuperar la lista de practicantes: " + e.getMessage());

        }

    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaListaExperienciasEducativas.fxml", 
        "Mis Grupos Asignados");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
