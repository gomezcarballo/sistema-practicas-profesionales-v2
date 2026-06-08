/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author Luz Fernanda H J
 */
public class EncabezadoReporte {
    
    private String proyecto;
    private String nombreResponsableProyecto;
    private String metodologia;
    private String objetivoGeneral;
    private String organizacion;
    private String nrc;
    private String profesor;
    private String alumno;
    private String carrera; 
    private String matricula;
    
    public EncabezadoReporte(String nrc, String profesor, String alumno, String organizacion, 
        String proyecto, String objetivoGeneral, String metodologia, String nombreResponsable, 
        String carrera, String matricula) {
       
        this.nrc = nrc;
        this.profesor = profesor;
        this.alumno = alumno;
        this.organizacion = organizacion;
        this.proyecto = proyecto;
        this.objetivoGeneral = objetivoGeneral;
        this.metodologia = metodologia;
        this.nombreResponsableProyecto = nombreResponsable;
        this.matricula = matricula;
    }

    public EncabezadoReporte() {
    }

    public void asignarCarreraPorDefecto() {
        this.carrera = "Ingenieria de Software";
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

    public String getNombreResponsableProyecto() {
        return nombreResponsableProyecto;
    }

    public void setNombreResponsableProyecto(String nombreResponsable) {
        this.nombreResponsableProyecto = nombreResponsable;
    }

    public String getCarrera(){
        return carrera;
    }

    public void setCarrera(String carrera){
        this.carrera = carrera;
    }

    public String getMatricula(){
        return matricula;
    }

    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
}
