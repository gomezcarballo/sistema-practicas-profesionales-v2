/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLTimeoutException;
import java.sql.Statement;
import java.util.logging.Level;

import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.interfacesdao.IEvaluacionDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class EvaluacionDAO implements IEvaluacionDAO{

    @Override
    public int insertarEvaluacion(Evaluacion evaluacion) throws OperacionesDeDaoExcepcion {
        
        int idGenerado = 0;
        
        String consultaSQL = "INSERT INTO Evaluacion (nrc, periodo, calificacionFinal, Profesor_idUsuario, "
                + "observaciones, Practicante_idUsuario) VALUES (?, ?, ?, ?, ?, ?)";
                
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL, 
            Statement.RETURN_GENERATED_KEYS);){
            
            consultaPreparada.setString(1, evaluacion.getNrc());
            consultaPreparada.setString(2, evaluacion.getPeriodo());
            consultaPreparada.setDouble(3, evaluacion.getCalificacionFinal());
            consultaPreparada.setInt(4, evaluacion.getIdProfesor());
            consultaPreparada.setString(5, evaluacion.getObservaciones());
            consultaPreparada.setInt(6, evaluacion.getPracticante().getIdUsuario());

            consultaPreparada.executeUpdate();
            
            ResultSet resultadosConsulta = consultaPreparada.getGeneratedKeys();

            if (resultadosConsulta.next()) {
                
                idGenerado = resultadosConsulta.getInt(1);
                evaluacion.setIdEvaluacion(idGenerado);
                
            }
                        
        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Violación de integridad al insertar la evaluacion. " +
                "El ID del profesor: " + evaluacion.getIdProfesor() +
                ", el ID del practicante: " + evaluacion.getPracticante().getIdUsuario() +
                ", el NRC : " + evaluacion.getNrc() + 
                ", el perido: " + evaluacion.getPeriodo() + 
                ", la calificacion: " + evaluacion.getCalificacionFinal() , e);
            
            throw new OperacionesDeDaoExcepcion("Ya existe un registro con la misma información", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar coordinador." +
                "El ID del profesor: " + evaluacion.getIdProfesor() +
                ", el ID del practicante: " + evaluacion.getPracticante().getIdUsuario() +
                ", el NRC : " + evaluacion.getNrc() + 
                ", el perido: " + evaluacion.getPeriodo() + 
                ", la calificacion: " + evaluacion.getCalificacionFinal() , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar el coordinador." +
                " El ID del profesor: " + evaluacion.getIdProfesor() +
                ", el ID del practicante: " + evaluacion.getPracticante().getIdUsuario() +
                ", el NRC : " + evaluacion.getNrc() + 
                ", el perido: " + evaluacion.getPeriodo() + 
                ", la calificacion: " + evaluacion.getCalificacionFinal() , e);
            
            throw new OperacionesDeDaoExcepcion("Los datos ingresados no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al insertar el coordinador. " +
                " El ID del profesor: " + evaluacion.getIdProfesor() +
                ", el ID del practicante: " + evaluacion.getPracticante().getIdUsuario() +
                ", el NRC : " + evaluacion.getNrc() + 
                ", el perido: " + evaluacion.getPeriodo() + 
                ", la calificacion: " + evaluacion.getCalificacionFinal() + 
                ".SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al guardar la evaluación, " + 
                "intente de nuevo más tarde", e);
        }
        
        return idGenerado;
        
    }

    @Override
    public Evaluacion consultarEvaluacion(int idEvaluacion) throws OperacionesDeDaoExcepcion {
        
        Evaluacion evaluacion = null;
        
        String consultaSQL = "SELECT * FROM Evaluacion WHERE idEvaluacion = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
          
            consultaPreparada.setInt(1, idEvaluacion);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {
                evaluacion = new Evaluacion();

                evaluacion.setNrc(resultadosConsulta.getString("nrc"));
                evaluacion.setPeriodo(resultadosConsulta.getString("periodo"));
                evaluacion.setCalificacionFinal(resultadosConsulta.getDouble("calificacionFinal"));;
                evaluacion.setIdProfesor(resultadosConsulta.getInt("Profesor_idUsuario"));
                
                Practicante practicante = new Practicante();
                practicante.setIdUsuario(resultadosConsulta.getInt("Practicante_idUsuario"));
                evaluacion.setPracticante(practicante);
            }

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar la evaluación"+
                "El ID de la evaluación: " + idEvaluacion, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al consultar la evaluación. " + 
                "El ID de la evaluación: " + idEvaluacion +
                ".SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al consultar la evaluación, " + 
                "intente de nuevo más tarde", e);
        }

    return evaluacion;
        
    }
    
    @Override
    public boolean eliminarEvaluacion(int idEvaluacion)throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Evaluacion WHERE idEvaluacion = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idEvaluacion);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar la evaluación. IdEvaluación: " + idEvaluacion, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al eliminar la evaluación. " + 
                "IDEvaluación : " + idEvaluacion +
                ".SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar la evaluación, " + 
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    }
    
}
