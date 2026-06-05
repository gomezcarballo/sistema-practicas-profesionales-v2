package spp.logicadenegocio.clasesdto;

import java.time.LocalDate;
import java.util.List;

public class ReporteFinal {
    
    private String carrera; 
    private String nrc;
    private String profesor;
    private String periodoEscolar; 
    private String alumno;
    private String organizacion;
    private String proyecto;
    private LocalDate fechaReporte;
    private String objetivoGeneral;
    private String metodologia;
    private String nombreResponsable;
    private String observaciones;
    private List<ActividadReporteFinal> actividades;
    private String tipoReporte; 
    private String matricula;


    public ReporteFinal(String carrera, String nrc, String profesor, String periodoEscolar, String alumno,
            String organizacion, String proyecto, LocalDate fechaReporte, String objetivoGeneral, String metodologia,
            String nombreResponsable, String observaciones, List<ActividadReporteFinal> actividades,
            String tipoReporte, String matricula) {

        this.carrera = carrera;
        this.nrc = nrc;
        this.profesor = profesor;
        this.periodoEscolar = periodoEscolar;
        this.alumno = alumno;
        this.organizacion = organizacion;
        this.proyecto = proyecto;
        this.fechaReporte = fechaReporte;
        this.objetivoGeneral = objetivoGeneral;
        this.metodologia = metodologia;
        this.nombreResponsable = nombreResponsable;
        this.observaciones = observaciones;
        this.actividades = actividades;
        this.tipoReporte = tipoReporte;
        this.matricula = matricula;

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

    public void asignarCarreraPorDefecto() {
        this.carrera = "Ingenieria de Software";
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getPeriodoEscolar() {
        calcularPeriodoEscolar();
        return periodoEscolar;
    }

    public void setPeriodoEscolar(String periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }

    public String getAlumno() {
        return alumno;
    }

    public void setAlumno(String alumno) {
        this.alumno = alumno;
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

    public LocalDate getFechaReporte() {
        asignarFechaActual();
        return fechaReporte;
    }

    public void setFechaReporte(LocalDate fechaReporte) {
        this.fechaReporte = fechaReporte;
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

    public String getCarrera() {
        asignarCarreraPorDefecto();
        return carrera;
    }

    public void setCarrera(String carrera) {
        this.carrera = carrera;
    }
    
    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

}

