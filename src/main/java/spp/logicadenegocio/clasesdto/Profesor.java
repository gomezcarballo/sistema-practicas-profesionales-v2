/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

import java.util.Objects;

/**
 *
 * @author Luz Fernanda H J
 */
public class Profesor extends Usuario{
    
    private String numeroDePersonal;

    public Profesor() {
    }

    public Profesor(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno,
           String correoInstitucional, String contraseña, boolean esActivo,String numeroDePersonal) {
        
        super(idUsuario, nombre, apellidoPaterno, apellidoMaterno, correoInstitucional, contraseña, esActivo);
        this.numeroDePersonal = numeroDePersonal;
        
    }
    
    public String getNumeroDePersonal() {
        return numeroDePersonal;
    }

    public void setNumeroDePersonal(String numeroDePersonal) {
        this.numeroDePersonal = numeroDePersonal;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.numeroDePersonal);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Profesor other = (Profesor) obj;
        return Objects.equals(this.numeroDePersonal, other.numeroDePersonal);
    }
    
    
}
