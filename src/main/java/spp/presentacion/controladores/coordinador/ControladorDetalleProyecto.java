/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.utilerias.ventanas.cerradordeventanas.CerradorVentana;

/**
 *
 * @author gomes
 */
public class ControladorDetalleProyecto {
    
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
    private TextField txtNombreOrganizacion;
    
    private Organizacion organizacion;
    
    @FXML
    public void initialize() {

        txtNombre.setEditable(false);

        taObjetivoGeneral.setEditable(false);

        txtNombreResponsable.setEditable(false);

        txtCupoMaximo.setEditable(false);
        
        txtNombreOrganizacion.setEditable(false);
        
        txtContactoResponsable.setEditable(false);
                
        taMetodologia.setEditable(false);       
        
    }
    
    public void cargarProyecto(Proyecto proyecto){
       
        cargarDatosProyecto(proyecto);
        
    }
    
    public void cargarProyecto(Proyecto proyecto, Organizacion organizacion){
        
        this.organizacion = organizacion;
        
        cargarDatosProyecto(proyecto);
        
    }
    
    public void cargarDatosProyecto(Proyecto proyecto){
        
        txtNombre.setText(proyecto.getNombre());
        taObjetivoGeneral.setText(proyecto.getObjetivoGeneral());
        txtNombreResponsable.setText(proyecto.getNombreResponsable());
        txtCupoMaximo.setText(String.valueOf(proyecto.getCupoMaximo()));
        taMetodologia.setText(proyecto.getMetodologia());
        txtContactoResponsable.setText(proyecto.getContactoResponsable());
        
        if (organizacion != null) {

            txtNombreOrganizacion.setText(organizacion.getNombre());

        } else {

            txtNombreOrganizacion.setText(proyecto.getOrganizacion().getNombre());

        }

    }

    @FXML
    public void regresar(ActionEvent evento){

        CerradorVentana.cerrarVentana(evento);
        
    }
  
}
