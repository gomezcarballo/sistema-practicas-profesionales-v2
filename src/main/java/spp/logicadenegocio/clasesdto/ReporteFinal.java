package spp.logicadenegocio.clasesdto;

import java.time.LocalDate;
import java.util.List;

public class ReporteFinal extends EncabezadoReporte {
    
    private String periodoEscolar; 
    private LocalDate fechaReporte;
    private String observaciones;
    private List<ActividadReporteFinal> actividades;
    private List<ActividadReporteFinal> entregables;
    private String tipoReporte; 


    public ReporteFinal(String nrc, String profesor, String alumno, String organizacion, 
        String proyecto, String objetivoGeneral, String metodologia, String nombreResponsable, 
        String carrera, String matricula,  String periodoEscolar, LocalDate fechaReporte, 
        String observaciones,List<ActividadReporteFinal> actividades, String tipoReporte){

        super(nrc, profesor, alumno, organizacion, proyecto, objetivoGeneral, metodologia, nombreResponsable, carrera, matricula);    
        this.periodoEscolar = periodoEscolar;
        this.fechaReporte = fechaReporte;
        this.observaciones = observaciones;
        this.actividades = actividades;
        this.tipoReporte = tipoReporte;

    }

    public ReporteFinal(){
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

    public void asignarFechaActual() {
        this.fechaReporte = LocalDate.now();
    }

    public void asignarTipoReporteDefecto(){
        this.tipoReporte = "Final";
    }

    public String getPeriodoEscolar() {
        calcularPeriodoEscolar();
        return periodoEscolar;
    }

    public void setPeriodoEscolar(String periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }

    public LocalDate getFechaReporte() {
        asignarFechaActual();
        return fechaReporte;
    }

    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public List<ActividadReporteFinal> getActividades() {
        return actividades;
    }

    public void setActividades(List<ActividadReporteFinal> actividades) {
        this.actividades = actividades;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }

    public List<ActividadReporteFinal> getEntregables() {
        return entregables;
    }

    public void setEntregables(List<ActividadReporteFinal> entregables) {
        this.entregables = entregables;
    }
}

