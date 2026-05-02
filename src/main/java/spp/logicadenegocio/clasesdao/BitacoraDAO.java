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
import spp.logicadenegocio.clasesdto.Bitacora;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.interfacesdao.IBitacoraDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class BitacoraDAO implements IBitacoraDAO{
    
    @Override
    public boolean insertarBitacora(Bitacora bitacora)throws OperacionesDeDaoExcepcion{
        
        boolean registroExitoso = false;
        
        String consultaSQL = "INSERT INTO Bitacora (actividadRealizada, seccionUtilizada, detalle, "
                + "fecha, Usuario_idUsuario) VALUES (?, ?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {

            consultaPreparada.setString(1, bitacora.getActividadRealizada());
            consultaPreparada.setString(2, bitacora.getSeccionUtilizada());
            consultaPreparada.setString(3, bitacora.getDetalle());
            java.sql.Date fechaParaBD = java.sql.Date.valueOf(bitacora.getFecha());
            consultaPreparada.setDate(4, fechaParaBD);
            consultaPreparada.setInt(5, bitacora.getUsuario().getIdUsuario());
            
            consultaPreparada.executeUpdate();
            
            registroExitoso = true;

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
    return registroExitoso;
    
    }
       
    @Override
   public Bitacora consultarBitacora(int idBitacora)throws OperacionesDeDaoExcepcion {
        
        Bitacora bitacora = null;
        
        String consultaSQL = "SELECT * FROM Bitacora WHERE idBitacora = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
          
            consultaPreparada.setInt(1, idBitacora);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {
                bitacora = new Bitacora();

                bitacora.setActividadRealizada(resultadosConsulta.getString("actividadRealizada"));
                bitacora.setSeccionUtilizada(resultadosConsulta.getString("seccionUtilizada"));
                bitacora.setDetalle(resultadosConsulta.getString("detalle"));
                bitacora.setFecha(resultadosConsulta.getDate("fecha").toLocalDate());

                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultadosConsulta.getInt("Usuario_idUsuario"));

                bitacora.setUsuario(usuario);
            }

            conexion.close();

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return bitacora;
        
    }
}
