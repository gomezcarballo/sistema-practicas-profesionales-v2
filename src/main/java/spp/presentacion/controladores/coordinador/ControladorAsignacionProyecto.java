/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.gestores.GestorAsignacionProyectos;
import spp.logicadenegocio.gestores.GestorPracticantes;
import spp.logicadenegocio.gestores.GestorSolicitudesProyectos;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorAsignacionProyecto extends ControladorBaseListaProyectos{
    
    @FXML 
    private Button btnAsignar;
    @FXML 
    private TableColumn<Proyecto, String> colNombreResponsable;
    
    private GestorSolicitudesProyectos gestorSolicitudes;
    
    private GestorAsignacionProyectos gestorAsignacionProyecto;
    
    private Practicante practicante;
    
    public void inicializarDatos(Practicante practicante) {
        
        this.practicante = practicante;
        cargarProyectosSolicitados();
        verificarEstadoPracticante();
    
    }
    
    @FXML
    @Override
    public void initialize() {
        
        gestorSolicitudes = new GestorSolicitudesProyectos();
        gestorAsignacionProyecto = new GestorAsignacionProyectos();

        super.initialize();
        
        tblListaProyectos.setEditable(true);
    
    }
    
    @Override
    protected void configurarColumnasEspecificas() {
       
        colNombreResponsable.setCellValueFactory(new PropertyValueFactory<>("nombreResponsable"));
   
    }
    
    private void cargarProyectosSolicitados() {
        
        try {
           
            List<Proyecto> lista = gestorSolicitudes.recuperarProyectosSolicitados(practicante.getIdUsuario());
            tblListaProyectos.setItems(FXCollections.observableArrayList(lista));
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar Proyectos", 
            "Hubo un error al recuperar los Proyectos solicitados. Intente más tarde");
        
        }
   
    } 
    
    private void verificarEstadoPracticante() {
        
        try {
            
            GestorPracticantes gestor = new GestorPracticantes();
            boolean yaTieneProyecto = gestor.verificarAsignacionProyecto(practicante.getIdUsuario());
            
            if (yaTieneProyecto) {
                
                btnAsignar.setDisable(true);
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Practicante asignado", 
                "Este practicante ya tiene un proyecto asignado. Solo modo lectura.");
            
            }
            
        } catch (OperacionesDeDaoExcepcion e) {
            RegistroErrores.registrarError(Level.WARNING, "Fallo al verificar estado previo del practicante", e);
        }
        
    }
    
    @FXML
    private void asignarProyecto(ActionEvent evento) {
        
        Proyecto proyectoSeleccionado = obtenerProyectoSeleccionado();
        
        if (proyectoSeleccionado != null) {
            validarYRegistrarAsignacion(proyectoSeleccionado, evento);
        }
        
    }
    
    private void validarYRegistrarAsignacion(Proyecto proyectoSeleccionado, ActionEvent evento) {
       
        try {
           
            GestorPracticantes gestorPracticantes = new GestorPracticantes();
            boolean yaTieneProyecto = gestorPracticantes.verificarAsignacionProyecto(practicante.getIdUsuario());
            
            if (yaTieneProyecto) {
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Asignación bloqueada", 
                "El registro no procede: Este practicante ya cuenta con un proyecto activo asignado.");
            } else {
                registrarAsignacionProyecto(proyectoSeleccionado, evento);
            }
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error de validación", 
            "No se pudo verificar el estado actual del practicante.");
        
        }
    }
    
    private void registrarAsignacionProyecto(Proyecto proyectoSeleccionado, ActionEvent evento) {
       
        try {
           
            List<String> errores = gestorAsignacionProyecto.asignarProyecto(proyectoSeleccionado.getIdProyecto()
            , practicante.getIdUsuario());

            if (errores.isEmpty()) {
                
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Asignación exitosa",
                "El proyecto fue asignado correctamente");
                
                regresar(evento);
            
            } else {
                VentanaMensaje.mostrarVentanaErrores(errores);
            }

        } catch (OperacionesDeDaoExcepcion e) {
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error", e.getMessage());
        }
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        CargadorVentana.cargarVentana("/fxml/VistaSolicitudesPracticantes.fxml", "Solicitudes de Proyectos");
        CerradorVentana.cerrarVentana(evento);
    }
    
}
