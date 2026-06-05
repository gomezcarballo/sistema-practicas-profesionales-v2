/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.coordinador;

import java.util.List;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.IndicadorReporte;
import spp.logicadenegocio.gestores.GestorReporteIndicadores;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorReporteIndicadores {
    
    @FXML
    private TableView<IndicadorReporte> tblIndicadores;

    @FXML
    private TableColumn<IndicadorReporte, String> colIndicador;

    @FXML
    private TableColumn<IndicadorReporte, Integer> colValor;
    
    @FXML
    public void initialize() {

        configurarColumnas();
        cargarIndicadores();
        
    }
    
    private void configurarColumnas() {

        colIndicador.setCellValueFactory(new PropertyValueFactory<>("indicador"));
        colValor.setCellValueFactory(new PropertyValueFactory<>("valor"));
        
    }
    
    private void cargarIndicadores(){
        
        try{
                
            GestorReporteIndicadores gestorReporteIndicadores = new GestorReporteIndicadores();
            List<IndicadorReporte> indicadores = gestorReporteIndicadores.obtenerIndicadores();
            
            tblIndicadores.getItems().clear();
            
            tblIndicadores.setItems(FXCollections.observableArrayList(indicadores));
            
        }catch(ReglaDeNegocioExcepcion e){
        
            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR,"Error al recuperar practicantes",
            "Hubo un error al recuperar los indicadores");
        
        }
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        CargadorVentana.cargarVentana("/fxml/VistaMenuPrincipalCoordinador.fxml", "Menu para Coordinador");
        CerradorVentana.cerrarVentana(evento);
        
    }
    
}
