/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.interfacesdao.IMensajeDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class MensajeDAO implements IMensajeDAO{

    @Override
    public int insertarMensaje(Mensaje mensaje) throws OperacionesDeDaoExcepcion{
        
        String consultaSQL = "INSERT INTO Mensaje (asunto, cuerpo, fecha) VALUES (?, ?, NOW())";
        
        int idMensajeGenerado = 0;
        
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

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar mensaje. " +
                "Asunto: " + mensaje.getAsunto(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar mensaje. " +
                "Asunto: " + mensaje.getAsunto() + 
                " (longitud: " + mensaje.getAsunto().length() + "), " +
                "Cuerpo longitud: " + mensaje.getCuerpo().length(), e);
            
            throw new OperacionesDeDaoExcepcion("El mensaje excede el tamaño permitido, " + 
                "acorte el texto", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al insertar mensaje. " +
                "Asunto: " + mensaje.getAsunto() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al enviar el mensaje, " + 
                "intente de nuevo más tarde", e);
        }
        return idMensajeGenerado;  
    }
    
    @Override
    public List<Mensaje> consultarMensajesPorDestinatario(int idUsuarioDestinatario)throws OperacionesDeDaoExcepcion {

        List<Mensaje> mensajesPorDestinatario = new ArrayList<>();

        String consultaSQL = """
            SELECT 
                m.idMensaje,
                m.asunto,
                m.cuerpo,
                m.fecha,
                u.correoInstitucional
            FROM Mensaje m
            INNER JOIN EnvioMensaje em
                ON m.idMensaje = em.Mensaje_idMensaje
            INNER JOIN Usuario u
                ON em.Usuario_idRemitente = u.idUsuario
            WHERE em.Usuario_idDestinatario = ?
            ORDER BY m.fecha DESC
         """;

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuarioDestinatario);

            ResultSet resultado = consultaPreparada.executeQuery();

            while(resultado.next()) {

                Mensaje mensaje = new Mensaje();

                mensaje.setIdMensaje(resultado.getInt("idMensaje"));
                mensaje.setAsunto(resultado.getString("asunto"));
                mensaje.setCuerpo(resultado.getString("cuerpo"));
                mensaje.setFecha(resultado.getObject("fecha",LocalDateTime.class));
                mensaje.setCorreoRemitente(resultado.getString("correoInstitucional"));

                mensajesPorDestinatario.add(mensaje);
                
            }

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al consultar mensajes por destinatario. " +
                "ID Destinatario: " + idUsuarioDestinatario + 
                " - Verificar tablas: Mensaje, EnvioMensaje, Usuario y sus columnas", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar mensajes por destinatario. " +
                "ID Destinatario: " + idUsuarioDestinatario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar mensajes por destinatario. " +
                "ID Destinatario: " + idUsuarioDestinatario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron recuperar los mensajes, " +
                "intente de nuevo", e);
        }
        return mensajesPorDestinatario;
    }
    
    @Override
    public List<Mensaje> consultarMensajesEnviados(int idUsuarioRemitente) throws OperacionesDeDaoExcepcion {

        List<Mensaje> mensajesEnviados = new ArrayList<>();

        String consultaSQL = """
            SELECT
                m.idMensaje,
                m.asunto,
                m.cuerpo,
                m.fecha,
                u.correoInstitucional AS destinatario
            FROM Mensaje m
            INNER JOIN EnvioMensaje em
                ON m.idMensaje = em.Mensaje_idMensaje
            INNER JOIN Usuario u
                ON em.Usuario_idDestinatario = u.idUsuario
            WHERE em.Usuario_idRemitente = ?
            ORDER BY m.fecha DESC
            """;

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuarioRemitente);

            ResultSet resultado = consultaPreparada.executeQuery();

            while(resultado.next()) {

                Mensaje mensaje = new Mensaje();

                mensaje.setIdMensaje(resultado.getInt("idMensaje"));
                mensaje.setAsunto(resultado.getString("asunto"));
                mensaje.setCuerpo(resultado.getString("cuerpo"));
                mensaje.setFecha(resultado.getObject("fecha",LocalDateTime.class));
                mensaje.setCorreoDestinatario(resultado.getString("destinatario"));
                mensajesEnviados.add(mensaje);
            }

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al consultar mensajes enviados. " +
                "ID Remitente: " + idUsuarioRemitente + 
                " - Verificar tablas: Mensaje, EnvioMensaje, Usuario y sus columnas", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar mensajes enviados. " +
                "ID Remitente: " + idUsuarioRemitente, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar mensajes enviados. " +
                "ID Remitente: " + idUsuarioRemitente + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron consultar los mensajes enviados", e);
        }

        return mensajesEnviados;
    }
    
    @Override
    public boolean eliminarMensaje(int idMensaje) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Mensaje WHERE idMensaje = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idMensaje);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al eliminar mensaje. " +
                "ID Mensaje: " + idMensaje + 
                " - El mensaje tiene envíos relacionados en EnvioMensaje", e);
            
            throw new OperacionesDeDaoExcepcion("No se puede eliminar el mensaje en este momento", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar mensaje. ID: " + idMensaje, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al eliminar mensaje. " +
                "ID Mensaje: " + idMensaje + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar el mensaje, " +  
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    }
    
}
