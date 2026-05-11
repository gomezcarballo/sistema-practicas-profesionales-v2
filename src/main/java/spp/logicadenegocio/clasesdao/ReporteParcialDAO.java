/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdao;

import com.lowagie.text.Document;
import com.lowagie.text.DocumentException;
import com.lowagie.text.Paragraph;
import com.lowagie.text.pdf.PdfWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.ReporteParcial;
import spp.logicadenegocio.interfacesdao.IReporteParcialDAO;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class ReporteParcialDAO implements IReporteParcialDAO{
    
    @Override
    public Documento generarReporteParcial(ReporteParcial reporteParcial) throws OperacionesDeDaoExcepcion {
        
        Documento documentoGenerado = new Documento();
        
        try( Document documento = new Document() ){
            
            String rutaRaizUsuario = System.getProperty("user.home");
            String nombreArchivo = "Reporte_Parcial" + ".pdf";
            String rutaDelArchivo = rutaRaizUsuario + File.separator + "Downloads" + File.separator + nombreArchivo;
            PdfWriter.getInstance( documento, new FileOutputStream(rutaDelArchivo) );
            
            documento.open();
            
            documento.add( new Paragraph("Sistema de Gestión de Practicas Profesionales") );
            documento.add( new Paragraph("---------------------------------------------") );
            documento.add( new Paragraph("Matricula del practicante: " + reporteParcial.getMatricula() ));
            documento.add( new Paragraph("Tipo de reporte: " + reporteParcial.getTipoReporte() ));
            documento.add( new Paragraph("NRC: " + reporteParcial.getNrc()));
            documento.add( new Paragraph("Periodo Escolar: " + reporteParcial.getPeriodoEscolar() ));
            documento.add( new Paragraph("Tiempo planeado: " + reporteParcial.getTiempoPlaneado() ));
            documento.add( new Paragraph("Tiempo real: " + reporteParcial.getTiempoReal() ));
            documento.add( new Paragraph("Fecha de inicio: " + reporteParcial.getFechaInicio() ));
            documento.add( new Paragraph("Fecha de termino: " + reporteParcial.getFechaTermino() ));
            
            documentoGenerado.setNombre(nombreArchivo);
            documentoGenerado.setRuta(rutaDelArchivo);
            documentoGenerado.setTipo(reporteParcial.getTipoReporte());
            
            
        }catch(FileNotFoundException e){
             throw new OperacionesDeDaoExcepcion("No se pudo encontrar el archivo ",e);
             
        }catch (DocumentException e){
             throw new OperacionesDeDaoExcepcion("No es posible escribir en el reporte",e);
        }
        
        return documentoGenerado;
    }
    
}
