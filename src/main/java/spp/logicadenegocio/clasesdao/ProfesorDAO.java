/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import spp.accesoadatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.interfacesdao.IProfesorDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ProfesorDAO extends UsuarioDAO implements IProfesorDAO{
    
    @Override
    public boolean insertarProfesor(Profesor profesor)throws OperacionesDeDaoExcepcion{
        
        boolean registroExitoso = false;
        
        String consultaSQL = "INSERT INTO Profesor (idUsuario, noPersonal, nrcAsignado) VALUES (?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {

            consultaPreparada.setInt(1, profesor.getIdUsuario());
            consultaPreparada.setString(2, profesor.getNumeroDePersonal());
            consultaPreparada.setString(3, profesor.getNrcAsignado());
            
            consultaPreparada.executeUpdate();
            
            registroExitoso = true;
            
        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        return registroExitoso;
    }
    
    @Override
    public List<Profesor> consultarProfesoresActivos()throws OperacionesDeDaoExcepcion {
    
        List<Profesor> profesoresActivos = new ArrayList<>();

        String consultaSQL = """
            SELECT p.noPersonal as numeroDePersonal,
            p.idUsuario,
            p.nrcAsignado,                                 
            u.nombre,
            u.apellidoPaterno,
            u.apellidoMaterno,
            u.correoInstitucional
            FROM Profesor p
            INNER JOIN Usuario u
            ON p.idUsuario = u.idUsuario
            WHERE u.estado = 1
            """;

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            while(resultadoConsulta.next()) {

                Profesor profesor = new Profesor();

                profesor.setNumeroDePersonal(resultadoConsulta.getString("numeroDePersonal"));
                profesor.setIdUsuario(resultadoConsulta.getInt("idUsuario"));
                profesor.setNrcAsignado(resultadoConsulta.getString("nrcAsignado"));
                profesor.setNombre(resultadoConsulta.getString("nombre"));
                profesor.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                profesor.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                profesor.setCorreoInstitucional(resultadoConsulta.getString("correoInstitucional"));

                profesoresActivos.add(profesor);

            }

        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);

        }

        return profesoresActivos;
    
    }
    
    @Override
    public List<Profesor> consultarProfesoresInactivos() throws OperacionesDeDaoExcepcion{
        
        List<Profesor> profesoresInactivos = new ArrayList<>();
        
        String consultaSQL = """
            SELECT p.noPersonal,
            p.idUsuario,
            p.nrcAsignado,
            u.nombre,
            u.apellidoPaterno,
            u.apellidoMaterno,
            u.correoInstitucional
            FROM Profesor p
            INNER JOIN Usuario u
            ON p.idUsuario = u.idUsuario
            WHERE u.estado = 0
            """;

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            while(resultadoConsulta.next()) {

                Profesor profesor = new Profesor();

                profesor.setNumeroDePersonal(resultadoConsulta.getString("numeroDePersonal"));
                profesor.setIdUsuario(resultadoConsulta.getInt("idUsuario"));
                profesor.setNrcAsignado(resultadoConsulta.getString("nrcAsignado"));
                profesor.setNombre(resultadoConsulta.getString("nombre"));
                profesor.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                profesor.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                profesor.setCorreoInstitucional(resultadoConsulta.getString("correoInstitucional"));

                profesoresInactivos.add(profesor);

            }

        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);

        }
        
    return profesoresInactivos;
    
    }
    
    @Override
    public int obtenerCantidadProfesoresActivos()throws OperacionesDeDaoExcepcion {

        int cantidadProfesoresActivos = 0;

        String consultaSQL = """
            SELECT COUNT(*)
            FROM Profesor p
            INNER JOIN Usuario u
            ON p.idUsuario = u.idUsuario
            WHERE u.estado = 1
            """;

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            if(resultadoConsulta.next()) {

                cantidadProfesoresActivos = resultadoConsulta.getInt(1);

            }

        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos.", e);

        }

    return cantidadProfesoresActivos;

    }

    @Override
    public boolean inactivarProfesor(int idUsuario)throws OperacionesDeDaoExcepcion {
        
        boolean inactivacionExitosa = false;

        String consultaSQL = "UPDATE Usuario SET estado = 0 WHERE idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            int filasAfectadas = consultaPreparada.executeUpdate();

            if (filasAfectadas > 0) {
                inactivacionExitosa = true;
            }
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return inactivacionExitosa; 

    }

    @Override
    public boolean reactivarProfesor(int idUsuario) throws OperacionesDeDaoExcepcion {
        
        boolean reactivacionExitosa = false;
        
        String consultaSQL = "UPDATE Usuario u INNER JOIN Profesor p ON u.idUsuario = p.idUsuario"
                + "SET u.estado = 1 WHERE u.idUsuario = ?";
        
        try(Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setInt(1, idUsuario);
            
            int filasAfectadas = consultaPreparada.executeUpdate();
            
            if(filasAfectadas > 0){
                reactivacionExitosa = true;
            }
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
    return reactivacionExitosa;
    
    }    

}
