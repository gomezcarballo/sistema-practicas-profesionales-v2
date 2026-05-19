/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.enums;

/**
 *
 * @author Luz Fernanda H J
 */
public enum TipoDocumento {
    
    FORMATO_PRESENTACION("Formato de Presentación"),
    ACTIVIDAD("Actividad"),
    BITACORA_PSP("Bitácora de PSP"),
    REPORTE_PARCIAL("Reporte Parcial"),
    REPORTE_MENSUAL("Reporte Mensual"),
    HORARIO("Horario"),
    AUTOEVALUACION("Autoevaluación"),
    PLAN_ACTIVIDADES("Plan de Actividades");
    
    private final String descripcion;
    
    TipoDocumento(String descripcion){
        this.descripcion = descripcion; 
    }
    
    public String getDescripcion(){
        return descripcion;
    }
}
