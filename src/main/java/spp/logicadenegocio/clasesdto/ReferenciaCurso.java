/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author gomes
 */
public class ReferenciaCurso {
    
    private int idReferenciaCurso;
    private String nrc;

    public ReferenciaCurso() {
    }

    public ReferenciaCurso(String clave) {
        this.nrc = clave;
    }

    public String getNrc() {
        return nrc;
    }

    public void setNrc(String nrc) {
        this.nrc = nrc;
    }

    public int getIdReferenciaCurso() {
        return idReferenciaCurso;
    }

    public void setIdReferenciaCurso(int idReferenciaCurso) {
        this.idReferenciaCurso = idReferenciaCurso;
    }
    
}
