/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import spp.logicadenegocio.enums.TipoMensaje;
import spp.presentacion.mensajeria.ControladorListaMensajesEnviados;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;

/**
 *
 * @author gomes
 */
public class ControladorSubMenuMensajes extends ControladorMenus{
    
    @FXML
    private void abrirMensajesRecibidos(ActionEvent evento){
        
         cambiarVentana("/fxml/VistaListaMensajesRecibidos.fxml/", "Mensajes Recibidos", evento);
        
    }
    
    @FXML
    private void abrirMensajesEnviados(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaListaMensajesEnviados.fxml/", "Mensajes Enviados", evento);
        
    }
    
    @FXML
    private void abrirEnvioMensaje(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaEnvioMensajes.fxml", "Enviar Mensaje", evento);
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
