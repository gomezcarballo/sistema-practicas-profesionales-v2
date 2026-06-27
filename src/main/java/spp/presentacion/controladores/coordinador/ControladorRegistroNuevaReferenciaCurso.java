/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.List;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;
import spp.logicadenegocio.clasesdto.ReferenciaCurso;
import spp.logicadenegocio.gestores.GestorReferenciaCurso;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.validadorsoloenteros.ValidadorEnteros;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.ventanas.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorRegistroNuevaReferenciaCurso {
    
    @FXML
    private TextField txtNrc;
    
    @FXML
    public void validarNrc(KeyEvent evento) {
        TextField campoTexto = (TextField) evento.getSource();
        ValidadorEnteros.validarSoloNumeros(campoTexto);
    }

    @FXML
    private void leerNuevoNRC(ActionEvent evento) {
        
        if (sonCamposValidos()) {
            
            ReferenciaCurso nuevoNrc = crearNrc();
            procesarRegistro(nuevoNrc, evento);
        
        } else {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.WARNING, "Datos faltantes", 
            "Por favor, ingrese un número de NRC válido.");
        
        }
    }

    private ReferenciaCurso crearNrc() {
        
        String valorNrc = txtNrc.getText().trim();
        
        ReferenciaCurso nrc = new ReferenciaCurso();
        nrc.setNrc(valorNrc);
        
        return nrc;
    }

    private boolean sonCamposValidos() {
       
        boolean validos = true;
        
        if (txtNrc.getText() == null || txtNrc.getText().trim().isEmpty()) {
            validos = false;
        }
        
        return validos;
        
    }

    private void procesarRegistro(ReferenciaCurso nrc, ActionEvent evento) {
        
        try {
            
            GestorReferenciaCurso gestor = new GestorReferenciaCurso();
            
            if (validarNrc(nrc)) {
                gestor.ingresarNRC(nrc);

                VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.INFORMATION, "Registro Exitoso", 
                "NRC registrado correctamente");
                
                regresar(evento);
            }
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Registro fallido", e.getMessage());
        
        }
        
    }

    private boolean validarNrc(ReferenciaCurso nrc) {
        
        boolean nrcValido = false;
        
        try {
            
            GestorReferenciaCurso gestor = new GestorReferenciaCurso();
            
            List<String> listaValidaciones = gestor.validarCamposDeNrc(nrc);

            if (listaValidaciones.isEmpty()) {
                
                nrcValido = true;
                
            } else {
                
                VentanaMensaje.mostrarVentanaErrores(listaValidaciones);
            }
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error de validación", e.getMessage());
        
        }
        return nrcValido;
    }

    @FXML
    public void regresar(ActionEvent evento) {
        CargadorVentana.cargarVentana("/fxml/VistaListaReferenciaCurso.fxml", "Lista de NRC");
        CerradorVentana.cerrarVentana(evento);
    }
    
}
