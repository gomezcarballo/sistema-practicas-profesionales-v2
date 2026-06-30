/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.time.LocalDateTime;
import spp.logicadenegocio.clasesdao.EnvioMensajeDAO;
import spp.logicadenegocio.clasesdao.MensajeDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class GestorNotificaciones {
    
    public boolean notificarPracticante(Practicante practicante, TipoDocumento tipo) throws OperacionesDeDaoExcepcion {
        
        String asunto = "Documento rechazado: " + tipo.getDescripcion();
        
        String cuerpo = "El documento " + tipo.getDescripcion() +
            " que enviaste ha sido rechazado por el coordinador. " +
            "Por favor, revisa los requisitos y súbelo nuevamente.";

        return enviarMensaje(practicante, asunto, cuerpo);
        
    }
    
    public boolean notificarFinDeCurso(Practicante practicante) throws OperacionesDeDaoExcepcion {
        
        String asunto = "¡Felicidades! Prácticas Profesionales Concluidas";
        
        String cuerpo = "Estimado(a) " + practicante.getNombre() + ",\n\n" +
            "Tu Oficio de Liberación ha sido aprobado exitosamente por el coordinador. " +
            "Con esto, has concluido formalmente tus prácticas profesionales.\n\n" +
            "A partir de este momento, tu acceso al sistema se limitará únicamente a la bandeja de mensajes " +
            "para cualquier comunicación final.\n\n" +
            "¡Mucho éxito en tus futuros proyectos!";

        return enviarMensaje(practicante, asunto, cuerpo);
        
    }

    private boolean enviarMensaje(Practicante practicante, String asunto, String cuerpo) throws OperacionesDeDaoExcepcion {
        
        SesionUsuario sesion = SesionUsuario.getInstancia();
        String correoRemitente = sesion.getIdentificador();
        String correoDestinatario = practicante.getCorreoInstitucional();

        Mensaje mensaje = new Mensaje();
        mensaje.setAsunto(asunto);
        mensaje.setCuerpo(cuerpo);
        mensaje.setFecha(LocalDateTime.now());
        mensaje.setCorreoRemitente(correoRemitente);
        mensaje.setCorreoDestinatario(correoDestinatario);

        MensajeDAO mensajeDAO = new MensajeDAO();
        EnvioMensajeDAO envioMensajeDAO = new EnvioMensajeDAO();
        UsuarioDAO usuarioDAO = new UsuarioDAO();

        int idDestinatario = usuarioDAO.buscarIdPorCorreo(correoDestinatario);
        int idMensaje = mensajeDAO.insertarMensaje(mensaje);

        boolean envioExitoso;
        envioExitoso = envioMensajeDAO.insertarEnvioMensaje(
            idMensaje, sesion.getIdUsuario(), idDestinatario);

        return envioExitoso;
        
    }
    
}
