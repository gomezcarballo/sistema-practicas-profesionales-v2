/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.ReferenciaCurso;
import spp.logicadenegocio.gestores.GestorExperienciasEducativas;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.validadorsoloenteros.ValidadorEnteros;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorNuevaExperienciaEducativa {
    
    @FXML
    private TextField txtNombre;
    
    @FXML
    private ComboBox<String> cbPeriodo;
    
    @FXML
    private TextField txtCupo;
    
    private ReferenciaCurso nrcSeleccionado;

    @FXML
    public void initialize() {

        String[] periodos = {"Febrero - Julio", "Agosto - Enero"};
        for (int i = 0; i < periodos.length; i++) {
            cbPeriodo.getItems().add(periodos[i]);
        }
        
    }
    
    public void inicializarDatos(ReferenciaCurso nrc) {
        
        this.nrcSeleccionado = nrc;
        
    }

    @FXML
    private void leerDatosNuevaExperienciaEducativa(ActionEvent evento) {
        
        if (sonCamposValidos() && esCupoMenorADigitosMaximos()) {

            ExperienciaEducativa experienciaEducativa = crearExperienciaEducativa();
            registrarNuevaExperienciaEducativa(experienciaEducativa, evento);
        
        } else {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes o inválidos", 
            "Por favor verifica que todos los campos estén llenos y el cupo sea un número válido.");
        
        }
    
    }

    private ExperienciaEducativa crearExperienciaEducativa() {
        
        String nombre = txtNombre.getText().trim();
        String periodo = cbPeriodo.getValue();
        int cupo = Integer.parseInt(txtCupo.getText().trim());

        ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
        experienciaEducativa.setNombreExperienciaEducativa(nombre);
        experienciaEducativa.setPeriodo(periodo);
        experienciaEducativa.setIdReferenciaCurso(nrcSeleccionado.getIdReferenciaCurso());
        experienciaEducativa.setCupo(cupo);

        return experienciaEducativa;
    
    }

    private boolean sonCamposValidos() {
        
        boolean sonCamposValidos = true;
        
        if (txtNombre.getText().isBlank() ||  cbPeriodo.getValue() == null || txtCupo.getText().isBlank()) {
            sonCamposValidos = false;
        } 
        
        return sonCamposValidos;
    }
    
    @FXML
    private void validarCupo(KeyEvent evento) {

        TextField campoTexto = (TextField) evento.getSource();
        ValidadorEnteros.validarSoloNumeros(campoTexto);
     
    }
    
    @FXML    
    private boolean esCupoMenorADigitosMaximos(){
        
        String cupoMaximo = txtCupo.getText();
        
        return ValidadorEnteros.verificarMaximoDigitos(cupoMaximo);

    }

    private void registrarNuevaExperienciaEducativa(ExperienciaEducativa experienciaEducativa, ActionEvent evento) {
        
        try {
            
            GestorExperienciasEducativas gestor = new GestorExperienciasEducativas();
            
            if (validarExperienciaEducativa(experienciaEducativa)) {
                
                gestor.ingresarExperienciaEducativa(experienciaEducativa);

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
                "Experiencia Educativa registrada correctamente");
                
                regresar(evento);
            }
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", e.getMessage());
        
        }
    
    }

    private boolean validarExperienciaEducativa(ExperienciaEducativa experienciaEducativa) {
        
        boolean eeValida = false;
        
        try {
            
            GestorExperienciasEducativas gestor = new GestorExperienciasEducativas();
            List<String> listaValidaciones = gestor.validarCamposDeEE(experienciaEducativa);

            if (listaValidaciones.isEmpty()) {
                
                eeValida = true;
            
            } else {
                
                VentanaMensaje.mostrarVentanaErrores(listaValidaciones);
            
            }
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error de validación", e.getMessage());
        
        }
        return eeValida;
    }

    @FXML
    public void regresar(ActionEvent evento) {
        CargadorVentana.cargarVentana("/fxml/VistaListaReferenciaCurso.fxml", "Lista de NRC");
        CerradorVentana.cerrarVentana(evento);
    }
    
}
