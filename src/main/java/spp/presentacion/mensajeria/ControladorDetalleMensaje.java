/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.mensajeria;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;
import spp.utilerias.formatofechas.FormatoFechas;

/**
 *
 * @author gomes
 */
public class ControladorDetalleMensaje {
    
    @FXML
    private TextField txtAsunto;

    @FXML
    private Label lblTipoCorreo;

    @FXML
    private TextField txtCorreo;

    @FXML
    private TextField txtFecha;

    @FXML
    private TextArea taCuerpo;
    
    @FXML
    public void initialize() {

        txtAsunto.setEditable(false);

        txtCorreo.setEditable(false);

        txtFecha.setEditable(false);

        taCuerpo.setEditable(false);
        
    }
    
    public void cargarMensaje(Mensaje mensaje) {

        txtAsunto.setText(mensaje.getAsunto());

        if(mensaje.getCorreoRemitente() != null) {

            lblTipoCorreo.setText("Remitente:");

            txtCorreo.setText(mensaje.getCorreoRemitente());

        } else {

            lblTipoCorreo.setText("Destinatario:");

            txtCorreo.setText(mensaje.getCorreoDestinatario());
            
        }
        
        txtFecha.setText(FormatoFechas.formatearFechaHora(mensaje.getFecha()));
        
        taCuerpo.setText(mensaje.getCuerpo());
        
    }
    
    
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
