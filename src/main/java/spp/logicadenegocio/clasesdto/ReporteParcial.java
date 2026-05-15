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
public class ReporteParcial {
    
    String matricula;
    String periodoEscolar;
    int horasCubiertas;
    int tiempoPlaneado;
    int tiempoReal;
    LocalDate fechaInicio;
    LocalDate fechaTermino;
    String tipoReporte;

    public String getMatricula(){
        return matricula;
    }
    public void setMatricula(String matricula){
        this.matricula = matricula;
    }
    
    public String getPeriodoEscolar() {
        return periodoEscolar;
    }

    public void setPeriodoEscolar(String periodoEscolar) {
        this.periodoEscolar = periodoEscolar;
    }

    public int getHorasCubiertas() {
        return horasCubiertas;
    }

    public void setHorasCubiertas(int horasCubiertas) {
        this.horasCubiertas = horasCubiertas;
    }

    public int getTiempoPlaneado() {
        return tiempoPlaneado;
    }

    public void setTiempoPlaneado(int tiempoPlaneado) {
        this.tiempoPlaneado = tiempoPlaneado;
    }

    public int getTiempoReal() {
        return tiempoReal;
    }

    public void setTiempoReal(int tiempoReal) {
        this.tiempoReal = tiempoReal;
    }

    public LocalDate getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(LocalDate fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public LocalDate getFechaTermino() {
        return fechaTermino;
    }

    public void setFechaTermino(LocalDate fechaTermino) {
        this.fechaTermino = fechaTermino;
    }

    public String getTipoReporte() {
        return tipoReporte;
    }

    public void setTipoReporte(String tipoReporte) {
        this.tipoReporte = tipoReporte;
    }
    
    
}
