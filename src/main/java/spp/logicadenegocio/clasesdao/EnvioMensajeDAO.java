/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
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
    public boolean insertarEnvioMensaje(int idMensaje, int idUsuario, String destinatario) 
    throws OperacionesDeDaoExcepcion {

        boolean registroExitoso = false;

        String consultaSQL = "INSERT INTO EnvioMensaje (Mensaje_idMensaje, Usuario_idUsuario, destinatario)"
                + "VALUES (?, ?, ?)";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idMensaje);
            consultaPreparada.setInt(2, idUsuario);
            consultaPreparada.setString(3, destinatario);

            int filasAfectadas = consultaPreparada.executeUpdate();

            if (filasAfectadas > 0) {
                registroExitoso = true;
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return registroExitoso;      
    }
    
    @Override
    public boolean eliminarEnvioMensaje(int idMensaje, int idUsuario) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM EnvioMensaje WHERE Mensaje_idMensaje = ? "
                + "AND Usuario_idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idMensaje);
            consultaPreparada.setInt(2, idUsuario);

            int filasAfectadas = consultaPreparada.executeUpdate();

            if (filasAfectadas > 0) {
                eliminacionExitosa = true;
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return eliminacionExitosa;
    }

    @Override
    public String consultarDestinatario(int idMensaje, int idUsuario) throws OperacionesDeDaoExcepcion {

        String destinatario = null;

        String consultaSQL = "SELECT destinatario FROM EnvioMensaje WHERE Mensaje_idMensaje = ? "
                + "AND Usuario_idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idMensaje);
            consultaPreparada.setInt(2, idUsuario);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {
                destinatario = resultadosConsulta.getString("destinatario");
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return destinatario;
    }
    
}
