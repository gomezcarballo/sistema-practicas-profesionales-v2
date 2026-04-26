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
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.interfacesdao.IPracticanteDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class PracticanteDAO extends UsuarioDAO implements IPracticanteDAO{
    
    @Override
    public boolean insertarPracticante(Practicante practicante)throws OperacionesDeDaoExcepcion{
        
        boolean registroExitoso = false;
        
        String consultaSQL = "INSERT INTO Practicante (idUsuario, matricula, genero, "
                + "lenguaIndigena, fechaNacimiento) VALUES (?, ?, ?, ?, ?)";
        
        try (Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {

            consultaPreparada.setInt(1, practicante.getIdUsuario());
            consultaPreparada.setString(2, practicante.getMatricula());
            consultaPreparada.setString(3, practicante.getGenero());
            consultaPreparada.setBoolean(4, practicante.gethablaLenguaIndigena());

            java.sql.Date fechaParaBD = new java.sql.Date(practicante.getFechaNacimiento().getTime());
            consultaPreparada.setDate(5, fechaParaBD);
            
            consultaPreparada.executeUpdate();
            
            registroExitoso = true;

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
    return registroExitoso;
    
    }
     
    @Override
    public Practicante consultarPracticante(String matricula)throws OperacionesDeDaoExcepcion{

        Practicante practicante = null;
        
        String consultaSQL = "SELECT idUsuario, matricula, genero, lenguaIndigena, "
                + "fechaNacimiento FROM Practicante WHERE matricula = ?";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
            
            consultaPreparada.setString(1, matricula);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {

                practicante = new Practicante();

                practicante.setIdUsuario(resultadosConsulta.getInt("idUsuario"));
                practicante.setMatricula(resultadosConsulta.getString("matricula"));
                practicante.setGenero(resultadosConsulta.getString("genero"));
                practicante.setHablaLenguaIndigena(resultadosConsulta.getBoolean("lenguaIndigena"));
                practicante.setFechaNacimiento(resultadosConsulta.getDate("fechaNacimiento"));

            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return practicante;
    
    }

    @Override
    public boolean eliminarPracticante(String matricula)throws OperacionesDeDaoExcepcion {
        
        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Practicante WHERE matricula = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, matricula);

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
    public boolean actualizarPracticante(Practicante practicante)throws OperacionesDeDaoExcepcion {
        
        boolean actualizacionExitosa = false;

        String consultaSQL = "UPDATE Practicante SET matricula = ?, genero = ?, "
                + "lenguaIndigena = ?, fechaNacimiento = ? "
                + "WHERE matricula = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, practicante.getMatricula());
            consultaPreparada.setString(2, practicante.getGenero());
            consultaPreparada.setBoolean(3, practicante.gethablaLenguaIndigena());
            java.sql.Date fechaParaBD = new java.sql.Date(practicante.getFechaNacimiento().getTime());
            consultaPreparada.setDate(4, fechaParaBD);
            consultaPreparada.setString(5, practicante.getMatricula());

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
