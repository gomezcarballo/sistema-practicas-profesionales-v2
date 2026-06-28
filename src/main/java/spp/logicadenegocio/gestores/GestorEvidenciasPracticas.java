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

    public boolean puedeSubirAutoevaluacion(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        DocumentoDAO documentoDAO = new DocumentoDAO();

        int totalMensuales = documentoDAO.contarDocumentosPorTipo(idPracticante, TipoDocumento.REPORTE_MENSUAL);
        int totalParciales = documentoDAO.contarDocumentosPorTipo(idPracticante, TipoDocumento.REPORTE_PARCIAL);
        int totalFinales = documentoDAO.contarDocumentosPorTipo(idPracticante, TipoDocumento.REPORTE_FINAL);

        return (totalMensuales >= MINIMO_MENSUALES) && 
               (totalParciales >= MINIMO_PARCIALES) && 
               (totalFinales >= MINIMO_FINALES);
               
    }
    
}
