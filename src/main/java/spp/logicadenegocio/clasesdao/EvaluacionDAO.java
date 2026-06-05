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
import spp.logicadenegocio.clasesdto.Evaluacion;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.interfacesdao.IEvaluacionDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class EvaluacionDAO implements IEvaluacionDAO{

    @Override
    public void insertarEvaluacion(Evaluacion evaluacion) throws OperacionesDeDaoExcepcion {
        
        String consultaSQL = "INSERT INTO Evaluacion (nrc, periodo, calificacionFinal, Profesor_idUsuario, "
                + "Practicante_idUsuario) VALUES (?, ?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1, evaluacion.getNrc());
            consultaPreparada.setString(2, evaluacion.getPeriodo());
            consultaPreparada.setDouble(3, evaluacion.getCalificacionFinal());
            consultaPreparada.setInt(4, evaluacion.getProfesor().getIdUsuario());
            consultaPreparada.setInt(5, evaluacion.getPracticante().getIdUsuario());

            int filasAfectadas = consultaPreparada.executeUpdate();
            
            if (filasAfectadas == 0) {
                throw new OperacionesDeDaoExcepcion("Fallo al guardar: No se reflejaron los cambios en la base de datos");               
            }
            
        }catch( SQLException e ){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
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

                Profesor profesor = new Profesor();
                profesor.setIdUsuario(resultadosConsulta.getInt("Profesor_idUsuario"));
                evaluacion.setProfesor(profesor);
                
                Practicante practicante = new Practicante();
                practicante.setIdUsuario(resultadosConsulta.getInt("Practicante_idUsuario"));
                evaluacion.setPracticante(practicante);
            }

            conexion.close();

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return evaluacion;
        
    }
    
}
