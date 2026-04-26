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
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.interfacesdao.IDocumentoDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class DocumentoDAO implements IDocumentoDAO {

    @Override
    public boolean insertarDocumento(Documento documento) throws OperacionesDeDaoExcepcion{
        
        boolean registroExitoso = false;
        
        String consultaSQL = "INSERT INTO Documento (nombre, tipo, ruta, Usuario_idUsuario) VALUES (?, ?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {

            consultaPreparada.setString(1, documento.getNombre());
            consultaPreparada.setString(2, documento.getTipo());
            consultaPreparada.setString(3, documento.getRuta());
            consultaPreparada.setInt(4, documento.getUsuario().getIdUsuario());
            
            consultaPreparada.executeUpdate();
            
            registroExitoso = true;

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
    return registroExitoso;
    
    }

    @Override
    public Documento consultarDocumento(String nombre) throws OperacionesDeDaoExcepcion{
        
        Documento documento = null;
        
        String consultaSQL = "SELECT * FROM Documento WHERE nombre = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
          
            consultaPreparada.setString(1, nombre);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {
                documento = new Documento();

                documento.setIdDocumento(resultadosConsulta.getInt("idDocumento"));
                documento.setNombre(resultadosConsulta.getString("nombre"));
                documento.setTipo(resultadosConsulta.getString("tipo"));
                documento.setRuta(resultadosConsulta.getString("ruta")); 
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(resultadosConsulta.getInt("Usuario_idUsuario"));
                
                documento.setUsuario(usuario);
            }

            conexion.close();

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return documento;
    
    }
    
}
