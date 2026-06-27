package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.logging.Level;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

public class ExperienciaEducativaDAO {

    public int insertarExperienciaEducativa(ExperienciaEducativa experiencia) throws OperacionesDeDaoExcepcion {

        int idInsertado = 0; 

        String consultaSQL = "INSERT INTO ExperienciaEducativa (nombre, periodo, cupo, idReferenciaCurso) " +
                             "VALUES (?, ?, ?, ?)";
        
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {

            consultaPreparada.setString(1, experiencia.getNombreExperienciaEducativa());
            consultaPreparada.setString(2, experiencia.getPeriodo());
            consultaPreparada.setInt(3, experiencia.getCupo());
            consultaPreparada.setInt(4, experiencia.getIdReferenciaCurso());

            consultaPreparada.executeUpdate();
            
            ResultSet resultadosConsulta = consultaPreparada.getGeneratedKeys();

            if (resultadosConsulta.next()) {
                
                idInsertado = resultadosConsulta.getInt(1);
                experiencia.setIdExperienciaEducativa(idInsertado);
                
            }

            resultadosConsulta.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nViolación de integridad al insertar una experiencia educativa. " +
                "Nombre: " + experiencia.getNombreExperienciaEducativa() + " Periodo: " + experiencia.getPeriodo() + 
                " - Posible periodo duplicado", e);
            
            throw new OperacionesDeDaoExcepcion("La experiencia educativa ya está registrada en este periodo.", e);
            
        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al insertar experiencia educativa. Nombre : " + experiencia.getNombreExperienciaEducativa(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch (SQLDataException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nDatos inválidos al insertar experiencia educativa. " +
                "Nombre: " + experiencia.getNombreExperienciaEducativa() + 
                ", Periodo: " + experiencia.getPeriodo(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos de la experiencia educativa no son válidos. " + 
                "Revise la información", e);
            
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al insertar experiencia educativa. " +
                "Nombre: " + experiencia.getNombreExperienciaEducativa() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al registrar la experiencia educativa, " + 
                "intente de nuevo más tarde", e);
        }

        return idInsertado;
        
    }

    public ExperienciaEducativa consultarExperienciaEducativa(int idExperienciaEducativa) throws OperacionesDeDaoExcepcion {

        ExperienciaEducativa experienciaEncontrada = null;
        
        String consultaSQL = "SELECT nombre, periodo, cupo, idReferenciaCurso FROM ExperienciaEducativa WHERE idExperienciaEducativa = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {
            
            consultaPreparada.setInt(1, idExperienciaEducativa);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {

                experienciaEncontrada = new ExperienciaEducativa();
                experienciaEncontrada.setIdExperienciaEducativa(idExperienciaEducativa);
                
                experienciaEncontrada.setNombreExperienciaEducativa(resultadosConsulta.getString("nombreExperienciaEducativa"));
                experienciaEncontrada.setPeriodo(resultadosConsulta.getString("periodo"));
                experienciaEncontrada.setCupo(resultadosConsulta.getInt("cupo"));
                experienciaEncontrada.setIdReferenciaCurso(resultadosConsulta.getInt("idReferenciaCurso"));
                
            } 
            
            resultadosConsulta.close();

        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al consultar la experiencia educativa. idExperienciaEducativa: " 
                + idExperienciaEducativa, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al consultar la experiencia educativa. " +
                "idExperienciaEducativa: " + idExperienciaEducativa + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo consultar la experiencia educativa, " + 
                "intente de nuevo por favor", e);
        }

        return experienciaEncontrada;

    }

    public boolean actualizarEstadoExperienciaEducativa() {

        boolean esRegistroExitoso = false;

        return esRegistroExitoso;
    
    }

    public int consultarAsignacionesActivas() throws OperacionesDeDaoExcepcion {

        int asignacionesActivas = 0 ;

        String consultaSQL = "SELECT ()";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
             ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            if (resultadoConsulta.next()) {
                asignacionesActivas = resultadoConsulta.getInt(1);
            }

        } catch (SQLSyntaxErrorException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nLa función almacenada '()' no existe o no es accesible. " +
                "Verificar que la función esté creada en la base de datos", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al ejecutar función almacenada '()'", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al ejecutar función '()'. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al verificar la información, " + 
                "intente de nuevo más tarde", e);
        }
        
        return asignacionesActivas;

    }

}
