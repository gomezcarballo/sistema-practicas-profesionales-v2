/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionProyecto;
import spp.utilerias.cargadordeventanas.CargadorVentana;
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
    private TextArea taObjetivoGeneral;
    
    @FXML
    private TextField txtNombreResponsable;
    
    @FXML
    private TextField txtContactoResponsable;
    
    @FXML
    private TextField taMetodologia;
    
    @FXML
    private TextField txtCupoMaximo;
    
    @FXML
    private Label lblTituloFormulario;

    @FXML
    private Button btnGuardar;
    
    private Organizacion organizacion;
    
    private Proyecto proyecto;
    
    ValidacionProyecto validacion = new ValidacionProyecto();
    
    @FXML
    public void initialize() {
        
        lblTituloFormulario.setText("Registrar Proyecto");
        btnGuardar.setText("Registrar");
                
    }
    
    @FXML
    private void validarCupoMaximo(KeyEvent evento) {

        
        String texto = txtCupoMaximo.getText();

        if (!texto.matches("[0-9]*")) {

            texto = texto.replaceAll("[^0-9]", "");

            txtCupoMaximo.setText(texto);
            
            txtCupoMaximo.positionCaret(texto.length());

        }

        
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
    private void leerDatosDeProyecto(ActionEvent evento){
        
        if(!sonCamposValidos()){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Faltan datos por agregar. Por favor ingreselos");
            return;
            
        }
        
        if (!esCupoMaximo()){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos incorrectos", 
            "El cupo no es valido. Por favor ingresa un numero valido");
            return;
            
        }
        
        if(proyecto == null){
                
            proyecto = new Proyecto();

        }

        String nombre = txtNombre.getText();
        String objetivoGeneral = taObjetivoGeneral.getText();
        String nombreResponsable = txtNombreResponsable.getText();
        String contactoResponsable = txtContactoResponsable.getText();
        String metodologia = taMetodologia.getText();
        int cupoMaximo = Integer.parseInt(txtCupoMaximo.getText());

        proyecto.setNombre(nombre);
        proyecto.setObjetivoGeneral(objetivoGeneral);
        proyecto.setNombreResponsable(nombreResponsable);
        proyecto.setContactoResponsable(contactoResponsable);
        proyecto.setMetodologia(metodologia);
        proyecto.setCupoMaximo(cupoMaximo);
        proyecto.setOrganizacion(organizacion);

        int idProyectoNoValido = 0;
        
        if(proyecto.getIdProyecto () > idProyectoNoValido){

            actualizarProyecto(proyecto, evento);

        }else {

            registrarProyecto(proyecto, evento);

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
    
    
    
    @FXML
    private void registrarProyecto(Proyecto proyecto, ActionEvent evento){
       
        try{
            
            validacion.ingresarProyecto(proyecto);
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
            "Proyecto registrado correctamente");
            
            CerradorVentana.cerrarVentana(evento);
            
        }catch(ReglaDeNegocioExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", 
            e.getMessage());
                        
        }
        
    }
    
    @FXML
    private void actualizarProyecto(Proyecto proyecto, ActionEvent evento){
        
        try{
            
            validacion.actualizarProyecto(proyecto);
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Actualización Exitosa", 
            "Proyecto actualizado correctamente");   
            
            FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaListaProyectos.fxml",
            "Lista de Proyectos");

            if(cargador != null){

                ControladorListaProyectos controlador = cargador.getController();

                controlador.inicializarDatos(organizacion);
                
                CerradorVentana.cerrarVentana(evento);
                
            }
            
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
