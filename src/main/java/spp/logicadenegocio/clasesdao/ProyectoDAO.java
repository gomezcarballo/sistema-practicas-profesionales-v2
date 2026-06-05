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
        
        String consultaSQL = "INSERT INTO Proyecto (nombre, objetivoGeneral, nombreResponsable, "
                + "contactoResponsable, cupoMaximo, estado, metodologia, Organizacion_idOrganizacion) "
                + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {

            consultaPreparada.setString(1, proyecto.getNombre());
            consultaPreparada.setString(2, proyecto.getObjetivoGeneral());
            consultaPreparada.setString(3, proyecto.getNombreResponsable());
            consultaPreparada.setString(4, proyecto.getContactoResponsable());
            consultaPreparada.setInt(5, proyecto.getCupoMaximo());
            consultaPreparada.setBoolean(6, proyecto.getEsActivo());
            consultaPreparada.setString(7, proyecto.getMetodologia());
            consultaPreparada.setInt(8, proyecto.getOrganizacion().getIdOrganizacion());
            
            consultaPreparada.executeUpdate();
            
            registroExitoso = true;

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
    return registroExitoso;
    
    }
       
    @Override
    public boolean disminuirCupoProyecto(int idProyecto)throws OperacionesDeDaoExcepcion {
        
        boolean actualizado = false;
        
        String consultaSQL = "UPDATE Proyecto SET cupoMaximo = cupoMaximo - 1 "+
        "WHERE idProyecto = ? AND cupoMaximo > 0";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
          
            consultaPreparada.setInt(1, idProyecto);

            int resultadosConsulta = consultaPreparada.executeUpdate();

            if(resultadosConsulta > 0){
                
                actualizado = true;
                
            }

        } catch (SQLException e) {
            
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        
        }

    return actualizado;
        
    }


    @Override
    public boolean inactivarProyecto(int idProyecto)throws OperacionesDeDaoExcepcion {
        
        boolean inactivacionExitosa = false;

        String consultaSQL = "UPDATE Proyecto SET estado = 0 WHERE idProyecto = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idProyecto);

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
    public boolean inactivarProyectosDeOrganizacion(int idOrganizacion)throws OperacionesDeDaoExcepcion {
        
        boolean inactivacionExitosa = false;

        String consultaSQL = "UPDATE Proyecto SET estado = 0 WHERE Organizacion_idOrganizacion = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idOrganizacion);

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
    public boolean actualizarProyecto(Proyecto proyecto)throws OperacionesDeDaoExcepcion {
        
        boolean actualizacionExitosa = false;

        String consultaSQL = "UPDATE PROYECTO SET nombre = ?, objetivoGeneral = ?, "
                + "nombreResponsable = ?, contactoResponsable = ?, cupoMaximo = ?, metodologia = ? "
                + "WHERE idProyecto = ? ";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {
                
            consultaPreparada.setString(1, proyecto.getNombre());
            consultaPreparada.setString(2, proyecto.getObjetivoGeneral());
            consultaPreparada.setString(3, proyecto.getNombreResponsable());
            consultaPreparada.setString(4, proyecto.getContactoResponsable());
            consultaPreparada.setInt(5, proyecto.getCupoMaximo());
            consultaPreparada.setString(6, proyecto.getMetodologia());
            consultaPreparada.setInt(7, proyecto.getIdProyecto());

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

    @Override
    public List<Proyecto> obtenerProyectosActivos()throws OperacionesDeDaoExcepcion {
        
        List<Proyecto> proyectos = new ArrayList<>();
        
         String consultaSQL = "SELECT p.idProyecto, " +
                            "p.nombre, " +
                            "p.objetivoGeneral, " +
                            "p.nombreResponsable, " +
                            "p.contactoResponsable, " +
                            "p.metodologia, " +
                            "p.cupoMaximo, " +
                            "o.nombre AS nombreOrganizacion " +
                            "FROM Proyecto p " +
                            "INNER JOIN Organizacion o " +
                            "ON p.Organizacion_idOrganizacion = o.idOrganizacion " +
                            "WHERE p.estado = 1";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
            
            ResultSet resultadosConsulta = consultaPreparada.executeQuery();
            
            while (resultadosConsulta.next()) {
                
                Proyecto proyecto = new Proyecto();
                
                proyecto.setIdProyecto(resultadosConsulta.getInt("idProyecto"));
                proyecto.setNombre(resultadosConsulta.getString("nombre"));
                proyecto.setObjetivoGeneral(resultadosConsulta.getString("objetivoGeneral"));
                proyecto.setNombreResponsable(resultadosConsulta.getString("nombreResponsable"));
                proyecto.setContactoResponsable(resultadosConsulta.getString("contactoResponsable"));
                proyecto.setMetodologia(resultadosConsulta.getString("metodologia"));
                proyecto.setCupoMaximo(resultadosConsulta.getInt("cupoMaximo"));
                
                Organizacion organizacion = new Organizacion();
                organizacion.setNombre(resultadosConsulta.getString("nombreOrganizacion"));
                proyecto.setOrganizacion(organizacion);
                
                proyectos.add(proyecto);
                
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        return proyectos;
    }
    
     @Override
    public List<Proyecto> obtenerProyectosActivosPorOrganizacion(int idOrganizacion)throws OperacionesDeDaoExcepcion {
        
        List<Proyecto> proyectos = new ArrayList<>();
        
         String consultaSQL = "SELECT idProyecto, nombre, objetivoGeneral, nombreResponsable, contactoResponsable, "
                 + "metodologia, cupoMaximo FROM Proyecto "
                 + "WHERE Organizacion_idOrganizacion = ? AND estado = 1";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
            
            consultaPreparada.setInt(1, idOrganizacion);
            ResultSet resultadosConsulta = consultaPreparada.executeQuery();
            
            while (resultadosConsulta.next()) {
                
                Proyecto proyecto = new Proyecto();
                
                proyecto.setIdProyecto(resultadosConsulta.getInt("idProyecto"));
                proyecto.setNombre(resultadosConsulta.getString("nombre"));
                proyecto.setObjetivoGeneral(resultadosConsulta.getString("objetivoGeneral"));
                proyecto.setNombreResponsable(resultadosConsulta.getString("nombreResponsable"));
                proyecto.setContactoResponsable(resultadosConsulta.getString("contactoResponsable"));
                proyecto.setMetodologia(resultadosConsulta.getString("metodologia"));
                proyecto.setCupoMaximo(resultadosConsulta.getInt("cupoMaximo"));

                proyectos.add(proyecto);
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        return proyectos;
    }
    
}
