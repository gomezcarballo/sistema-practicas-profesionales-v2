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
    public Documento consultarDocumento(int idDocumento) throws OperacionesDeDaoExcepcion{
        
        Documento documento = null;
        
        String consultaSQL = "SELECT * FROM Documento WHERE idDocumento = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {
          
            consultaPreparada.setInt(1, idDocumento);

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
                "Timeout al consultar el docuemnto. idDocumento del documento: " + idDocumento , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al consultar el documento. id del documento: " + 
                idDocumento + 
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
            consultaPreparada.setString(2, tipoDocumento.toString());

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
            consultaPreparada.setString(2, tipoDocumento.toString());

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
    
    @Override
    public int contarDocumentosCalificadosPorTipo(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion {
        
        int cantidadCalificados = 0;

        String consultaSQL = "SELECT COUNT(d.idDocumento) FROM documento d " +
                             "INNER JOIN evaluacion e ON d.idDocumento = e.Documento_idDocumento " +
                             "WHERE d.Usuario_idUsuario = ? AND d.tipo = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idPracticante);
            consultaPreparada.setString(2, tipoDocumento.toString());

            try (ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

                if (resultadosConsulta.next()) {
                    cantidadCalificados = resultadosConsulta.getInt(1);
                }
                
            }

        } catch(SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al contar documentos calificados. ID: " + idPracticante, e);
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo", e);
            
        } catch(SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al contar documentos calificados. ID: " + idPracticante, e);
            throw new OperacionesDeDaoExcepcion("No se pudieron verificar las calificaciones, intente de nuevo", e);
            
        }

        return cantidadCalificados;
    }

    @Override
    public boolean verificarDocumentoAprobado(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion {
        
        boolean estaAprobado = false;
        
        String consultaSQL = "SELECT 1 FROM documento WHERE Usuario_idUsuario = ? AND tipo = ? AND estado = 'Aprobado' LIMIT 1";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idPracticante);
            consultaPreparada.setString(2, tipoDocumento.toString());

            try (ResultSet resultados = consultaPreparada.executeQuery()) {
                if (resultados.next()) {
                    estaAprobado = true;
                }
            }

        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, "Timeout al verificar aprobación de documento. ID: " + idPracticante, e);
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Error al verificar aprobación de documento. ID: " + idPracticante, e);
            throw new OperacionesDeDaoExcepcion("No se pudo verificar el estado del documento", e);
            
        }

        return estaAprobado;
        
    }
    
    public boolean verificarDocumentoCalificado(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion {
    
    boolean estaCalificado = false;

    String consultaSQL = "SELECT COUNT(d.idDocumento) FROM documento d " +
                         "INNER JOIN evaluacion e ON d.idDocumento = e.Documento_idDocumento " +
                         "WHERE d.Usuario_idUsuario = ? AND d.tipo = ?";

    try (Connection conexion = ConexionBD.getConexion();
         PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

        consultaPreparada.setInt(1, idPracticante);
        consultaPreparada.setString(2, tipoDocumento.toString());

        try (ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

            if (resultadosConsulta.next()) {
                int cantidad = resultadosConsulta.getInt(1);
                if (cantidad > 0) {
                    estaCalificado = true;
                }
            }
            
        }

    } catch(SQLTimeoutException e) {
        
        RegistroErrores.registrarError(Level.WARNING, 
            "Timeout al verificar si el documento está calificado. ID: " + idPracticante, e);
        throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo", e);
        
    } catch(SQLException e) {
        
        RegistroErrores.registrarError(Level.SEVERE, 
            "Error al verificar documento calificado. ID: " + idPracticante, e);
        throw new OperacionesDeDaoExcepcion("No se pudo verificar la calificación, intente de nuevo", e);
        
    }

    return estaCalificado;
}

    @Override
    public boolean verificarDocumentoRechazado(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion {

        boolean estaAprobado = false;

        String consultaSQL = "SELECT 1 FROM documento WHERE Usuario_idUsuario = ? AND tipo = ? AND estado = 'Rechazado' LIMIT 1";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idPracticante);
            consultaPreparada.setString(2, tipoDocumento.toString());

            try (ResultSet resultados = consultaPreparada.executeQuery()) {
                if (resultados.next()) {
                    estaAprobado = true;
                }
            }

        } catch (SQLTimeoutException e) {

            RegistroErrores.registrarError(Level.WARNING, "Timeout al verificar el estado rechazado del documento. ID: " + idPracticante, e);
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);

        } catch (SQLException e) {

            RegistroErrores.registrarError(Level.SEVERE, "Error al verificar el estado rechazado del documento. ID: " + idPracticante, e);
            throw new OperacionesDeDaoExcepcion("No se pudo verificar el estado del documento", e);

        }

        return estaAprobado;

    }

    @Override
    public boolean actualizarEstadoDocumento(Documento documento) throws OperacionesDeDaoExcepcion {
        
        boolean actualizacionExitosa = false;
        
        String consultaSQL = "UPDATE Documento SET estado = ? WHERE idDocumento = ?";
        
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {
                        
            consultaPreparada.setString(1, documento.getEstadoDocumento());
            consultaPreparada.setInt(2,documento.getIdDocumento());

            actualizacionExitosa = consultaPreparada.executeUpdate() > 0;
        
        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al actualizar el documento. IdDocumento: " + documento.getIdDocumento(), e);

            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
        
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError de base de datos al actualizar el estado del documento. " + 
                "idDocumento: " + documento.getIdDocumento() +
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al inactivar el administrador, " + 
                "intente de nuevo más tarde", e);
        }
        
         return actualizacionExitosa;
    }

    @Override 
    public Documento buscarDocumentoPorUsuarioYTipo(int idUsuario, String tipo) throws OperacionesDeDaoExcepcion {
        
        Documento documentoEncontrado = null;

        String consultaSQL = "SELECT idDocumento, nombre, tipo, ruta, Usuario_idUsuario, estado " +
                    "FROM Documento WHERE Usuario_idUsuario = ? AND tipo = ? LIMIT 1";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {
            
            consultaPreparada.setInt(1, idUsuario);
            consultaPreparada.setString(2, tipo);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {

                documentoEncontrado = new Documento();

                documentoEncontrado.setIdDocumento(resultadosConsulta.getInt("idDocumento"));
                documentoEncontrado.setNombre(resultadosConsulta.getString("nombre"));
                documentoEncontrado.setTipo(resultadosConsulta.getString("tipo"));
                documentoEncontrado.setRuta(resultadosConsulta.getString("ruta")); 
                documentoEncontrado.setIdUsuario(resultadosConsulta.getInt("Usuario_idUsuario"));
                documentoEncontrado.setEstadoDocumento(resultadosConsulta.getString("estado"));
            }

            return documentoEncontrado;

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar el docuemnto por idUsuario y tipo. idUsuario:  " + idUsuario + " ,el tipo: " + tipo , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al consultar el documento. El idUsuario: " + 
                idUsuario + " ,tipo del documento: " + tipo + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al consultar el documento, intente de nuevo más tarde", e);
        }

    }     
    
    @Override
    public boolean eliminarDocumentoPorId(int idDocumento) throws OperacionesDeDaoExcepcion {
        
        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Documento WHERE idDocumento = ?";

        try (Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idDocumento);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;
            
        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al eliminar documento. " +
                "ID Documento: " + idDocumento + 
                " - Posiblemente tiene registros relacionados (aprobaciones, etc.)", e);
            
            throw new OperacionesDeDaoExcepcion("No se puede eliminar el documento porque " +    
                "tiene información asociada", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar documento. ID: " + idDocumento, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al eliminar documento. " +
                "ID: " + idDocumento + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar el documento, " +  
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    }

}
