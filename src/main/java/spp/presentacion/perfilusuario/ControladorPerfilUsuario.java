/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.perfilusuario;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.gestores.GestorPerfilUsuario;
import spp.utilerias.cargadordeventanas.CargadorVentana;
import spp.utilerias.cerradordeventanas.CerradorVentana;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.ventanademensajes.VentanaMensaje;

/**
 *
 * @author gomes
 */
public class ControladorPerfilUsuario {
    
    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtApellidoPaterno;

    @FXML
    private TextField txtApellidoMaterno;

    @FXML
    private TextField txtCorreoInstitucional;

    private Usuario usuario;

    private GestorPerfilUsuario gestorPerfil;

    @FXML
    public void initialize() {

        gestorPerfil = new GestorPerfilUsuario();

        configurarCampos();

        cargarUsuarioSesion();

    }

    private void configurarCampos() {

        txtNombre.setEditable(false);

        txtApellidoPaterno.setEditable(false);

        txtApellidoMaterno.setEditable(false);

        txtCorreoInstitucional.setEditable(false);

    }

    private void cargarUsuarioSesion() {

        try {
            
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            int idUsuario = sesionUsuario.getIdUsuario();

            usuario = gestorPerfil.recuperarUsuario(idUsuario);

            mostrarDatosUsuario();

        } catch (ReglaDeNegocioExcepcion e) {

            VentanaMensaje.mostrarVentanaMensaje(Alert.AlertType.ERROR, "Error al cargar perfil",
            e.getMessage());

        }

    }

    private void mostrarDatosUsuario() {

        txtNombre.setText(usuario.getNombre());
        txtApellidoPaterno.setText(usuario.getApellidoPaterno());
        txtApellidoMaterno.setText(usuario.getApellidoMaterno());
        txtCorreoInstitucional.setText(usuario.getCorreoInstitucional());

    }

    @FXML
    private void abrirCambiarContrasena(ActionEvent evento) {

        FXMLLoader cargador = CargadorVentana.cargarVentanaConControlador("/fxml/VistaCambioContraseña.fxml",
        "Cambiar Contraseña");

        if (cargador != null) {

            ControladorCambioContraseña controlador = cargador.getController();

            controlador.cargarUsuario(usuario);
            CerradorVentana.cerrarVentana(evento);

        }

    }
    
     @FXML
    private void regresar(ActionEvent evento) {

        CerradorVentana.cerrarVentana(evento);

    }
    
}
