/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.cerradordesesion;

import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.stage.Stage;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.cargadordeventanas.CargadorVentana;

/**
 *
 * @author gomes
 */
public class CerradorSesion {
    
    public static void cerrarSesion(ActionEvent evento) {

        SesionUsuario.getInstancia().cerrarSesion();

        CargadorVentana.cargarVentana("/fxml/VistaInicioSesion.fxml", "Inicio de sesión");

        Stage escenarioActual = (Stage) ((Node) evento.getSource()).getScene().getWindow();

        escenarioActual.close();
        
    }
    
}
