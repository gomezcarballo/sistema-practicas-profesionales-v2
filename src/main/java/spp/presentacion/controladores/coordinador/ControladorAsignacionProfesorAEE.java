/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.gestores.GestorExperienciaEducativa;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorAsignacionProfesorAEE extends ControladorBaseAsignacionEE{
    
    private Profesor profesorSeleccionado;
    private GestorExperienciaEducativa gestor;

    @FXML
    @Override
    public void initialize() {

        super.initialize();
        gestor = new GestorExperienciaEducativa();

    }

    public void inicializarDatos(Profesor profesor, List<ExperienciaEducativa> listaExperienciaEducativas) {

        this.profesorSeleccionado = profesor;
        cargarExperienciasEducativas(listaExperienciaEducativas);
        
    }

    @FXML 
    private void asignarProfesorAEE(ActionEvent evento) {
        
        ExperienciaEducativa experiencia = obtenerExperienciaSeleccionada();
        boolean asignacionExitosa = false;
         
        if (experiencia != null) {

            try {

                asignacionExitosa = gestor.asignarProfesorAExperiencia(profesorSeleccionado, experiencia);

            } catch (OperacionesDeDaoExcepcion e) {

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error en la asignación", 
                e.getMessage());
                
            }

            if (asignacionExitosa) {

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Asignación exitosa",
                "El profesor ha sido asignado al grupo correctamente.");
                
                regresar(evento);

            } else {

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Error al asignar",
                 "Hubo un problema al procesar la asignación en el sistema. Intente de nuevo.");

            }
            
        }
        
    }

    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentanaConControlador("/fxml/VistaListaProfesoresAsignacionEE.fxml", 
        "Lista de Profesores");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
