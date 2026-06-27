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

    public int insertarExperienciaEducativa (ExperienciaEducativa experiencia) throws OperacionesDeDaoExcepcion {

        int idInsertado = 0; 

        String consultaSQL = "INSERT INTO ExperienciaEducativa (nombre, nrc, periodo, cupo) " +
                            "VALUES (?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement
            (consultaSQL, Statement.RETURN_GENERATED_KEYS);) {

            consultaPreparada.setString(1, experiencia.getNombreExperienciaEducativa());
            consultaPreparada.setString(2, experiencia.getNrc());
            consultaPreparada.setString(3, experiencia.getPeriodo());
            consultaPreparada.setInt(4, experiencia.getCupo());

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
                "NRC: " + experiencia.getNrc() + " Periodo: " + experiencia.getPeriodo() + 
                " - Posible correo duplicado", e);
            
            throw new OperacionesDeDaoExcepcion("La experiencia educativa ya esta registrada.", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al insertar experiencia educativa. NRC : " + experiencia.getNrc(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nDatos inválidos al insertar experiencia educativa. " +
                "NRC: " + experiencia.getNrc() + 
                ", Periodo: " + experiencia.getPeriodo(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos del la experiencia educativa no son válidos. " + 
                "Revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al insertar experiencia educativa. " +
                "Correo: " + experiencia.getNrc() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al registrar la experiencia educativa, " + 
                "intente de nuevo más tarde", e);
        }


        return idInsertado;
        
    }

    public ExperienciaEducativa consultarExperienciaEducativa(int idExperienciaEducativa) throws OperacionesDeDaoExcepcion{

        ExperienciaEducativa experienciaEncontrada = null;
        String consultaSQL = "SELECT nombre, nrc, periodo, cupo FROM ExperienciaEducativa WHERE idExperienciaEducativa = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
            
            consultaPreparada.setInt(1, idExperienciaEducativa);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {

                experienciaEncontrada = new ExperienciaEducativa();

                experienciaEncontrada.setNombreExperienciaEducativa(resultadosConsulta.getString("nombre"));
                experienciaEncontrada.setNrc(resultadosConsulta.getString("nrc"));
                experienciaEncontrada.setPeriodo(resultadosConsulta.getString("periodo"));
                experienciaEncontrada.setCupo(resultadosConsulta.getInt("cupo"));
                
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

    public boolean actualizarEstadoExperienciaEducativa(){

        boolean esRegistroExitoso = false;

        return esRegistroExitoso;
    
    }

    public int consultarAsignacionesActivas()throws OperacionesDeDaoExcepcion{

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
                "Timeout al ejecutar función almacenada '()'", e);
            
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


}
