/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.Administrador;
import spp.logicadenegocio.interfacesdao.IAdministradorDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class AdministradorDAO implements IAdministradorDAO{
    
    @Override
    public void insertarAdministrador(Administrador administrador) throws OperacionesDeDaoExcepcion{ 
        
        String consultaSQL = "INSERT INTO Administrador (idUsuario, noPersonal) VALUES (?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setInt(1, administrador.getIdUsuario());
            consultaPreparada.setString(2, administrador.getNumeroDePersonal());
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
    public boolean inactivarAdministrador() throws OperacionesDeDaoExcepcion{
        
        boolean inactivacionExitosa = false;
        
        String consultaSQL = "UPDATE Usuario u INNER JOIN Administrador a ON u.idUsuario = a.idUsuario "
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
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
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
            
        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return eliminacionExitosa;
    } 
    
}
