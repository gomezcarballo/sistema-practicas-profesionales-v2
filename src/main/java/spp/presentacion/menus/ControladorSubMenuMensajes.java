/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import spp.logicadenegocio.enums.TipoMensaje;
import spp.presentacion.mensajeria.ControladorListaMensajes;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;

/**
 *
 * @author gomes
 */
public class ControladorSubMenuMensajes extends ControladorMenus{
    
    private void abrirMensajes(TipoMensaje tipoMensaje, String tituloVentana) {

        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaListaMensajes.fxml",
        tituloVentana);

        if(cargador != null){

            ControladorListaMensajes controlador = cargador.getController();

            controlador.setTipoMensaje(tipoMensaje);

        }
        
    }
    
    @FXML
    private void abrirMensajesRecibidos(ActionEvent evento){
        
         abrirMensajes(TipoMensaje.RECIBIDOS, "Mensajes Recibidos");
         CerradorVentana.cerrarVentana(evento);
        
    }
    
    @FXML
    private void abrirMensajesEnviados(ActionEvent evento){
        
        abrirMensajes(TipoMensaje.ENVIADOS, "Mensajes Enviados");
        CerradorVentana.cerrarVentana(evento);
        
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
