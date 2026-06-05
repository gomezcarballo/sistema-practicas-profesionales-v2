/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.time.LocalDateTime;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.interfacesdao.IActividadDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class ActividadDAO implements IActividadDAO{

    @Override
    public boolean insertarActividad(Actividad actividad) throws OperacionesDeDaoExcepcion {
        
        boolean registroExitoso = false;
        
        String consultaSQL = "INSERT INTO Practica (titulo, descripcion, "
                + "fechaLimite, Profesor_idUsuario) VALUES (?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada 
            = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1,actividad.getTitulo());
            consultaPreparada.setString(2,actividad.getDescripcion());
            consultaPreparada.setObject(3,actividad.getFechaLimite());
            consultaPreparada.setInt(4,actividad.getIdProfesor());
            
            consultaPreparada.executeUpdate();
            registroExitoso = true;
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
    return registroExitoso;
    
    }

    @Override
    public Actividad consultarActividad(String titulo) throws OperacionesDeDaoExcepcion{
        
        Actividad actividad = null;
        
        String consultaSQL = "SELECT idActividad, titulo, descripcion, fechaLimite, Profesor_idUsuario "
                + "FROM Practica WHERE titulo = ? ";
        
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1,titulo); 
            
            try(ResultSet resultadosConsulta = consultaPreparada.executeQuery();){
                if(resultadosConsulta.next() ){
                    
                actividad = new Actividad();
                
                actividad.setIdActividad(resultadosConsulta.getInt("idActividad"));
                actividad.setTitulo(resultadosConsulta.getString("titulo"));
                actividad.setDescripcion(resultadosConsulta.getString("descripcion"));
                actividad.setFechaLimite(resultadosConsulta.getObject("fechaLimite",LocalDateTime.class));
                actividad.setIdProfesor(resultadosConsulta.getInt("idProfesor"));
                
                }
            }
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
    return actividad;
    
    }
    
    @Override
    public List<Actividad> consultarActividadesAsignadas(int idUsuario) throws OperacionesDeDaoExcepcion {

        List<Actividad> actividades = new ArrayList<>();

        String consultaSQL = "SELECT "
                + "a.idActividad, "
                + "a.titulo, "
                + "a.descripcion, "
                + "a.fechaLimite "
                + "FROM practicante pr "
                + "INNER JOIN profesor p "
                + "ON pr.nrcAsignado = p.nrcAsignado "
                + "INNER JOIN practica a "
                + "ON a.Profesor_idUsuario = p.idUsuario "
                + "WHERE pr.idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            while (resultadosConsulta.next()) {

                Actividad actividad = new Actividad();

                actividad.setIdActividad(resultadosConsulta.getInt("idActividad"));
                actividad.setTitulo(resultadosConsulta.getString("titulo"));
                actividad.setDescripcion(resultadosConsulta.getString("descripcion"));

                if (resultadosConsulta.getTimestamp("fechaLimite") != null) {
                    
                    actividad.setFechaLimite(resultadosConsulta.getTimestamp("fechaLimite").toLocalDateTime());
                    
                }

                actividades.add(actividad);
                
            }

        } catch (SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos.", e);
            
        }

        return actividades;
    }

    @Override
    public boolean eliminarActividad(String titulo) throws OperacionesDeDaoExcepcion {
        
        boolean eliminacionExitosa = false;
        
        String consultaSQL = "DELETE FROM Practica WHERE titulo = ?";
        
         try(Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {
            
            consultaPreparada.setString(1, titulo);
            
            int filasAfectadas = consultaPreparada.executeUpdate();
            if(filasAfectadas > 0 ){
                eliminacionExitosa = true;
            }
             
         }catch(SQLException e){
             throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
         }
        
    return eliminacionExitosa; 
    
    }

    @Override
    public boolean actualizarActividad(Actividad actividad) throws OperacionesDeDaoExcepcion{
        
        boolean actualizacionExitosa = false;
        
        String consultaSQL = "UPDATE Practica SET titulo = ?, descripcion = ?, "
                + "fechaLimite = ?  WHERE titulo = ?";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {
            
            consultaPreparada.setString(1, actividad.getTitulo());
            consultaPreparada.setString(2, actividad.getDescripcion());
            consultaPreparada.setObject(3, actividad.getFechaLimite());
            consultaPreparada.setString(4, actividad.getTitulo());
            
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
