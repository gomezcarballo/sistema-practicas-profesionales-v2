/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.pruebasclasesdao;

import org.junit.Test;
import static org.junit.Assert.assertTrue;
import java.time.LocalDateTime;
import java.util.List;
import org.junit.After;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import org.junit.Before;
import spp.logicadenegocio.clasesdao.ActividadDAO;
import spp.logicadenegocio.clasesdao.ProfesorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Actividad;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;


/**
 *
 * @author Luz Fernanda H J
 */
public class PruebaActividadDAO {
    
    private ActividadDAO actividadDAO;
    private UsuarioDAO usuarioDAO;
    private ProfesorDAO profesorDAO;

    private int idUsuarioFalso;
    private String tituloActividad;

    private static int secuencia = (int) (System.currentTimeMillis() % 10000);

    @Before
    public void inicializarDatosPrueba() throws OperacionesDeDaoExcepcion {
        actividadDAO = new ActividadDAO();
        usuarioDAO = new UsuarioDAO();
        profesorDAO = new ProfesorDAO();

        secuencia++;
        tituloActividad = "Practica_BD_" + secuencia;

        Usuario usuario = new Usuario();
        usuario.setNombre("Prof");
        usuario.setApellidoPaterno("Actividad");
        usuario.setApellidoMaterno("Test");
        usuario.setCorreoInstitucional("prof_act" + secuencia + "@uv.mx");
        usuario.setContraseña("123");
        usuario.setEsActivo(true);
        idUsuarioFalso = usuarioDAO.insertarUsuario(usuario);

        Profesor p = new Profesor();
        p.setIdUsuario(idUsuarioFalso);
        p.setNumeroDePersonal("PA" + secuencia);
        p.setNrcAsignado("NRC01");
        profesorDAO.insertarProfesor(p);

        Actividad actividadBase = new Actividad();
        actividadBase.setTitulo(tituloActividad);
        actividadBase.setDescripcion("Descripción inicial de prueba");
        actividadBase.setFechaLimite(LocalDateTime.now().plusDays(5));
        actividadBase.setIdProfesor(idUsuarioFalso);

        actividadDAO.insertarActividad(actividadBase);
    }

    @After
    public void eliminarDatosPrueba() throws OperacionesDeDaoExcepcion {

        actividadDAO.eliminarActividad(tituloActividad);

        profesorDAO.eliminarProfesor(idUsuarioFalso);

        usuarioDAO.eliminarUsuario(idUsuarioFalso);

    }


    @Test
    public void pruebaInsertarActividadExitoso() throws OperacionesDeDaoExcepcion {
        
        String tituloNuevo = "Practica_Extra_" + secuencia;
        Actividad nuevaActividad = new Actividad();
        nuevaActividad.setTitulo(tituloNuevo);
        nuevaActividad.setDescripcion("Otra práctica insertada desde el test");
        nuevaActividad.setFechaLimite(LocalDateTime.now().plusDays(3));
        nuevaActividad.setIdProfesor(idUsuarioFalso);

        boolean resultado = actividadDAO.insertarActividad(nuevaActividad);

        actividadDAO.eliminarActividad(tituloNuevo);

        assertTrue(resultado);
        
    }

    @Test
    public void pruebaConsultarActividadExistente() throws OperacionesDeDaoExcepcion {
        
        Actividad resultado = actividadDAO.consultarActividad(tituloActividad);
        assertNotNull(resultado);
        
    }

    @Test
    public void pruebaConsultarActividadesAsignadasNoNulo() throws OperacionesDeDaoExcepcion {

        List<Actividad> lista = actividadDAO.consultarActividadesAsignadas(idUsuarioFalso);
        assertNotNull(lista);
        
    }

    @Test
    public void pruebaActualizarActividadExitoso() throws OperacionesDeDaoExcepcion {
        
        Actividad actividadModificada = new Actividad();
        
        actividadModificada.setTitulo(tituloActividad); 
        actividadModificada.setDescripcion("Descripción editada durante la prueba");
        actividadModificada.setFechaLimite(LocalDateTime.now().plusDays(10));
        actividadModificada.setIdProfesor(idUsuarioFalso);

        boolean resultado = actividadDAO.actualizarActividad(actividadModificada);
        assertTrue(resultado);
        
    }

    @Test
    public void pruebaEliminarActividadExistente() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = actividadDAO.eliminarActividad(tituloActividad);
        assertTrue(resultado);
        
    }

    @Test
    public void pruebaConsultarActividadNoExistente() throws OperacionesDeDaoExcepcion {
        
        Actividad resultado = actividadDAO.consultarActividad("Titulo_Que_No_Existe_123");
        assertNull(resultado);
        
    }

    @Test
    public void pruebaEliminarActividadNoExistente() throws OperacionesDeDaoExcepcion {
        
        boolean resultado = actividadDAO.eliminarActividad("Titulo_Que_No_Existe_123");
        assertFalse(resultado);
        
    }
    
}
