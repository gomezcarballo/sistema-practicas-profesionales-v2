/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.interfacesdao.ISolicitudProyectosDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class SolicitudProyectosDAO implements ISolicitudProyectosDAO{

    @Override
    public boolean guardarSolicitud(int idUsuario, int idProyecto) throws OperacionesDeDaoExcepcion {
        
        boolean registroExitoso = false; 

        String consultaSQL = "INSERT INTO SolicitudProyecto (Practicante_idUsuario, Proyecto_idProyecto) "
        + "VALUES (?, ?)";

        try(Connection conexion = ConexionBD.getConexion();
           PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            consultaPreparada.setInt(2, idProyecto);

            if(consultaPreparada.executeUpdate() > 0){
                registroExitoso = true;
            }

         } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al guardar solicitud. " +
                "ID Usuario: " + idUsuario + 
                ", ID Proyecto: " + idProyecto + 
                " - Posible solicitud duplicada o FK inválida", e);
            
            throw new OperacionesDeDaoExcepcion("Ya has enviado una solicitud a este proyecto", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al guardar solicitud. ID Usuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al guardar solicitud. " +
                "ID Usuario: " + idUsuario + 
                ", ID Proyecto: " + idProyecto + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al enviar la solicitud, " + 
                "intente de nuevo más tarde", e);
        }
        
        return registroExitoso;
    }
    
    @Override
    public List<Proyecto> obtenerProyectosSolicitados(int idUsuario)throws OperacionesDeDaoExcepcion {

        List<Proyecto> proyectos = new ArrayList<>();

        String consultaSQL = "SELECT p.idProyecto, "
                            + "p.nombre, "
                            + "p.objetivoGeneral, "
                            + "p.nombreResponsable, "
                            + "p.cupoMaximo "
                            + "FROM SolicitudProyecto sp "
                            + "INNER JOIN Proyecto p "
                            + "ON sp.Proyecto_idProyecto = p.idProyecto "
                            + "WHERE sp.Practicante_idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            try (ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

                while (resultadosConsulta.next()) {

                    Proyecto proyecto = new Proyecto();

                    proyecto.setIdProyecto(resultadosConsulta.getInt("idProyecto"));

                    proyecto.setNombre(resultadosConsulta.getString("nombre"));

                    proyecto.setObjetivoGeneral(resultadosConsulta.getString("objetivoGeneral"));

                    proyecto.setNombreResponsable( resultadosConsulta.getString("nombreResponsable"));

                    proyecto.setCupoMaximo(resultadosConsulta.getInt("cupoMaximo"));

                    proyectos.add(proyecto);
                }
            }

         } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al obtener proyectos solicitados. " +
                "ID Usuario: " + idUsuario + 
                " - Verificar tablas: SolicitudProyecto, Proyecto", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al obtener proyectos solicitados. ID Usuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al obtener proyectos solicitados. " +
                "ID Usuario: " + idUsuario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron obtener las solicitudes, " + 
                "intente de nuevo por favor", e);
        }

        return proyectos;
    }
    
    @Override
    public boolean eliminarSolicitud(int idUsuario, int idProyecto)throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM SolicitudProyecto WHERE Practicante_idUsuario = ? "
                + "AND Proyecto_idProyecto = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);
            consultaPreparada.setInt(2, idProyecto);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar solicitud. " +
                "ID Usuario: " + idUsuario + 
                ", ID Proyecto: " + idProyecto, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al eliminar solicitud. " +
                "ID Usuario: " + idUsuario + 
                ", ID Proyecto: " + idProyecto + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al cancelar la solicitud, " + 
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    }
    
}
