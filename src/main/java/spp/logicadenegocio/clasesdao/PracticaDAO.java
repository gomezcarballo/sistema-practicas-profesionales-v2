package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.time.LocalDateTime;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.interfacesdao.IPracticaDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PracticaDAO implements IPracticaDAO{

    @Override
    public boolean insertarActividad(Actividad actividad) throws OperacionesDeDaoExcepcion {
        
        boolean registroExitoso = false;
        
        String consultaSQL = "INSERT INTO Practica (titulo, descripcion, "
                + "fechaLimite, Profesor_idUsuario) VALUES (?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada  = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1,actividad.getTitulo());
            consultaPreparada.setString(2,actividad.getDescripcion());
            consultaPreparada.setObject(3,actividad.getFechaLimite());
            consultaPreparada.setInt(4,actividad.getIdProfesor());
            
            consultaPreparada.executeUpdate();
            registroExitoso = true;
            
        }catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Violación de integridad al insertar actividad. " +
                "Título: " + actividad.getTitulo() + 
                ", Profesor ID: " + actividad.getIdProfesor(), e);
            
            throw new OperacionesDeDaoExcepcion("Ya existe una actividad con ese título", e);
        
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar actividad. Título: " + actividad.getTitulo(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando en responder, intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar actividad. " +
                "Título: " + actividad.getTitulo() + 
                ", Fecha: " + actividad.getFechaLimite(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos ingresados no son válidos, revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al insertar actividad. " +
                "Título: " + actividad.getTitulo() + 
                ", Profesor ID: " + actividad.getIdProfesor() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al guardar la actividad, intente de nuevo más tarde", e);
        }
        
        return registroExitoso;
    
    }

    @Override
    public Actividad consultarActividad(String titulo) throws OperacionesDeDaoExcepcion{
        
        Actividad practicaConsultada = null;
        
        String consultaSQL = "SELECT idActividad, titulo, descripcion, fechaLimite, Profesor_idUsuario "
                + "FROM Practica WHERE titulo = ? ";
        
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1,titulo); 
            
            try(ResultSet resultadosConsulta = consultaPreparada.executeQuery();){
                if(resultadosConsulta.next() ){
                    
                practicaConsultada = new Actividad();
                
                practicaConsultada.setIdActividad(resultadosConsulta.getInt("idActividad"));
                practicaConsultada.setTitulo(resultadosConsulta.getString("titulo"));
                practicaConsultada.setDescripcion(resultadosConsulta.getString("descripcion"));
                practicaConsultada.setFechaLimite(resultadosConsulta.getObject("fechaLimite",LocalDateTime.class));
                practicaConsultada.setIdProfesor(resultadosConsulta.getInt("Profesor_idUsuario"));
                
                }
            }
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar actividad. Título buscado: " + titulo, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema no responde, intente de nuevo más tarde", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar actividad. Título buscado: " + titulo + 
                ", SQL State: " + e.getSQLState(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo consultar la actividad, intente de nuevo", e);
        }
        
        return practicaConsultada;
    
    }
    
    @Override
    public List<Actividad> consultarActividadesAsignadas(int idUsuario) throws OperacionesDeDaoExcepcion {

        List<Actividad> practicasConsultadas = new ArrayList<>();

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

                Actividad actividadConsultada = new Actividad();

                actividadConsultada.setIdActividad(resultadosConsulta.getInt("idActividad"));
                actividadConsultada.setTitulo(resultadosConsulta.getString("titulo"));
                actividadConsultada.setDescripcion(resultadosConsulta.getString("descripcion"));

                if (resultadosConsulta.getTimestamp("fechaLimite") != null) {
                    
                    actividadConsultada.setFechaLimite(resultadosConsulta.getTimestamp("fechaLimite").toLocalDateTime());
                    
                }

                practicasConsultadas.add(actividadConsultada);
                
            }

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar actividades asignadas. El ID es: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema no responde, intente de nuevo más tarde", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar actividades asignadas. El ID es:" + idUsuario + 
                ", SQL State: " + e.getSQLState(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo consultar las actividades, intente de nuevo", e);
        }

        return practicasConsultadas;
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
             
         } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar actividad. Título: " + titulo, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al eliminar actividad. " +
                "Título: " + titulo + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar la actividad, intente de nuevo más tarde", e);
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
            
        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Violación de integridad al actualizar actividad. " +
                "Título: " + actividad.getTitulo() + 
                ", Profesor ID: " + actividad.getIdProfesor(), e);
            
            throw new OperacionesDeDaoExcepcion("Ya existe una actividad con ese título o el profesor no es válido", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al actualizar actividad. Título: " + actividad.getTitulo(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al actualizar actividad. " +
                "Título: " + actividad.getTitulo() + 
                ", Fecha: " + actividad.getFechaLimite(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos ingresados no son válidos, revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al actualizar actividad. " +
                "Título: " + actividad.getTitulo() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al actualizar la actividad, intente de nuevo más tarde", e);
        }
        
    return actualizacionExitosa;
    
    }
    
}
