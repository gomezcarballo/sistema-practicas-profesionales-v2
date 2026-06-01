/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author gomes
 */
public class IndicadorReporte {
    
    private String indicador;
    private int valor;

    public IndicadorReporte(String indicador, int valor) {
        
        this.indicador = indicador;
        this.valor = valor;
        
    }

    public IndicadorReporte() {
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }      

    public String getIndicador() {
        return indicador;
    }

    public int getValor() {
        return valor;
    }
    
}
