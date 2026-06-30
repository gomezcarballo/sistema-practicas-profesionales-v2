package spp.logicadenegocio.validaciones.validacionesreportes;

import java.util.List;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;
import spp.logicadenegocio.clasesdto.ReporteFinal;
import java.util.ArrayList;

/**
 *
 * @author gomes
 */
public class ValidacionReporteFinal {
    
    private final int MAX_OBSERVACIONES_GENERALES = 1000;
    private final int MAX_OBSERVACION_FILA = 500;
    private final int MIN_PORCENTAJE_ACTIVIDAD = 0;
    private final int MAX_PORCENTAJE_ACTIVIDAD = 100;

    public List<String> validarReporteFinal(ReporteFinal reporte){

        List<String> listaValidaciones = new ArrayList<>();
        String mensajeAlerta;
        String observaciones = reporte.getObservaciones();

        if(!esTamañoObservacionesValido(observaciones)){
            mensajeAlerta = "Las observaciones generales exceden de " + MAX_OBSERVACIONES_GENERALES + " caracteres";
            listaValidaciones.add(mensajeAlerta);
            
        }

        List<ActividadReporteFinal> entregables = reporte.getEntregables();
        List<ActividadReporteFinal> actividades = reporte.getActividades();

        if(entregables != null  && !entregables.isEmpty()){

            for (ActividadReporteFinal entregable : entregables) {
                
                if(!esTamañoValidoObservacionesActividadValido(entregable)){
                    mensajeAlerta = "Las observaciones de la actividad " + entregable.getNombreActividad() + " exceden de " + MAX_OBSERVACION_FILA + " caracteres";
                    listaValidaciones.add(mensajeAlerta);
                }

                if(!esPorcentajeValido(entregable)){
                    mensajeAlerta = "El porcentaje de la " + entregable.getNombreActividad() + 
                    "debe estar en el rango de " + MIN_PORCENTAJE_ACTIVIDAD + " y " + MAX_PORCENTAJE_ACTIVIDAD;
                    listaValidaciones.add(mensajeAlerta);
                }

            }
            
            if(actividades != null && !actividades.isEmpty()){
                
                for (ActividadReporteFinal actividad : actividades) {

                    if(!esTamañoValidoObservacionesActividadValido(actividad)){
                        mensajeAlerta = "Las observaciones de la actividad " + actividad.getNombreActividad() +
                        " exceden de" + MAX_OBSERVACION_FILA + " caracteres";
                        listaValidaciones.add(mensajeAlerta);
                    }

                    if(!esPorcentajeValido(actividad)){
                        mensajeAlerta = "El porcentaje de la " + actividad.getNombreActividad() + 
                        "debe estar en el rango de" + MIN_PORCENTAJE_ACTIVIDAD + " y " + MAX_PORCENTAJE_ACTIVIDAD;
                        listaValidaciones.add(mensajeAlerta);
                    }

                }
                
            }    

        }

        return listaValidaciones;

    }

    public boolean esTamañoObservacionesValido(String observaciones) {
        
        boolean esTamañoValido = false; 
        if (observaciones != null && !observaciones.isBlank() && observaciones.length() <= MAX_OBSERVACIONES_GENERALES) {
            esTamañoValido = true; 
        }

        return esTamañoValido; 
    }

    public boolean esTamañoValidoObservacionesActividadValido(ActividadReporteFinal actividad){
        
        boolean esTamañoValido = false; 
        String observacionesActividad = actividad.getObservaciones();
        if(observacionesActividad != null && !observacionesActividad.isBlank() && observacionesActividad.length() <= MAX_OBSERVACION_FILA){
            esTamañoValido = true;
        }
        return esTamañoValido;
    }

    public boolean esPorcentajeValido(ActividadReporteFinal actividad){
        
        boolean esPorcentajeValido = false; 
        String porcentajeTexto = actividad.getPorcentajeAvance();
        String procentajeDigitos = porcentajeTexto.trim().replaceAll("[^0-9]","");
        
        if(!procentajeDigitos.isEmpty()){
            
            int porcentajeActividad;

            try{

                porcentajeActividad = Integer.parseInt(procentajeDigitos);
                
                if (porcentajeActividad >= MIN_PORCENTAJE_ACTIVIDAD && porcentajeActividad <= MAX_PORCENTAJE_ACTIVIDAD) {
                    
                    esPorcentajeValido = true;
                
                }

            }catch(NumberFormatException e){

                esPorcentajeValido = false;

            }
        }

        return esPorcentajeValido; 

    }

}
   

