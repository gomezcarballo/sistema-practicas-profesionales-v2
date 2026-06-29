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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.interfacesdao.IExperienciaEducativaDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

public class ExperienciaEducativaDAO implements IExperienciaEducativaDAO{

    @Override
    public int insertarExperienciaEducativa(ExperienciaEducativa experiencia) throws OperacionesDeDaoExcepcion {

        int idInsertado = 0; 

        String consultaSQL = "INSERT INTO ExperienciaEducativa (nombre, periodo, cupo, estado, idReferenciaCurso) " +
                             "VALUES (?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement
            (consultaSQL, Statement.RETURN_GENERATED_KEYS);) {

            consultaPreparada.setString(1, experiencia.getNombreExperienciaEducativa());
            consultaPreparada.setString(2, experiencia.getPeriodo());
            consultaPreparada.setInt(3, experiencia.getCupo());
            consultaPreparada.setBoolean(4, experiencia.getEstado());
            consultaPreparada.setInt(5, experiencia.getIdReferenciaCurso());

            consultaPreparada.executeUpdate();
            
            ResultSet resultadosConsulta = consultaPreparada.getGeneratedKeys();

            if (resultadosConsulta.next()) {
                
                idInsertado = resultadosConsulta.getInt(1);
                experiencia.setIdExperienciaEducativa(idInsertado);
                
            }

            resultadosConsulta.close();

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "\nViolación de integridad al insertar una experiencia educativa. " +
                "NRC: " + experiencia.getIdReferenciaCurso() + " Periodo: " + experiencia.getPeriodo() + 
                " - Posible experiencia educativa duplicada", e);
            
            throw new OperacionesDeDaoExcepcion("La experiencia educativa ya esta registrada.", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al insertar experiencia educativa. NRC : " + experiencia.getIdReferenciaCurso() 
                + " Periodo: " + experiencia.getPeriodo(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nDatos inválidos al insertar experiencia educativa. " +
                "NRC: " + experiencia.getIdReferenciaCurso() + 
                ", Periodo: " + experiencia.getPeriodo(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos del la experiencia educativa no son válidos. " + 
                "Revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al insertar experiencia educativa. " +
                "NRC: " + experiencia.getIdReferenciaCurso() + 
                " Periodo: " + experiencia.getPeriodo() +
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al registrar la experiencia educativa, " + 
                "intente de nuevo más tarde", e);
        }


        return idInsertado;
        
    }

    @Override
    public ExperienciaEducativa consultarExperienciaEducativa(int idExperienciaEducativa) throws OperacionesDeDaoExcepcion {

        ExperienciaEducativa experienciaEncontrada = null;

        String consultaSQL = "SELECT nombre, periodo, cupo, idReferenciaCurso FROM ExperienciaEducativa WHERE idExperienciaEducativa = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
            
            consultaPreparada.setInt(1, idExperienciaEducativa);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {

                experienciaEncontrada = new ExperienciaEducativa();

                experienciaEncontrada.setNombreExperienciaEducativa(resultadosConsulta.getString("nombre"));
                experienciaEncontrada.setPeriodo(resultadosConsulta.getString("periodo"));
                experienciaEncontrada.setCupo(resultadosConsulta.getInt("cupo"));
                experienciaEncontrada.setIdReferenciaCurso(resultadosConsulta.getInt("idReferenciaCurso"));
                
                resultadosConsulta.close();

            } 

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al consultar la experiencias educativas. idExperienciaEducativa: " 
                + idExperienciaEducativa, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
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
    
    public List<ExperienciaEducativa> consultarExperienciasEducativasActivas() throws OperacionesDeDaoExcepcion{
        
        List<ExperienciaEducativa> listaExperiencias = new ArrayList<>();

        String consultaSQL = "SELECT * FROM ExperienciaEducativa WHERE estado = 1";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
             ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

            while (resultadosConsulta.next()) {

                ExperienciaEducativa experiencia = new ExperienciaEducativa();

                experiencia.setIdExperienciaEducativa(resultadosConsulta.getInt("idExperienciaEducativa"));
                experiencia.setNombreExperienciaEducativa(resultadosConsulta.getString("nombre"));
                experiencia.setIdReferenciaCurso(resultadosConsulta.getInt("idReferenciaCurso"));
                experiencia.setCupo(resultadosConsulta.getInt("cupo"));
                experiencia.setIdProfesorAsignado(resultadosConsulta.getInt("idUsuarioProfesor"));

                listaExperiencias.add(experiencia);
                
            }

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError de sintaxis al consultar experiencias educativas activas " +
                "Verificar tabla: Experiencia educativa.", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al consultar experiencias educativas activas", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al consultar experiencias educativas activas" +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron consultar las experiencias educativas, " + 
                "intente de nuevo", e);
        }

        return listaExperiencias;
    
    }

    @Override
    public int consultarAsignacionesActivas() throws OperacionesDeDaoExcepcion {

        int asignacionesActivas = 0 ;

        String consultaSQL = "SELECT ()";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            if(resultadoConsulta.next()) {
                asignacionesActivas = resultadoConsulta.getInt(1);
            }

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nLa función almacenada '()' no existe o no es accesible. " +
                "Verificar que la función esté creada en la base de datos", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al ejecutar función almacenada '()'", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al ejecutar función '()'. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al verificar la información, " + 
                "intente de nuevo más tarde", e);
        }

        return asignacionesActivas;

    }

    public boolean asignarExperienciaEducativaAPracticante(int idExperiencia, String matricula) throws OperacionesDeDaoExcepcion{
         
        boolean asignacionExitosa = false; 

        String consultaSQL = "UPDATE Practicante SET idExperienciaEducativa = ? WHERE matricula = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idExperiencia);
            consultaPreparada.setString(2, matricula);

            if(consultaPreparada.executeUpdate() > 0){
                asignacionExitosa = true;
            }

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "\nViolación de integridad al asignar una experiencia educativa. " +
                "ID_EE: " + idExperiencia + 
                ", Matricula: " + matricula, e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo asignar la EE al practicante", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al asignar una experiencia educativa. ID_EE : " + idExperiencia + 
                " , la Matricula : " + matricula, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al asignar una experiencia educativa. " +
                "ID_EE: " + idExperiencia + 
                ", la Matricula: " + matricula + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al asignar la EE, " + 
                "intente de nuevo más tarde", e);
        }
        return asignacionExitosa; 
    }

    public boolean asignarExperienciaEducativaAProfesor(int idExperiencia, String numeroPersonal) throws OperacionesDeDaoExcepcion {
         
        boolean asignacionExitosa = false; 

        String consultaSQL = "UPDATE experienciaeducativa " +
                             "SET idUsuarioProfesor = (SELECT idUsuario FROM profesor WHERE noPersonal = ?) " +
                             "WHERE idExperienciaEducativa = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, numeroPersonal);
            consultaPreparada.setInt(2, idExperiencia);

            if (consultaPreparada.executeUpdate() > 0) {
                asignacionExitosa = true;
            }

        } catch(SQLIntegrityConstraintViolationException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nViolación de integridad al asignar profesor a experiencia educativa. " +
                "ID_EE: " + idExperiencia + 
                ", No. Personal: " + numeroPersonal, e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo asignar el profesor a la EE debido a un conflicto de datos", e);
            
        } catch(SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al asignar profesor a experiencia educativa. ID_EE : " + idExperiencia + 
                " , No. Personal : " + numeroPersonal, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al asignar profesor a experiencia educativa. " +
                "ID_EE: " + idExperiencia + 
                ", No. Personal: " + numeroPersonal + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al asignar la EE, " + 
                "intente de nuevo más tarde", e);
                
        }
        
        return asignacionExitosa; 
        
    }
    
    public List<ExperienciaEducativa> consultarExperienciasEducativasSinProfesor() throws OperacionesDeDaoExcepcion {
        
        List<ExperienciaEducativa> listaExperiencias = new ArrayList<>();

        String consultaSQL = "SELECT idExperienciaEducativa, nombre, periodo, cupo, idReferenciaCurso, estado " +
                             "FROM experienciaeducativa " +
                             "WHERE idUsuarioProfesor IS NULL";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
             ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

            while (resultadosConsulta.next()) {

                ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();

                experienciaEducativa.setIdExperienciaEducativa(resultadosConsulta.getInt("idExperienciaEducativa"));
                experienciaEducativa.setNombreExperienciaEducativa(resultadosConsulta.getString("nombre"));
                experienciaEducativa.setPeriodo(resultadosConsulta.getString("periodo"));
                experienciaEducativa.setCupo(resultadosConsulta.getInt("cupo"));
                experienciaEducativa.setIdReferenciaCurso(resultadosConsulta.getInt("idReferenciaCurso"));
                
                listaExperiencias.add(experienciaEducativa);
                
            }

        } catch(SQLSyntaxErrorException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError de sintaxis al consultar experiencias educativas sin profesor. " +
                "Verificar tabla: experienciaeducativa", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al consultar experiencias educativas sin profesor.", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al consultar experiencias educativas sin profesor. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron consultar los grupos disponibles, " + 
                "intente de nuevo", e);
                
        }

        return listaExperiencias;
        
    }
    
}
