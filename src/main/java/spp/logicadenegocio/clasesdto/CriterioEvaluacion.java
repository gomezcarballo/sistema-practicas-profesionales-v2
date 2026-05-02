/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author gomes
 */
public class CriterioEvaluacion {
    
    private int idCriterioEvaluacion;
    private String nombreCriterio;
    private double porcentajeCalificacion;

    public CriterioEvaluacion() {
    }

    public CriterioEvaluacion(int idCriterioEvaluacion, String nombreCriterio, double porcentajeCalificacion) {
        this.idCriterioEvaluacion = idCriterioEvaluacion;
        this.nombreCriterio = nombreCriterio;
        this.porcentajeCalificacion = porcentajeCalificacion;
    }

    public int getIdCriterioEvaluacion() {
        return idCriterioEvaluacion;
    }

    public void setIdCriterioEvaluacion(int idCriterioEvaluacion) {
        this.idCriterioEvaluacion = idCriterioEvaluacion;
    }

    public String getNombreCriterio() {
        return nombreCriterio;
    }

    public void setNombreCriterio(String nombreCriterio) {
        this.nombreCriterio = nombreCriterio;
    }

    public double getPorcentajeCalificacion() {
        return porcentajeCalificacion;
    }

    public void setPorcentajeCalificacion(double porcentajeCalificacion) {
        this.porcentajeCalificacion = porcentajeCalificacion;
    }
       
}
