/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.gestores.GestorProyectos;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.validadorsoloenteros.ValidadorEnteros;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorGestionProyecto {

    @FXML
    private TextField txtNombre;
    
    @FXML
    private TextArea taObjetivoGeneral;
    
    @FXML
    private TextField txtNombreResponsable;
    
    @FXML
    private TextField txtContactoResponsable;
    
    @FXML
    private TextArea taMetodologia;
    
    @FXML
    private TextField txtCupoMaximo;
    
    @FXML
    private Label lblTituloFormulario;

    @FXML
    private Button btnGuardar;
    
    private Organizacion organizacion;
    
    private Proyecto proyecto;
        
    @FXML
    public void initialize() {
        
        lblTituloFormulario.setText("Registrar Proyecto");
        btnGuardar.setText("Registrar");
                
    }
    
    @FXML
    private void validarCupoMaximo(KeyEvent evento) {

        TextField campoTexto = (TextField) evento.getSource();
        ValidadorEnteros.validarSoloNumeros(campoTexto);
     
    }
    
    public void inicializarOrganizacion(Organizacion organizacion){

        this.organizacion = organizacion;

    }
    
    public void inicializarDatos(Proyecto proyecto, Organizacion organizacion){
        
        this.proyecto = proyecto;
        this.organizacion = organizacion;
            
        txtNombre.setText(proyecto.getNombre());
        taObjetivoGeneral.setText(proyecto.getObjetivoGeneral());
        txtNombreResponsable.setText(proyecto.getNombreResponsable());
        txtContactoResponsable.setText(proyecto.getContactoResponsable());
        taMetodologia.setText(proyecto.getMetodologia());
        txtCupoMaximo.setText(String.valueOf(proyecto.getCupoMaximo()));      
        
        lblTituloFormulario.setText("Actualizar Proyecto");
        btnGuardar.setText("Guardar cambios");
        
    }

    @FXML
    private void leerDatosDeProyecto(ActionEvent evento) {
        
        if (!sonCamposValidos() || !esCupoMaximo()) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos incorrectos", 
            "Por favor verifica que todos los campos estén llenos y el cupo sea un número válido.");
            return; 
        
        }

        boolean esActualizacion = (proyecto != null && proyecto.getIdProyecto() > 0);
        boolean necesitaGuardar = true;

        if (esActualizacion && !huboCambios()) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Sin cambios", 
            "No ha cambiado nada de los datos del proyecto.");
            necesitaGuardar = false; 
       
        }

        if (necesitaGuardar) {
            
            if (proyecto == null) {
                proyecto = new Proyecto();
            }
            
            mapearDatosAProyecto(proyecto);

            GestorProyectos gestor = new GestorProyectos();
            List<String> listaErrores = gestor.validarCamposProyecto(proyecto);

            if (listaErrores.isEmpty()) {
                
                if (esActualizacion) {
                    actualizarProyecto(proyecto, evento);
                } else {
                    registrarProyecto(proyecto, evento);
                
                }
            } else {
                VentanaMensaje.mostrarVentanaErrores(listaErrores);
            }
        }
    }
    
    @FXML
    private boolean sonCamposValidos(){
        
        boolean sonCamposValidos = true; 
        
        if(txtNombre.getText().isBlank() ||  taObjetivoGeneral.getText().isBlank() || 
           txtNombreResponsable.getText().isBlank() || txtCupoMaximo.getText().isBlank() ||
           txtContactoResponsable.getText().isBlank() || taMetodologia.getText().isBlank()){
            
            sonCamposValidos = false; 

        }
        
        return sonCamposValidos; 
        
    }   
    
    @FXML    
    private boolean esCupoMaximo(){
        
        boolean esCupoMaximo = true;
        
        int digitosMaximos = 10;
        
        String cupoMaximo = txtCupoMaximo.getText();
        
        if(cupoMaximo.length() > digitosMaximos ){
            
            esCupoMaximo = false;
            
        }
        
        return esCupoMaximo;
        
    }
    
    private boolean huboCambios() {

        if (this.proyecto == null) return true;

        return !this.proyecto.getNombre().equals(txtNombre.getText().trim()) ||
               !this.proyecto.getObjetivoGeneral().equals(taObjetivoGeneral.getText().trim()) ||
               !this.proyecto.getNombreResponsable().equals(txtNombreResponsable.getText().trim()) ||
               !this.proyecto.getContactoResponsable().equals(txtContactoResponsable.getText().trim()) ||
               !this.proyecto.getMetodologia().equals(taMetodologia.getText().trim()) ||
               this.proyecto.getCupoMaximo() != Integer.parseInt(txtCupoMaximo.getText().trim());
        
    }
    
    @FXML
    private void registrarProyecto(Proyecto proyecto, ActionEvent evento) {
        
        try {
            
            GestorProyectos gestor = new GestorProyectos();
            gestor.ingresarProyecto(proyecto);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
            "Proyecto registrado correctamente");
            
            CerradorVentana.cerrarVentana(evento);
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", "Ocurrió un error al registrar el proyecto. Intente más tarde.");                      
        
        }
    }
    
    @FXML
    private void actualizarProyecto(Proyecto proyecto, ActionEvent evento) {
        
        try {
            
            GestorProyectos gestor = new GestorProyectos();
            gestor.actualizarProyecto(proyecto);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Actualización Exitosa", 
            "Proyecto actualizado correctamente");   
            
            CerradorVentana.cerrarVentana(evento);
                
        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Actualizacion fallida", "Ocurrió un error al actualizar el proyecto. Intente más tarde.");                    
        
        }
    }
    
    private void mapearDatosAProyecto(Proyecto proyecto) {
        
        proyecto.setNombre(txtNombre.getText().trim());
        proyecto.setObjetivoGeneral(taObjetivoGeneral.getText().trim());
        proyecto.setNombreResponsable(txtNombreResponsable.getText().trim());
        proyecto.setContactoResponsable(txtContactoResponsable.getText().trim());
        proyecto.setMetodologia(taMetodologia.getText().trim());
        proyecto.setCupoMaximo(Integer.parseInt(txtCupoMaximo.getText().trim()));
        proyecto.setOrganizacion(this.organizacion);
        
    }
    
    @FXML
    public void cancelar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
