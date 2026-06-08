/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit4TestClass.java to edit this template
 */
package spp.pruebasclasesdao;

import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import spp.logicadenegocio.clasesdao.EnvioMensajeDAO;
import spp.logicadenegocio.clasesdao.MensajeDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Mensaje;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author gomes
 */
public class PruebaMensajeDAO {
    
    private MensajeDAO mensajeDAO;
    private EnvioMensajeDAO envioMensajeDAO;
    private UsuarioDAO usuarioDAO;

    private int idMensajePrueba;
    private int idRemitentePrueba;
    private int idDestinatarioPrueba;

    @Before
    public void inicializarDatosPrueba() {

        mensajeDAO = new MensajeDAO();
        envioMensajeDAO = new EnvioMensajeDAO();
        usuarioDAO = new UsuarioDAO();

        idMensajePrueba = 0;
        idRemitentePrueba = 0;
        idDestinatarioPrueba = 0;
        
    }

    @After
    public void eliminarDatosPrueba() throws OperacionesDeDaoExcepcion {

        if (idMensajePrueba > 0 && idRemitentePrueba > 0 && idDestinatarioPrueba > 0) {

            envioMensajeDAO.eliminarEnvioMensaje(idMensajePrueba, idRemitentePrueba, idDestinatarioPrueba);
            
        }

        if (idMensajePrueba > 0) {
            mensajeDAO.eliminarMensaje(idMensajePrueba);
        }

        if (idRemitentePrueba > 0) {
            usuarioDAO.eliminarUsuario(idRemitentePrueba);
        }

        if (idDestinatarioPrueba > 0) {
            usuarioDAO.eliminarUsuario(idDestinatarioPrueba);
        }

        idMensajePrueba = 0;
        idRemitentePrueba = 0;
        idDestinatarioPrueba = 0;
        
    }

    private Usuario crearUsuario(String prefijo) {

        Usuario usuario = new Usuario();

        usuario.setNombre("Taylor");
        usuario.setApellidoPaterno("Swift");
        usuario.setApellidoMaterno("Perez");
        usuario.setCorreoInstitucional(prefijo + "_" + System.currentTimeMillis() + "@uv.mx");
        usuario.setContraseña("123456");
        usuario.setEsActivo(true);

        return usuario;
        
    }

    private Mensaje crearMensaje() {

        Mensaje mensaje = new Mensaje();

        mensaje.setAsunto("Asunto de prueba");
        mensaje.setCuerpo("Cuerpo de prueba");

        return mensaje;
        
    }

    @Test
    public void pruebaInsertarMensajeExitoso()throws OperacionesDeDaoExcepcion {

        Mensaje mensaje = crearMensaje();

        idMensajePrueba = mensajeDAO.insertarMensaje(mensaje);

        assertTrue(idMensajePrueba > 0);
        
    }

    @Test
    public void pruebaConsultarMensajesPorDestinatarioExistente()throws OperacionesDeDaoExcepcion {

        Usuario remitente = crearUsuario("remitente");
        Usuario destinatario = crearUsuario("destinatario");

        idRemitentePrueba = usuarioDAO.insertarUsuario(remitente);

        idDestinatarioPrueba = usuarioDAO.insertarUsuario(destinatario);

        idMensajePrueba = mensajeDAO.insertarMensaje(crearMensaje());

        envioMensajeDAO.insertarEnvioMensaje(idMensajePrueba, idRemitentePrueba, idDestinatarioPrueba);

        List<Mensaje> mensajes = mensajeDAO.consultarMensajesPorDestinatario(idDestinatarioPrueba);

        assertFalse(mensajes.isEmpty());
        
    }

    @Test
    public void pruebaConsultarMensajesPorDestinatarioInexistente()throws OperacionesDeDaoExcepcion {

        List<Mensaje> mensajes = mensajeDAO.consultarMensajesPorDestinatario(-1);

        assertTrue(mensajes.isEmpty());
        
    }

    @Test
    public void pruebaConsultarMensajesEnviadosExistente()throws OperacionesDeDaoExcepcion {

        Usuario remitente = crearUsuario("remitente");
        Usuario destinatario = crearUsuario("destinatario");

        idRemitentePrueba = usuarioDAO.insertarUsuario(remitente);

        idDestinatarioPrueba = usuarioDAO.insertarUsuario(destinatario);

        idMensajePrueba = mensajeDAO.insertarMensaje(crearMensaje());

        envioMensajeDAO.insertarEnvioMensaje(idMensajePrueba, idRemitentePrueba, idDestinatarioPrueba);

        List<Mensaje> mensajes = mensajeDAO.consultarMensajesEnviados(idRemitentePrueba);

        assertFalse(mensajes.isEmpty());
        
    }

    @Test
    public void pruebaConsultarMensajesEnviadosInexistente()throws OperacionesDeDaoExcepcion {

        List<Mensaje> mensajes = mensajeDAO.consultarMensajesEnviados(-1);

        assertTrue(mensajes.isEmpty());
    }
    
}
