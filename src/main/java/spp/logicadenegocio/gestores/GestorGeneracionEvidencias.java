/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import spp.logicadenegocio.clasesdao.DocumentoDAO;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorGeneracionEvidencias {
    
    private final int MINIMO_MENSUALES = 4;
    private final int MINIMO_PARCIALES = 2;
    private final int MINIMO_FINALES = 1;

    
    public boolean puedeGenerarReporteParcial(int idPracticante)throws OperacionesDeDaoExcepcion {

        PracticanteDAO practicanteDAO = new PracticanteDAO();

        boolean tieneGrupo = practicanteDAO.tieneGrupoAsignado(idPracticante);

        return tieneGrupo;
        
    }
    
    public boolean puedeGenerarReporteFinal(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        boolean puedeGenerar = false;
        DocumentoDAO documentoDAO = new DocumentoDAO();
        
        int mensualesCalificados = documentoDAO.contarDocumentosCalificadosPorTipo(idPracticante, TipoDocumento.REPORTE_MENSUAL);
        int parcialesCalificados = documentoDAO.contarDocumentosCalificadosPorTipo(idPracticante, TipoDocumento.REPORTE_PARCIAL);
        
        if (mensualesCalificados >= MINIMO_MENSUALES && parcialesCalificados >= MINIMO_PARCIALES) {
            
            puedeGenerar = true;
            
        }
        
        return puedeGenerar;
        
    }

    public boolean puedeGenerarAutoevaluacion(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        boolean puedeGenerar = false;
        
        if (puedeGenerarReporteFinal(idPracticante)) {
            
            DocumentoDAO documentoDAO = new DocumentoDAO();
            int finalesCalificados = documentoDAO.contarDocumentosCalificadosPorTipo(idPracticante, TipoDocumento.REPORTE_FINAL);
            
            if (finalesCalificados >= MINIMO_FINALES) {
                
                puedeGenerar = true;
                
            }
            
        }
        
        return puedeGenerar;
        
    }

    public boolean yaSubioReporteFinal(int idPracticante) throws OperacionesDeDaoExcepcion {
        return new DocumentoDAO().verificarExistenciaDocumento(idPracticante, TipoDocumento.REPORTE_FINAL);
    }

    public boolean yaSubioAutoevaluacion(int idPracticante) throws OperacionesDeDaoExcepcion {
        return new DocumentoDAO().verificarExistenciaDocumento(idPracticante, TipoDocumento.AUTOEVALUACION);
    }
    
}
