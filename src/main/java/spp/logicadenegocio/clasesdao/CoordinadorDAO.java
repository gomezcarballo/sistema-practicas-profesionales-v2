/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.interfacesdao.ICoordinadorDAO;
import spp.accesoadatos.ConexionBD;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
/**
 *
 * @author gomes
 */
public class CoordinadorDAO implements ICoordinadorDAO {
    
    @Override
    public boolean insertarCoordinador(Coordinador coordinador) throws OperacionesDeDaoExcepcion{ 
        
        boolean registroExitoso = false;

        String consultaSQL = "INSERT INTO Coordinador (idUsuario, noPersonal) VALUES (?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setInt(1, coordinador.getIdUsuario());
            consultaPreparada.setString(2, coordinador.getNumeroDePersonal());
            int filasAfectadas = consultaPreparada.executeUpdate();
            
            if (filasAfectadas > 0) {
                registroExitoso = true;          
            }
            
        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Violación de integridad al insertar coordinador. El ID " + coordinador.getIdUsuario() 
                + "El noPersonal: " + coordinador.getNumeroDePersonal(), e);
            
            throw new OperacionesDeDaoExcepcion("Ya existe un coordinador con ese numero de personal", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar coordinador. IdUsuario: " + coordinador.getIdUsuario() + 
                ", noPersonal: " + coordinador.getNumeroDePersonal() , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar el coordinador. IdUsuario: " + coordinador.getIdUsuario() + 
                ", noPersonal: " + coordinador.getNumeroDePersonal(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos ingresados no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al insertar el coordinador. " + 
                "IdUsuario: " + coordinador.getIdUsuario() + 
                ", noPersonal: " + coordinador.getNumeroDePersonal() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al guardar el coordinador, " + 
                "intente de nuevo más tarde", e);
        }

        return registroExitoso;
        
    }

    @Override
    public List<Coordinador> consultarCoordinadoresInactivos() throws OperacionesDeDaoExcepcion{
        
        List<Coordinador> coordinadoresInactivos = new ArrayList<>();
        
        String consultaSQL = """
            SELECT c.noPersonal AS numeroDePersonal,
            c.idUsuario,
            u.nombre,
            u.apellidoPaterno,
            u.apellidoMaterno,
            u.correoInstitucional
            FROM Coordinador c
            INNER JOIN Usuario u
            ON c.idUsuario = u.idUsuario
            WHERE u.estado = 0
            """;

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            while(resultadoConsulta.next()) {

                Coordinador coordinador = new Coordinador();

                coordinador.setNumeroDePersonal(resultadoConsulta.getString("numeroDePersonal"));
                coordinador.setIdUsuario(resultadoConsulta.getInt("idUsuario"));
                coordinador.setNombre(resultadoConsulta.getString("nombre"));
                coordinador.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                coordinador.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                coordinador.setCorreoInstitucional(resultadoConsulta.getString("correoInstitucional"));

                coordinadoresInactivos.add(coordinador);

            }

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar los coordinadores inactivos." , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al consultar los coordinadores inactivos. " + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al consultar los coordinadores inactivos, " + 
                "intente de nuevo más tarde", e);
        }
        
         return coordinadoresInactivos;
    
    }
    
    @Override
    public boolean inactivarCoordinador() throws OperacionesDeDaoExcepcion{
        
        boolean inactivacionExitosa = false;
        
        String consultaSQL = "UPDATE Usuario u INNER JOIN Coordinador c ON u.idUsuario = c.idUsuario "
                + "SET u.estado = 0 WHERE u.estado = 1";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
                        
            inactivacionExitosa = consultaPreparada.executeUpdate() > 0;
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al inactivar el coordinador.", e);

            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
        
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al inactivar el coordinador. " + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al inactivar el coordinador, " + 
                "intente de nuevo más tarde", e);
        }
        
        return inactivacionExitosa;
    
    }

    @Override
    public boolean reactivarCoordinador(int idUsuario) throws OperacionesDeDaoExcepcion {
        
        boolean reactivacionExitosa = false;
        
        String consultaSQL = "UPDATE Usuario u INNER JOIN Coordinador c ON u.idUsuario = c.idUsuario "
                + "SET u.estado = 1 WHERE u.idUsuario = ?";
        
        try(Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setInt(1, idUsuario);
            
            reactivacionExitosa = consultaPreparada.executeUpdate() > 0;
     
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al reactivar el coordinador. El ID : " + idUsuario, e);

            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
        
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al reactivar el coordinador. El ID: " + idUsuario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al reactivar el coordinador, " + 
                "intente de nuevo más tarde", e);
        }
        
    return reactivacionExitosa;
        
    }
    
    @Override
    public boolean existeCoordinadorActivo() throws OperacionesDeDaoExcepcion {

        boolean existeCoordinador = false;

        String consultaSQL = "SELECT existeCoordinadorActivo()";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            if(resultadoConsulta.next()) {
                existeCoordinador = resultadoConsulta.getBoolean(1);
            }

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "La función almacenada 'existeCoordinadorActivo()' no existe o no es accesible. " +
                "Verificar que la función esté creada en la base de datos", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al ejecutar función almacenada 'existeCoordinadorActivo()'", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al ejecutar función 'existeCoordinadorActivo()'. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al verificar la información, " + 
                "intente de nuevo más tarde", e);
        }

        return existeCoordinador;
    }
    
    @Override
    public boolean eliminarCoordinador(int idUsuario) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Coordinador WHERE idUsuario = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar coordinador. IdUsuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al eliminar el coordinador. " + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar el coordinador, " + 
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    }   
    
    @Override
    public boolean insertarCoordinadorConTransaccion(Coordinador coordinador, Connection conexion) throws OperacionesDeDaoExcepcion { 
        
        boolean registroExitoso = false;

        String consultaSQL = "INSERT INTO Coordinador (idUsuario, noPersonal) VALUES (?, ?)";
        
        try (PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {
            
            consultaPreparada.setInt(1, coordinador.getIdUsuario());
            consultaPreparada.setString(2, coordinador.getNumeroDePersonal());
            
            registroExitoso = consultaPreparada.executeUpdate() > 0;
            

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Violación de integridad al insertar coordinador transaccional. ID " + coordinador.getIdUsuario() 
                + " El noPersonal: " + coordinador.getNumeroDePersonal(), e);
            throw new OperacionesDeDaoExcepcion("Ya existe un coordinador registrado con ese número de personal.", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar coordinador transaccional. IdUsuario: " + coordinador.getIdUsuario() + 
                ", noPersonal: " + coordinador.getNumeroDePersonal() , e);
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor.", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar el coordinador transaccional. IdUsuario: " + coordinador.getIdUsuario() + 
                ", noPersonal: " + coordinador.getNumeroDePersonal(), e);
            throw new OperacionesDeDaoExcepcion("Los datos ingresados no son válidos, revise la información.", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de BD al insertar el coordinador transaccional. " + 
                "IdUsuario: " + coordinador.getIdUsuario() + 
                ", noPersonal: " + coordinador.getNumeroDePersonal() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            throw new OperacionesDeDaoExcepcion("Error al guardar el coordinador, intente de nuevo más tarde.", e);
        }
        
        return registroExitoso;
    }
    
    
    @Override
    public void registrarCoordinadorCompleto(Usuario usuario, Coordinador coordinador) throws OperacionesDeDaoExcepcion {
        
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.getConexion(); 
            conexion.setAutoCommit(false); 
            
            UsuarioDAO usuarioDao = new UsuarioDAO();
            
            int idUsuario = usuarioDao.insertarUsuarioConTransaccion(usuario, conexion);
            coordinador.setIdUsuario(idUsuario);
            
            this.insertarCoordinadorConTransaccion(coordinador, conexion);
            
            conexion.commit(); 
            
        } catch(SQLException | OperacionesDeDaoExcepcion e) {
            
            if (conexion != null) {
                
                try { 
                    conexion.rollback(); 
                } catch (SQLException exRollback) {
                    RegistroErrores.registrarError(Level.SEVERE, "Fallo al hacer rollback en registro de coordinador.", exRollback);
                }
                
            }
            
            throw new OperacionesDeDaoExcepcion("Fallo en la transacción de base de datos al registrar coordinador.", e);
            
        } finally {
            
            if (conexion != null) {
                
                try { 
                    conexion.setAutoCommit(true); 
                    conexion.close(); 
                } catch (SQLException exClose) {
                    RegistroErrores.registrarError(Level.SEVERE, "Error al cerrar conexión.", exClose);
                }
                
            }
        }
    }

}
