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
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.interfacesdao.IProfesorDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ProfesorDAO extends UsuarioDAO implements IProfesorDAO{
    
    @Override
    public boolean registrarProfesor(Profesor profesor)throws OperacionesDeDaoExcepcion{
        
        boolean registroExitoso = false;
        
        String consultaSQL = """
                INSERT INTO Profesor (idUsuario, noPersonal) VALUES (?, ?)""";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {

            consultaPreparada.setInt(1, profesor.getIdUsuario());
            consultaPreparada.setString(2, profesor.getNumeroDePersonal());
            
            consultaPreparada.executeUpdate();
            
            registroExitoso = true;
            
        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        return registroExitoso;
    }
    
    @Override
    public Profesor consultarProfesor(String numeroDePersonal)throws OperacionesDeDaoExcepcion {

         Profesor profesor = null;
         
         String consultaSQL = "SELECT idUsuario, noPersonal FROM PROFESOR WHERE noPersonal = ?";

         try(Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
             
             consultaPreparada.setString(1, numeroDePersonal);

             ResultSet resultadosConsulta = consultaPreparada.executeQuery();

             if (resultadosConsulta.next()) {

                 profesor = new Profesor();

                 profesor.setIdUsuario(resultadosConsulta.getInt("idUsuario"));
                 profesor.setNumeroDePersonal(resultadosConsulta.getString("noPersonal"));

             }

         } catch (SQLException e) {
             throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
         }

    return profesor;
    
    }

    @Override
    public boolean eliminarProfesor(String numeroDePersonal)throws OperacionesDeDaoExcepcion {
        
        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Profesor WHERE noPersonal = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, numeroDePersonal);

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
    public boolean actualizarProfesor(Profesor profesor)throws OperacionesDeDaoExcepcion {
        
        boolean actualizacionExitosa = false;

        String consultaSQL = "UPDATE Profesor SET noPersonal = ?"
                + "WHERE idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, profesor.getNumeroDePersonal());
            consultaPreparada.setInt(2, profesor.getIdUsuario());

            int filasAfectadas = consultaPreparada.executeUpdate();

            if (filasAfectadas > 0) {
                actualizacionExitosa = true;
            }
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return actualizacionExitosa;
    
    }

}
