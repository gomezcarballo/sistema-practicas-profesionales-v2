package spp.logicadenegocio.gestores;

import spp.logicadenegocio.clasesdao.DocumentoDAO;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorEvidenciasPracticas {
    
    private final int MINIMO_MENSUALES = 4;
    private final int MINIMO_PARCIALES = 2;
    private final int MINIMO_FINALES = 1;

    public boolean puedeSubirDocumentosSeguimiento(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        DocumentoDAO documentoDAO = new DocumentoDAO();
        PracticanteDAO practicanteDAO = new PracticanteDAO();

        boolean tieneHorario = documentoDAO.verificarExistenciaDocumento(idPracticante, TipoDocumento.HORARIO);
        boolean tienePlan = documentoDAO.verificarExistenciaDocumento(idPracticante, TipoDocumento.PLAN_ACTIVIDADES);
        boolean tieneOficio = documentoDAO.verificarExistenciaDocumento(idPracticante, TipoDocumento.OFICIO_ACEPTACION);
        boolean tieneAsignacion = practicanteDAO.tieneProyectoYGrupoAsignado(idPracticante);

        return tieneHorario && tienePlan && tieneOficio && tieneAsignacion;
        
    }

    public boolean puedeSubirReporteFinal(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        boolean puedeSubir = false;
        DocumentoDAO documentoDAO = new DocumentoDAO();
        
        int mensualesCalificados = documentoDAO.contarDocumentosCalificadosPorTipo(idPracticante, TipoDocumento.REPORTE_MENSUAL);
        int parcialesCalificados = documentoDAO.contarDocumentosCalificadosPorTipo(idPracticante, TipoDocumento.REPORTE_PARCIAL);
        
        if (mensualesCalificados >= MINIMO_MENSUALES && parcialesCalificados >= MINIMO_PARCIALES) {
            
            puedeSubir = true;
            
        }
        
        return puedeSubir;
        
    }

    public boolean puedeSubirAutoevaluacion(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        boolean puedeSubir = false;
        
        if (puedeSubirReporteFinal(idPracticante)) {
            
            DocumentoDAO documentoDAO = new DocumentoDAO();
            int finalesCalificados = documentoDAO.contarDocumentosCalificadosPorTipo(idPracticante, TipoDocumento.REPORTE_FINAL);
            
            if (finalesCalificados >= MINIMO_FINALES) {
                
                puedeSubir = true;
                
            }
            
        }

        return puedeSubir;
        
    }
    
    public boolean yaSubioHorario(int idPracticante) throws OperacionesDeDaoExcepcion {
        return new DocumentoDAO().verificarExistenciaDocumento(idPracticante, TipoDocumento.HORARIO);
    }

    public boolean yaSubioPlanActividades(int idPracticante) throws OperacionesDeDaoExcepcion {
        return new DocumentoDAO().verificarExistenciaDocumento(idPracticante, TipoDocumento.PLAN_ACTIVIDADES);
    }

    public boolean yaSubioOficioAceptacion(int idPracticante) throws OperacionesDeDaoExcepcion {
        return new DocumentoDAO().verificarExistenciaDocumento(idPracticante, TipoDocumento.OFICIO_ACEPTACION);
    }

    public boolean yaSubioLimitesMensuales(int idPracticante) throws OperacionesDeDaoExcepcion {
        return new DocumentoDAO().contarDocumentosPorTipo(idPracticante, TipoDocumento.REPORTE_MENSUAL) >= MINIMO_MENSUALES;
    }

    public boolean yaSubioLimitesParciales(int idPracticante) throws OperacionesDeDaoExcepcion {
        return new DocumentoDAO().contarDocumentosPorTipo(idPracticante, TipoDocumento.REPORTE_PARCIAL) >= MINIMO_PARCIALES;
    }

    public boolean yaSubioReporteFinal(int idPracticante) throws OperacionesDeDaoExcepcion {
        return new DocumentoDAO().verificarExistenciaDocumento(idPracticante, TipoDocumento.REPORTE_FINAL);
    }

    public boolean yaSubioBitacoraPSP(int idPracticante) throws OperacionesDeDaoExcepcion {
        return new DocumentoDAO().verificarExistenciaDocumento(idPracticante, TipoDocumento.BITACORA_PSP);
    }

    public boolean yaSubioAutoevaluacion(int idPracticante) throws OperacionesDeDaoExcepcion {
        return new DocumentoDAO().verificarExistenciaDocumento(idPracticante, TipoDocumento.AUTOEVALUACION);
    }
    
}
