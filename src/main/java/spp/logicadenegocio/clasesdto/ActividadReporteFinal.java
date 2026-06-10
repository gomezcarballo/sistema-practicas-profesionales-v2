package spp.logicadenegocio.clasesdto;

public class ActividadReporteFinal {

    private String nombreActividad; 
    private String porcentajeAvance;
    private String observaciones;
    private boolean esEntregable;

    public ActividadReporteFinal(String nombreActividad, String porcentajeAvance, String observaciones, 
        boolean esEntregable) {
        this.nombreActividad = nombreActividad;
        this.porcentajeAvance = porcentajeAvance;
        this.observaciones = observaciones;
        this.esEntregable = esEntregable;
    }

    public ActividadReporteFinal() {
    }
    
    public String getNombreActividad() {
        return nombreActividad;
    }

    public void setNombreActividad(String nombreActividad) {
        this.nombreActividad = nombreActividad;
    }

    public String getPorcentajeAvance() {
        return porcentajeAvance;
    }

    public void setPorcentajeAvance(String porcentajeAvance) {
        this.porcentajeAvance = porcentajeAvance;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public boolean getEsEntregable() {
        return esEntregable;
    }

    public void setEsEntregable(boolean esEntregable) {
        this.esEntregable = esEntregable;
    }

}
