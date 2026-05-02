/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.CriterioEvaluacion;
import spp.logicadenegocio.clasesdto.DetalleCalificacion;
import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.logicadenegocio.interfacesdao.IDetalleCalificacionDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class DetalleCalificacionDAO implements IDetalleCalificacionDAO{

    @Override
    public void insertarDetalleCalificacion(DetalleCalificacion detalleCalificacion) throws OperacionesDeDaoExcepcion {
        
        String consultaSQL = "INSERT INTO DetalleCalificacion (Evaluacion_idEvaluacion, "
                + "CriterioEvaluacion_idCriterioEvaluacion, calificacion, observacion) VALUES (?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setInt(1, detalleCalificacion.getEvaluacion().getIdEvaluacion());
            consultaPreparada.setInt(2, detalleCalificacion.getCriterioEvaluacion().getIdCriterioEvaluacion());
            consultaPreparada.setDouble(3, detalleCalificacion.getCalificacion());
            consultaPreparada.setString(4, detalleCalificacion.getObservacion());           

            int filasAfectadas = consultaPreparada.executeUpdate();
            
            if (filasAfectadas == 0) {
                throw new OperacionesDeDaoExcepcion("Fallo al guardar: No se reflejaron los cambios en la base de datos");               
            }
            
        }catch( SQLException e ){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
    }

    @Override
    public DetalleCalificacion consultarDetalleCalificacion(int idDetalleCalificacion) throws OperacionesDeDaoExcepcion {
        
        DetalleCalificacion detalleCalificacion = null;
        
        String consultaSQL = "SELECT * FROM DetalleCalificacion WHERE idDetalleCalificacion = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
          
            consultaPreparada.setInt(1, idDetalleCalificacion);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {
                detalleCalificacion = new DetalleCalificacion();

                detalleCalificacion.setCalificacion(resultadosConsulta.getDouble("calificacion"));
                detalleCalificacion.setObservacion(resultadosConsulta.getString("observacion"));

                Evaluacion evaluacion = new Evaluacion();
                evaluacion.setIdEvaluacion(resultadosConsulta.getInt("Evaluacion_idEvaluacion"));
                detalleCalificacion.setEvaluacion(evaluacion);
                
                CriterioEvaluacion criterioEvaluacion = new CriterioEvaluacion();
                criterioEvaluacion.setIdCriterioEvaluacion
                (resultadosConsulta.getInt("CriterioEvaluacion_idCriterioEvaluacion"));
                detalleCalificacion.setCriterioEvaluacion(criterioEvaluacion);
            }

            conexion.close();

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return detalleCalificacion;
        
    }
    
    
    
}
