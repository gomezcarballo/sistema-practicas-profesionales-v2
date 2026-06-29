/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.gestores.GestorPracticantes;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorMiProyecto {
        
    @FXML 
    private TextField txtNombre;
    
    @FXML 
    private TextField txtResponsable;
   
    @FXML 
    private TextArea txtObjetivoGeneral; 

    private Proyecto proyecto;
    
    private GestorPracticantes gestorPracticante;

    @FXML
    public void initialize() {

        gestorPracticante = new GestorPracticantes();
        configurarCampos();
        cargarProyectoAsignado();

    }

    private void configurarCampos() {

        txtNombre.setEditable(false);
        txtResponsable.setEditable(false);
        txtObjetivoGeneral.setEditable(false);

    }

    private void cargarProyectoAsignado() {

        try {
            
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuario = sesionUsuario.getIdUsuario();

            proyecto = gestorPracticante.recuperarProyectoAsignado(idUsuario);

            if (proyecto != null) {
                
                mostrarDatosProyecto();
                
            } else {
                
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Sin asignación",
                "Aún no tienes un proyecto asignado.");
                
            }

        } catch (OperacionesDeDaoExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al cargar proyecto",
            e.getMessage());

        }

    }

    private void mostrarDatosProyecto() {

        txtNombre.setText(proyecto.getNombre());
        txtResponsable.setText(proyecto.getNombreResponsable());
        txtObjetivoGeneral.setText(proyecto.getObjetivoGeneral());

    }
    
    @FXML
    private void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
        "Menú Principal para Practicante");
        CerradorVentana.cerrarVentana(evento);

    }
    
}
