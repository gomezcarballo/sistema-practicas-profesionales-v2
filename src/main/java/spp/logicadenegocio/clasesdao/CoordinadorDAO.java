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
    public Coordinador consultarCoordinador(String numeroDePersonal) throws OperacionesDeDaoExcepcion{
        
        Coordinador coordinador = null;
        
        String consultaSQL = "SELECT idUsuario, noPersonal FROM Coordinador WHERE "
                + "noPersonal = ?";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement
            (consultaSQL);){
            
            consultaPreparada.setString(1, numeroDePersonal);
            
            try(ResultSet resultadosConsulta = consultaPreparada.executeQuery();){
                if(resultadosConsulta.next() ){
                    coordinador = new Coordinador();
                    coordinador.setNumeroDePersonal
                    (resultadosConsulta.getString("noPersonal"));
                }
            }
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
    return coordinador;
    
    }
    
    @Override
    public boolean eliminarCoordinador(String numeroDePersonal) throws OperacionesDeDaoExcepcion{
        
        boolean eliminacionExitosa = false;
        
        String consultaSQL = "DELETE FROM Coordinador WHERE noPersonal = ?";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1, numeroDePersonal);
            
            int filasAfectadas = consultaPreparada.executeUpdate();
            if(filasAfectadas > 0){
                eliminacionExitosa = true;
            }
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
    return eliminacionExitosa;
    
    }

    @Override
    public boolean actualizarCoordinador(Coordinador coordinador) throws OperacionesDeDaoExcepcion {
        
        boolean actualizacionExitosa = false;
        
        String consultaSQL = "UPDATE Coordinador SET noPersonal WHERE idUsuario = ?";
        
        try(Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setInt(1, coordinador.getIdUsuario());
            
            int filasAfectadas = consultaPreparada.executeUpdate();
            if(filasAfectadas > 0){
                actualizacionExitosa = true;
            }
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
    return actualizacionExitosa;
        
    }
    
}
