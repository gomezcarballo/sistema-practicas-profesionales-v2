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

import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.interfacesdao.IOrganizacionDAO;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;


/**
 *
 * @author Luz Fernanda H J
 */
public class OrganizacionDAO implements IOrganizacionDAO{
    
    @Override
    public boolean insertarOrganizacion(Organizacion organizacion) throws OperacionesDeDaoExcepcion{
        
        boolean registroExitoso = false;
        
        String consultaSQL = "INSERT INTO Organizacion(nombre, direccion, sector, estado) VALUES (?, ?, ?, ?)";
        
        try (Connection conexion = ConexionBD.getConexion(); 
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1, organizacion.getNombre());
            consultaPreparada.setString(2, organizacion.getDireccion());
            consultaPreparada.setString(3, organizacion.getSector());
            consultaPreparada.setBoolean(4, organizacion.getEsActivo());

            registroExitoso = consultaPreparada.executeUpdate() > 0;
            
        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Violación de integridad al insertar la organización. " + 
                "El nombre de la organización:  " + organizacion.getNombre() ,e);
            
            throw new OperacionesDeDaoExcepcion("Ya existe una organización con esa información.", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al insertar la organización. " +
                "El nombre de la organización:  " + organizacion.getNombre() , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al insertar la organización. " +
                "El nombre de la organización:  " + organizacion.getNombre(), e);
            
            throw new OperacionesDeDaoExcepcion("Los datos ingresados no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al insertar el coordinador. " + 
                "El nombre de la organización:  " + organizacion.getNombre() +
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al guardar la organización, " + 
                "intente de nuevo más tarde", e);
        }
        
        return registroExitoso;
    }
    
    @Override
    public Organizacion consultarOrganizacion(String nombreOrganizacion)throws OperacionesDeDaoExcepcion {

        Organizacion organizacion = null;
        
        String consultaSQL = "SELECT * FROM Organizacion WHERE nombre = ?";
        
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1, nombreOrganizacion);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {

                organizacion = new Organizacion();

                organizacion.setIdOrganizacion(resultadosConsulta.getInt("idOrganizacion"));
                organizacion.setNombre(resultadosConsulta.getString("nombre"));
                organizacion.setDireccion(resultadosConsulta.getString("direccion"));
                organizacion.setSector(resultadosConsulta.getString("sector"));

                int esActivo = resultadosConsulta.getInt("estado");
                organizacion.setEsActivo(esActivo == 1);

            }

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar la organización." + 
                "El nombre de la organización: " + nombreOrganizacion , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al consultar la organización. " +
                "El nombre de la organización: " + nombreOrganizacion +
                ". SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al consultar la organización, " + 
                "intente de nuevo más tarde", e);
        }

         return organizacion;
        
    }

    @Override
    public boolean inactivarOrganizacion(int idOrganizacion)throws OperacionesDeDaoExcepcion{
        
        boolean inactivacionExitosa = false;

        String consultaSQL = "UPDATE Organizacion SET estado = 0 WHERE idOrganizacion = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setInt(1, idOrganizacion);

            inactivacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al inactivar la organizacion." + 
                "El ID de la organización: " + idOrganizacion , e);

            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
        
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al inactivar la organización. " +
                "El ID de la organización: " + idOrganizacion + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al inactivar la organización, " + 
                "intente de nuevo más tarde", e);
        }

        return inactivacionExitosa; 

    }

    @Override
    public boolean actualizarOrganizacion(Organizacion organizacion) throws OperacionesDeDaoExcepcion{
        
        boolean actualizacionExitosa = false;

        String consultaSQL = "UPDATE Organizacion SET nombre = ?, direccion = ?, "
                + "sector = ? WHERE idOrganizacion = ?";

        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, organizacion.getNombre());
            consultaPreparada.setString(2, organizacion.getDireccion());
            consultaPreparada.setString(3, organizacion.getSector());
            consultaPreparada.setInt(4, organizacion.getIdOrganizacion());

            actualizacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLIntegrityConstraintViolationException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Violación de integridad al actualizar la organización. " + 
                "El Id de organización: " + organizacion.getIdOrganizacion() +
                ", el nombre de la organización:  " + organizacion.getNombre() +
                ", la dirección de la organización : " +organizacion.getDireccion() +
                 ", el sector de la organización: " + organizacion.getSector() 
                 ,e);
            
            throw new OperacionesDeDaoExcepcion("Ya existe una organización con esa información.", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al actualizar organización. " +
                "El Id de organización: " + organizacion.getIdOrganizacion() +
                ", el nombre de la organización:  " + organizacion.getNombre() +
                ", la dirección de la organización : " +organizacion.getDireccion() +
                 ", el sector de la organización: " + organizacion.getSector() , e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLDataException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Datos inválidos al actualizar la organización. " +
                "El Id de organización: " + organizacion.getIdOrganizacion() +
                ", el nombre de la organización:  " + organizacion.getNombre() +
                ", la dirección de la organización : " +organizacion.getDireccion() +
                 ", el sector de la organización: " + organizacion.getSector() , e);
            
            throw new OperacionesDeDaoExcepcion("Los datos ingresados no son válidos, " + 
                "revise la información", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de base de datos al insertar el coordinador. " +
                "El Id de organización: " + organizacion.getIdOrganizacion() +
                ", el nombre de la organización:  " + organizacion.getNombre() +
                ", la dirección de la organización : " +organizacion.getDireccion() +
                 ", el sector de la organización: " + organizacion.getSector() +
                ". SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al actualizar la organización, " + 
                "intente de nuevo más tarde", e);
        }

    return actualizacionExitosa;
    
    }
    
    @Override
    public List<Organizacion>obtenerOrganizacionesActivas() throws OperacionesDeDaoExcepcion {

        List<Organizacion> organizaciones = new ArrayList<>();

        String consultaSQL = "SELECT idOrganizacion, nombre, direccion, sector "
        + "FROM Organizacion WHERE estado = 1";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            while(resultadosConsulta.next()){

                Organizacion organizacion = new Organizacion();

                organizacion.setIdOrganizacion(resultadosConsulta.getInt("idOrganizacion"));
                organizacion.setNombre(resultadosConsulta.getString("nombre"));
                organizacion.setDireccion(resultadosConsulta.getString( "direccion"));
                organizacion.setSector(resultadosConsulta.getString("sector"));

                organizaciones.add(organizacion);

            }

        } catch(SQLSyntaxErrorException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error de sintaxis al consultar organizaciones activas. " +
                "Verificar tabla Organizacion y columnas: idOrganizacion, nombre, direccion, sector, estado", e);
            
            throw new OperacionesDeDaoExcepcion("Error en el sistema, contacte al administrador", e);
            
        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al consultar organizaciones activas", e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado," + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al consultar organizaciones activas. " +
                "SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("No se pudieron recuperar las organizaciones, " + 
                "intente de nuevo", e);
        }

        return organizaciones;

    }
    
    @Override
    public boolean eliminarOrganizacion(String nombreOrganizacion) throws OperacionesDeDaoExcepcion {

        boolean eliminacionExitosa = false;

        String consultaSQL = "DELETE FROM Organizacion WHERE nombre = ?";

        try(Connection conexion = ConexionBD.getConexion();
            PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL)) {

            consultaPreparada.setString(1, nombreOrganizacion);

            eliminacionExitosa = consultaPreparada.executeUpdate() > 0;

        } catch(SQLTimeoutException e) {
            RegistroErrores.registrarError(Level.WARNING, 
                "Timeout al eliminar organización. Nombre: " + nombreOrganizacion, e);
            
            throw new OperacionesDeDaoExcepcion("El sistema está tardando demasiado, " + 
                "intente de nuevo por favor", e);
            
        } catch(SQLException e) {
            RegistroErrores.registrarError(Level.SEVERE, 
                "Error al eliminar organización. " +
                "Nombre: " + nombreOrganizacion + 
                ", SQL State: " + e.getSQLState() + 
                ", Error Code: " + e.getErrorCode(), e);
            
            throw new OperacionesDeDaoExcepcion("Error al eliminar la organización, " + 
                "intente de nuevo más tarde", e);
        }

        return eliminacionExitosa;
    }
 
}
