/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.gestores.GestorOrganizaciones;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorGestionOrganizacion {
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtDireccion;
    
    @FXML
    private Label lblTituloFormulario;

    @FXML
    private Button btnGuardar;
    
    @FXML
    private ComboBox<String> cbOpcionesSector;
    
    private Organizacion organizacion;
    
    @FXML
    public void initialize() {
        
        lblTituloFormulario.setText("Registrar Organizacion");
        btnGuardar.setText("Registrar");
        
        cbOpcionesSector.setItems(FXCollections.observableArrayList("Público", "Privado", "Social"));
        
    }    
    
    public void inicializarDatos(Organizacion organizacion){

        this.organizacion = organizacion;

        txtNombre.setText(organizacion.getNombre());
        txtDireccion.setText(organizacion.getDireccion());
        cbOpcionesSector.setValue(organizacion.getSector());
        
        lblTituloFormulario.setText("Actualizar Organizacion");
        btnGuardar.setText("Guardar cambios");
        
    }
    
    @FXML
    private void leerDatosDeOrganizacion(ActionEvent evento){
        
        if (!camposValidos()) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos.");
            return; 
    
        }
    
        if (organizacion == null) {
            
            organizacion = new Organizacion();
            
        }

        boolean esActualizacion = (organizacion.getIdOrganizacion() > 0);
        boolean necesitaGuardar = true;

        if (esActualizacion && !huboCambios()) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Sin cambios", 
            "No se detectaron modificaciones en los datos de la organización.");
            necesitaGuardar = false; 
            
        }

        if (necesitaGuardar) {
            
            mapearDatosAOrganizacion(organizacion); 

            if (esActualizacion) {
                actualizarOrganizacion(organizacion, evento);
            } else {
                registrarOrganizacion(organizacion, evento);
            }
            
        }
        
    }
    
    @FXML 
    private void registrarOrganizacion(Organizacion organizacion, ActionEvent evento){
        
        try{
            
            GestorOrganizaciones gestor = new GestorOrganizaciones();
            gestor.ingresarOrganizacion(organizacion);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
            "Organización registrada correctamente");
            
            CerradorVentana.cerrarVentana(evento);
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
            e.getMessage());
                        
        }
    }
    
    @FXML
    private void actualizarOrganizacion(Organizacion organizacion, ActionEvent evento){

        try{

            GestorOrganizaciones gestor = new GestorOrganizaciones();
            gestor.actualizarOrganizacion(organizacion);

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Actualización Exitosa",
            "Organización actualizada correctamente");
            
            CerradorVentana.cerrarVentana(evento);


        }catch(ReglaDeNegocioExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Actualización fallida",
            e.getMessage());
            

        }

    }
    
    private void mapearDatosAOrganizacion(Organizacion organizacion) {
        
        organizacion.setNombre(txtNombre.getText().trim());
        organizacion.setDireccion(txtDireccion.getText().trim());
        organizacion.setSector(cbOpcionesSector.getValue());
        
    }   

    private boolean huboCambios() {
        return !this.organizacion.getNombre().equals(txtNombre.getText().trim()) ||
               !this.organizacion.getDireccion().equals(txtDireccion.getText().trim()) ||
               !this.organizacion.getSector().equals(cbOpcionesSector.getValue());
    }
    
    private boolean camposValidos(){
        
        boolean sonCamposValidos = true; 
        
        if(txtNombre.getText().isBlank() ||  txtDireccion.getText().isBlank() || 
            cbOpcionesSector.getValue() == null){
           
            sonCamposValidos = false; 
            
        }
        return sonCamposValidos; 
    }
    
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
