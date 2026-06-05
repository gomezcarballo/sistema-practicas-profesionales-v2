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
import spp.logicadenegocio.interfacesdao.ICriterioEvaluacionDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class CriterioEvaluacionDAO implements ICriterioEvaluacionDAO{
    
    @Override
    public void insertarCriterioEvaluacion(CriterioEvaluacion criterio) throws OperacionesDeDaoExcepcion{ 
        
        String consultaSQL = "INSERT INTO CriterioEvaluacion (nombreCriterio, porcentajeCalificacion) VALUES (?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1, criterio.getNombreCriterio());
            consultaPreparada.setDouble(2, criterio.getPorcentajeCalificacion());
            int filasAfectadas = consultaPreparada.executeUpdate();
            
            if (filasAfectadas == 0) {
                throw new OperacionesDeDaoExcepcion("Fallo al guardar: No se reflejaron los cambios en la base de datos");               
            }
            
        }catch( SQLException e ){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
    }

    @Override
    public CriterioEvaluacion consultarCriterioEvaluacion(String nombreCriterio) throws OperacionesDeDaoExcepcion{
        
        CriterioEvaluacion criterio = null;
        
        String consultaSQL = "SELECT idCriterioEvaluacion, nombreCriterio, porcentajeCalificacion"
                + "FROM CriterioEvaluacion WHERE nombreCriterio = ?";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1, nombreCriterio);
            
            try(ResultSet resultadosConsulta = consultaPreparada.executeQuery();){
                if(resultadosConsulta.next() ){
                    criterio = new CriterioEvaluacion();
                    criterio.setIdCriterioEvaluacion(resultadosConsulta.getInt("idCriterioEvaluacion"));
                    criterio.setNombreCriterio(resultadosConsulta.getString("nombreCriterio"));
                    criterio.setPorcentajeCalificacion(resultadosConsulta.getDouble("porcentajeCalificacion"));
                }
            }
            
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
    return criterio;
    }
    
}
