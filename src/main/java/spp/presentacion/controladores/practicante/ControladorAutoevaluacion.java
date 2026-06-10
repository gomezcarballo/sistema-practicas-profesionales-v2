/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import spp.logicadenegocio.clasesdto.Autoevaluacion;
import spp.logicadenegocio.gestores.GestorAutoevaluacion;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionAutoevaluacion;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.validadorsoloenteros.ValidadorEnteros;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorAutoevaluacion {

    @FXML private TextField txtValorPrimeraAfirmacion;
    @FXML private TextField txtValorSegundaAfirmacion;
    @FXML private TextField txtValorTerceraAfirmacion;
    @FXML private TextField txtValorCuartaAfirmacion;
    @FXML private TextField txtValorQuintaAfirmacion;
    @FXML private TextField txtValorSextaAfirmacion;
    @FXML private TextField txtValorSeptimaAfirmacion;
    @FXML private TextField txtValorOctavaAfirmacion;
    @FXML private TextField txtValorNovenaAfirmacion;
    @FXML private TextField txtValorDecimaAfirmacion;
    
    @FXML
    public void validarNumeros(KeyEvent evento) {
        
        TextField campoTexto = (TextField) evento.getSource();
        ValidadorEnteros.validarSoloNumeros(campoTexto);
        
    }
    
    @FXML
    public void leerDatosDeAutoevaluacion(ActionEvent evento){
        
        if(sonCamposDeAutoevaluacionValidos()){
            
            Autoevaluacion autoevaluacion = crearAutoevaluacion();
            ValidacionAutoevaluacion validacionAutoevaluacion = new ValidacionAutoevaluacion();
            
            if(validacionAutoevaluacion.sonValoresValidos(autoevaluacion)){
                
                int puntuacionTotal = validacionAutoevaluacion.calcularPuntuacionFinal(autoevaluacion);
                autoevaluacion.setPuntuacionFinal(puntuacionTotal);
                
                guardarAutoevaluacion(autoevaluacion);
                
            }else{
                
                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Valores fuera de rango", 
                "Asegúrese de que todas las calificaciones estén entre 1 y 5.");
                
            }
            
        }else{
            
            mostrarMensajeCamposFaltantes();
            
        }
        
        
    }
    
    @FXML
    public boolean sonCamposDeAutoevaluacionValidos(){
        
        boolean sonCamposValidos = true;
        
        if(txtValorPrimeraAfirmacion.getText().isBlank() || txtValorSegundaAfirmacion.getText().isBlank() ||
           txtValorTerceraAfirmacion.getText().isBlank() || txtValorCuartaAfirmacion.getText().isBlank() ||
           txtValorQuintaAfirmacion.getText().isBlank() || txtValorSextaAfirmacion.getText().isBlank() ||
           txtValorSeptimaAfirmacion.getText().isBlank() || txtValorOctavaAfirmacion.getText().isBlank() ||
           txtValorNovenaAfirmacion.getText().isBlank() || txtValorDecimaAfirmacion.getText().isBlank()){
        
            sonCamposValidos = false;
            
        }
        return sonCamposValidos;
        
    }
    
    private Autoevaluacion crearAutoevaluacion(){
        
        Autoevaluacion autoevaluacion = new Autoevaluacion();
        
        int primeraAfirmacion = Integer.parseInt(txtValorPrimeraAfirmacion.getText());
        int segundaAfirmacion = Integer.parseInt(txtValorSegundaAfirmacion.getText());
        int terceraAfirmacion = Integer.parseInt(txtValorTerceraAfirmacion.getText());
        int cuartaAfirmacion = Integer.parseInt(txtValorCuartaAfirmacion.getText());
        int quintaAfirmacion = Integer.parseInt(txtValorQuintaAfirmacion.getText());
        int sextaAfirmacion = Integer.parseInt(txtValorSextaAfirmacion.getText());
        int septimaAfirmacion = Integer.parseInt(txtValorSeptimaAfirmacion.getText());
        int octavaAfirmacion = Integer.parseInt(txtValorOctavaAfirmacion.getText());
        int novenaAfirmacion = Integer.parseInt(txtValorNovenaAfirmacion.getText());
        int decimaAfirmacion = Integer.parseInt(txtValorDecimaAfirmacion.getText());
        
        autoevaluacion.setValorPrimeraAfirmacion(primeraAfirmacion);
        autoevaluacion.setValorSegundaAfirmacion(segundaAfirmacion);
        autoevaluacion.setValorTerceraAfirmacion(terceraAfirmacion);
        autoevaluacion.setValorCuartaAfirmacion(cuartaAfirmacion);
        autoevaluacion.setValorQuintaAfirmacion(quintaAfirmacion);
        autoevaluacion.setValorSextaAfirmacion(sextaAfirmacion);
        autoevaluacion.setValorSeptimaAfirmacion(septimaAfirmacion);
        autoevaluacion.setValorOctavaAfirmacion(octavaAfirmacion);
        autoevaluacion.setValorNovenaAfirmacion(novenaAfirmacion);
        autoevaluacion.setValorDecimaAfirmacion(decimaAfirmacion);
        
        return autoevaluacion;
        
    }
    
    private void guardarAutoevaluacion(Autoevaluacion autoevaluacion){
        
        try{
            
            GestorAutoevaluacion gestor = new GestorAutoevaluacion();
            gestor.generarAutoevaluacion(autoevaluacion);

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Descarga Exitosa", 
            "Autoevaluacion generada correctamente."); 
            
        }catch(OperacionesDeDaoExcepcion | ProcesamientoSistemaExcepcion e){
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al generar autoevaluación", 
            e.getMessage());
            
        }
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaGenerarEvidenciaPracticas.fxml", 
        "Generar Evidencias de Practicas");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    private void mostrarMensajeCamposFaltantes(){
        
        VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
        "Faltan datos por agregar. Por favor ingrese todos los campos requeridos.");           
        
    }
    
}