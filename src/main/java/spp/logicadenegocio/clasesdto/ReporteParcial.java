/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

import java.time.LocalDate;
import java.util.List;

/**
 *
 * @author Luz Fernanda H J
 */
public class ReporteParcial {
    
    private String nrc;
    private String profesor;
    private String alumno;
    private String organizacion;
    private String proyecto;
    private String objetivoGeneral;
    private String metodologia;
    private String nombreResponsable;
    private String periodoReporteYHorasCubiertas;
    private List<ActividadReporteParcial> actividades;
    private String resultados;
    private String observaciones;
    private String periodoEscolar;
    private String fechaReporte;
    private int numeroInforme;
    
    public ReporteParcial(String nrc, String profesor, String alumno, String organizacion, 
        String proyecto, String objetivoGeneral, String metodologia, String nombreResponsable, 
        String periodoReporte, List<ActividadReporteParcial> actividades, String resultados, 
        String observaciones, String periodoEscolar, String fechaReporte, int numeroInforme) {
       
        this.nrc = nrc;
        this.profesor = profesor;
        this.alumno = alumno;
        this.organizacion = organizacion;
        this.proyecto = proyecto;
        this.objetivoGeneral = objetivoGeneral;
        this.metodologia = metodologia;
        this.nombreResponsable = nombreResponsable;
        this.periodoReporteYHorasCubiertas = periodoReporte;
        this.actividades = actividades;
        this.resultados = resultados;
        this.observaciones = observaciones;
        this.periodoEscolar = periodoEscolar;
        this.fechaReporte = fechaReporte;
        this.numeroInforme = numeroInforme;
    }

    public ReporteParcial() {
    }

    public void calcularPeriodoEscolar() {
        LocalDate fechaActual = LocalDate.now();
        int mes = fechaActual.getMonthValue();
        int año = fechaActual.getYear();

        if (mes >= 2 && mes <= 7) {
            this.periodoEscolar = "Febrero - Julio " + año;
        } else if (mes >= 8 && mes <= 12) {
            this.periodoEscolar = "Agosto " + año + " - Enero " + (año + 1);
        } else {
            // Si el mes es Enero (1), sigue perteneciendo al ciclo del año pasado
            this.periodoEscolar = "Agosto " + (año - 1) + " - Enero " + año;
        }
    }

    public void asignarFechaActual() {
        this.fechaReporte = LocalDate.now().toString();
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumnos) {
        this.alumno = alumnos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getProyecto() {
        return proyecto;
    }

    public void setProyecto(String proyecto) {
        this.proyecto = proyecto;
    }

    public String getObjetivoGeneral() {
        return objetivoGeneral;
    }

    public void setObjetivoGeneral(String objetivoGeneral) {
        this.objetivoGeneral = objetivoGeneral;
    }

    public String getMetodologia() {
        return metodologia;
    }

    public void setMetodologia(String metodologia) {
        this.metodologia = metodologia;
    }

    public String getNombreResponsable() {
        return nombreResponsable;
    }

    public void setNombreResponsable(String nombreResponsable) {
        this.nombreResponsable = nombreResponsable;
    }

    public String getPeriodoReporteYHorasCubiertas() {
        return periodoReporteYHorasCubiertas;
    }

    public void setPeriodoReporteYHorasCubiertas(String periodoReporteYHorasCubiertas) {
        this.periodoReporteYHorasCubiertas = periodoReporteYHorasCubiertas;
    }

    public List<ActividadReporteParcial> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadReporteParcial> actividades) {
        this.actividades = actividades;
    }

    public String getResultados() {
        return resultados;
    }

    public void setResultados(String resultados) {
        this.resultados = resultados;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public String getPeriodoEscolar() {
        return periodoEscolar;
    }

    public void setPeriodoEscolar(String periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }

    public String getFechaReporte() {
        return fechaReporte;
    }

    public void setFechaReporte(String fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public int getNumeroInforme() {
        return numeroInforme;
    }

    public void setNumeroInforme(int numeroInforme) {
        this.numeroInforme = numeroInforme;
    }
    
}
