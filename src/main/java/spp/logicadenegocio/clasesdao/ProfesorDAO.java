/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

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
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.interfacesdao.IProfesorDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ProfesorDAO extends UsuarioDAO implements IProfesorDAO{
    
    @Override
    public boolean insertarProfesor(Profesor profesor)throws OperacionesDeDaoExcepcion{
        
        boolean registroExitoso = false;
        
        String consultaSQL = "INSERT INTO Profesor (idUsuario, noPersonal) VALUES (?, ?, ?)";
        
        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);) {

            consultaPreparada.setInt(1, profesor.getIdUsuario());
            consultaPreparada.setString(2, profesor.getNumeroDePersonal());
            
            registroExitoso = consultaPreparada.executeUpdate() > 0;
           
        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al insertar profesor. " +
                "ID Usuario: " + profesor.getIdUsuario() + 
                ", No. Personal: " + profesor.getNumeroDePersonal(), e);
            
            throw new OperacionesDeDaoExcepcion("El profesor ya está registrado " +    
                "o el número de personal está duplicado", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar profesor. No. Personal: " + profesor.getNumeroDePersonal(), e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar profesor. " +
                "ID Usuario: " + profesor.getIdUsuario() + 
                ", No. Personal: " + profesor.getNumeroDePersonal(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos del profesor no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al insertar profesor. " +
                "ID Usuario: " + profesor.getIdUsuario() + 
                ", No. Personal: " + profesor.getNumeroDePersonal() + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al registrar el profesor, " + 
                "intente de nuevo más tarde", e);
        }
        return registroExitoso;
    }
    
    @Override
    public List<Profesor> consultarProfesoresActivos()throws OperacionesDeDaoExcepcion {
    
        List<Profesor> profesoresActivos = new ArrayList<>();

        String consultaSQL = """
            SELECT p.noPersonal as numeroDePersonal,
            p.idUsuario,                                 
            u.nombre,
            u.apellidoPaterno,
            u.apellidoMaterno,
            u.correoInstitucional
            FROM Profesor p
            INNER JOIN Usuario u
            ON p.idUsuario = u.idUsuario
            WHERE u.estado = 1
            """;

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            while(resultadoConsulta.next()) {

                Profesor profesor = new Profesor();

                profesor.setNumeroDePersonal(resultadoConsulta.getString("numeroDePersonal"));
                profesor.setIdUsuario(resultadoConsulta.getInt("idUsuario"));
                profesor.setNombre(resultadoConsulta.getString("nombre"));
                profesor.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                profesor.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                profesor.setCorreoInstitucional(resultadoConsulta.getString("correoInstitucional"));

                profesoresActivos.add(profesor);

            }

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al consultar profesores activos. " +
                "Verificar tablas: Profesor, Usuario y sus columnas", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar profesores activos", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar profesores activos. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron consultar los profesores, " + 
                "intente de nuevo", e);
        }

        return profesoresActivos;
    
    }
    
    @Override
    public List<Profesor> consultarProfesoresInactivos() throws OperacionesDeDaoExcepcion{
        
        List<Profesor> profesoresInactivos = new ArrayList<>();
        
        String consultaSQL = """
            SELECT p.noPersonal,
            p.idUsuario,
            u.nombre,
            u.apellidoPaterno,
            u.apellidoMaterno,
            u.correoInstitucional
            FROM Profesor p
            INNER JOIN Usuario u
            ON p.idUsuario = u.idUsuario
            WHERE u.estado = 0
            """;

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            while(resultadoConsulta.next()) {

                Profesor profesor = new Profesor();

                profesor.setNumeroDePersonal(resultadoConsulta.getString("numeroDePersonal"));
                profesor.setIdUsuario(resultadoConsulta.getInt("idUsuario"));
                profesor.setNombre(resultadoConsulta.getString("nombre"));
                profesor.setApellidoPaterno(resultadoConsulta.getString("apellidoPaterno"));
                profesor.setApellidoMaterno(resultadoConsulta.getString("apellidoMaterno"));
                profesor.setCorreoInstitucional(resultadoConsulta.getString("correoInstitucional"));

                profesoresInactivos.add(profesor);

            }

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al consultar profesores inactivos. " +
                "Verificar tablas: Profesor, Usuario y sus columnas", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar profesores inactivos", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar profesores inactivos. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron consultar los profesores, " + 
                "intente de nuevo", e);
        }
        
         return profesoresInactivos;
    
    }
    
    @Override
    public int obtenerCantidadProfesoresActivos()throws OperacionesDeDaoExcepcion {

        int cantidadProfesoresActivos = 0;

        String consultaSQL = """
            SELECT COUNT(*)
            FROM Profesor p
            INNER JOIN Usuario u
            ON p.idUsuario = u.idUsuario
            WHERE u.estado = 1
            """;

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
            ResultSet resultadoConsulta = consultaPreparada.executeQuery()) {

            if(resultadoConsulta.next()) {

                cantidadProfesoresActivos = resultadoConsulta.getInt(1);

            }

         } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al contar profesores activos. " +
                "Verificar tablas: Profesor, Usuario", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al contar profesores activos", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al contar profesores activos. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudo obtener la cantidad de profesores, " + 
                "intente de nuevo", e);
        }

         return cantidadProfesoresActivos;

    }

    @Override
    public boolean inactivarProfesor(int idUsuario)throws OperacionesDeDaoExcepcion {
        
        boolean inactivacionExitosa = false;

        String consultaSQL = "UPDATE Usuario SET estado = 0 WHERE idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            inactivacionExitosa = consultaPreparada.executeUpdate() > 0;
            
         } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al inactivar profesor. ID Usuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al inactivar profesor. " +
                "ID Usuario: " + idUsuario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al inactivar el profesor, " + 
                "intente de nuevo más tarde", e);
        }

         return inactivacionExitosa; 

    }

    @Override
    public boolean reactivarProfesor(int idUsuario) throws OperacionesDeDaoExcepcion {
        
        boolean reactivacionExitosa = false;
        
        String consultaSQL = "UPDATE Usuario u INNER JOIN Profesor p ON u.idUsuario = p.idUsuario "
                + "SET u.estado = 1 WHERE u.idUsuario = ?";
        
        try(Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setInt(1, idUsuario);
            
            reactivacionExitosa = consultaPreparada.executeUpdate() > 0;
            
        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al reactivar profesor. " +
                "ID Usuario: " + idUsuario + 
                " - Verificar sintaxis UPDATE con INNER JOIN", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al reactivar profesor. ID Usuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al reactivar profesor. " +
                "ID Usuario: " + idUsuario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al reactivar el profesor, " + 
                "intente de nuevo más tarde", e);
        }
        
         return reactivacionExitosa;
    
    }   
    
    @Override
    public boolean eliminarProfesor(int idUsuario) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Profesor WHERE idUsuario = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuario);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Violación de integridad al eliminar profesor. " +
                "ID Usuario: " + idUsuario + 
                " - Posiblemente tiene registros relacionados (prácticas, NRC, etc.)", e);
            
            throw new OperacionesDeDaoExcepcion("No se puede eliminar el profesor porque " + 
                "tiene elementos asignados", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar profesor. ID Usuario: " + idUsuario, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al eliminar profesor. " +
                "ID Usuario: " + idUsuario + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar el profesor, " + 
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    }   
    
    public List<ExperienciaEducativa> obtenerExperienciasEducativasPorProfesor(int idUsuarioProfesor) throws OperacionesDeDaoExcepcion {
        
        List<ExperienciaEducativa> listaExperiencias = new ArrayList<>();

        String consultaSQL = "SELECT idExperienciaEducativa, nombre, periodo, idReferenciaCurso " +
                             "FROM experienciaeducativa WHERE idUsuarioProfesor = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuarioProfesor);

            try (ResultSet resultados = consultaPreparada.executeQuery()) {
                
                while (resultados.next()) {
                    ExperienciaEducativa experienciaEducativa = new ExperienciaEducativa();
                    experienciaEducativa.setIdExperienciaEducativa(resultados.getInt("idExperienciaEducativa"));
                    experienciaEducativa.setNombreExperienciaEducativa(resultados.getString("nombre"));
                    experienciaEducativa.setPeriodo(resultados.getString("periodo"));
                    experienciaEducativa.setIdReferenciaCurso(resultados.getInt("idReferenciaCurso"));
                    
                    listaExperiencias.add(experienciaEducativa);
                }
                
            }

        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, "Timeout al recuperar EE del profesor: " + idUsuarioProfesor, e);
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Error al recuperar EE del profesor: " + idUsuarioProfesor, e);
            throw new OperacionesDeDaoExcepcion("No se pudieron cargar sus Experiencias Educativas", e);
            
        }

        return listaExperiencias;
        
    }

    public List<Practicante> obtenerPracticantesPorExperienciaEducativa(int idExperienciaEducativa) throws OperacionesDeDaoExcepcion {
        
        List<Practicante> listaPracticantes = new ArrayList<>();

        String consultaSQL = "SELECT p.idUsuario, p.matricula, u.nombre, u.apellidoPaterno, u.apellidoMaterno " +
                             "FROM practicante p " +
                             "INNER JOIN usuario u ON p.idUsuario = u.idUsuario " +
                             "WHERE p.idExperienciaEducativa = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idExperienciaEducativa);

            try (ResultSet resultados = consultaPreparada.executeQuery()) {
                
                while (resultados.next()) {
                    Practicante alumno = new Practicante();
                    alumno.setIdUsuario(resultados.getInt("idUsuario"));
                    alumno.setMatricula(resultados.getString("matricula"));
                    alumno.setNombre(resultados.getString("nombre"));
                    alumno.setApellidoPaterno(resultados.getString("apellidoPaterno"));
                    alumno.setApellidoMaterno(resultados.getString("apellidoMaterno"));
                    
                    listaPracticantes.add(alumno);
                }
                
            }

        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, "Timeout al recuperar alumnos de EE: " + idExperienciaEducativa, e);
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Error al recuperar alumnos de EE: " + idExperienciaEducativa, e);
            throw new OperacionesDeDaoExcepcion("No se pudo cargar la lista de alumnos asignados", e);
            
        }

        return listaPracticantes;
        
    }
    
    public Profesor obtenerProfesorPorIdUsuario(int idUsuarioProfesor) throws OperacionesDeDaoExcepcion {
        
        Profesor profesorEncontrado = null;

        String consultaSQL = "SELECT u.nombre, u.apellidoPaterno, u.apellidoMaterno, p.noPersonal " +
                             "FROM usuario u " +
                             "INNER JOIN profesor p ON u.idUsuario = p.idUsuario " +
                             "WHERE p.idUsuario = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idUsuarioProfesor);

            try (ResultSet resultados = consultaPreparada.executeQuery()) {
                
                if (resultados.next()) {
                    profesorEncontrado = new Profesor();
                    profesorEncontrado.setNombre(resultados.getString("nombre"));
                    profesorEncontrado.setApellidoPaterno(resultados.getString("apellidoPaterno"));
                    profesorEncontrado.setApellidoMaterno(resultados.getString("apellidoMaterno"));
                    profesorEncontrado.setNumeroDePersonal(resultados.getString("noPersonal"));
                }
                
            }

        } catch (SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, "Timeout al recuperar profesor: " + idUsuarioProfesor, e);
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, intente de nuevo por favor", e);
            
        } catch (SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Error al recuperar profesor: " + idUsuarioProfesor, e);
            throw new OperacionesDeDaoExcepcion("No se pudo cargar la información del profesor", e);
            
        }

        return profesorEncontrado;
        
    }
    
    public List<Profesor> consultarProfesoresParaAsignacionEE() throws OperacionesDeDaoExcepcion {
        
        List<Profesor> listaProfesores = new ArrayList<>();

        String consultaSQL = "CALL ListarProfesoresParaAsignarEE()";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);
             ResultSet resultadosConsulta = consultaPreparada.executeQuery()) {

            while (resultadosConsulta.next()) {

                Profesor profesor = new Profesor();

                profesor.setIdUsuario(resultadosConsulta.getInt("idUsuario"));
                profesor.setNumeroDePersonal(resultadosConsulta.getString("noPersonal")); 
                profesor.setNombre(resultadosConsulta.getString("nombre"));
                profesor.setApellidoPaterno(resultadosConsulta.getString("apellidoPaterno"));
                profesor.setApellidoMaterno(resultadosConsulta.getString("apellidoMaterno"));
                profesor.setCorreoInstitucional(resultadosConsulta.getString("correoInstitucional"));

                listaProfesores.add(profesor);
                
            }

        } catch(SQLSyntaxErrorException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError de sintaxis al consultar profesores para asignación de una experiencia educativa. " +
                "Verificar tablas: Profesor, Usuario", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            
            RegistroErrores.registrarError(Level.WARNING, 
                "\nTimeout al consultar profesores para asignación de una experiencia educativa. ", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al consultar profesores para asignación de una experiencia educativa. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron consultar los profesores, " + 
                "intente de nuevo", e);
                
        }

        return listaProfesores;
    }
    
}
