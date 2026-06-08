/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionesdocumentos;

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
    public final long LIMITE_TAMANIO_MB = 50L;
    public final long LIMITE_MAXIMO_BYTES = BYTES_POR_MEGABYTE * LIMITE_TAMANIO_MB; 
    
    public Path crearDireccionArchivo(TipoDocumento tipoDocumento, File archivoSeleccionado) throws ProcesamientoSistemaExcepcion{
        
        validarArchivoSeleccionado(archivoSeleccionado);
    
        String identificador = String.valueOf(SesionUsuario.getInstancia().getIdentificador());
        String rutaProyecto = System.getProperty("user.dir");
        String carpetaTipoDocumento = obtenerNombreCarpeta(tipoDocumento);
        String carpetaGeneralDocumentos = "Documentos_SPP";
        Path rutaCarpetaFinal = Paths.get(rutaProyecto,carpetaGeneralDocumentos ,carpetaTipoDocumento, identificador);
        
        return crearRutaDestino(rutaCarpetaFinal, archivoSeleccionado);

    }
    
    public void guardarDocumento(TipoDocumento tipoDocumento, File archivoSeleccionado) throws OperacionesDeDaoExcepcion, ProcesamientoSistemaExcepcion {
            
            Path rutaArchivo;
            rutaArchivo = guardarDocumentoEnSistema(tipoDocumento, archivoSeleccionado);
            guardarDocumentoEnBaseDatos(tipoDocumento.toString(), archivoSeleccionado, rutaArchivo);
        
    }
    
    public Path guardarDocumentoEnSistema(TipoDocumento tipoDocumento, File archivoSeleccionado) throws ProcesamientoSistemaExcepcion {
        
        try {

            Path direccionFinalArchivo = crearDireccionArchivo(tipoDocumento, archivoSeleccionado);

            Files.copy(archivoSeleccionado.toPath(), direccionFinalArchivo, StandardCopyOption.REPLACE_EXISTING);
            return direccionFinalArchivo;
            
        } catch (IOException e ) {
            throw new ProcesamientoSistemaExcepcion ("No se pudo guardar el documento en el sistema." );
        } 
    }
    
    public void guardarDocumentoEnBaseDatos (String tipoDocumento, File archivoSeleccionado, Path rutaArchivo ) throws OperacionesDeDaoExcepcion{

        Documento documento = crearDocumento(tipoDocumento, archivoSeleccionado, rutaArchivo);

        DocumentoDAO documentoDao = new DocumentoDAO();

        documentoDao.insertarDocumento(documento);  
    }
    
    private Documento crearDocumento(String tipoDocumento, File archivoSeleccionado, Path rutaArchivo){
        
        Documento documento = new Documento();
        
        int identificador = SesionUsuario.getInstancia().getIdUsuario();

        documento.setNombre(archivoSeleccionado.getName());
        documento.setTipo(tipoDocumento);
        String rutaArchivoFinal = rutaArchivo.toString();
        documento.setRuta(rutaArchivoFinal);
        documento.setIdUsuario(identificador);
        
        return documento;
    }

    public void archivoValidoPorReglaDeNegocio(File archivoSeleccionado) throws ReglaDeNegocioExcepcion {
       
        if (archivoSeleccionado.length() > LIMITE_MAXIMO_BYTES){
            throw new ReglaDeNegocioExcepcion("El archivo pesa más de lo permitido");
        }  
        
    }
    
    public void validarArchivoSeleccionado(File archivoSeleccionado)throws ProcesamientoSistemaExcepcion{
        
        if (archivoSeleccionado == null) {
            throw new ProcesamientoSistemaExcepcion("Error: No se recibió ningún archivo para guardar.");
        }
        
    }
    
    private String obtenerNombreCarpeta(TipoDocumento tipoDocumento)throws ProcesamientoSistemaExcepcion{
        
        String carpeta;
        switch (tipoDocumento) {

            case FORMATO_PRESENTACION:
                carpeta = "FormatoPresentacion";
                break;

            case REPORTE_MENSUAL:
                carpeta = "ReportesMensuales";
                break;

            case REPORTE_PARCIAL:
                carpeta = "ReportesParciales";
                break;

            case ACTIVIDAD:
                carpeta = "Actividades";
                break;

            case BITACORA_PSP:
                carpeta = "Bitacora_PSP";
                break;

            case HORARIO:
                carpeta = "Horario";
                break;

            case AUTOEVALUACION:
                carpeta = "Autoevaluacion";
                break;

            case PLAN_ACTIVIDADES:
                carpeta = "PlanActividades";
                break;

            default:
                throw new ProcesamientoSistemaExcepcion("No se encontró una carpeta válida para el documento.");
          
        }
        
        return carpeta;
    }
    
    private Path crearRutaDestino(Path rutaCarpeta, File archivoSeleccionado)throws ProcesamientoSistemaExcepcion{
        
         try {

            Files.createDirectories(rutaCarpeta);

            return rutaCarpeta.resolve(archivoSeleccionado.getName());

        } catch (IOException e) {

            throw new ProcesamientoSistemaExcepcion("No se pudo crear la dirección para guardar el archivo", e);
        
        }
    }

}