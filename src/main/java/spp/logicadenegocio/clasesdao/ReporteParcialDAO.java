/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;
/**
 *
 * @author Luz Fernanda H J
 */

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.logicadenegocio.interfacesdao.IReporteParcialDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

public class ReporteParcialDAO implements IReporteParcialDAO{
    
    @Override
    public ReporteParcial recuperarDatosReporte(int idPRacticante) throws OperacionesDeDaoExcepcion {
        
        ReporteParcial reporteParcial = new ReporteParcial();

        String consultaSQL = """
            SELECT * FROM VistaDatosReporteParcial 
            WHERE idPracticante = ?
            """;

        try( Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL) ){
            
            consultaPreparada.setInt(1, idPRacticante);
           
            try (ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

                if (resultadoConsulta.next()) {

                    reporteParcial.setProyecto(resultadoConsulta.getString("nombreProyecto"));
                    reporteParcial.setNombreResponsable(resultadoConsulta.getString("nombreResponsable"));
                    reporteParcial.setMetodologia(resultadoConsulta.getString("metodologia"));
                    reporteParcial.setObjetivoGeneral(resultadoConsulta.getString("objetivoGeneral"));
                    reporteParcial.setOrganizacion(resultadoConsulta.getString("nombreOrganizacion"));
                    reporteParcial.setNrc(resultadoConsulta.getString("nrc"));
                    reporteParcial.setProfesor(resultadoConsulta.getString("nombreProfesor"));
                    reporteParcial.setAlumno(resultadoConsulta.getString("nombrePracticante"));
                    
                }
            }
            
        }catch (SQLException e){

             throw new OperacionesDeDaoExcepcion("No se puede acceder a la base de datos.",e);
        }

        return reporteParcial;
    }
}