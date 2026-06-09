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
import spp.logicadenegocio.clasesdto.Documento;
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
            consultaPreparada.setInt(4, documento.getIdUsuario());
            
            int filasAfectadas = consultaPreparada.executeUpdate();
            
            if(filasAfectadas == 0){
                 throw new OperacionesDeDaoExcepcion("No se registro el documento en la base de datos.");
            }
            
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
                documento.setIdUsuario(resultadosConsulta.getInt("Usuario_idUsuario"));
                
            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }

    return documento;
    
    }
    
    @Override
    public boolean eliminarDocumento(String nombre) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Documento WHERE nombre = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, nombre);

            int filasAfectadas = consultaPreparada.executeUpdate();

            if(filasAfectadas > 0) {
                eliminacionExitosa = true;
            }

        } catch(SQLException e) {

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
        }

        return eliminacionExitosa;
    } 
    
    public List<Documento> recuperarDocumentosPorPracticante(int idUsuarioPracticante) throws OperacionesDeDaoExcepcion {
        
        List<Documento> listaDocumentos = new ArrayList<>();
        
        String consultaSQL = "SELECT idDocumento, nombre, tipo, ruta, Usuario_idUsuario FROM documento WHERE Usuario_idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuarioPracticante);

            try (ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {
                
                while (resultadoConsulta.next()) {
                    
                    Documento documento = new Documento();
                    
                    documento.setIdDocumento(resultadoConsulta.getInt("idDocumento"));
                    documento.setNombre(resultadoConsulta.getString("nombre"));
                    documento.setTipo(resultadoConsulta.getString("tipo"));
                    documento.setRuta(resultadoConsulta.getString("ruta"));
                    documento.setIdUsuario(resultadoConsulta.getInt("Usuario_idUsuario"));

                    listaDocumentos.add(documento);
                }
            }
            
        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos.", e);
        }

        return listaDocumentos;
    }
    
}
