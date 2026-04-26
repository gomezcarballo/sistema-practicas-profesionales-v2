/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.interfacesdao.IParticipacionDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class ParticipacionDAO implements IParticipacionDAO{
    
    @Override
    public boolean insertarParticipacion(int idPracticante, int idProyecto) throws OperacionesDeDaoExcepcion {

        boolean registroExitoso = false;

        String consultaSQL = """
                INSERT INTO Participacion (Practicante_idUsuario, Proyecto_idProyecto)
                VALUES (?, ?)""";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idPracticante);
            consultaPreparada.setInt(2, idProyecto);

            int filasAfectadas = consultaPreparada.executeUpdate();

            if (filasAfectadas > 0) {
                registroExitoso = true;
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return registroExitoso;
    }

    @Override
    public boolean eliminarParticipacion(int idPracticante, int idProyecto) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = """
                DELETE FROM Participacion
                WHERE Practicante_idUsuario = ? AND Proyecto_idProyecto = ?
                """;

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idPracticante);
            consultaPreparada.setInt(2, idProyecto);

            int filasAfectadas = consultaPreparada.executeUpdate();

            if (filasAfectadas > 0) {
                eliminacionExitosa = true;
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return eliminacionExitosa;
    }

    @Override
    public List<Integer> consultarProyectosPorPracticante(int idPracticante) throws OperacionesDeDaoExcepcion {

        List<Integer> proyectos = new ArrayList<>();

        String consultaSQL = """
                SELECT Proyecto_idProyecto FROM Participacion WHERE Practicante_idUsuario = ?
                """;

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idPracticante);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            while (resultadosConsulta.next()) {
                proyectos.add(resultadosConsulta.getInt("Proyecto_idProyecto"));
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return proyectos;
    }

    @Override
    public List<Integer> consultarPracticantesPorProyecto(int idProyecto) throws OperacionesDeDaoExcepcion {

        List<Integer> practicantes = new ArrayList<>();

        String consultaSQL = """
                SELECT Practicante_idUsuario FROM Participacion WHERE Proyecto_idProyecto = ?
                """;

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idProyecto);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            while (resultadosConsulta.next()) {
                practicantes.add(resultadosConsulta.getInt("Practicante_idUsuario"));
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return practicantes;
    }
}
