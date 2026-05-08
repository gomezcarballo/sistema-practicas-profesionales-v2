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
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.interfacesdao.ISolicitudDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class SolicitudDAO implements ISolicitudDAO{

    @Override
    public void guardarSolicitud(int idUsuario, int idProyecto) throws OperacionesDeDaoExcepcion {
        
        String consultaSQL = "INSERT INTO SolicitudProyecto (Practicante_idUsuario, Proyecto_idProyecto) "
        + "VALUES (?, ?)";

        try(Connection conexion = ConexionBD.getConexion();
           PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            consultaPreparada.setInt(2, idProyecto);

            consultaPreparada.executeUpdate();

        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }
        
    }
    
    @Override
    public List<Proyecto> obtenerProyectosSolicitados(int idUsuario)throws OperacionesDeDaoExcepcion {

        List<Proyecto> proyectos = new ArrayList<>();

        String consultaSQL = "SELECT p.idProyecto, "
                            + "p.nombre, "
                            + "p.descripcion, "
                            + "p.nombreResponsable, "
                            + "p.cupoMaximo "
                            + "FROM SolicitudProyecto sp "
                            + "INNER JOIN Proyecto p "
                            + "ON sp.Proyecto_idProyecto = p.idProyecto "
                            + "WHERE sp.Practicante_idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            try (ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

                while (resultadosConsulta.next()) {

                    Proyecto proyecto = new Proyecto();

                    proyecto.setIdProyecto(resultadosConsulta.getInt("idProyecto"));

                    proyecto.setNombre(resultadosConsulta.getString("nombre"));

                    proyecto.setDescripcion(resultadosConsulta.getString("descripcion"));

                    proyecto.setNombreResponsable( resultadosConsulta.getString("nombreResponsable"));

                    proyecto.setCupoMaximo(resultadosConsulta.getInt("cupoMaximo"));

                    proyectos.add(proyecto);
                }
            }

        } catch (SQLException e) {

            throw new OperacionesDeDaoExcepcion( "No se puede conectar a la base de datos", e);
        }

        return proyectos;
    }
    
}
