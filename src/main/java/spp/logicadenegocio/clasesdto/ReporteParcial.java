package spp.logicadenegocio.clasesdto;

import java.time.LocalDate;
import java.util.List;


public class ReporteParcial extends EncabezadoReporte{

    private String periodoReporteYHorasCubiertas;
    private List<ActividadReporteParcial> actividades;
    private String resultados;
    private String observaciones;
    private String periodoEscolar;
    private LocalDate fechaReporte;
    private int numeroInforme;

    public ReporteParcial(String nrc, String profesor, String alumno, String organizacion, 
        String proyecto, String objetivoGeneral, String metodologia, String nombreResponsable, 
        String carrera, String matricula, String periodoReporteYHorasCubiertas, 
        List<ActividadReporteParcial> actividades, String resultados, String observaciones, 
        String periodoEscolar,LocalDate fechaReporte, int numeroInforme){
        
        super(nrc, profesor, alumno, organizacion, proyecto, objetivoGeneral, metodologia, nombreResponsable, carrera, matricula);
        this.periodoReporteYHorasCubiertas = periodoReporteYHorasCubiertas;
        this.actividades = actividades;
        this.resultados = resultados;
        this.observaciones = observaciones;
        this.periodoEscolar = periodoEscolar;
        this.fechaReporte = fechaReporte;
        this.numeroInforme = numeroInforme;
    }


    public ReporteParcial(){

    }

    
    public void asignarNumeroInforme(){
        this.numeroInforme = 12345;
    }

    public void asignarFechaActual() {
        this.fechaReporte = LocalDate.now();
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
            this.periodoEscolar = "Agosto " + (año - 1) + " - Enero " + año;
        }
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
    public LocalDate getFechaReporte() {
        return fechaReporte;
    }
    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
    }
    public int getNumeroInforme() {
        return numeroInforme;
    }
    public void setNumeroInforme(int numeroInforme) {
        this.numeroInforme = numeroInforme;
    }

    
}

