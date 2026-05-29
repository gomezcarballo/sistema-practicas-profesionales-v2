/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesdocumentos;

import spp.logicadenegocio.clasesdao.DocumentoDAO;
import spp.logicadenegocio.clasesdao.ReporteParcialDAO;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionesReporteParcial {
    
    public void generarReporteParcial(ReporteParcial reporteParcial) throws ReglaDeNegocioExcepcion {
        
        sonCamposValidosPorReglaNegocio(reporteParcial);
        
        ReporteParcialDAO reporteParcialDao = new ReporteParcialDAO();
        DocumentoDAO documentoDao = new DocumentoDAO();
        Documento documento;
        Usuario usuario = new Usuario();
        
        try{
            
            documento = reporteParcialDao.generarReporteParcial(reporteParcial);
            SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
            
            int idUsuario= sesionUsuario.getIdUsuario();
            documento.setIdUsuario(idUsuario);
            documentoDao.insertarDocumento(documento);
            
            
        }catch(OperacionesDeDaoExcepcion e){
            throw new ReglaDeNegocioExcepcion("No se pudo generar el reporte parcial.", e);
        }
    }
    
    public void sonCamposValidosPorReglaNegocio(ReporteParcial reporteParcial) throws ReglaDeNegocioExcepcion {
        
        int horasCubiertas = reporteParcial.getHorasCubiertas();
        int tiempoPlaneado = reporteParcial.getTiempoPlaneado();
        int tiempoReal = reporteParcial.getTiempoReal();

        if( horasCubiertas < 0 ){
            throw new ReglaDeNegocioExcepcion("Las horas cubiertas deben ser mayor a 0 horas");
        }
        
        if( tiempoPlaneado < 0 ){
            throw new ReglaDeNegocioExcepcion("El tiempo planeado deben ser mayor a 0 horas");
        }
        
        if( tiempoReal < 0 ){
            throw new ReglaDeNegocioExcepcion("El tiempo real deben ser mayor a 0 horas");
        }
        
    }
}
