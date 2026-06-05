package spp.logicadenegocio.clasesdto;

public class ActividadReporteFinal {

    private String nombreActividad; 
    private int porcentajeAvance;
    private String observaciones;

    public ActividadReporteFinal(String nombreActividad, int porcentajeAvance, String observaciones) {
        this.nombreActividad = nombreActividad;
        this.porcentajeAvance = porcentajeAvance;
        this.observaciones = observaciones;
    }

    public ActividadReporteFinal() {
    }
    
    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public int getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(int porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

}
