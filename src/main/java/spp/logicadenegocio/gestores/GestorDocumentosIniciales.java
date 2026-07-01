package spp.logicadenegocio.gestores;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.DocumentoDAO;
import spp.logicadenegocio.clasesdto.Documento;
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
