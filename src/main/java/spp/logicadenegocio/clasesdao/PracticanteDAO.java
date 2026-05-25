/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.CallableStatement;
import spp.accesoadatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
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
            consultaPreparada.setBoolean(4, practicante.getHablaLenguaIndigena());
            java.sql.Date fechaParaBD = java.sql.Date.valueOf(practicante.getFechaNacimiento());
            consultaPreparada.setDate(5, fechaParaBD);
            
            consultaPreparada.executeUpdate();
            
            registroExitoso = true;

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
    return registroExitoso;
    
    }
     
    @Override
    public List<Practicante> consultarPracticantes()throws OperacionesDeDaoExcepcion {

        List<Practicante> practicantes = new ArrayList<>();

        String consultaSQL = "SELECT u.idUsuario, u.nombre, u.apellidoPaterno, "
            + "u.apellidoMaterno, u.correoInstitucional, "
            + "p.matricula, p.genero, p.lenguaIndigena, "
            + "p.fechaNacimiento "
            + "FROM Practicante p "
            + "INNER JOIN Usuario u "
            + "ON p.idUsuario = u.idUsuario "
            + "WHERE u.estado = 1";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            while(resultadosConsulta.next()) {

                Practicante practicante = new Practicante();
                practicante.setIdUsuario(resultadosConsulta.getInt("idUsuario"));
                practicante.setNombre(resultadosConsulta.getString("nombre"));
                practicante.setApellidoPaterno(resultadosConsulta.getString("apellidoPaterno"));
                practicante.setApellidoMaterno(resultadosConsulta.getString("apellidoMaterno"));
                practicante.setCorreoInstitucional(resultadosConsulta.getString("correoInstitucional"));
                practicante.setMatricula(resultadosConsulta.getString("matricula"));
                practicante.setGenero(resultadosConsulta.getString("genero"));
                practicante.setHablaLenguaIndigena(resultadosConsulta.getBoolean("lenguaIndigena"));
                practicante.setFechaNacimiento(resultadosConsulta.getDate("fechaNacimiento").toLocalDate());
                
                practicantes.add(practicante);
            }

        } catch (SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);

        }

    return practicantes;
    
    }

    @Override
    public boolean inactivarPracticante(int idUsuario)throws OperacionesDeDaoExcepcion {
        
        boolean inactivacionExitosa = false;

        String consultaSQL = "UPDATE Practicante SET estado = 0 WHERE idUsuario = ?";

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
    public boolean actualizarPracticante(Practicante practicante)throws OperacionesDeDaoExcepcion {
        
        boolean actualizacionExitosa = false;

        String consultaSQL = "UPDATE Practicante SET matricula = ?, genero = ?, "
                + "lenguaIndigena = ?, fechaNacimiento = ? "
                + "WHERE matricula = ?";

        try ( Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL) ) {

            consultaPreparada.setString(1, practicante.getMatricula());
            consultaPreparada.setString(2, practicante.getGenero());
            consultaPreparada.setBoolean(3, practicante.getHablaLenguaIndigena());
            java.sql.Date fechaParaBD = java.sql.Date.valueOf(practicante.getFechaNacimiento());
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
    
    @Override
    public UsuarioEncontrado buscarPracticante (String matricula) throws OperacionesDeDaoExcepcion{
        
        String consultaSQL = "{CALL obtener_datos_practicante(?)}";
        
        try(Connection conexion = ConexionBD.getConexion();
            CallableStatement consultaPreparada = conexion.prepareCall(consultaSQL);) {
            
            consultaPreparada.setString(1, matricula);

           
            ResultSet resultadosConsulta = consultaPreparada.executeQuery();
            
            if (!resultadosConsulta.next()){
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
    public void asignarProyecto(int idProyecto, int idUsuario) throws OperacionesDeDaoExcepcion {

        String consultaSQL = "UPDATE Practicante SET Proyecto_idProyecto = ? WHERE idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idProyecto);
            consultaPreparada.setInt(2, idUsuario);

            consultaPreparada.executeUpdate();

        } catch (SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }
    }
    
}
