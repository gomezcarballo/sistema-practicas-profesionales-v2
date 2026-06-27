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
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.ReferenciaCurso;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class ReferenciaCursoDAO {
    
    public int insertarReferenciaCurso(ReferenciaCurso referenciaCurso) throws OperacionesDeDaoExcepcion {

        int idInsertado = 0; 

        String consultaSQL = "INSERT INTO referencia_curso (nrc) VALUES (?)";
        
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL, Statement.RETURN_GENERATED_KEYS)) {

            consultaPreparada.setString(1, referenciaCurso.getNrc());

            consultaPreparada.executeUpdate();
            
            ResultSet resultadosConsulta = consultaPreparada.getGeneratedKeys();

            if (resultadosConsulta.next()) {
                
                idInsertado = resultadosConsulta.getInt(1);
                referenciaCurso.setIdReferenciaCurso(idInsertado);
                
            }

            resultadosConsulta.close();

        } catch (SQLIntegrityConstraintViolationException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nViolación de integridad al insertar ReferenciaCurso. " +
                "NRC: " + referenciaCurso.getNrc() + 
                " - El NRC ya existe en la base de datos.", e);
            
            throw new OperacionesDeDaoExcepcion("El NRC ingresado ya se encuentra registrado en el sistema.", e);
            
        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al insertar ReferenciaCurso. NRC: " + referenciaCurso.getNrc(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor.", e);
            
        } catch (SQLDataException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nDatos inválidos al insertar ReferenciaCurso. " +
                "NRC: " + referenciaCurso.getNrc(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos de la referencia del curso no son válidos. " + 
                "Revise la información.", e);
            
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al insertar ReferenciaCurso. " +
                "NRC: " + referenciaCurso.getNrc() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al registrar el nuevo NRC, " + 
                "intente de nuevo más tarde.", e);
        }

        return idInsertado;
        
    }

    public List<ReferenciaCurso> consultarTodasLasReferenciasCursos() throws OperacionesDeDaoExcepcion {
        
        List<ReferenciaCurso> listaReferencias = new ArrayList<>();
        
        String consultaSQL = "SELECT idReferenciaCurso, nrc FROM referencia_curso";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
             ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

            while (resultadosConsulta.next()) {

                ReferenciaCurso referencia = new ReferenciaCurso();
                
                referencia.setIdReferenciaCurso(resultadosConsulta.getInt("idReferenciaCurso"));
                referencia.setNrc(resultadosConsulta.getString("nrc"));
                
                listaReferencias.add(referencia);
                
            } 

        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al consultar la lista de ReferenciaCurso.", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado en recuperar los datos, " + 
                "intente de nuevo por favor.", e);
            
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al consultar ReferenciaCurso. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo recuperar la lista de NRCs, " + 
                "intente de nuevo por favor.", e);
        }

        return listaReferencias;

    }

    public ReferenciaCurso consultarReferenciaCursoPorId(int idReferenciaCurso) throws OperacionesDeDaoExcepcion {

        ReferenciaCurso referenciaEncontrada = null;
        
        String consultaSQL = "SELECT nrc FROM referencia_curso WHERE idReferenciaCurso = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {
            
            consultaPreparada.setInt(1, idReferenciaCurso);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {

                referenciaEncontrada = new ReferenciaCurso();
                referenciaEncontrada.setIdReferenciaCurso(idReferenciaCurso);
                referenciaEncontrada.setNrc(resultadosConsulta.getString("nrc"));
                
            } 
            
            resultadosConsulta.close();

        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al consultar ReferenciaCurso por ID. idReferenciaCurso: " + idReferenciaCurso, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor.", e);
            
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al consultar ReferenciaCurso por ID. " +
                "idReferenciaCurso: " + idReferenciaCurso + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo consultar el curso, " + 
                "intente de nuevo por favor.", e);
        }

        return referenciaEncontrada;

    }
    
}
