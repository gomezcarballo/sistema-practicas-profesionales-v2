/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

import java.util.Date;

/**
 *
 * @author Luz Fernanda H J
 */
public class Practicante extends Usuario {
    
    private String matricula; 
    private String genero;
    private Date fechaNacimiento;
    private boolean hablaLenguaIndigena;

    public Practicante() {
    }

    public Practicante(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, 
           String contraseña, boolean esActivo, String matricula, String genero, Date fechaNacimiento, 
           boolean hablaLenguaIndigena) {
        
        super(idUsuario, nombre, apellidoPaterno, apellidoMaterno, contraseña,esActivo);
        this.matricula = matricula;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.hablaLenguaIndigena = hablaLenguaIndigena;
        
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean gethablaLenguaIndigena() {
        return hablaLenguaIndigena;
    }

    public void setHablaLenguaIndigena(boolean hablaLenguaIndigena) {
        this.hablaLenguaIndigena = hablaLenguaIndigena;
    }

}
