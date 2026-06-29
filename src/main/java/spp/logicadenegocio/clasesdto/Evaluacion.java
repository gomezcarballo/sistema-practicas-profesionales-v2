/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

import java.time.LocalDate;

/**
 *
 * @author gomes
 */
public class Evaluacion {
    
    private int idEvaluacion;
    private double calificacionFinal;
    private String periodo;
    private String observaciones;
    private int idProfesor;
    private Practicante practicante;
    private Documento documento;
    
    public Evaluacion() {
    }

    public Evaluacion(int idEvaluacion, double calificacionFinal, 
           String observaciones, int idProfesor, Practicante practicante) {
        this.idEvaluacion = idEvaluacion;
        this.calificacionFinal = calificacionFinal;
        this.idProfesor = idProfesor;
        this.practicante = practicante;
        this.observaciones = observaciones;
                
    }

    public int getIdEvaluacion() {
        return idEvaluacion;
    }

    public void setIdEvaluacion(int idEvaluacion) {
        this.idEvaluacion = idEvaluacion;
    }


    public void calcularPeriodoEscolar() {
        LocalDate fechaActual = LocalDate.now();
        int mes = fechaActual.getMonthValue();
        int año = fechaActual.getYear();

        if (mes >= 2 && mes <= 7) {
            this.periodo = "Febrero - Julio " + año;
        } else if (mes >= 8 && mes <= 12) {
            this.periodo = "Agosto " + año + " - Enero " + (año + 1);
        } else {
            this.periodo = "Agosto " + (año - 1) + " - Enero " + año;
        }
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

    public int getIdProfesor() {
        return idProfesor;
    }

    public void setIdProfesor(int idProfesor) {
        this.idProfesor = idProfesor;
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

    public Documento getDocumento() {
        return documento;
    }

    public void setDocumento(Documento documento) {
        this.documento = documento;
    }
    
    
    
}
