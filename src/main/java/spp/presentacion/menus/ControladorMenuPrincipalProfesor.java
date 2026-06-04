/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.presentacion.documentos.ControladorDocumentos;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordesesion.CerradorSesion;
//import spp.utilerias.cerradordeventanas.CerradorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorMenuPrincipalProfesor extends ControladorMenus{

    @FXML
    private void agregarFormatoPresentacion() {
        
       FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaSubidaDocumentos.fxml",
        "Subir Documento");
        
        if(cargador != null){
            
            ControladorDocumentos controlador = cargador.getController();
            controlador.configurarTipoDocumento(TipoDocumento.FORMATO_PRESENTACION);
            
        }
    }
    
    @FXML
    private void abrirRegistroNuevaActividad(ActionEvent evento){
        
       cambiarVentana("/fxml/VistaRegistroNuevaActividad.fxml", "Nueva Actividad", evento); 
        
    }
    
    @FXML
    private void abrirSubMenuMensajes(){
        
        abrirVentana("/fxml/VistaSubMenuMensajes.fxml", "Mensajes");
        
    }
    
    @FXML
    private void abrirVerPerfil(){
        
        abrirVentana("/fxml/VistaPerfilUsuario.fxml", "Mensajes");
        
    }
    
    @FXML
    private void cerrarSesion(ActionEvent evento) {

        CerradorSesion.cerrarSesion(evento);
        
    }
    
}
