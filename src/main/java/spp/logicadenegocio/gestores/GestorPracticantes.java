/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.gestores;

import java.util.List;
import java.util.logging.Level;
import spp.logicadenegocio.clasesdao.PracticanteDAO;
import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.Practicante;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.logicadenegocio.validaciones.validacionesinsercion.ValidacionPracticante;
import spp.utilerias.bitacora.RegistroErrores;
import spp.utilerias.enviodecorreo.EnvioCorreo;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.contrasenas.generadordecontrasenas.GeneradorContrasena;
import spp.utilerias.contrasenas.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author gomes
 */
public class GestorPracticantes {
        
    public void ingresarPracticante(Practicante practicante) throws ReglaDeNegocioExcepcion {
        
        validarPracticante(practicante);
        
        String contrasenaPlana = GeneradorContrasena.generarContraseña(10);        
        Usuario usuarioPracticante = prepararUsuarioParaRegistro(practicante, contrasenaPlana);
        
        guardarPracticanteEnBaseDeDatos(usuarioPracticante, practicante);
        
        enviarContraseñaPorCorreo(usuarioPracticante.getCorreoInstitucional(), contrasenaPlana);
        
    }


    private void validarPracticante(Practicante practicante) throws ReglaDeNegocioExcepcion {
        
        ValidacionPracticante validacion = new ValidacionPracticante();
        validacion.sonCamposValidosPorReglaNegocio(practicante);
        
    }

    private Usuario prepararUsuarioParaRegistro(Practicante practicante, String contrasenaPlana) {
        
        Usuario usuarioPracticante = crearUsuarioPracticante(practicante);
        String contrasenaHasheada = HasheoContrasena.hashearContraseña(contrasenaPlana);
        usuarioPracticante.setContraseña(contrasenaHasheada);
        return usuarioPracticante;
        
    }

    private void guardarPracticanteEnBaseDeDatos(Usuario usuarioPracticante, Practicante practicante) throws ReglaDeNegocioExcepcion {
        
        try {
            
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            PracticanteDAO practicanteDAO = new PracticanteDAO();
            
            int idUsuario = usuarioDAO.insertarUsuario(usuarioPracticante);
            practicante.setIdUsuario(idUsuario);
            
            practicanteDAO.insertarPracticante(practicante);
            
        } catch (OperacionesDeDaoExcepcion e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico al registrar un nuevo practicante.", e);  
            
            throw new ReglaDeNegocioExcepcion(e.getMessage());
        }
        
    }

    private void enviarContraseñaPorCorreo(String correoDestino, String contrasenaPlana) throws ReglaDeNegocioExcepcion {
        
        try {
            
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(correoDestino, contrasenaPlana);
            
        } catch(RuntimeException e) {
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ReglaDeNegocioExcepcion("El practicante se registró, pero no se pudo enviar la contraseña por correo. Por favor contacte al personal.");
        
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
    
    public List<Practicante> recuperarPracticantesActivos() throws ReglaDeNegocioExcepcion{
        
        try{
               PracticanteDAO practicanteDAO = new PracticanteDAO();
               return practicanteDAO.consultarPracticantes();

           }catch(OperacionesDeDaoExcepcion e){

               throw new ReglaDeNegocioExcepcion("No se pudieron obtener los practicantes activos");

        }
        
    }
    
    public List<Practicante> obtenerPracticantesConSolicitudes() throws ReglaDeNegocioExcepcion {
    
        try{
            
            PracticanteDAO practicanteDAO = new PracticanteDAO();
            return practicanteDAO.consultarPracticantesConSolicitudes();
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("No se pudieron obtener los practicantes con solicitudes");
            
        }
    
    }
    
    public void inactivarPracticante(int idPracticante)throws ReglaDeNegocioExcepcion {
       
       try{
          
            PracticanteDAO practicanteDAO = new PracticanteDAO();
            practicanteDAO.inactivarPracticante(idPracticante);  
          
       }catch(OperacionesDeDaoExcepcion e){
           
           throw new ReglaDeNegocioExcepcion("No se pudo inactivar el practicante");
           
       }
   }
    
    public boolean verificarAsignacionProyecto(int idUsuario) throws ReglaDeNegocioExcepcion {
    
        try {
        
            PracticanteDAO practicanteDao = new PracticanteDAO();
        
            return practicanteDao.tieneProyectoAsignado(idUsuario);
    
        } catch (OperacionesDeDaoExcepcion e) {
        
            throw new ReglaDeNegocioExcepcion("Error al consultar el estado del proyecto", e);
    
        }

    }
    
}
