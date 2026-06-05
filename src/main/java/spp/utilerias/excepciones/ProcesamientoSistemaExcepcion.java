/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.excepciones;

/**
 *
 * @author Luz Fernanda H J
 */
public class ProcesamientoSistemaExcepcion extends Exception {
    
    public ProcesamientoSistemaExcepcion(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
    
    public ProcesamientoSistemaExcepcion(String mensaje){
        super(mensaje);
    }
    
    public ProcesamientoSistemaExcepcion(Throwable causa){
        super(causa);
    }
    
}
