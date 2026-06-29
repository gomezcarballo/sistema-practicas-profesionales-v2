/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

import java.time.LocalDate;

/**
 *
 * @author Luz Fernanda H J
 */
public class Practicante extends Usuario {
    
    private String matricula; 
    private String genero;
    private LocalDate fechaNacimiento;
    private boolean hablaLenguaIndigena;
    //private String nrcAsignado;

    public Practicante() {
    }
/*
    public Practicante(int idUsuario, String nombre, String apellidoPaterno, String apellidoMaterno, 
           String correoInstitucional,String contraseña, boolean esActivo, String matricula, String genero, 
           LocalDate fechaNacimiento, boolean hablaLenguaIndigena, String nrcAsignado) {
        
        super(idUsuario, nombre, apellidoPaterno, apellidoMaterno, correoInstitucional, contraseña, esActivo);
        this.matricula = matricula;
        this.genero = genero;
        this.fechaNacimiento = fechaNacimiento;
        this.hablaLenguaIndigena = hablaLenguaIndigena;
        this.nrcAsignado = nrcAsignado;
        
    }
*/
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

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean getHablaLenguaIndigena() {
        return hablaLenguaIndigena;
    }

    public void setHablaLenguaIndigena(boolean hablaLenguaIndigena) {
        this.hablaLenguaIndigena = hablaLenguaIndigena;
    }
/*
    public String getNrcAsignado() {
        return nrcAsignado;
    }

    public void setNrcAsignado(String nrcAsignado) {
        this.nrcAsignado = nrcAsignado;
    }   
*/
}
