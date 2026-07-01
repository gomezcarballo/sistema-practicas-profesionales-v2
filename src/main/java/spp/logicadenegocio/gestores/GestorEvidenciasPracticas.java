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
        
        boolean puedeSubir = false;
        
        DocumentoDAO documentoDAO = new DocumentoDAO();
        PracticanteDAO practicanteDAO = new PracticanteDAO();

        boolean horarioAprobado = documentoDAO.verificarDocumentoAprobado(idPracticante, TipoDocumento.HORARIO);
        boolean planAprobado = documentoDAO.verificarDocumentoAprobado(idPracticante, TipoDocumento.PLAN_ACTIVIDADES);
        boolean oficioAprobado = documentoDAO.verificarDocumentoAprobado(idPracticante, TipoDocumento.OFICIO_ACEPTACION);
        
        boolean tieneAsignacion = practicanteDAO.tieneProyectoYGrupoAsignado(idPracticante);

        if (horarioAprobado && planAprobado && oficioAprobado && tieneAsignacion) {
            
            puedeSubir = true;
            
        }

        return puedeSubir;
        
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
    
    private boolean verificarSiExisteDocumento(int idPracticante, TipoDocumento tipoDocumento) throws OperacionesDeDaoExcepcion {
        
        DocumentoDAO documentoDao = new DocumentoDAO();
        boolean bloqueado = false;
        boolean existe = documentoDao.verificarExistenciaDocumento(idPracticante, tipoDocumento);

        if (existe) {
            
            boolean rechazado = documentoDao.verificarDocumentoRechazado(idPracticante, tipoDocumento);
            bloqueado = !rechazado;
            
        }

        return bloqueado;
        
    }
    
    public boolean yaSubioHorarioAprobado(int idPracticante) throws OperacionesDeDaoExcepcion {

        return verificarSiExisteDocumento(idPracticante, TipoDocumento.HORARIO);

    }

    public boolean yaSubioPlanActividades(int idPracticante) throws OperacionesDeDaoExcepcion {

        return verificarSiExisteDocumento(idPracticante, TipoDocumento.PLAN_ACTIVIDADES);

    }

    public boolean yaSubioOficioAceptacion(int idPracticante) throws OperacionesDeDaoExcepcion {

        return verificarSiExisteDocumento(idPracticante, TipoDocumento.OFICIO_ACEPTACION);

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
    
    public boolean puedeSubirOficioLiberacion(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        boolean puedeSubir = false;
        DocumentoDAO documentoDAO = new DocumentoDAO();
        
        int autoevaluacionesCalificadas = documentoDAO.contarDocumentosCalificadosPorTipo(idPracticante, TipoDocumento.AUTOEVALUACION);
        
        if (autoevaluacionesCalificadas > 0) {
            
            puedeSubir = true;
            
        }
        
        return puedeSubir;
        
    }
    
    public boolean yaSubioOficioLiberacion(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        return new DocumentoDAO().verificarExistenciaDocumento(idPracticante, TipoDocumento.OFICIO_LIBERACION);
        
    }
    
}
