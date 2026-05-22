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
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.interfacesdao.IProyectoDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;


/**
 *
 * @author Luz Fernanda H J
 */
public class ProyectoDAO implements IProyectoDAO {
    
    @Override
    public boolean insertarProyecto(Proyecto proyecto)throws OperacionesDeDaoExcepcion{
        
        boolean registroExitoso = false;
        
        String consultaSQL = "INSERT INTO Proyecto (nombre, descripcion, nombreResponsable, "
                + "cupoMaximo, estado, Organizacion_idOrganizacion) VALUES (?, ?, ?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {

            consultaPreparada.setString(1, proyecto.getNombre());
            consultaPreparada.setString(2, proyecto.getDescripcion());
            consultaPreparada.setString(3, proyecto.getNombreResponsable());
            consultaPreparada.setInt(4, proyecto.getCupoMaximo());
            consultaPreparada.setBoolean(5, proyecto.getEsActivo());
            consultaPreparada.setInt(6, proyecto.getOrganizacion().getIdOrganizacion());
            
            consultaPreparada.executeUpdate();
            
            registroExitoso = true;

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
    return registroExitoso;
    
    }
       
    @Override
    public Proyecto consultarProyecto(String nombre)throws OperacionesDeDaoExcepcion {
        
        Proyecto proyecto = null;
        
         String consultaSQL = "SELECT * FROM Proyecto WHERE nombre = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
          
            consultaPreparada.setString(1, nombre);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {
                proyecto = new Proyecto();

                proyecto.setIdProyecto(resultadosConsulta.getInt("idProyecto"));
                proyecto.setNombre(resultadosConsulta.getString("nombre"));
                proyecto.setDescripcion(resultadosConsulta.getString("descripcion"));
                proyecto.setNombreResponsable(resultadosConsulta.getString("nombreResponsable"));
                proyecto.setCupoMaximo(resultadosConsulta.getInt("cupoMaximo"));
                
                int esActivo = resultadosConsulta.getInt("estado");
                if (esActivo == 1) {
                    proyecto.setEsActivo(true);
                } else {
                    proyecto.setEsActivo(false);
                }
                
                Organizacion organizacion = new Organizacion();
                organizacion.setIdOrganizacion(resultadosConsulta.getInt("Organizacion_idOrganizacion"));

                proyecto.setOrganizacion(organizacion);
            }

            conexion.close();

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return proyecto;
        
    }


    @Override
    public boolean eliminarProyecto(String nombre)throws OperacionesDeDaoExcepcion {
        
        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM PROYECTO WHERE nombre = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, nombre);

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
    public boolean actualizarProyecto(Proyecto proyecto)throws OperacionesDeDaoExcepcion {
        
        boolean actualizacionExitosa = false;

        String consultaSQL = "UPDATE PROYECTO SET nombre = ?, descripcion = ?, "
                + "nombreResponsable = ?, cupoMaximo = ? WHERE idProyecto = ? ";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {
                
            consultaPreparada.setString(1, proyecto.getNombre());
            consultaPreparada.setString(2, proyecto.getDescripcion());
            consultaPreparada.setString(3, proyecto.getNombreResponsable());
            consultaPreparada.setInt(4, proyecto.getCupoMaximo());
            consultaPreparada.setInt(5, proyecto.getIdProyecto());

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
    public List<Proyecto> obtenerProyectosActivos(int idOrganizacion)throws OperacionesDeDaoExcepcion {
        
        List<Proyecto> proyectos = new ArrayList<>();
        
         String consultaSQL = "SELECT idProyecto, nombre, descripcion, nombreResponsable, cupoMaximo FROM Proyecto "
                 + "WHERE Organizacion_idOrganizacion = ? AND estado = 1";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
            
            consultaPreparada.setInt(1, idOrganizacion);
            ResultSet resultadosConsulta = consultaPreparada.executeQuery();
            
            while (resultadosConsulta.next()) {
                
                Proyecto proyecto = new Proyecto();
                
                proyecto.setIdProyecto(resultadosConsulta.getInt("idProyecto"));
                proyecto.setNombre(resultadosConsulta.getString("nombre"));
                proyecto.setDescripcion(resultadosConsulta.getString("descripcion"));
                proyecto.setNombreResponsable(resultadosConsulta.getString("nombreResponsable"));
                proyecto.setCupoMaximo(resultadosConsulta.getInt("cupoMaximo"));

                proyectos.add(proyecto);
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        return proyectos;
    }
    
}
