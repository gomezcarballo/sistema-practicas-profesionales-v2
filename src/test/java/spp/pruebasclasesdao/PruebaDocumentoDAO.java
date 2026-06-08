/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertTrue;
import spp.logicadenegocio.clasesdao.DocumentoDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Documento;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;

/**
 *
 * @author Luz Fernanda H J
 */
public class PruebaDocumentoDAO {
    
    private UsuarioDAO usuarioDAO;
    private DocumentoDAO documentoDAO;

    private int idUsuarioFalso;
    private String nombreDocumentoPrueba;

    private static int secuencia = (int) (System.currentTimeMillis() % 10000);

    @Before
    public void inicializarDatosPrueba() throws OperacionesDeDaoExcepcion {
        usuarioDAO = new UsuarioDAO();
        documentoDAO = new DocumentoDAO();

        secuencia++;
        nombreDocumentoPrueba = "Doc_Prueba_" + secuencia + ".pdf";

        Usuario usuario = new Usuario();
        usuario.setNombre("Test");
        usuario.setApellidoPaterno("Doc");
        usuario.setApellidoMaterno("DAO");
        usuario.setCorreoInstitucional("usuario_doc" + secuencia + "@uv.mx");
        usuario.setContraseña("123");
        usuario.setEsActivo(true);

        idUsuarioFalso = usuarioDAO.insertarUsuario(usuario);

        Documento documento = new Documento();
        documento.setNombre(nombreDocumentoPrueba);
        documento.setTipo("PDF");
        documento.setRuta("/ruta/falsa/documentos/" + nombreDocumentoPrueba);
        documento.setIdUsuario(idUsuarioFalso);

        documentoDAO.insertarDocumento(documento);
    }

    @After
    public void eliminarDatosPrueba() throws OperacionesDeDaoExcepcion {
        
            documentoDAO.eliminarDocumento(nombreDocumentoPrueba);
            usuarioDAO.eliminarUsuario(idUsuarioFalso);
            

    }


    @Test
    public void pruebaInsertarDocumentoExitoso() throws OperacionesDeDaoExcepcion {
        
        String nombreNuevo = "Doc_Extra_" + secuencia + ".docx";
        
        Documento nuevoDocumento = new Documento();
        nuevoDocumento.setNombre(nombreNuevo);
        nuevoDocumento.setTipo("DOCX");
        nuevoDocumento.setRuta("/ruta/falsa/documentos/" + nombreNuevo);
        nuevoDocumento.setIdUsuario(idUsuarioFalso);

        boolean resultado = documentoDAO.insertarDocumento(nuevoDocumento);

        documentoDAO.eliminarDocumento(nombreNuevo);

        assertTrue(resultado);
        
    }

    @Test
    public void pruebaConsultarDocumentoExistente() throws OperacionesDeDaoExcepcion {
        
        Documento resultado = documentoDAO.consultarDocumento(nombreDocumentoPrueba);
        assertNotNull(resultado);
        
    }

    @Test
    public void pruebaEliminarDocumentoExitoso() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = documentoDAO.eliminarDocumento(nombreDocumentoPrueba);
        assertTrue(resultado);
        
    }


    
    @Test
    public void pruebaConsultarDocumentoNoExistente() throws OperacionesDeDaoExcepcion {
        
        Documento resultado = documentoDAO.consultarDocumento("Archivo_Inexistente_999.txt");
        assertNull(resultado);
        
    }

    @Test
    public void pruebaEliminarDocumentoNoExistente() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = documentoDAO.eliminarDocumento("Archivo_Inexistente_999.txt");
        assertFalse(resultado);
        
    }
    
}

