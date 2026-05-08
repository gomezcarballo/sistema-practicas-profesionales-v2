/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import spp.accesoadatos.ConexionBD;
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
    
}
