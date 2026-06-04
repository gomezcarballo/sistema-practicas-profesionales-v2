package spp.logicadenegocio.clasesdto;

public class ActividadReporteParcial {
    private String descripcion;
    private int horasPlaneadas;
    private int horasReales;
    private boolean[] semanasPlan;
    private boolean[] semanasReal;

    public ActividadReporteParcial() {
        this.semanasPlan = new boolean[8];
        this.semanasReal = new boolean[8];
    }
    
    public ActividadReporteParcial(String descripcion, int horasPlaneadas, int horasReales) {
        this.descripcion = descripcion;
        this.horasPlaneadas = horasPlaneadas;
        this.horasReales = horasReales;
        this.semanasPlan = new boolean[8];
        this.semanasReal = new boolean[8];
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public int getHorasPlaneadas() {
        return horasPlaneadas;
    }

    public void setHorasPlaneadas(int horasPlaneadas) {
        this.horasPlaneadas = horasPlaneadas;
    }

    public int getHorasReales() {
        return horasReales;
    }

    public void setHorasReales(int horasReales) {
        this.horasReales = horasReales;
    }

    public boolean[] getSemanasPlan() {
        return semanasPlan;
    }

    public void setSemanasPlan(boolean[] semanasPlan) {
        this.semanasPlan = semanasPlan;
    }

    public boolean[] getSemanasReal() {
        return semanasReal;
    }

    public void setSemanasReal(boolean[] semanasReal) {
        this.semanasReal = semanasReal;
    }
}
