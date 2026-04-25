/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.interfacesdao.IMensajeDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class MensajeDAO implements IMensajeDAO{

    @Override
    public boolean registrarMensaje(Mensaje mensaje) throws OperacionesDeDaoExcepcion{
        boolean registroExitoso = false;
        
        String consultaSQL = """
                INSERT INTO Mensaje 
                (asunto, cuerpo, fecha) VALUES (?, ?, ?)""";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {

            consultaPreparada.setString(1, mensaje.getAsunto());
            consultaPreparada.setString(2, mensaje.getCuerpo());
            consultaPreparada.setObject(3, mensaje.getFecha());
            
            consultaPreparada.executeUpdate();
            
            registroExitoso = true;

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
    return registroExitoso;
    
    }

    @Override
    public Mensaje consultarMensaje(String asunto) throws OperacionesDeDaoExcepcion{
        
        Mensaje mensaje = null;
        
        String consultaSQL = "SELECT idMensaje, asunto, cuerpo, fecha FROM Mensaje WHERE asunto = ?";
        
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1, asunto); 
            
            try(ResultSet resultadosConsulta = consultaPreparada.executeQuery();){
                if(resultadosConsulta.next() ){
                    
                mensaje = new Mensaje();
                
                mensaje.setIdMensaje(resultadosConsulta.getInt("idMensaje"));
                mensaje.setAsunto(resultadosConsulta.getString("asunto"));
                mensaje.setFecha(resultadosConsulta.getObject("fecha", LocalDateTime.class));
                
                }
            }
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
    return mensaje; 
    }
    
}
