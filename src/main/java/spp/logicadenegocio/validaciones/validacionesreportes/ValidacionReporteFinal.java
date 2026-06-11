/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesreportes;

import java.util.List;
import spp.logicadenegocio.clasesdto.ActividadReporteFinal;
import spp.logicadenegocio.clasesdto.ReporteFinal;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author gomes
 */
public class ValidacionReporteFinal {
    
    public void validarDatosReporte(ReporteFinal reporte) throws ReglaDeNegocioExcepcion {
        
        int maximoCaracteresObservacionesGenerales = 1000;
        
        if (reporte.getObservaciones() != null && reporte.getObservaciones().length() > maximoCaracteresObservacionesGenerales) {
            
            throw new ReglaDeNegocioExcepcion("Las observaciones generales exceden el tamaño máximo permitido de " 
            + maximoCaracteresObservacionesGenerales + " caracteres.");
            
        }

        validarListaActividades(reporte.getActividades());
        validarListaActividades(reporte.getEntregables());
        
    }

    private void validarListaActividades(List<ActividadReporteFinal> lista) throws ReglaDeNegocioExcepcion {
        
        int maximoCaracteresObservacionFila = 500;

        for (ActividadReporteFinal actividad : lista) {
            
            if (actividad.getObservaciones() != null && actividad.getObservaciones().length() > maximoCaracteresObservacionFila) {
                
                throw new ReglaDeNegocioExcepcion("La observación en la actividad '" 
                + actividad.getNombreActividad() + "' excede el límite de " 
                + maximoCaracteresObservacionFila + " caracteres.");
                
            }

            try {
                
                int avance = Integer.parseInt(actividad.getPorcentajeAvance().trim());
                
                if (avance < 0 || avance > 100) {
                    
                    throw new ReglaDeNegocioExcepcion("El porcentaje de avance en la actividad '" 
                    + actividad.getNombreActividad() + "' debe ser un valor entre 0 y 100.");
                    
                }
                
            } catch (NumberFormatException e) {
                
                throw new ReglaDeNegocioExcepcion("El porcentaje de avance en la actividad '" 
                + actividad.getNombreActividad() + "' contiene texto o caracteres no válidos. "
                + "Por favor, ingrese únicamente números.");
                
            }
            
        }
        
    }
    
}
