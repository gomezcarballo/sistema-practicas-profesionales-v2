/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.accesoadatos;

import java.sql.Connection;
import java.sql.DriverManager;
import spp.utilerias.ConfiguracionBaseDatos;
import java.sql.SQLException;

/**
 *
 * @author Luz Fernanda H J
 */
public class ConexionBD {
        
    public static Connection getConexion() throws SQLException{
        
        String URL = ConfiguracionBaseDatos.get("basedatos.url");
        String USUARIO = ConfiguracionBaseDatos.get("basedatos.usuario");
        String CONTRASENIA = ConfiguracionBaseDatos.get("basedatos.contrasenia");
        
        return DriverManager.getConnection(URL, USUARIO, CONTRASENIA);
    }
}
 