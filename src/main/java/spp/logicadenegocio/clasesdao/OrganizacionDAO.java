/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import spp.accesoadatos.ConexionBD;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import spp.logicadenegocio.clasesdto.Organizacion;
import spp.logicadenegocio.interfacesdao.IOrganizacionDAO;
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
           
            consultaPreparada.executeUpdate();
            
            registroExitoso = true;
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
        }
        
        return registroExitoso;
    }
    
    @Override
    public Organizacion consultarOrganizacion(String nombre)throws OperacionesDeDaoExcepcion {

        Organizacion organizacion = null;
        
        String consultaSQL = "SELECT * FROM Organizacion WHERE nombre = ?";
        
        try (Connection conexion = ConexionBD.getConexion();
             PreparedStatement consultaPreparada = conexion.prepareStatement(consultaSQL);){
            
            consultaPreparada.setString(1, nombre);

            ResultSet resultadosConsulta = consultaPreparada.executeQuery();

            if (resultadosConsulta.next()) {

                organizacion = new Organizacion();

                organizacion.setIdOrganizacion(resultadosConsulta.getInt("idOrganizacion"));
                organizacion.setNombre(resultadosConsulta.getString("nombre"));
                organizacion.setDireccion(resultadosConsulta.getString("direccion"));
                organizacion.setSector(resultadosConsulta.getString("sector"));

                int esActivo = resultadosConsulta.getInt("estado");
                
                if (esActivo == 1) {
                    organizacion.setEsActivo(true);
                } else {
                    organizacion.setEsActivo(false);
                }

            }

        } catch (SQLException e) {
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
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

            int filasAfectadas = consultaPreparada.executeUpdate();

            if (filasAfectadas > 0) {
                inactivacionExitosa = true;
            }
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
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

            int filasAfectadas = consultaPreparada.executeUpdate();

            if (filasAfectadas > 0) {
                actualizacionExitosa = true;
            }
        }catch(SQLException e){
            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos",e);
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

        }catch(SQLException e){

            throw new OperacionesDeDaoExcepcion("No se puede conectar a la base de datos", e);
            
        }

        return organizaciones;

    }
 
}
