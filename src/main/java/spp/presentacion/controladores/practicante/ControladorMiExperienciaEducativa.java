/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Profesor;
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
public class ControladorMiExperienciaEducativa {
        
    @FXML 
    private TextField txtNombreEE;
    
    @FXML 
    private TextField txtPeriodo;
    
    @FXML 
    private TextField txtProfesor;
    
    private ExperienciaEducativa grupo;
    private GestorPracticantes gestorPracticante;

    @FXML
    public void initialize() {

        gestorPracticante = new GestorPracticantes();
        configurarCampos();
        cargarGrupoAsignado();

    }

    private void configurarCampos() {

        txtNombreEE.setEditable(false);
        txtPeriodo.setEditable(false);
        txtProfesor.setEditable(false);

    }

    private void cargarGrupoAsignado() {

        try {
            
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuario = sesionUsuario.getIdUsuario();

            grupo = gestorPracticante.recuperarExperienciaEducativaAsignada(idUsuario);

            if (grupo != null) {
                
                Profesor profesorAsignado = gestorPracticante.recuperarProfesorPorId(grupo.getIdProfesorAsignado());
                mostrarDatosGrupo(profesorAsignado);
                
            } else {
                
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Sin asignación",
                "Aún no tienes un grupo asignado.");
                
            }

        } catch (OperacionesDeDaoExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al cargar grupo",
            e.getMessage());

        }

    }

    private void mostrarDatosGrupo(Profesor profesor) {

        txtNombreEE.setText(grupo.getNombreExperienciaEducativa());
        txtPeriodo.setText(grupo.getPeriodo());
        
        if (profesor != null) {
            
            String nombreCompleto = profesor.getNombre() + " " + 
            profesor.getApellidoPaterno() + " " +  profesor.getApellidoMaterno();
            txtProfesor.setText(nombreCompleto);
            
        } else {
            
            txtProfesor.setText("Sin profesor asignado");
       
        }

    }
    
    @FXML
    private void regresar(ActionEvent evento) {

        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
        "Menú Principal para Practicante");
        CerradorVentana.cerrarVentana(evento);

    }   
    
}
