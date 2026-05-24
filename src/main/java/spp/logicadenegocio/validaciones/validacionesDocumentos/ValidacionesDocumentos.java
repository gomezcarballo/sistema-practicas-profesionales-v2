/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesDocumentos;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import spp.logicadenegocio.clasesdao.DocumentoDAO;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;


/**
 *
 * @author Luz Fernanda H J
 */
public class ValidacionesDocumentos {
    
    public final long BYTES_POR_MEGABYTE = 1024L * 1024L;
    public final long LIMITE_TAMANIO_MB = 5L;
    public final long LIMITE_MAXIMO_BYTES = BYTES_POR_MEGABYTE * LIMITE_TAMANIO_MB; 
    
    public Path crearDireccionArchivo(TipoDocumento tipoDocumento, File archivoSeleccionado) throws ProcesamientoSistemaExcepcion{
        
        if (archivoSeleccionado == null) {
            throw new ProcesamientoSistemaExcepcion("Error: No se recibió ningún archivo para guardar.");
        }
        
        String identificador = String.valueOf(SesionUsuario.getInstancia().getIdentificador());

        String rutaProyecto = System.getProperty("user.dir");
        String carpetaDocumentosSistema = "Documentos_SPP";
        Path rutaCarpetaFinal; 

        switch (tipoDocumento) {
            case FORMATO_PRESENTACION -> {
                rutaCarpetaFinal = Paths.get(rutaProyecto, carpetaDocumentosSistema, "FormatoPresentacion", identificador);
            }
            case REPORTE_MENSUAL -> {
                rutaCarpetaFinal = Paths.get(rutaProyecto, carpetaDocumentosSistema, "ReportesMensuales", identificador);
            }
            case REPORTE_PARCIAL -> {
                rutaCarpetaFinal = Paths.get(rutaProyecto, carpetaDocumentosSistema, "ReportesParciales", identificador);
            }
            case ACTIVIDAD -> {
                rutaCarpetaFinal = Paths.get(rutaProyecto, carpetaDocumentosSistema, "Actividades", identificador);
            }
            case BITACORA_PSP -> {
                rutaCarpetaFinal = Paths.get(rutaProyecto, carpetaDocumentosSistema, "Bitacora_PSP", identificador);
            }
            case HORARIO -> {
                rutaCarpetaFinal = Paths.get(rutaProyecto, carpetaDocumentosSistema, "Horario", identificador);
            }
            case AUTOEVALUACION -> {
                rutaCarpetaFinal = Paths.get(rutaProyecto, carpetaDocumentosSistema, "Autoevaluacion", identificador);
            }
            case PLAN_ACTIVIDADES -> {
                rutaCarpetaFinal = Paths.get(rutaProyecto, carpetaDocumentosSistema, "Autoevaluacion", identificador);
            }
            
            default -> {
                throw new ProcesamientoSistemaExcepcion("No se pudo crear la dirección, no coincide con ningún tipo de archivo permitido");
            }
        }
       
        try{   
            
            Files.createDirectories(rutaCarpetaFinal);
            Path destinoCompleto = rutaCarpetaFinal.resolve(archivoSeleccionado.getName()); 
            return destinoCompleto; 
            
        }catch(IOException e){
            throw new ProcesamientoSistemaExcepcion("No se pudo crear la dirección para guardar el archivo",e);
        }
    }
    
    public void guardarDocumento(TipoDocumento tipoDocumento, File archivoSeleccionado) throws OperacionesDeDaoExcepcion, ProcesamientoSistemaExcepcion {
            
            Path rutaArchivo;
            rutaArchivo = guardarDocumentoEnSistema(tipoDocumento, archivoSeleccionado);
            guardarDocumentoEnBaseDatos(tipoDocumento.toString(), archivoSeleccionado, rutaArchivo);
        
    }
    
    public Path guardarDocumentoEnSistema(TipoDocumento tipoDocumento, File archivoSeleccionado) throws ProcesamientoSistemaExcepcion {
        
        try {

            Path direccionFinalArchivo;
            direccionFinalArchivo = crearDireccionArchivo(tipoDocumento, archivoSeleccionado);

            Files.copy(archivoSeleccionado.toPath(), direccionFinalArchivo, StandardCopyOption.REPLACE_EXISTING);
            return direccionFinalArchivo;
            
        } catch (IOException e ) {
            throw new ProcesamientoSistemaExcepcion ("No se pudo guardar el documento en el sistema." );
        } 
    }
    
    public void guardarDocumentoEnBaseDatos (String tipoDocumento, File archivoSeleccionado, Path rutaArchivo ) throws OperacionesDeDaoExcepcion{

        Documento documento = new Documento();
        DocumentoDAO documentoDao = new DocumentoDAO();
        int identificador = SesionUsuario.getInstancia().getIdUsuario();

        documento.setNombre(archivoSeleccionado.getName());
        documento.setTipo(tipoDocumento);
        String rutaArchivoFinal = rutaArchivo.toString();
        documento.setRuta(rutaArchivoFinal);
        documento.setIdUsuario(identificador);

        documentoDao.insertarDocumento(documento);  
    }

    public void archivoValidoPorReglaDeNegocio(File archivoSeleccionado) throws ReglaDeNegocioExcepcion {
       
        if (archivoSeleccionado.length() > LIMITE_MAXIMO_BYTES){
            throw new ReglaDeNegocioExcepcion("El archivo pesa más de lo permitido");
        }  
        
    }
}
