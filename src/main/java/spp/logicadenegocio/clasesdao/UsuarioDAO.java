/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;


import java.sql.CallableStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import spp.logicadenegocio.interfacesdao.IUsuarioDAO;
import spp.logicadenegocio.clasesdto.Usuario;
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
        
        boolean registroExitoso = false;
        
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
                int idGenerado = resultadosConsulta.getInt(1);
                usuario.setIdUsuario(idGenerado);
            }

            registroExitoso = true;
            resultadosConsulta.close();

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }
        
        if(registroExitoso){
            return usuario.getIdUsuario();
        }else{
            return 0;
        }
    }
    
    @Override
    public Usuario consultarUsuario(int idUsuario)throws OperacionesDeDaoExcepcion {

        Usuario usuario = null;
        
        String consultaSQL = "SELECT idUsuario, nombre, apellidoPaterno, apellidoMaterno, "
                + "correoInstitucional, estado FROM Usuario WHERE idUsuario = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
            
            consultaPreparada.setInt(1, idUsuario);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {

                usuario = new Usuario();

                usuario.setIdUsuario(resultadosConsulta.getInt("idUsuario"));
                usuario.setNombre(resultadosConsulta.getString("nombre"));
                usuario.setApellidoPaterno(resultadosConsulta.getString("apellidoPaterno"));
                usuario.setApellidoMaterno(resultadosConsulta.getString("apellidoMaterno"));
                usuario.setCorreoInstitucional(resultadosConsulta.getString("correoInstitucional"));

                int esActivo = resultadosConsulta.getInt("estado");
                if (esActivo == 1) {
                    usuario.setEsActivo(true);
                } else {
                    usuario.setEsActivo(false);
                }

            } 

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
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

            int filasAfectadas = consultaPreparada.executeUpdate();

            if (filasAfectadas > 0) {
                eliminacionExitosa = true;
            }
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return eliminacionExitosa; 

    }

    @Override
    public boolean actualizarUsuario(Usuario usuario)throws OperacionesDeDaoExcepcion {
        
        boolean actualizacionExitosa = false;

        String consultaSQL = "UPDATE Usuario SET nombre = ?, apellidoPaterno = ?, "
                + "apellidoMaterno = ? correoInstitucional = ? WHERE idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, usuario.getNombre());
            consultaPreparada.setString(2, usuario.getApellidoPaterno());
            consultaPreparada.setString(3, usuario.getApellidoMaterno());
            consultaPreparada.setString(4, usuario.getCorreoInstitucional());
            consultaPreparada.setInt(5, usuario.getIdUsuario());

            int filasAfectadas = consultaPreparada.executeUpdate();

            if (filasAfectadas > 0) {
                actualizacionExitosa = true;
            }
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return actualizacionExitosa;
    
    }
    
    @Override
    public UsuarioEncontrado buscarUsuario(String correoInstitucional)throws OperacionesDeDaoExcepcion{
        
        String consultaSQL = "{CALL obtener_datos_inicio_sesion(?)}";
        
        try(Connection conexion = ConexionBD.getConexion();
            CallableStatement consultaPreparada = conexion.prepareCall(consultaSQL);) {
            
            consultaPreparada.setString(1, correoInstitucional);
            
            ResultSet resultadosConsulta = consultaPreparada.executeQuery();
            
            if(!resultadosConsulta.next()){
                return null;
            }
            
            int idEncontrado = resultadosConsulta.getInt("idUsuario");
            String rolEncontrado = resultadosConsulta.getString("rol");
            String hashEncontrado = resultadosConsulta.getString("hash");
            
            return new UsuarioEncontrado(idEncontrado, rolEncontrado, hashEncontrado);
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos.",e);
        }
    }
    
    @Override
    public boolean existeCorreo(String correo)throws OperacionesDeDaoExcepcion {

        boolean existeCorreo = false;

        String consultaSQL = "SELECT correoInstitucional FROM Usuario WHERE correoInstitucional = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, correo.trim().toLowerCase());

            ResultSet resultado = consultaPreparada.executeQuery();

            if(resultado.next()) {
                existeCorreo = true;
            }

        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se pudo consultar el usuario", e);
        }

        return existeCorreo;
    }
 
}
