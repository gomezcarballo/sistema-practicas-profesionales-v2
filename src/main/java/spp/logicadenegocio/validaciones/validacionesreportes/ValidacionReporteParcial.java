package spp.logicadenegocio.validaciones.validacionesreportes;

import java.util.ArrayList;
import java.util.List;

import spp.logicadenegocio.clasesdto.ActividadReporteParcial;

/**
 *
 * @author gomes
 */
public class ValidacionReporteParcial {

    private final int MAX_CARACTERES_DESCRIPCION = 250;
    private final int TAMAÑO_REQUERIDO_SEMANAS = 8; 
    private final int MAX_CARACTERES_RESULTADO  = 1000; 
    private final int MAX_CARACTESRES_OBSERVACIONES = 1000;
    private final int MAX_CARACTERES_PERIODO = 20;
    
    public List<String> validarTamañoActividad(ActividadReporteParcial actividad){
        
        List<String> listaValidaciones = new ArrayList<>();
        String mensajeAlerta;
        String descripcion = actividad.getDescripcion();
        int tiempoPlaneado = actividad.getHorasPlaneadas();
        int tiempoReal = actividad.getHorasReales();

        if (!esDescripcionValida(descripcion)) {
            
            mensajeAlerta = "La descripción excede de " + MAX_CARACTERES_DESCRIPCION + " caracteres";
            listaValidaciones.add(mensajeAlerta);

        }

        if(!esTiempoValido(tiempoPlaneado)){

            mensajeAlerta = "El tiempo planeado es de " + MAX_CARACTERES_DESCRIPCION + " horas maximas";
            listaValidaciones.add(mensajeAlerta);

        }
        
        if(!esTiempoValido(tiempoReal)){
            
            mensajeAlerta = "El tiempo real es de " + MAX_CARACTERES_DESCRIPCION + " horas maximas";
            listaValidaciones.add(mensajeAlerta);
        
        }
        
        return listaValidaciones;
    }
    
    private boolean esDescripcionValida(String descripcion){

        boolean esDescripcionValida = false;

        if (descripcion != null && !descripcion.isBlank() && descripcion.length() <= MAX_CARACTERES_DESCRIPCION) {
            esDescripcionValida = true;
        }

        return esDescripcionValida;
    }

    private boolean esTiempoValido (int tiempo){

        boolean esTiempoValido = false;

        if(tiempo != 0 && tiempo <= TAMAÑO_REQUERIDO_SEMANAS){
            esTiempoValido = true;
        }
        
        return esTiempoValido;

    }
    
    private boolean esResultadoValido(String resultado){

        boolean esResultadoValido = false;

        if (resultado != null && !resultado.isBlank() && resultado.length() <= MAX_CARACTERES_RESULTADO) {
            esResultadoValido = true;
        }

        return esResultadoValido;
    }

    private boolean esPeriodoValido(String periodo){
        
        boolean esResultadoValido = false; 
        if(periodo != null && periodo.length() <= MAX_CARACTERES_PERIODO){
            esResultadoValido = true; 
        }
        return esResultadoValido; 
    }

    private boolean esObservacionesValida(String observaciones){
        
        boolean esResultadoValido = false; 
        if(observaciones != null && !observaciones.isBlank() && observaciones.length() <= MAX_CARACTESRES_OBSERVACIONES ){
            esResultadoValido = true;
        }

        return esResultadoValido;
    }

    public List<String> validarTamañoReporte(String periodo, String resultado, String observaciones) {
        
        List<String> listaValidaciones = new ArrayList<>();
        String mensajeAlerta; 

        if(!esPeriodoValido(periodo)){

            mensajeAlerta = "El periodo  " + MAX_CARACTERES_PERIODO + " caracteres";
            listaValidaciones.add(mensajeAlerta);

        }

        if (!esResultadoValido(resultado)) {
            
            mensajeAlerta = "El resultado excede de " + MAX_CARACTERES_RESULTADO + " caracteres";
            listaValidaciones.add(mensajeAlerta);

        }

        if(!esObservacionesValida(observaciones)){

            mensajeAlerta = "Las observaciones excede de " + MAX_CARACTESRES_OBSERVACIONES + " caracteres";
            listaValidaciones.add(mensajeAlerta);

        }

        return listaValidaciones; 
        
    }
    
}
