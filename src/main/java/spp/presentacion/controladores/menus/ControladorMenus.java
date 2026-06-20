/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.menus;

import javafx.event.ActionEvent;
import spp.utilerias.ventanas.cargadordeventanas.CargadorVentana;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;

/**
 *
 * @author gomes
 */
public abstract class ControladorMenus {
    
    protected void cambiarVentana(String ruta, String titulo, ActionEvent evento){
        
        CargadorVentana.cargarVentana(ruta, titulo);
        CerradorVentana.cerrarVentana(evento);
        
    }
    
    protected void abrirVentana(String ruta, String titulo){
        
        CargadorVentana.cargarVentana(ruta, titulo);
        
    }
    
}
