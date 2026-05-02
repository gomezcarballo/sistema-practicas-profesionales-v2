/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author gomes
 */
public class DetalleCalificacion {
    
    private Evaluacion evaluacion;
    private CriterioEvaluacion criterioEvaluacion;
    private double calificacion;
    private String observacion;

    public DetalleCalificacion() {
    }

    public DetalleCalificacion(Evaluacion evaluacion, CriterioEvaluacion criterioEvaluacion, double calificacion, String observacion) {
        this.evaluacion = evaluacion;
        this.criterioEvaluacion = criterioEvaluacion;
        this.calificacion = calificacion;
        this.observacion = observacion;
    }

    public Evaluacion getEvaluacion() {
        return evaluacion;
    }

    public void setEvaluacion(Evaluacion evaluacion) {
        this.evaluacion = evaluacion;
    }

    public CriterioEvaluacion getCriterioEvaluacion() {
        return criterioEvaluacion;
    }

    public void setCriterioEvaluacion(CriterioEvaluacion criterioEvaluacion) {
        this.criterioEvaluacion = criterioEvaluacion;
    }

    public double getCalificacion() {
        return calificacion;
    }

    public void setCalificacion(double calificacion) {
        this.calificacion = calificacion;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }
   
}
