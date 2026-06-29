package spp.logicadenegocio.clasesdto;

public class ExperienciaEducativa {
  
    private int idExperienciaEducativa;
    private String periodo;
    private String nombreExperienciaEducativa;
    private int cupo;
    private boolean estado;
    private int idReferenciaCurso;
    private int idProfesorAsignado;
    private String nrc;
    

    public ExperienciaEducativa() {

    }

    public ExperienciaEducativa(int idExperienciaEducativa, int idReferenciaCurso, String periodo,
            String nombreExperienciaEducativa, int cupo, int idProfesorAsignado, boolean estado) {
        this.idExperienciaEducativa = idExperienciaEducativa;
        this.idReferenciaCurso = idReferenciaCurso;
        this.periodo = periodo;
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
        this.cupo = cupo;
        this.idProfesorAsignado = idProfesorAsignado;
        this.estado = estado;
    }

    public int getIdExperienciaEducativa() {
        return idExperienciaEducativa;
    }

    public void setIdExperienciaEducativa(int idExperienciaEducativa) {
        this.idExperienciaEducativa = idExperienciaEducativa;
    }

    public int getIdReferenciaCurso() {
        return idReferenciaCurso;
    }

    public void setIdReferenciaCurso(int idReferenciaCurso) {
        this.idReferenciaCurso = idReferenciaCurso;
    }  
    
    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getNombreExperienciaEducativa() {
        return nombreExperienciaEducativa;
    }

    public void setNombreExperienciaEducativa(String nombreExperienciaEducativa) {
        this.nombreExperienciaEducativa = nombreExperienciaEducativa;
    }

    public int getCupo() {
        return cupo;
    }

    public void setCupo(int cupo) {
        this.cupo = cupo;
    }

    public boolean getEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }   
    
    public int getIdProfesorAsignado(){
        return idProfesorAsignado;
    }

    public void setIdProfesorAsignado(int idProfesorAsignado){
        this.idProfesorAsignado = idProfesorAsignado; 
    }

     public String getNrc(){
        return nrc;
    }

    public void setNrc(String nrc){
        this.nrc = nrc; 
    }
}
