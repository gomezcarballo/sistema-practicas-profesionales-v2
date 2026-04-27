/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.excepciones;

/**
 *
 * @author Luz Fernanda H J
 */
public class ReglaDeNegocioExcepcion extends Exception {
    
    public ReglaDeNegocioExcepcion(String mensaje, Throwable causa){
        super(mensaje, causa);
    }
}
