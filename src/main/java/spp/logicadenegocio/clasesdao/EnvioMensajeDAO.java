/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.interfacesdao.IEnvioMensajeDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class EnvioMensajeDAO implements IEnvioMensajeDAO{
    
    @Override
    public boolean insertarEnvioMensaje(int idMensaje, int idRemitente, int idDestinatario) 
    throws OperacionesDeDaoExcepcion {

        boolean registroExitoso = false;

        String consultaSQL = "INSERT INTO EnvioMensaje (Mensaje_idMensaje, Usuario_idRemitente, "
                + "Usuario_idDestinatario) VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idMensaje);
            consultaPreparada.setInt(2, idRemitente);
            consultaPreparada.setInt(3, idDestinatario);

            registroExitoso = consultaPreparada.executeUpdate() > 0;

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return registroExitoso;      
    }
    
    @Override
    public boolean eliminarEnvioMensaje(int idMensaje,int idRemitente,int idDestinatario)throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL =
                "DELETE FROM EnvioMensaje "
                + "WHERE Mensaje_idMensaje = ? "
                + "AND Usuario_idRemitente = ? "
                + "AND Usuario_idDestinatario = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idMensaje);
            consultaPreparada.setInt(2, idRemitente);
            consultaPreparada.setInt(3, idDestinatario);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
            
        }

        return eliminacionExitosa;
    }

}
