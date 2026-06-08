/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author gomes
 */
public class Evaluacion {
    
    private int idEvaluacion;
    private String nrc;
    private String periodo;
    private double calificacionFinal;
    private String observaciones;
    private Profesor profesor;
    private Practicante practicante;

    public Evaluacion() {
    }

    public Evaluacion(int idEvaluacion, String nrc, String periodo, double calificacionFinal, 
           String observaciones, Profesor profesor, Practicante practicante) {
        this.idEvaluacion = idEvaluacion;
        this.nrc = nrc;
        this.periodo = periodo;
        this.calificacionFinal = calificacionFinal;
        this.profesor = profesor;
        this.practicante = practicante;
        this.observaciones = observaciones;
                
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public double getCalificacionFinal() {
        return calificacionFinal;
    }

    public void setCalificacionFinal(double calificacionFinal) {
        this.calificacionFinal = calificacionFinal;
    }

    public Profesor getProfesor() {
        return profesor;
    }

    public void setProfesor(Profesor profesor) {
        this.profesor = profesor;
    }

    public Practicante getPracticante() {
        return practicante;
    }

    public void setPracticante(Practicante practicante) {
        this.practicante = practicante;
    }   

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }
    
}
