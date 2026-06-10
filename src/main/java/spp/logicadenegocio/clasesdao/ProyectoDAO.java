/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import spp.accesoadatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.interfacesdao.IProyectoDAO;
import spp.utilerias.bitacora.RegistroErrores;
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
            
            registroExitoso = consultaPreparada.executeUpdate() > 0;

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al insertar proyecto. " +
                "Nombre: " + proyecto.getNombre() + 
                ", Organización ID: " + proyecto.getOrganizacion().getIdOrganizacion(), e);
            
            throw new OperacionesDeDaoExcepcion("Ya existe un proyecto con ese nombre " + 
                "o la organización no es válida", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar proyecto. Nombre: " + proyecto.getNombre(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar proyecto. " +
                "Nombre: " + proyecto.getNombre() + 
                ", Cupo: " + proyecto.getCupoMaximo() + 
                ", Organización ID: " + proyecto.getOrganizacion().getIdOrganizacion(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos del proyecto no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al insertar proyecto. " +
                "Nombre: " + proyecto.getNombre() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al registrar el proyecto, " + 
                "intente de nuevo más tarde", e);
        }
    
         return registroExitoso;
    
    }
    
    @Override
    public Proyecto consultarProyecto(String nombre)throws OperacionesDeDaoExcepcion {

        Proyecto proyecto = null;

        String consultaSQL =
                "SELECT p.idProyecto, "
                + "p.nombre, "
                + "p.objetivoGeneral, "
                + "p.nombreResponsable, "
                + "p.contactoResponsable, "
                + "p.metodologia, "
                + "p.cupoMaximo, "
                + "p.estado, "
                + "o.idOrganizacion, "
                + "o.nombre AS nombreOrganizacion "
                + "FROM Proyecto p "
                + "INNER JOIN Organizacion o "
                + "ON p.Organizacion_idOrganizacion = o.idOrganizacion "
                + "WHERE p.nombre = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, nombre);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if(resultadosConsulta.next()) {

                proyecto = new Proyecto();

                proyecto.setIdProyecto(resultadosConsulta.getInt("idProyecto"));
                proyecto.setNombre(resultadosConsulta.getString("nombre"));
                proyecto.setObjetivoGeneral(resultadosConsulta.getString("objetivoGeneral"));
                proyecto.setNombreResponsable(resultadosConsulta.getString("nombreResponsable"));
                proyecto.setContactoResponsable(resultadosConsulta.getString("contactoResponsable"));
                proyecto.setMetodologia(resultadosConsulta.getString("metodologia"));
                proyecto.setCupoMaximo(resultadosConsulta.getInt("cupoMaximo"));
                proyecto.setEsActivo(resultadosConsulta.getInt("estado") == 1);
                Organizacion organizacion = new Organizacion();
                organizacion.setIdOrganizacion(resultadosConsulta.getInt("idOrganizacion"));
                organizacion.setNombre(resultadosConsulta.getString("nombreOrganizacion"));
                proyecto.setOrganizacion(organizacion);
                
            }          

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al consultar proyecto. " +
                "Nombre: " + nombre + 
                " - Verificar tablas: Proyecto, Organizacion", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar proyecto. Nombre: " + nombre, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar proyecto. " +
                "Nombre: " + nombre + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo consultar el proyecto, " + 
                "intente de nuevo", e);
        }

        return proyecto;
    }
       
    @Override
    public boolean disminuirCupoProyecto(int idProyecto)throws OperacionesDeDaoExcepcion {
        
        boolean actualizado = false;
        
        String consultaSQL = "UPDATE Proyecto SET cupoMaximo = cupoMaximo - 1 "+
        "WHERE idProyecto = ? AND cupoMaximo > 0";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
          
            consultaPreparada.setInt(1, idProyecto);

            actualizado = consultaPreparada.executeUpdate() > 0;

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al disminuir cupo de proyecto. ID Proyecto: " + idProyecto, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al disminuir cupo de proyecto. " +
                "ID Proyecto: " + idProyecto + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al actualizar el cupo, " + 
                "intente de nuevo más tarde", e);
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

            inactivacionExitosa = consultaPreparada.executeUpdate() > 0;
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al inactivar proyecto. ID Proyecto: " + idProyecto, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al inactivar proyecto. " +
                "ID Proyecto: " + idProyecto + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al inactivar el proyecto, " + 
                "intente de nuevo más tarde", e);
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

            inactivacionExitosa = consultaPreparada.executeUpdate() > 0;
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al inactivar proyectos de organización. ID Organización: " + idOrganizacion, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al inactivar proyectos de organización. " +
                "ID Organización: " + idOrganizacion + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al inactivar los proyectos, " + 
                "intente de nuevo más tarde", e);
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

            actualizacionExitosa = consultaPreparada.executeUpdate() > 0;

            
        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al actualizar proyecto. " +
                "ID Proyecto: " + proyecto.getIdProyecto() + 
                ", Nombre: " + proyecto.getNombre(), e);
            
            throw new OperacionesDeDaoExcepcion("Ya existe un proyecto con ese nombre", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al actualizar proyecto. ID: " + proyecto.getIdProyecto(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al actualizar proyecto. " +
                "ID Proyecto: " + proyecto.getIdProyecto() + 
                ", Cupo: " + proyecto.getCupoMaximo(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos del proyecto no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al actualizar proyecto. " +
                "ID Proyecto: " + proyecto.getIdProyecto() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al actualizar el proyecto, " + 
                "intente de nuevo más tarde", e);
        }

        return actualizacionExitosa;
        
    }
    
    @Override
    public boolean asignarProyecto(int idProyecto, int idUsuario) throws OperacionesDeDaoExcepcion {

        boolean asignacionExitosa = false; 

        String consultaSQL = "UPDATE Practicante SET Proyecto_idProyecto = ? WHERE idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idProyecto);
            consultaPreparada.setInt(2, idUsuario);

            if(consultaPreparada.executeUpdate() > 0){
                asignacionExitosa = true;
            }

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al asignar proyecto. " +
                "ID Proyecto: " + idProyecto + 
                ", ID Usuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo asignar el proyecto al practicante", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al asignar proyecto. ID Proyecto: " + idProyecto, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al asignar proyecto. " +
                "ID Proyecto: " + idProyecto + 
                ", ID Usuario: " + idUsuario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al asignar el proyecto, " + 
                "intente de nuevo más tarde", e);
        }
        return asignacionExitosa; 
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

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al obtener proyectos activos. " +
                "Verificar tablas: Proyecto, Organizacion", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al obtener proyectos activos", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al obtener proyectos activos. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron obtener los proyectos, " + 
                "intente de nuevo", e);
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

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al obtener proyectos por organización. ID Organización: " + idOrganizacion, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al obtener proyectos por organización. " +
                "ID Organización: " + idOrganizacion + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron obtener los proyectos, " + 
                "intente de nuevo por favor", e);
        }
        
        return proyectos;
    }
    
    @Override
    public boolean eliminarProyecto(String nombre) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Proyecto WHERE nombre = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, nombre);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al eliminar proyecto. " +
                "Nombre: " + nombre + 
                " - Posiblemente tiene practicantes asignados", e);
            
            throw new OperacionesDeDaoExcepcion("No se puede eliminar el proyecto porque " + 
                "tiene practicantes asignados", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar proyecto. Nombre: " + nombre, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al eliminar proyecto. " +
                "Nombre: " + nombre + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar el proyecto, " + 
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    }
    
    @Override
    public boolean desasignarProyecto(int idUsuario)throws OperacionesDeDaoExcepcion {

        String consultaSQL = "UPDATE Practicante SET Proyecto_idProyecto = NULL WHERE idUsuario = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            return consultaPreparada.executeUpdate() > 0;

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al desasignar proyecto. ID Usuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al desasignar proyecto. " +
                "ID Usuario: " + idUsuario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al desasignar el proyecto, " +
                "intente de nuevo más tarde", e);
        }
    }
    
}
