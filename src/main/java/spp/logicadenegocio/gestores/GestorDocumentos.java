package spp.logicadenegocio.gestores;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;
import java.util.logging.Level;

import spp.logicadenegocio.clasesdao.DocumentoDAO;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

/**
 *
 * @author gomes
 */
public class GestorDocumentos {
    
    public List<Documento> recuperarDocumentosPorPracticante(Practicante practicante)throws OperacionesDeDaoExcepcion{
 
        DocumentoDAO documentoDAO = new DocumentoDAO();
        return documentoDAO.recuperarDocumentosPorPracticante(practicante.getIdUsuario());
                
    }
    
    public Path crearDireccionArchivo(TipoDocumento tipoDocumento, File archivoSeleccionado) throws ProcesamientoSistemaExcepcion{
        
        String identificador = String.valueOf(SesionUsuario.getInstancia().getIdentificador());
        String rutaProyecto = System.getProperty("user.dir");
        String carpetaTipoDocumento = obtenerNombreCarpeta(tipoDocumento);
        Path rutaCarpetaFinal = null;
        
        if(!carpetaTipoDocumento.isEmpty()){

            String carpetaGeneralDocumentos = "Documentos_SPP";
            rutaCarpetaFinal = Paths.get(rutaProyecto,carpetaGeneralDocumentos ,carpetaTipoDocumento, identificador);
            rutaCarpetaFinal = crearRutaDestino(rutaCarpetaFinal, archivoSeleccionado);

        }else{

            RegistroErrores.registrarMensaje(Level.WARNING, 
                "\nEl nombre de la carpeta según su tipo es nulo. CarpetaTipoDocumento: " + carpetaTipoDocumento);

        }
    
        return rutaCarpetaFinal;
        
    }
    
    public boolean guardarDocumento(TipoDocumento tipoDocumento, File archivoSeleccionado) throws OperacionesDeDaoExcepcion, ProcesamientoSistemaExcepcion {
            
        boolean documentoGuardadoExitosamente = false; 
        Path rutaArchivo;
        rutaArchivo = guardarDocumentoEnSistema(tipoDocumento, archivoSeleccionado);
        
        if(rutaArchivo != null){
            guardarDocumentoEnBaseDatos(tipoDocumento.toString(), archivoSeleccionado, rutaArchivo);
            documentoGuardadoExitosamente = true;
        }

        return documentoGuardadoExitosamente;
        
    }
    
    public Path guardarDocumentoEnSistema(TipoDocumento tipoDocumento, File archivoSeleccionado) throws ProcesamientoSistemaExcepcion {
        
        try {

            Path direccionFinalArchivo = crearDireccionArchivo(tipoDocumento, archivoSeleccionado);
            if(direccionFinalArchivo != null){
                Files.copy(archivoSeleccionado.toPath(), direccionFinalArchivo, StandardCopyOption.REPLACE_EXISTING);
               
            }
            return direccionFinalArchivo;
            
        } catch (IOException e ) {

            RegistroErrores.registrarError(Level.SEVERE, "No se pudo copiar el archivo en la ruta dentro del sistema.", e);
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
    
    private String obtenerNombreCarpeta(TipoDocumento tipoDocumento){
        
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

            case REPORTE_FINAL:
                carpeta = "ReporteFinal";
                break;
            
            case CALENDARIZACION:
                carpeta = "Calendarizacion";
                break;
                
            case OFICIO_ACEPTACION:
                carpeta = "OficioAceptacion";
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
                carpeta = "";
          
        }
        
        return carpeta;
    }
    
    private Path crearRutaDestino(Path rutaCarpeta, File archivoSeleccionado)throws ProcesamientoSistemaExcepcion{
        
         try {

            Files.createDirectories(rutaCarpeta);

            return rutaCarpeta.resolve(archivoSeleccionado.getName());

        } catch (IOException e) {

            RegistroErrores.registrarError(Level.SEVERE, 
                "\nError al crear la ruta destino para guardar el documennto. RutaCarpeta: " + rutaCarpeta , e);

            throw new ProcesamientoSistemaExcepcion("No se pudo crear la dirección para guardar el archivo", e);
        
        }
    }
    
}
