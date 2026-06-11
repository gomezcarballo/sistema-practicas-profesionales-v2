/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.cell.CheckBoxTableCell;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.gestores.GestorProyectos;
import spp.logicadenegocio.gestores.GestorSolicitudesProyectos;
import spp.presentacion.controladores.coordinador.ControladorBaseListaProyectos;
import spp.presentacion.controladores.coordinador.ControladorDetalleProyecto;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.selecciones.seleccionproyecto.SeleccionProyecto;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorSolicitudProyectos extends ControladorBaseListaProyectos{
    
    @FXML
    private Button btnSolicitarProyectos;
    
    @FXML
    private TableColumn<Proyecto, Boolean> colSeleccionar;  

    @FXML
    private TableColumn<Proyecto, String> colNombreOrganizacion;
    
    private GestorProyectos gestorProyectos;
    
    private GestorSolicitudesProyectos gestorSolicitudes;
        
    @FXML
    @Override
    public void initialize() {
        
        gestorProyectos = new GestorProyectos();
        
        gestorSolicitudes = new GestorSolicitudesProyectos();
        
        super.initialize();
        
        tblListaProyectos.setEditable(true);
        
        cargarProyectos();
        
        aplicarRestriccionDeSolicitudes();
        
    }
    
    private void aplicarRestriccionDeSolicitudes() {
        
        try {
            
            int idUsuarioActual = SesionUsuario.getInstancia().getIdUsuario();
            int cantidadSolicitudes = gestorSolicitudes.contarSolicitudesPorPracticante(idUsuarioActual);
            int cantidadMaxima = 3;
            if (cantidadSolicitudes >= cantidadMaxima) {
                
                btnSolicitarProyectos.setDisable(true);
                
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Límite de solicitudes", 
                "Has alcanzado el límite máximo de 3 solicitudes activas. El botón ha sido deshabilitado.");
                
            }
            
        } catch (ReglaDeNegocioExcepcion e) {
            
            btnSolicitarProyectos.setDisable(true);
            
            RegistroErrores.registrarError(Level.SEVERE, "Error al validar límite de solicitudes", e);
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error de validación", 
            "No se pudo verificar tu límite de solicitudes actuales.");
            
        }
        
    }
    
    
    @Override
    protected void  configurarColumnasEspecificas(){
        

        colNombreOrganizacion.setCellValueFactory(new PropertyValueFactory<>("nombreOrganizacion"));
        
        colSeleccionar.setCellValueFactory(new SeleccionProyecto());

        colSeleccionar.setCellFactory(CheckBoxTableCell.forTableColumn(colSeleccionar));
        
        colSeleccionar.setEditable(true);
        
    }
    
    @FXML
    private void abrirDetalleProyecto(ActionEvent evento){

        Proyecto proyectoSeleccionado = obtenerProyectoSeleccionado();

         if (proyectoSeleccionado != null) {
        
            FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaDetalleProyecto.fxml",
            "Detalle Proyecto");

            if (cargador != null) {

                ControladorDetalleProyecto controlador = cargador.getController();

                controlador.cargarProyecto(proyectoSeleccionado);

            }

        }

    }
    
    private void cargarProyectos(){
        
        try{           
            
            ObservableList<Proyecto> proyectos = FXCollections.observableArrayList
            (gestorProyectos.recuperarProyectosActivos());
            
            tblListaProyectos.setItems(proyectos);
                        
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al recuperar Proyectos", 
            "Hubo un error al recuperar los Proyectos. Intente más tarde");

        }
    }
    
    @FXML
    private void solicitarProyectos(ActionEvent evento) {

        List<Proyecto> proyectosSeleccionados = obtenerProyectosSeleccionados();
        registrarSolicitudesSeleccionadas(proyectosSeleccionados, evento);

    }
    
    private List<Proyecto> obtenerProyectosSeleccionados() {

        List<Proyecto> proyectosSeleccionados = new ArrayList<>();

        for(Proyecto proyecto : tblListaProyectos.getItems()) {

            if(proyecto.getEsSeleccionado()) {

                proyectosSeleccionados.add(proyecto);
            }
        }

        return proyectosSeleccionados;
    }
    
    
    private void registrarSolicitudesSeleccionadas(List<Proyecto> proyectosSeleccionados, ActionEvent evento) {

        try {
                     
            gestorSolicitudes.registrarSolicitudes(proyectosSeleccionados);
            
            eliminarProyectosDeLaTabla(proyectosSeleccionados);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Solicitudes registradas",
            "Las solicitudes se registraron correctamente");
            
            irAlMenu(evento);

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error", e.getMessage());
            
        }
    }
    
    private void eliminarProyectosDeLaTabla(List<Proyecto> proyectosEliminados) {
        
        ObservableList<Proyecto> listaMaestra = tblListaProyectos.getItems();
        
        for (int i = 0; i < proyectosEliminados.size(); i++) {
            
            Proyecto proyectoParaEliminar = proyectosEliminados.get(i);
            
            listaMaestra.remove(proyectoParaEliminar);
            
        }
        
        tblListaProyectos.refresh();
        
    }
    
    
    private void irAlMenu(ActionEvent evento){
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
        "Menú Principal para Practicante");
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        irAlMenu(evento);
        
    }

}
