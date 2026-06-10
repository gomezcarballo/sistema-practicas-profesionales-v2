/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLTimeoutException;
import java.util.logging.Level;

import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.interfacesdao.IEnvioMensajeDAO;
import spp.utilerias.bitacora.RegistroErrores;
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

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar el mensaje." +
                "IdMensaje : " + idMensaje + 
                "IdRemitente: " + idRemitente + 
                "IdDestinatario: " + idDestinatario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar el mensaje." + 
                "IdMensaje : " + idMensaje + 
                "IdRemitente: " + idRemitente + 
                "IdDestinatario: " + idDestinatario, e);
            
            throw new OperacionesDeDaoExcepcion("Los datos ingresados no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al insertar el mensaje.  " + 
                "IdMensaje : " + idMensaje + 
                "IdRemitente: " + idRemitente + 
                "IdDestinatario: " + idDestinatario +
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al guardar el mensaje, " + 
                "intente de nuevo más tarde", e);
        }

        return registroExitoso;      
    }
    
    @Override
    public boolean eliminarEnvioMensaje(int idMensaje,int idRemitente,int idDestinatario)
    throws OperacionesDeDaoExcepcion {

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

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar el mensaje. " + 
                "IdMensaje : " + idMensaje + 
                "IdRemitente: " + idRemitente + 
                "IdDestinatario: " + idDestinatario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al eliminar el coordinador. " + 
                "IdMensaje : " + idMensaje + 
                "IdRemitente: " + idRemitente + 
                "IdDestinatario: " + idDestinatario +
                ". SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar el mensaje, " + 
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    }

}
