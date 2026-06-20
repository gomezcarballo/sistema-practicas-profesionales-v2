/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.presentacion.controladores.mensajeria;

import java.util.List;
import javafx.scene.control.cell.PropertyValueFactory;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ControladorListaMensajesRecibidos extends ControladorBaseListaMensajes{
    
    @Override
    protected void configurarColumnaEspecifica() {
        
        colCorreoUsuario.setText("Remitente");
        colCorreoUsuario.setCellValueFactory(new PropertyValueFactory<>("correoRemitente"));
    
    }

    @Override
    protected List<Mensaje> consultarMensajesBD(int idUsuario) throws ReglaDeNegocioExcepcion {
        
        return gestorMensajes.consultarMensajesRecibidos(idUsuario);
    
    }
    
}
