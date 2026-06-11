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
import java.sql.SQLTimeoutException;
import java.util.logging.Level;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.interfacesdao.IAdministradorDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class AdministradorDAO implements IAdministradorDAO{
    
    @Override
    public boolean insertarAdministrador(Administrador administrador) throws OperacionesDeDaoExcepcion{ 
        
        Boolean registroExitoso = false;

        String consultaSQL = "INSERT INTO Administrador (idUsuario, noPersonal) VALUES (?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setInt(1, administrador.getIdUsuario());
            consultaPreparada.setString(2, administrador.getNumeroDePersonal());
            registroExitoso = consultaPreparada.executeUpdate() > 0;

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Violación de integridad al insertar administrador. el ID " + administrador.getIdUsuario() 
                + "El noPersonal: " + administrador.getNumeroDePersonal(), e);
            
            throw new OperacionesDeDaoExcepcion("Ya existe un administrador con ese numero de personal", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar administrador. IdUsuario: " + administrador.getIdUsuario() + 
                ", noPersonal: " + administrador.getNumeroDePersonal() , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar el administrador. IdUsuario: " + administrador.getIdUsuario() + 
                ", noPersonal: " + administrador.getNumeroDePersonal(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos ingresados no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al insertar el administrador. " + 
                "IdUsuario: " + administrador.getIdUsuario() + 
                ", noPersonal: " + administrador.getNumeroDePersonal() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al guardar el administrador, " + 
                "intente de nuevo más tarde", e);
        }
        
        return registroExitoso;
    }
    
    @Override
    public boolean inactivarAdministrador() throws OperacionesDeDaoExcepcion{
        
        boolean inactivacionExitosa = false;
        
        String consultaSQL = "UPDATE Usuario u INNER JOIN Administrador a ON u.idUsuario = a.idUsuario "
                + "SET u.estado = 0 WHERE u.estado = 1";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
                        
            inactivacionExitosa = consultaPreparada.executeUpdate() > 0;
        
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al inactivar administrador.", e);

            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
        
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al inactivar el administrador. " + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al inactivar el administrador, " + 
                "intente de nuevo más tarde", e);
        }
        
         return inactivacionExitosa;
    
    }

    @Override
    public Administrador consultarAdministrador(String numeroDePersonal) throws OperacionesDeDaoExcepcion{
        
        Administrador administrador = null;
        
        String consultaSQL = "SELECT idUsuario, noPersonal FROM Administrador WHERE noPersonal = ?";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement
            (consultaSQL);){
            
            consultaPreparada.setString(1, numeroDePersonal);
            
            try(ResultSet resultadosConsulta = consultaPreparada.executeQuery();){
                if(resultadosConsulta.next() ){
                    administrador = new Administrador();
                    administrador.setNumeroDePersonal(resultadosConsulta.getString("noPersonal"));
                    administrador.setIdUsuario(resultadosConsulta.getInt("idUsuario"));
                }
            }
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar administrador. IdUsuario: " + administrador.getIdUsuario() + 
                ", noPersonal: " + administrador.getNumeroDePersonal() , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al consultar el administrador. " + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al consultar el administrador, " + 
                "intente de nuevo más tarde", e);
        }
    return administrador;
    
    }
    
    @Override
    public boolean eliminarAdministrador(int idUsuario) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Administrador WHERE idUsuario = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar administrador. IdUsuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " +
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al eliminar el administrador. " + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar el administrador, " +
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    } 
    
    @Override
    public boolean insertarAdministradorConTransaccion(Administrador administrador, Connection conexion) throws OperacionesDeDaoExcepcion { 
        
        boolean registroExitoso = false;

        String consultaSQL = "INSERT INTO Administrador (idUsuario, noPersonal) VALUES (?, ?)";
        
        try (PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {
            
            consultaPreparada.setInt(1, administrador.getIdUsuario());
            consultaPreparada.setString(2, administrador.getNumeroDePersonal());
            
            registroExitoso = consultaPreparada.executeUpdate() > 0;

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Violación de integridad al insertar administrador transaccional. el ID " + administrador.getIdUsuario() 
                + " El noPersonal: " + administrador.getNumeroDePersonal(), e);
            throw new OperacionesDeDaoExcepcion("Ya existe un administrador con ese numero de personal", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar administrador transaccional. IdUsuario: " + administrador.getIdUsuario() + 
                ", noPersonal: " + administrador.getNumeroDePersonal() , e);
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar el administrador transaccional. IdUsuario: " + administrador.getIdUsuario() + 
                ", noPersonal: " + administrador.getNumeroDePersonal(), e);
            throw new OperacionesDeDaoExcepcion("Los datos ingresados no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de BD al insertar el administrador transaccional. " + 
                "IdUsuario: " + administrador.getIdUsuario() + 
                ", noPersonal: " + administrador.getNumeroDePersonal() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            throw new OperacionesDeDaoExcepcion("Error al guardar el administrador, " + 
                "intente de nuevo más tarde", e);
        }
        
        return registroExitoso;
    }
    
    @Override
    public void registrarAdministradorCompleto(Usuario usuario, Administrador administrador) throws OperacionesDeDaoExcepcion {
        
        Connection conexion = null;
        
        try {
            conexion = ConexionBD.getConexion(); 
            conexion.setAutoCommit(false); 
            
            UsuarioDAO usuarioDao = new UsuarioDAO();
            
            int idUsuario = usuarioDao.insertarUsuarioConTransaccion(usuario, conexion);
            administrador.setIdUsuario(idUsuario);
            
            this.insertarAdministradorConTransaccion(administrador, conexion);
            
            conexion.commit(); 
            
        } catch(SQLException | OperacionesDeDaoExcepcion e) {
            
            if (conexion != null) {
                
                try { 
                    
                    conexion.rollback(); 
                } catch (SQLException ex) {
                    
                    RegistroErrores.registrarError(Level.SEVERE, "Fallo al hacer rollback en registro completo.", ex);
                    
                }
            }
            
            throw new OperacionesDeDaoExcepcion("Fallo en la transacción de base de datos al registrar administrador.", e);
            
        } finally {
            
            if (conexion != null) {
                
                try { 
                    conexion.setAutoCommit(true); 
                    conexion.close(); 
                    
                } catch (SQLException e) {
                    
                    RegistroErrores.registrarError(Level.SEVERE, "Error al cerrar conexión.", e);
                    
                }
            }
        }
    }
    
    
}
