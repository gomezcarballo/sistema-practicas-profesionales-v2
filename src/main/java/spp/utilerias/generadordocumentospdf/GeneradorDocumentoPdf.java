/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.utilerias.generadordocumentospdf;

import com.openhtmltopdf.pdfboxout.PdfRendererBuilder;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStream;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import org.thymeleaf.templateresolver.ClassLoaderTemplateResolver;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

/**
 *
 * @author gomes
 */
public class GeneradorDocumentoPdf {
    
    public void generarArchivoPdf(String htmlProcesado, String prefijoNombreArchivo)throws ProcesamientoSistemaExcepcion{
        
        String identificador = SesionUsuario.getInstancia().getIdentificador();
        File archivoPdf = crearArchivoPdf(prefijoNombreArchivo + identificador);
        exportarAPdf(htmlProcesado, archivoPdf.getAbsolutePath());
        
    }
        
    public void exportarAPdf(String html, String rutaCompleta) throws ProcesamientoSistemaExcepcion {
        
        try (OutputStream flujoSalida = new FileOutputStream(rutaCompleta)) {
            
            PdfRendererBuilder constructorPdf = new PdfRendererBuilder();
            
            constructorPdf.withHtmlContent(html, null);
            constructorPdf.toStream(flujoSalida);
            constructorPdf.run();
            
        } catch (Exception excepcion) {
            
            throw new ProcesamientoSistemaExcepcion("Ocurrió un error al generar el PDF: " + excepcion.getMessage());
            
        }
        
    }
    
    public String procesarPlantillaHtml(Context contexto, String carpetaPlantilla, String nombrePlantillaHtml) {
        
        ClassLoaderTemplateResolver resolutor = new ClassLoaderTemplateResolver();
        resolutor.setPrefix(carpetaPlantilla);
        resolutor.setSuffix(".html");
        resolutor.setTemplateMode("HTML");
        resolutor.setCharacterEncoding("UTF-8");

        TemplateEngine motorPlantillas = new TemplateEngine();
        motorPlantillas.setTemplateResolver(resolutor);

        return motorPlantillas.process(nombrePlantillaHtml, contexto);
        
    }
    
    public File crearArchivoPdf(String nombreBase){
        
        String rutaInicioUsuario = System.getProperty("user.home");
        String rutaDescargas = rutaInicioUsuario + File.separator + "Downloads";
        String extension = ".pdf";
        
        File archivoPdf = new File(rutaDescargas, nombreBase + extension);
        
        int contador = 1;

        while (archivoPdf.exists()) {
            archivoPdf = new File(rutaDescargas, nombreBase + " (" + contador + ")" + ".pdf");
            contador++;
        }
        
        return archivoPdf;
    }
    
}
