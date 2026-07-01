package spp.logicadenegocio.gestores;

import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.DocumentoDAO;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.ProfesorDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.ExperienciaEducativa;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Profesor;
import spp.logicadenegocio.clasesdto.Proyecto;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.enums.TipoDocumento;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionPracticante;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.enviodecorreo.EnvioCorreo;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.contrasenas.generadordecontrasenas.GeneradorContrasena;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;
import spp.utilerias.excepciones.ProcesamientoSistemaExcepcion;

/**
 *
 * @author gomes
 */
public class GestorPracticantes {
        
    public List<String> validarCamposPracticante(Practicante practicante) {
        
        ValidacionPracticante validacion = new ValidacionPracticante();
        return validacion.validarRegistroPracticante(practicante);
    
    }
    
    public void ingresarPracticante(Practicante practicante) throws OperacionesDeDaoExcepcion, ProcesamientoSistemaExcepcion {
                
        String contrasenaPlana = GeneradorContrasena.generarContraseña(10);        
        Usuario usuarioPracticante = prepararUsuarioParaRegistro(practicante, contrasenaPlana);
        
        guardarPracticanteEnBaseDeDatos(usuarioPracticante, practicante);
        
        enviarContraseñaPorCorreo(usuarioPracticante.getCorreoInstitucional(), contrasenaPlana);
        
    } 

    private Usuario prepararUsuarioParaRegistro(Practicante practicante, String contrasenaPlana) {
        
        Usuario usuarioPracticante = crearUsuarioPracticante(practicante);
        String contrasenaHasheada = HasheoContrasena.hashearContraseña(contrasenaPlana);
        usuarioPracticante.setContraseña(contrasenaHasheada);
        return usuarioPracticante;
        
    }

    private void guardarPracticanteEnBaseDeDatos(Usuario usuarioPracticante, Practicante practicante) throws OperacionesDeDaoExcepcion {
        
        UsuarioDAO usuarioDAO = new UsuarioDAO();
        PracticanteDAO practicanteDAO = new PracticanteDAO();
        
        int idUsuario = usuarioDAO.insertarUsuario(usuarioPracticante);
        practicante.setIdUsuario(idUsuario);
        
        practicanteDAO.insertarPracticante(practicante);
    
    }
    
    public boolean verificarFinDeCurso(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        boolean cursoTerminado = false;
        DocumentoDAO documentoDAO = new DocumentoDAO();
        
        int oficiosCalificados = documentoDAO.contarDocumentosCalificadosPorTipo(idPracticante, TipoDocumento.OFICIO_LIBERACION);
        
        if (oficiosCalificados > 0) {
            
            cursoTerminado = true;
            
        }
        
        return cursoTerminado;
        
    }

    private void enviarContraseñaPorCorreo(String correoDestino, String contrasenaPlana) throws ProcesamientoSistemaExcepcion {
        
        try {
            
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(correoDestino, contrasenaPlana);
            
        } catch(RuntimeException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ProcesamientoSistemaExcepcion("El practicante se registró, pero no se pudo enviar la contraseña por correo. Por favor contacte al personal.");
        
        }
        
    }
    
    private Usuario crearUsuarioPracticante(Practicante practicante){
        
        Usuario usuario = new Usuario();
        
        usuario.setNombre(practicante.getNombre());
        usuario.setApellidoPaterno(practicante.getApellidoPaterno());
        usuario.setApellidoMaterno(practicante.getApellidoMaterno());
        usuario.setCorreoInstitucional(practicante.getCorreoInstitucional());
        usuario.setEsActivo(true);
        
        return usuario;
    }
    
    public void inactivarPracticante(int idPracticante) throws OperacionesDeDaoExcepcion {
        
        PracticanteDAO practicanteDAO = new PracticanteDAO();
        practicanteDAO.inactivarPracticante(idPracticante);  
    
    }
    
    public boolean verificarAsignacionProyecto(int idUsuario) throws OperacionesDeDaoExcepcion {
        
        PracticanteDAO practicanteDao = new PracticanteDAO();
        return practicanteDao.tieneProyectoAsignado(idUsuario);
    
    }
    
    public boolean verificarAccesoGenerarEvidencias(int idUsuario) throws OperacionesDeDaoExcepcion {
        
        PracticanteDAO practicanteDao = new PracticanteDAO();
        return practicanteDao.tieneProyectoYGrupoAsignado(idUsuario);
    
    }

    public List<Practicante> recuperarPracticantesActivos() throws OperacionesDeDaoExcepcion {
        
        PracticanteDAO practicanteDAO = new PracticanteDAO();
        return practicanteDAO.consultarPracticantes();
    
    }
    
    public List<Practicante> recuperarPracticantesAsignados (int idProfesor) throws OperacionesDeDaoExcepcion {
    
        PracticanteDAO practicanteDAO = new PracticanteDAO();
        return practicanteDAO.consultarPracticantesPorProfesor(idProfesor);
        
    }
    
    public List<Practicante> obtenerPracticantesConSolicitudes() throws OperacionesDeDaoExcepcion {
        
        PracticanteDAO practicanteDAO = new PracticanteDAO();
        return practicanteDAO.consultarPracticantesConSolicitudes();
    
    }
    

    public List<Practicante> recuperarPracticantesParaAsignacionEE() throws OperacionesDeDaoExcepcion {

        PracticanteDAO practicanteDAO = new PracticanteDAO();
        return practicanteDAO.consultarPracticantesParaAsignacionEE();

    }
    
    public Proyecto recuperarProyectoAsignado(int idUsuarioPracticante) throws OperacionesDeDaoExcepcion {
        
        PracticanteDAO practicanteDAO = new PracticanteDAO();
        
        return practicanteDAO.obtenerProyectoAsignado(idUsuarioPracticante);
        
    }

    public ExperienciaEducativa recuperarExperienciaEducativaAsignada(int idUsuarioPracticante) throws OperacionesDeDaoExcepcion {
        
        PracticanteDAO practicanteDAO = new PracticanteDAO();
        
        return practicanteDAO.obtenerExperienciaEducativaAsignada(idUsuarioPracticante);
        
    }
    
    public Profesor recuperarProfesorPorId(int idUsuarioProfesor) throws OperacionesDeDaoExcepcion {
        
        ProfesorDAO profesorDAO = new ProfesorDAO();
        return profesorDAO.obtenerProfesorPorIdUsuario(idUsuarioProfesor);
        
    }
    
}
