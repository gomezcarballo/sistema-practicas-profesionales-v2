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

    public boolean notificarPracticante(Practicante practicante, TipoDocumento tipo)  throws OperacionesDeDaoExcepcion{
        
        SesionUsuario sesion = SesionUsuario.getInstancia();
        String correoRemitente = sesion.getIdentificador();
        String correoDestinatario = practicante.getCorreoInstitucional();

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto("Documento rechazado: " + tipo.getDescripcion());
       
        mensaje.setCuerpo(
            "El documento " + tipo.getDescripcion() +
            " que enviaste ha sido rechazado por el coordinador. " +
            "Por favor, revisa los requisitos y súbelo nuevamente."
        );

        mensaje.setFecha(LocalDateTime.now());
        mensaje.setCorreoRemitente(correoRemitente);
        mensaje.setCorreoDestinatario(correoDestinatario);

        MensajeDAO mensajeDAO = new MensajeDAO();
        EnvioMensajeDAO envioMensajeDAO = new EnvioMensajeDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        int idDestinatario = usuarioDAO.buscarIdPorCorreo(correoDestinatario);
        int idMensaje = mensajeDAO.insertarMensaje(mensaje);

        boolean envioExitoso;
        envioExitoso =  envioMensajeDAO.insertarEnvioMensaje(
            idMensaje, sesion.getIdUsuario(), idDestinatario);

        return envioExitoso;

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
