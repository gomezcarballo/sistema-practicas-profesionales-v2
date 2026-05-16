/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.interfacesdao.IMensajeDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class MensajeDAO implements IMensajeDAO{

    @Override
    public int insertarMensaje(Mensaje mensaje) throws OperacionesDeDaoExcepcion{
        
        String consultaSQL = "INSERT INTO Mensaje (asunto, cuerpo, fecha) VALUES (?, ?, NOW())";
        
        int idMensajeGenerado = -1;
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL, 
            Statement.RETURN_GENERATED_KEYS);) {

            consultaPreparada.setString(1, mensaje.getAsunto());
            consultaPreparada.setString(2, mensaje.getCuerpo());
            
            consultaPreparada.executeUpdate();
            
            ResultSet resultado = consultaPreparada.getGeneratedKeys();

            if(resultado.next()) {
                idMensajeGenerado = resultado.getInt(1);
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
        return idMensajeGenerado;  
    }
    
    @Override
    public List<Mensaje> consultarMensajesPorDestinatario(String correo)throws OperacionesDeDaoExcepcion {

        List<Mensaje> mensajes = new ArrayList<>();

        String consultaSQL = """
            SELECT 
                m.idMensaje,
                m.asunto,
                m.cuerpo,
                m.fecha,
                u.correoInstitucional,
            FROM Mensaje m
            INNER JOIN Mensaje_Usuario mu
                ON m.idMensaje = mu.Mensaje_idMensaje
            INNER JOIN Usuario u
                ON mu.Usuario_idUsuario = u.idUsuario
            WHERE mu.destinatario = ?
            ORDER BY m.fecha DESC
         """;

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, correo);

            ResultSet resultado = consultaPreparada.executeQuery();

            while(resultado.next()) {

                Mensaje mensaje = new Mensaje();

                mensaje.setIdMensaje(resultado.getInt("idMensaje"));
                mensaje.setAsunto(resultado.getString("asunto"));
                mensaje.setCuerpo(resultado.getString("cuerpo"));
                mensaje.setFecha(resultado.getObject("fecha",LocalDateTime.class));
                mensaje.setCorreoRemitente(resultado.getString("correoInstitucional"));

                mensajes.add(mensaje);
                
            }

        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        return mensajes;
    }
}
