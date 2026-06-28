/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import spp.accesoadatos.ConexionBD;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.logicadenegocio.interfacesdao.IDocumentoDAO;
import spp.utilerias.bitacora.RegistroErrores;
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
            
            if(filasAfectadas > 0){
                registroExitoso = true;
            }
            
        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al insertar documento. " +
                "Nombre: " + documento.getNombre() + 
                ", Ruta: " + documento.getRuta() + 
                ", Usuario ID: " + documento.getIdUsuario() + 
                " - Posible duplicado o FK inválida", e);
            
            throw new OperacionesDeDaoExcepcion("El documento ya existe en el sistema", e);
            
         } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar documento. " +
                "Nombre: " + documento.getNombre() + 
                ", Usuario ID: " + documento.getIdUsuario(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar documento. " +
                "Nombre: " + documento.getNombre() + 
                ", Tipo: " + documento.getTipo() + 
                ", Ruta: " + documento.getRuta() + 
                ", Usuario ID: " + documento.getIdUsuario(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos del documento no son válidos, revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al insertar documento. " +
                "Nombre: " + documento.getNombre() + 
                ", Tipo: " + documento.getTipo() + 
                ", Ruta: " + documento.getRuta() + 
                ", Usuario ID: " + documento.getIdUsuario() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al guardar el documento, intente de nuevo más tarde", e);
        }
        return registroExitoso;
    
    }

    @Override
    public Documento consultarDocumento(String nombreDocumento) throws OperacionesDeDaoExcepcion{
        
        Documento documento = null;
        
        String consultaSQL = "SELECT * FROM Documento WHERE nombre = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
          
            consultaPreparada.setString(1, nombreDocumento);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {
                documento = new Documento();

                documento.setIdDocumento(resultadosConsulta.getInt("idDocumento"));
                documento.setNombre(resultadosConsulta.getString("nombre"));
                documento.setTipo(resultadosConsulta.getString("tipo"));
                documento.setRuta(resultadosConsulta.getString("ruta")); 
                documento.setIdUsuario(resultadosConsulta.getInt("Usuario_idUsuario"));
                
            }

         } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar el docuemnto. Nombre del documento: " + nombreDocumento , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al consultar el documento. Nombre del documento: " + 
                nombreDocumento + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al consultar el documento, intente de nuevo más tarde", e);
        }

        return documento;
    
    }
    
    @Override
    public boolean eliminarDocumento(String nombreDocumento) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Documento WHERE nombre = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, nombreDocumento);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;


        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar el documento. Nombre del documento: " + nombreDocumento, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al eliminar el documento. Nombre del documento" +
                nombreDocumento + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar el documento, intente de nuevo más tarde", e);
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
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar los documentos." , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al consultar los documenos. " + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al consultar los documentos, intente de nuevo más tarde", e);
        }

        return listaDocumentos;
    }
    
    @Override
    public int contarDocumentosPorTipo(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion {
        
        int cantidadDocumentos = 0;

        String consultaSQL = "SELECT COUNT(*) FROM documento WHERE Usuario_idUsuario = ? AND tipo = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idPracticante);
            consultaPreparada.setString(2, tipoDocumento.getDescripcion());

            try (ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

                if (resultadosConsulta.next()) {
                    cantidadDocumentos = resultadosConsulta.getInt(1);
                }
            }

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al contar documentos. ID Practicante: " + idPracticante + 
                ", Tipo: " + tipoDocumento.name(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al contar documentos. " +
                "ID Practicante: " + idPracticante + 
                ", Tipo: " + tipoDocumento.name() +
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron contar los documentos, " + 
                "intente de nuevo", e);
        }

        return cantidadDocumentos;
    }
    
    @Override
    public boolean verificarExistenciaDocumento(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion {
        
        boolean existeDocumento = false;

        String consultaSQL = "SELECT idDocumento FROM documento WHERE Usuario_idUsuario = ? AND tipo = ? LIMIT 1";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idPracticante);
            consultaPreparada.setString(2, tipoDocumento.getDescripcion());

            try (ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

                if (resultadosConsulta.next()) {
                    existeDocumento = true;
                }
            }

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al verificar existencia de documento. ID Practicante: " + idPracticante + 
                ", Tipo: " + tipoDocumento.name(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al verificar existencia de documento. " +
                "ID Practicante: " + idPracticante + 
                ", Tipo: " + tipoDocumento.name() +
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo verificar el documento, " + 
                "intente de nuevo", e);
        }

        return existeDocumento;
    }
    
}
