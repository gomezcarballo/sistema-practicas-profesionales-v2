/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.menus;

import java.util.logging.Logger;
import javafx.fxml.FXML;
import spp.utilerias.cargadordeventanas.CargadorVentana;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorSubMenuPracticantes {
    
    private static final Logger bitacora = Logger.getLogger(ControladorMenu.class.getName());
    
    @FXML
    private void abrirRegistroPracticante() {
        
        CargadorVentana cargadorVentana = new CargadorVentana();
        cargadorVentana.cargarVentana("/fxml/GUI-FormularioRegistroPracticante.fxml", "Registrar Practicante");
        
    }
}
