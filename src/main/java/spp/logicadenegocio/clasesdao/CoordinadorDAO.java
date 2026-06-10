/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import spp.logicadenegocio.clasesdto.Coordinador;
import spp.logicadenegocio.interfacesdao.ICoordinadorDAO;
import spp.accesoadatos.ConexionBD;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
/**
 *
 * @author gomes
 */
public class CoordinadorDAO implements ICoordinadorDAO {
    
    @Override
    public void insertarCoordinador(Coordinador coordinador) throws OperacionesDeDaoExcepcion{ 
        
        String consultaSQL = "INSERT INTO Coordinador (idUsuario, noPersonal) VALUES (?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setInt(1, coordinador.getIdUsuario());
            consultaPreparada.setString(2, coordinador.getNumeroDePersonal());
            int filasAfectadas = consultaPreparada.executeUpdate();
            
            if (filasAfectadas == 0) {
                throw new OperacionesDeDaoExcepcion("Fallo al guardar: No se reflejaron los cambios en la base de datos");               
            }
            
        }catch( SQLIntegrityConstraintViolationException  e){
            throw new OperacionesDeDaoExcepcion("El numero de personal ya existe",e);
        }catch( SQLException e ){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
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

        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);

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
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
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
     
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
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

        } catch(SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
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

        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return eliminacionExitosa;
    }   
    
}
