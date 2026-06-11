/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.mensajeria;

import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;


/**
 *
 * @author gomes
 */
public class ControladorListaMensajesEnviados extends ControladorBaseListaMensajes{
    
    @Override
    protected String obtenerTituloMensajes() {
        return "Mensajes Enviados";
    }

    @Override
    protected void configurarColumnaEspecifica() {
        
        colCorreoUsuario.setText("Destinatario");
        colCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoDestinatario"));
    
    }

    @Override
    protected List<Mensaje> consultarMensajesBD(int idUsuario) throws ReglaDeNegocioExcepcion {
        
        return gestorMensajes.consultarMensajesEnviados(idUsuario);
    
    }
    
}