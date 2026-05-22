/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.function.UnaryOperator;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.TextFormatter;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.validacionesInsercion.ValidacionProyecto;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorFormularioProyecto {

    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextField txtDescripcion;
    
    @FXML
    private TextField txtNombreResponsable;
    
    @FXML
    private TextField txtCupoMaximo;
    
    @FXML
    private Label lblTituloFormulario;

    @FXML
    private Button btnGuardar;
    
    private Proyecto proyecto;
    
    @FXML
    public void initialize() {
        
        lblTituloFormulario.setText("Registrar Proyecto");
        btnGuardar.setText("Registrar");

        UnaryOperator<TextFormatter.Change> filtro = cambioEntero -> {
            if (cambioEntero.getText().matches("[0-9]*")) {
                return cambioEntero;
            }
            return null;
        };

        txtCupoMaximo.setTextFormatter(new TextFormatter<>(filtro));
    }
    
    public void inicializarDatos(Proyecto proyecto){
        
        this.proyecto = proyecto;
            
        txtNombre.setText(proyecto.getNombre());
        txtDescripcion.setText(proyecto.getDescripcion());
        txtNombreResponsable.setText(proyecto.getNombreResponsable());
        txtCupoMaximo.setText(String.valueOf(proyecto.getCupoMaximo()));      
        
        lblTituloFormulario.setText("Actualizar Proyecto");
        btnGuardar.setText("Guardar cambios");
        
    }

    
    @FXML
    private void leerDatosDeProyecto(){
        
        if( sonCamposValidos() ){
            
            if(proyecto == null){
                
                proyecto = new Proyecto();
                
            }
            
            String nombre = txtNombre.getText();
            String descripcion = txtDescripcion.getText();
            String nombreResponsable = txtNombreResponsable.getText();
            int cupoMaximo = Integer.parseInt(txtCupoMaximo.getText());


            proyecto.setNombre(nombre);
            proyecto.setDescripcion(descripcion);
            proyecto.setNombreResponsable(nombreResponsable);
            proyecto.setCupoMaximo(cupoMaximo);
                        
            if(proyecto.getIdProyecto ()>0){
                
                actualizarProyecto(proyecto);
                
            }else {
                
                registrarProyecto(proyecto);
                
            }
            
        
        }else {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            
        }
        
        
        
    }  
    
    @FXML
    private boolean sonCamposValidos(){

        boolean sonCamposValidos = true; 

        if(txtNombre.getText().isBlank() ||  txtDescripcion.getText().isBlank() || 
            txtNombreResponsable.getText().isBlank() ||txtCupoMaximo.getText().isBlank()){
            
            sonCamposValidos = false; 

        }
        
        return sonCamposValidos; 
        
    }   
    
    @FXML 
    private void registrarProyecto(Proyecto proyecto){
       
        try{
            
            ValidacionProyecto validacion = new ValidacionProyecto();
            validacion.ingresarProyecto(proyecto);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
            "Proyecto registrado correctamente");
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
            e.getMessage());
            
        }
        
    }
    
    @FXML
    private void actualizarProyecto(Proyecto proyecto){
        
        try{
            
            ValidacionProyecto validacion = new ValidacionProyecto();
            validacion.actualizarProyecto(proyecto);
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Actualización Exitosa", 
            "Proyecto actualizado correctamente");          
            
        }catch(ReglaDeNegocioExcepcion e){
        
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Actualizacion fallida", 
            e.getMessage());
        
        }
        
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
