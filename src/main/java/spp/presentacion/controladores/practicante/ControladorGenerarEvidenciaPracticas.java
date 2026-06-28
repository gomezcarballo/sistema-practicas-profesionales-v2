/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.practicante;

import java.util.logging.Level;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.gestores.GestorGeneracionEvidencias;
import spp.presentacion.controladores.menus.ControladorMenus;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ControladorGenerarEvidenciaPracticas extends ControladorMenus{
    
    @FXML 
    private Button btnReporteParcial;
    
    @FXML 
    private Button btnReporteFinal;
    
    @FXML 
    private Button btnAutoevaluacion;
    
    public void initialize() {
        
        aplicarReglasDeNegocio();
        
    }

    private void aplicarReglasDeNegocio() {
        
        try {
            
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idPracticante = sesionUsuario.getIdUsuario();
            GestorGeneracionEvidencias gestorGeneracion = new GestorGeneracionEvidencias();

            validarReporteFinal(idPracticante, gestorGeneracion);
            validarAutoevaluacion(idPracticante, gestorGeneracion);

        } catch (OperacionesDeDaoExcepcion e) {
            
            bloquearTodaLaInterfaz();
            RegistroErrores.registrarError(Level.SEVERE, "Fallo al validar permisos de generación", e);
            
        }
        
    }

    private void validarReporteFinal(int idPracticante, GestorGeneracionEvidencias gestor) throws OperacionesDeDaoExcepcion {
        
        if (!gestor.puedeGenerarReporteFinal(idPracticante) || gestor.yaSubioReporteFinal(idPracticante)) {
            
            btnReporteFinal.setDisable(true);
            
        }
        
    }

    private void validarAutoevaluacion(int idPracticante, GestorGeneracionEvidencias gestor) throws OperacionesDeDaoExcepcion {
        
        if (!gestor.puedeGenerarAutoevaluacion(idPracticante) || gestor.yaSubioAutoevaluacion(idPracticante)) {
            
            btnAutoevaluacion.setDisable(true);
            
        }
        
    }

    private void bloquearTodaLaInterfaz() {
        
        btnReporteParcial.setDisable(true);
        btnReporteFinal.setDisable(true);
        btnAutoevaluacion.setDisable(true);
        
    }
    
    @FXML 
    private void abrirReporteParcial(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaFormularioReporte.fxml", "Formulario de Reporte Parcial", evento);       
        
    }

    @FXML 
    private void abrirReporteFinal(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaFormularioReporteFinal.fxml", "Formulario de Reporte Final", evento);       

        
    }

    @FXML 
    private void abrirAutoevaluacion(ActionEvent evento){
        
        cambiarVentana("/fxml/VistaFormularioAutoevaluacion.fxml", "Formulario Autoevaluacion", evento);
        
    }
    
    @FXML
    public void regresar(ActionEvent evento) {
        
        cambiarVentana("/fxml/VistaMenuPrincipalPracticante.fxml",
        "Menú Principal para Practicante", evento);
        
    }
    
}
