/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.IndicadorReporte;
import spp.logicadenegocio.interfacesdao.IReporteIndicadoresDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class ReporteIndicadoresDAO implements IReporteIndicadoresDAO {
    
    @Override
    public List<IndicadorReporte> obtenerIndicadores() throws OperacionesDeDaoExcepcion {

        List<IndicadorReporte> indicadores = new ArrayList<>();

        String consultaSQL = "{CALL sp_obtener_indicadores_practicantes()}";

        try (Connection conexion = ConexionBD.getConexion();
             CallableStatement consultaPreparada = conexion.prepareCall(consultaSQL);
             ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            while (resultadoConsulta.next()) {
                
                IndicadorReporte indicador = new IndicadorReporte();
                
                indicador.setIndicador(resultadoConsulta.getString("indicador"));
                indicador.setValor(resultadoConsulta.getInt("valor"));
                
                indicadores.add(indicador);
            }
            
         } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "El procedimiento almacenado 'sp_obtener_indicadores_practicantes' no existe o no es accesible", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al obtener indicadores de reporte", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al obtener indicadores de reporte. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron obtener los indicadores, " + 
                "intente de nuevo por favor", e);
        }
        
        return indicadores;
    }
    
}
