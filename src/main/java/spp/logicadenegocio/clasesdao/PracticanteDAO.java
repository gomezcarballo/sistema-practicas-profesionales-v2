package spp.logicadenegocio.clasesdao;

import java.sql.CallableStatement;
import spp.accesoadatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLDataException;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.SQLSyntaxErrorException;
import java.sql.SQLTimeoutException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.UsuarioEncontrado;
import spp.logicadenegocio.interfacesdao.IPracticanteDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */

public class PracticanteDAO extends UsuarioDAO implements IPracticanteDAO{
    
    @Override
    public boolean insertarPracticante(Practicante practicante)throws OperacionesDeDaoExcepcion{
         
        boolean registroExitoso = false;
        
        String consultaSQL = "INSERT INTO Practicante (idUsuario, matricula, genero, "
                + "lenguaIndigena, fechaNacimiento, nrcAsignado) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {

            consultaPreparada.setInt(1, practicante.getIdUsuario());
            consultaPreparada.setString(2, practicante.getMatricula());
            consultaPreparada.setString(3, practicante.getGenero());
            consultaPreparada.setBoolean(4, practicante.getHablaLenguaIndigena());
            java.sql.Date fechaParaBD = java.sql.Date.valueOf(practicante.getFechaNacimiento());
            consultaPreparada.setDate(5, fechaParaBD);
            consultaPreparada.setString(6, practicante.getNrcAsignado());
            
            registroExitoso = consultaPreparada.executeUpdate() > 0;

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al insertar practicante. " +
                "ID Usuario: " + practicante.getIdUsuario() + 
                ", Matrícula: " + practicante.getMatricula(), e);
            
            throw new OperacionesDeDaoExcepcion("El practicante ya está registrado " + 
                "o la matrícula está duplicada", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar practicante. Matrícula: " + practicante.getMatricula(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar practicante. " +
                "ID Usuario: " + practicante.getIdUsuario() + 
                ", Matrícula: " + practicante.getMatricula() + 
                ", Fecha Nacimiento: " + practicante.getFechaNacimiento(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos del practicante no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al insertar practicante. " +
                "ID Usuario: " + practicante.getIdUsuario() + 
                ", Matrícula: " + practicante.getMatricula() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al registrar el practicante, " + 
                "intente de nuevo más tarde", e);
        }
        return registroExitoso;
    
    }
     
    @Override
    public List<Practicante> consultarPracticantes()throws OperacionesDeDaoExcepcion {

        List<Practicante> practicantesConsultados = new ArrayList<>();

        String consultaSQL = "SELECT u.idUsuario, u.nombre, u.apellidoPaterno, "
            + "u.apellidoMaterno, u.correoInstitucional, "
            + "p.matricula, p.genero, p.lenguaIndigena, "
            + "p.fechaNacimiento, p.nrcAsignado "
            + "FROM Practicante p "
            + "INNER JOIN Usuario u "
            + "ON p.idUsuario = u.idUsuario "
            + "WHERE u.estado = 1";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            while(resultadosConsulta.next()) {

                Practicante practicante = new Practicante();
                practicante.setIdUsuario(resultadosConsulta.getInt("idUsuario"));
                practicante.setNombre(resultadosConsulta.getString("nombre"));
                practicante.setApellidoPaterno(resultadosConsulta.getString("apellidoPaterno"));
                practicante.setApellidoMaterno(resultadosConsulta.getString("apellidoMaterno"));
                practicante.setCorreoInstitucional(resultadosConsulta.getString("correoInstitucional"));
                practicante.setMatricula(resultadosConsulta.getString("matricula"));
                practicante.setGenero(resultadosConsulta.getString("genero"));
                practicante.setHablaLenguaIndigena(resultadosConsulta.getBoolean("lenguaIndigena"));
                practicante.setFechaNacimiento(resultadosConsulta.getDate("fechaNacimiento").toLocalDate());
                practicante.setNrcAsignado(resultadosConsulta.getString("nrcAsignado"));
                
                practicantesConsultados.add(practicante);
            }

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al consultar practicantes. " +
                "Verificar tablas: Practicante, Usuario y sus columnas", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar practicantes", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar practicantes. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
        throw new OperacionesDeDaoExcepcion("No se pudieron consultar los practicantes, " + 
            "intente de nuevo", e);
        }

        return practicantesConsultados;
    
    }

    @Override
    public boolean inactivarPracticante(int idUsuarioPracticante)throws OperacionesDeDaoExcepcion {
        
        boolean inactivacionExitosa = false;

        String consultaSQL = "UPDATE Usuario SET estado = 0 WHERE idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuarioPracticante);

            inactivacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al inactivar practicante. ID Usuario: " + idUsuarioPracticante, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al inactivar practicante. " +
                "ID Usuario: " + idUsuarioPracticante + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al inactivar el practicante, " + 
                "intente de nuevo más tarde", e);
        }
        return inactivacionExitosa; 

    }

    @Override
    public List<Practicante> consultarPracticantesConSolicitudes() throws OperacionesDeDaoExcepcion {

        List<Practicante> listaPracticantes = new ArrayList<>();

        String consultaSQL =
        "SELECT DISTINCT u.idUsuario AS idUsuario, u.nombre, u.apellidoPaterno, " +
        "u.apellidoMaterno, u.correoInstitucional, " +
        "p.matricula, p.fechaNacimiento, p.genero, p.lenguaIndigena, p.nrcAsignado " +
        "FROM Practicante p " +
        "INNER JOIN Usuario u ON p.idUsuario = u.idUsuario " +
        "INNER JOIN solicitudProyecto sp ON p.idUsuario = sp.Practicante_idUsuario";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
             ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

            while (resultadosConsulta.next()) {

                Practicante practicante = new Practicante();

                practicante.setIdUsuario(resultadosConsulta.getInt("idUsuario"));
                practicante.setNombre(resultadosConsulta.getString("nombre"));
                practicante.setApellidoPaterno(resultadosConsulta.getString("apellidoPaterno"));
                practicante.setApellidoMaterno(resultadosConsulta.getString("apellidoMaterno"));
                practicante.setCorreoInstitucional(resultadosConsulta.getString("correoInstitucional"));
                practicante.setMatricula(resultadosConsulta.getString("matricula"));
                practicante.setGenero(resultadosConsulta.getString("genero"));
                practicante.setHablaLenguaIndigena(resultadosConsulta.getBoolean("lenguaIndigena"));
                practicante.setNrcAsignado(resultadosConsulta.getString("nrcAsignado"));
                practicante.setFechaNacimiento(resultadosConsulta.getDate("fechaNacimiento").toLocalDate());

                listaPracticantes.add(practicante);
                
            }

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al consultar practicantes con solicitudes. " +
                "Verificar tablas: Practicante, Usuario, solicitudProyecto", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar practicantes con solicitudes", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar practicantes con solicitudes. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron consultar los practicantes, " + 
                "intente de nuevo", e);
        }

        return listaPracticantes;
    }
      
    @Override
    public UsuarioEncontrado buscarPracticante (String matricula) throws OperacionesDeDaoExcepcion{
        
        UsuarioEncontrado usuarioEncontrado = null;
        
        String consultaSQL = "{CALL obtener_datos_practicante(?)}";
        
        try(Connection conexion = ConexionBD.getConexion();
            CallableStatement consultaPreparada = conexion.prepareCall(consultaSQL);) {
            
            consultaPreparada.setString(1, matricula);
           
            ResultSet resultadosConsulta = consultaPreparada.executeQuery();
            
            if (resultadosConsulta.next()){

                int idEncontrado = resultadosConsulta.getInt("idUsuario");
                String rolEncontrado = resultadosConsulta.getString("rol");
                String hashEncontrado = resultadosConsulta.getString("hash");
                
                usuarioEncontrado = new UsuarioEncontrado(idEncontrado, rolEncontrado, hashEncontrado);
            }

         } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "El procedimiento almacenado 'obtener_datos_practicante' no existe o no es accesible. " +
                "Matrícula buscada: " + matricula, e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al buscar practicante. Matrícula: " + matricula, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al buscar practicante. " +
                "Matrícula: " + matricula + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo buscar el practicante, " + 
                "intente de nuevo", e);
        }
        return usuarioEncontrado;
    }

    @Override
    public boolean tieneProyectoAsignado(int idPracticante) throws OperacionesDeDaoExcepcion {
    
        boolean estaAsignado = false;

        String consultaSQL = "SELECT Proyecto_idProyecto FROM Practicante WHERE idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idPracticante);

            try (ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

                if (resultadosConsulta.next() && resultadosConsulta.getObject(
                    "Proyecto_idProyecto") != null) {

                    estaAsignado = true;
                    
                }
            }

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al verificar proyecto asignado. ID Practicante: " + idPracticante, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al verificar proyecto asignado. " +
                "ID Practicante: " + idPracticante + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo verificar la asignación, " + 
                "intente de nuevo", e);
        }

        return estaAsignado;
    
    }
    
    @Override
    public boolean eliminarPracticante(int idUsuario) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Practicante WHERE idUsuario = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al eliminar practicante. " +
                "ID Usuario: " + idUsuario + 
                " - Posiblemente tiene registros relacionados", e);
            
            throw new OperacionesDeDaoExcepcion("No se puede eliminar el practicante porque " + 
                "tiene elementos asignados", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar practicante. ID Usuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al eliminar practicante. " +
                "ID Usuario: " + idUsuario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar el practicante, " + 
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    }
    
}
