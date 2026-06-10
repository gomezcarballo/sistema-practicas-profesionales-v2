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
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.util.logging.Level;

import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.EncabezadoReporte;
import spp.logicadenegocio.interfacesdao.IEncabezadoReporteDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

public class EncabezadoReporteDAO implements IEncabezadoReporteDAO{

    
    @Override
    public EncabezadoReporte recuperarDatosReporte(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        EncabezadoReporte encabezado = new EncabezadoReporte();

        String consultaSQL = """
            SELECT * FROM VistaDatosReporte
            WHERE idPracticante = ?
            """;

        try( Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL) ){
            
            consultaPreparada.setInt(1, idPracticante);
           
            try (ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

                if (resultadoConsulta.next()) {

                    encabezado.setProyecto(resultadoConsulta.getString("nombreProyecto"));
                    encabezado.setNombreResponsableProyecto(resultadoConsulta.getString("nombreResponsable"));
                    encabezado.setMetodologia(resultadoConsulta.getString("metodologia"));
                    encabezado.setObjetivoGeneral(resultadoConsulta.getString("objetivoGeneral"));
                    encabezado.setOrganizacion(resultadoConsulta.getString("nombreOrganizacion"));
                    encabezado.setNrc(resultadoConsulta.getString("nrc"));
                    encabezado.setProfesor(resultadoConsulta.getString("nombreProfesor"));
                    encabezado.setAlumno(resultadoConsulta.getString("nombrePracticante"));
                    
                }
            }
            
        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "La vista 'VistaDatosReporte' no existe o tiene columnas faltantes. " +
                "ID Practicante consultado: " + idPracticante, e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar VistaDatosReporte. " +
                "ID Practicante: " + idPracticante, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar VistaDatosReporte. " +
                "ID Practicante: " + idPracticante + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo recuperar la información del reporte, intente de nuevo", e);
        }

        return encabezado;
    }
    
}
