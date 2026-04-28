/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.excepciones;

/**
 *
 * @author Luz Fernanda H J
 */
public class OperacionesDeDaoExcepcion extends Exception{
    public OperacionesDeDaoExcepcion(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
    public OperacionesDeDaoExcepcion(String mensaje){
        super(mensaje);
    }
}
