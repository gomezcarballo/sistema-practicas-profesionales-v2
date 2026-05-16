/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import spp.logicadenegocio.clasesdao.MensajeDAO;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.interfacesdao.IMensajeDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class GestorMensajesEnviados {
    
    private IMensajeDAO mensajeDAO;
    
    public GestorMensajesEnviados(){
        mensajeDAO = new MensajeDAO();
    }
    
    public List<Mensaje> consultarMensajesEnviados(int idUsuario)throws ReglaDeNegocioExcepcion{
        
        try{
           return mensajeDAO.consultarMensajesEnviados(idUsuario);
        }catch(OperacionesDeDaoExcepcion e){
           throw new ReglaDeNegocioExcepcion("No se pudieron obtener los mensajes");
        }
        
    }
    
}
