/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;


import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.logging.Level;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;

import spp.logicadenegocio.interfacesdao.IUsuarioDAO;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;

/**
 *
 * @author Luz Fernanda H J
 */
public class UsuarioDAO implements IUsuarioDAO {
    
    @Override
    public int insertarUsuario(Usuario usuario) throws OperacionesDeDaoExcepcion{
        
        int idInsertado = 0;
        
        String consultaSQL = "INSERT INTO Usuario (nombre, apellidoPaterno, apellidoMaterno, "
                + "correoInstitucional, contrasena, estado) VALUES (?, ?, ?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement
            (consultaSQL,Statement.RETURN_GENERATED_KEYS);) {

            consultaPreparada.setString(1, usuario.getNombre());
            consultaPreparada.setString(2, usuario.getApellidoPaterno());
            consultaPreparada.setString(3, usuario.getApellidoMaterno());
            consultaPreparada.setString(4, usuario.getCorreoInstitucional());
            consultaPreparada.setString(5, usuario.getContraseña());
            consultaPreparada.setBoolean(6, usuario.getEsActivo());

            consultaPreparada.executeUpdate();
            
            ResultSet resultadosConsulta = consultaPreparada.getGeneratedKeys();

            if (resultadosConsulta.next()) {
                
                idInsertado = resultadosConsulta.getInt(1);
                usuario.setIdUsuario(idInsertado);
                
            }

            resultadosConsulta.close();

         } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al insertar usuario. " +
                "Correo: " + usuario.getCorreoInstitucional() + 
                " - Posible correo duplicado", e);
            
            throw new OperacionesDeDaoExcepcion("El correo institucional ya está registrado", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar usuario. Correo: " + usuario.getCorreoInstitucional(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar usuario. " +
                "Nombre: " + usuario.getNombre() + 
                ", Correo: " + usuario.getCorreoInstitucional(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos del usuario no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al insertar usuario. " +
                "Correo: " + usuario.getCorreoInstitucional() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al registrar el usuario, " + 
                "intente de nuevo más tarde", e);
        }
        
        return idInsertado;
    }
    
    @Override
    public Usuario consultarUsuario(int idUsuarioConsultado)throws OperacionesDeDaoExcepcion {

        Usuario usuario = null;
        
        String consultaSQL = "SELECT idUsuario, nombre, apellidoPaterno, apellidoMaterno, "
                + "correoInstitucional, estado FROM Usuario WHERE idUsuario = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
            
            consultaPreparada.setInt(1, idUsuarioConsultado);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {

                usuario = new Usuario();

                usuario.setIdUsuario(resultadosConsulta.getInt("idUsuario"));
                usuario.setNombre(resultadosConsulta.getString("nombre"));
                usuario.setApellidoPaterno(resultadosConsulta.getString("apellidoPaterno"));
                usuario.setApellidoMaterno(resultadosConsulta.getString("apellidoMaterno"));
                usuario.setCorreoInstitucional(resultadosConsulta.getString("correoInstitucional"));

                int esActivo = resultadosConsulta.getInt("estado");
                usuario.setEsActivo(esActivo == 1);
                
                resultadosConsulta.close();

            } 

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar usuario. ID: " + idUsuarioConsultado, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar usuario. " +
                "ID: " + idUsuarioConsultado + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo consultar el usuario, " + 
                "intente de nuevo por favor", e);
        }

        return usuario;
    
    }

    @Override
    public boolean eliminarUsuario(int idUsuario)throws OperacionesDeDaoExcepcion {
        
        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Usuario WHERE idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;
            
        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al eliminar usuario. " +
                "ID Usuario: " + idUsuario + 
                " - Posiblemente tiene registros relacionados (practicante, profesor, etc.)", e);
            
            throw new OperacionesDeDaoExcepcion("No se puede eliminar el usuario porque " +    
                "tiene información asociada", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar usuario. ID: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al eliminar usuario. " +
                "ID: " + idUsuario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar el usuario, " +  
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa; 

    }

    @Override
    public boolean actualizarContraseña(int idUsuario, String nuevaContraseña)throws OperacionesDeDaoExcepcion {
        
        boolean actualizacionExitosa = false;

        String consultaSQL = "UPDATE Usuario SET contrasena = ? WHERE idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, nuevaContraseña);
            consultaPreparada.setInt(2, idUsuario);

            actualizacionExitosa = consultaPreparada.executeUpdate() > 0;
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al actualizar contraseña. ID Usuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al actualizar contraseña. " +
                "ID Usuario: " + idUsuario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al actualizar la contraseña, " + 
                "intente de nuevo más tarde", e);
        }   

         return actualizacionExitosa;
    
    }
    
    @Override
    public UsuarioEncontrado buscarUsuario(String correoInstitucional)throws OperacionesDeDaoExcepcion{
        
        UsuarioEncontrado usuarioEncontrado = null;

        String consultaSQL = "{CALL obtener_datos_inicio_sesion(?)}";
        
        try(Connection conexion = ConexionBD.getConexion();
            CallableStatement consultaPreparada = conexion.prepareCall(consultaSQL);) {
            
            consultaPreparada.setString(1, correoInstitucional);
            
            ResultSet resultadosConsulta = consultaPreparada.executeQuery();
            
            if(resultadosConsulta.next()){
                
                int idEncontrado = resultadosConsulta.getInt("idUsuario");
                String rolEncontrado = resultadosConsulta.getString("rol");
                String hashEncontrado = resultadosConsulta.getString("hash");
            
                usuarioEncontrado = new UsuarioEncontrado(idEncontrado, rolEncontrado, hashEncontrado);
            }
            
        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "El procedimiento almacenado 'obtener_datos_inicio_sesion' no existe o no es accesible. " +
                "Correo: " + correoInstitucional, e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al buscar usuario. Correo: " + correoInstitucional, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al buscar usuario. " +
                "Correo: " + correoInstitucional + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo iniciar sesión, intente de nuevo", e);
        }

        return usuarioEncontrado; 
    }
    
    @Override
    public int buscarIdPorCorreo(String correo) throws OperacionesDeDaoExcepcion {
         
        int idUsuario = 0;
        
        String consultaSQL = "SELECT idUsuario FROM Usuario WHERE correoInstitucional = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, correo.trim().toLowerCase());

            ResultSet resultado = consultaPreparada.executeQuery();

            if (resultado.next()) {
                idUsuario = resultado.getInt("idUsuario");
            }

            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al buscar ID por correo. Correo: " + correo, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al buscar ID por correo. " +
                "Correo: " + correo + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo verificar el correo, " + 
                "intente de nuevo", e);
        }
        
        return idUsuario;
    }
    
    public int insertarUsuarioConTransaccion(Usuario usuario, Connection conexion) throws OperacionesDeDaoExcepcion {
        
        int idInsertado = 0;
        
        String consultaSQL = "INSERT INTO Usuario (nombre, apellidoPaterno, apellidoMaterno, "
                + "correoInstitucional, contrasena, estado) VALUES (?, ?, ?, ?, ?, ?)";
        
        try(PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {

            consultaPreparada.setString(1, usuario.getNombre());
            consultaPreparada.setString(2, usuario.getApellidoPaterno());
            consultaPreparada.setString(3, usuario.getApellidoMaterno());
            consultaPreparada.setString(4, usuario.getCorreoInstitucional());
            consultaPreparada.setString(5, usuario.getContraseña());
            consultaPreparada.setBoolean(6, usuario.getEsActivo());

            consultaPreparada.executeUpdate();
            
            try (ResultSet resultadosConsulta = consultaPreparada.getGeneratedKeys()) {
                if (resultadosConsulta.next()) {
                    idInsertado = resultadosConsulta.getInt(1);
                    usuario.setIdUsuario(idInsertado);
                }
            }

         } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al insertar usuario transaccional. " +
                "Correo: " + usuario.getCorreoInstitucional() + 
                " - Posible correo duplicado", e);
            
            throw new OperacionesDeDaoExcepcion("El correo institucional ya está registrado", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar usuario transaccional. Correo: " + usuario.getCorreoInstitucional(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar usuario transaccional. " +
                "Nombre: " + usuario.getNombre() + 
                ", Correo: " + usuario.getCorreoInstitucional(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos del usuario no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al insertar usuario transaccional. " +
                "Correo: " + usuario.getCorreoInstitucional() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al registrar el usuario, " + 
                "intente de nuevo más tarde", e);
        }
        
        return idInsertado;
    }
 
}
