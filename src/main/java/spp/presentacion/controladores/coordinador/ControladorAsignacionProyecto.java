/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.gestores.GestorAsignacionProyectos;
import spp.logicadenegocio.gestores.GestorSolicitudesProyectos;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorAsignacionProyecto extends ControladorBaseListaProyectos{

    @FXML
    private TableColumn<Proyecto, String> colNombreResponsable;
    
    private GestorSolicitudesProyectos gestorSolicitudes;
    
    private GestorAsignacionProyectos gestorAsignacionProyecto;
    
    private Practicante practicante;
    
    public void inicializarDatos(Practicante practicante){

        this.practicante = practicante;
        
        cargarProyectosSolicitados();

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
    protected void  configurarColumnasEspecificas(){
        
        colNombreResponsable.setCellValueFactory(new PropertyValueFactory<>("nombreResponsable"));
        
    }
    
    private void cargarProyectosSolicitados(){
        
        try{
                        
            List<Proyecto> lista = gestorSolicitudes.recuperarProyectosSolicitados(practicante.getIdUsuario());

            tblListaProyectos.setItems(FXCollections.observableArrayList(lista));
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar Proyectos", 
            "Hubo un error al recuperar los Proyectos solicitados. Intente más tarde");

        }
        
    } 
    
    @FXML
    private void asignarProyecto(ActionEvent evento) {

        Proyecto proyectoSeleccionado = obtenerProyectoSeleccionado();
        
        if(proyectoSeleccionado == null) {

            return;

        }
        
        registrarAsignacionProyecto(proyectoSeleccionado, evento);

    }
    
    private void registrarAsignacionProyecto(Proyecto proyectoSeleccionado, ActionEvent evento) {

        try {
            
            gestorAsignacionProyecto.asignarProyecto(proyectoSeleccionado.getIdProyecto(), practicante.getIdUsuario());

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Asignación exitosa",
            "El proyecto fue asignado correctamente");
            
            regresar(evento);

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error", e.getMessage());

        }
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaSolicitudesPracticantes.fxml", "Solicitudes de Proyectos");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
