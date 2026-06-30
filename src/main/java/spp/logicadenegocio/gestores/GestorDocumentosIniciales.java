package spp.logicadenegocio.gestores;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.DocumentoDAO;
import spp.logicadenegocio.clasesdao.EnvioMensajeDAO;
import spp.logicadenegocio.clasesdao.MensajeDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

public class GestorDocumentosIniciales {
    
    private DocumentoDAO documentoDao;

    public Documento obtenerDocumentoInicial(int idPracticante, TipoDocumento tipo) throws OperacionesDeDaoExcepcion {
            
        documentoDao = new DocumentoDAO();
        return documentoDao.buscarDocumentoPorUsuarioYTipo(idPracticante, tipo.name());
        
    }

    public boolean actualizarEstadoDocumento(Documento documento, String estadoDocumentoActualizar) throws OperacionesDeDaoExcepcion {
        
        boolean esDocumentoActualizado;
        documentoDao = new DocumentoDAO();

        documento.setEstadoDocumento(estadoDocumentoActualizar);
        esDocumentoActualizado = documentoDao.actualizarEstadoDocumento(documento);

        return esDocumentoActualizado;
    }

    public boolean eliminarDocumento(int idDocumento) throws OperacionesDeDaoExcepcion {
        
        boolean esDocumentoEliminado;
        documentoDao = new DocumentoDAO();

        esDocumentoEliminado = documentoDao.eliminarDocumentoPorId(idDocumento);

        return esDocumentoEliminado;
    }

    public void abrirDocumento(Documento documento) throws ProcesamientoSistemaExcepcion {

        File archivo = new File(documento.getRuta());
        try {

            Desktop.getDesktop().open(archivo);

        } catch (IOException e) {

            RegistroErrores.registrarError(Level.SEVERE,
                "Error al abrir el documento con ruta: " + documento.getRuta(), e);

            throw new ProcesamientoSistemaExcepcion("No se pudo abrir el archivo. " +
                "Verifique que tenga un programa asociado para este tipo de archivo.", e);
        }
    }

    public boolean rechazarDocumento(Documento documento, Practicante practicante) throws OperacionesDeDaoExcepcion {

        boolean esArchivoEliminado = false;
        boolean esArchivoEliminadoEnSistema = eliminarDocumentoEnSistema(documento);
        boolean esArchivoEliminadoEnBaseDatos = eliminarDocumentoEnBaseDatos(documento);

        if(esArchivoEliminadoEnBaseDatos && esArchivoEliminadoEnSistema){
            esArchivoEliminado = true;
        }
        if(esArchivoEliminadoEnSistema){
            RegistroErrores.registrarMensaje(Level.SEVERE,"\nEl documento: " + documento.getIdDocumento() +
                    " no se pudo eliminar del sistema.");
        }
        if(esArchivoEliminadoEnBaseDatos){
            RegistroErrores.registrarMensaje(Level.SEVERE, "\nEl documento: " + documento.getIdDocumento() +
                    " no se pudo eliminar de la base de datos");
        }

        return esArchivoEliminado;

    }

    private boolean eliminarDocumentoEnSistema(Documento documento){

        boolean esArchivoEliminado = false;

        if (documento != null){

            File archivo = new File(documento.getRuta());

            if (archivo.exists() && !archivo.delete()) {

                esArchivoEliminado = false;

            }else{

                esArchivoEliminado = true;
            }

        }
        return esArchivoEliminado;

    }

    private boolean eliminarDocumentoEnBaseDatos(Documento documento) throws OperacionesDeDaoExcepcion{

        boolean esArchivoEliminado = false;

        if (eliminarDocumento(documento.getIdDocumento())) {

            esArchivoEliminado = true;

        }

        return esArchivoEliminado;

    }

    public boolean verificarDocumentosInicialesAprobados(int idPracticante) throws OperacionesDeDaoExcepcion {

        DocumentoDAO documentoDao = new DocumentoDAO();

        TipoDocumento[] documentosIniciales = {
                TipoDocumento.HORARIO,
                TipoDocumento.PLAN_ACTIVIDADES,
                TipoDocumento.OFICIO_ACEPTACION
        };

        boolean todosAprobados = true;

        for (TipoDocumento tipo : documentosIniciales) {
            boolean estaAprobado = documentoDao.verificarDocumentoAprobado(idPracticante, tipo);
            if (!estaAprobado) {
                todosAprobados = false;

            }
        }

        return todosAprobados;

    }

}
