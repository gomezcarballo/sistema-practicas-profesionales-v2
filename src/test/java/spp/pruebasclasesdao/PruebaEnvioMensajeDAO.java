/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.After;
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
public class PruebaEnvioMensajeDAO {
    
    private EnvioMensajeDAO envioMensajeDAO;
    private UsuarioDAO usuarioDAO;
    private MensajeDAO mensajeDAO;

    private int idRemitentePrueba;
    private int idDestinatarioPrueba;
    private int idMensajePrueba;

    @Before
    public void inicializarDatosPrueba() {

        envioMensajeDAO = new EnvioMensajeDAO();
        usuarioDAO = new UsuarioDAO();
        mensajeDAO = new MensajeDAO();

        idRemitentePrueba = 0;
        idDestinatarioPrueba = 0;
        idMensajePrueba = 0;
        
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
    }

    private Usuario crearUsuario(String prefijo) {

        Usuario usuario = new Usuario();

        usuario.setNombre("Usuario");
        usuario.setApellidoPaterno("Prueba");
        usuario.setApellidoMaterno("DAO");
        usuario.setCorreoInstitucional(prefijo + "@uv.mx");
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
    public void pruebaInsertarEnvioMensajeExitoso() throws OperacionesDeDaoExcepcion {

        Usuario remitente = crearUsuario("remitente");
        Usuario destinatario = crearUsuario("destinatario");

        idRemitentePrueba = usuarioDAO.insertarUsuario(remitente);

        idDestinatarioPrueba = usuarioDAO.insertarUsuario(destinatario);

        idMensajePrueba = mensajeDAO.insertarMensaje(crearMensaje());

        boolean registroExitoso = envioMensajeDAO.insertarEnvioMensaje(idMensajePrueba, idRemitentePrueba,
        idDestinatarioPrueba);

        assertTrue(registroExitoso);
        
    }
    
}
