/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.IndicadorReporte;
import spp.logicadenegocio.interfacesdao.IReporteIndicadoresDAO;
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
            
        }catch(SQLException e){
            
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos"); 
            
        }

        return indicadores;
    }
    
}
