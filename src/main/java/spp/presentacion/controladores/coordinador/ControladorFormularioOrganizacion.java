/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.validaciones.validacionesInsercion.ValidacionOrganizacion;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorFormularioOrganizacion {
    
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
        
        if(camposValidos()){
            
            if(organizacion == null){
                
                organizacion = new Organizacion();
                
            }
            
            String nombre = txtNombre.getText();
            String direccion = txtDireccion.getText();
            String sector = cbOpcionesSector.getValue();

            organizacion.setNombre(nombre);
            organizacion.setDireccion(direccion);
            organizacion.setSector(sector);
        
            if(organizacion.getIdOrganizacion() > 0){
            
                actualizarOrganizacion(organizacion, evento);
                
            }else{  
                
                registrarOrganizacion(organizacion, evento);
                
            }
            
        }else{
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");

        }
    }
    
    @FXML
    private boolean camposValidos(){
        
        boolean sonCamposValidos = true; 
        
        if(txtNombre.getText().isBlank() ||  txtDireccion.getText().isBlank() || 
            cbOpcionesSector.getValue() == null){
           
            sonCamposValidos = false; 
            
        }
        return sonCamposValidos; 
    }
    
    @FXML 
    private void registrarOrganizacion(Organizacion organizacion, ActionEvent evento){
        
        try{
            
            ValidacionOrganizacion validacion = new ValidacionOrganizacion();
            validacion.ingresarOrganizacion(organizacion);
            
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

            ValidacionOrganizacion validacion = new ValidacionOrganizacion();
            validacion.actualizarOrganizacion(organizacion);

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Actualización Exitosa",
            "Organización actualizada correctamente");
            
            CargadorVentana.cargarVentanaConControlador("/fxml/VistaListaOrganizaciones.fxml",
            "Lista de Organizaciones");
            
            CerradorVentana.cerrarVentana(evento);


        }catch(ReglaDeNegocioExcepcion e){

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Actualización fallida",
            e.getMessage());
            

        }

    }
    
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
