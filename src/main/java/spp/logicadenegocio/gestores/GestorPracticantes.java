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
import spp.logicadenegocio.interfacesdao.IPracticanteDAO;
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
    
    private IPracticanteDAO practicanteDAO;
    
    public void ingresarPracticante(Practicante practicante)throws ReglaDeNegocioExcepcion{
        
        ValidacionPracticante validacion = new ValidacionPracticante();
        validacion.sonCamposValidosPorReglaNegocio(practicante);
        
        Usuario usuarioPracticante = crearUsuarioPracticante(practicante);

        String contraseñaPlana = GeneradorContrasena.generarContraseña(10);        
        String contraseñaHasheada = HasheoContrasena.hashearContraseña(contraseñaPlana);
        usuarioPracticante.setContraseña(contraseñaHasheada);
        
        UsuarioDAO usuarioDao = new UsuarioDAO();
        PracticanteDAO practicanteDao = new PracticanteDAO();
        
        try{
            
            int idUsuario = usuarioDao.insertarUsuario(usuarioPracticante);
            
            practicante.setIdUsuario(idUsuario);
            practicanteDao.insertarPracticante(practicante);
           
            EnvioCorreo envioCorreoContraseña = new EnvioCorreo();
            envioCorreoContraseña.enviarContraseña(usuarioPracticante.getCorreoInstitucional(), contraseñaPlana);
            
            
        }catch(OperacionesDeDaoExcepcion e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo crítico de base de datos al registrar un nuevo practicante.", e);  
            throw new ReglaDeNegocioExcepcion("No se pudo registrar al Practicante por un problema "
                + "interno del sistema. Intente más tarde.", e);
            
        }catch(RuntimeException e){
            
            RegistroErrores.registrarError(Level.SEVERE, "Fallo con el envio de la contraseña por correo electronico.", e);
            throw new ReglaDeNegocioExcepcion("No se pudo enviar la contraseña por correo electronico.");
            
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
               practicanteDAO = new PracticanteDAO();
               return practicanteDAO.consultarPracticantes();

           }catch(OperacionesDeDaoExcepcion e){

               throw new ReglaDeNegocioExcepcion("No se pudieron obtener los practicantes activos");

        }
        
    }
    
    public List<Practicante> obtenerPracticantesConSolicitudes() throws ReglaDeNegocioExcepcion {
    
        try{
            
            practicanteDAO = new PracticanteDAO();
            return practicanteDAO.consultarPracticantesConSolicitudes();
            
        }catch(OperacionesDeDaoExcepcion e){
            
            throw new ReglaDeNegocioExcepcion("No se pudieron obtener los practicantes con solicitudes");
            
        }
    
    }
    
    public void inactivarPracticante(int idPracticante)throws ReglaDeNegocioExcepcion {
       
       try{
          
            practicanteDAO = new PracticanteDAO();
            practicanteDAO.inactivarPracticante(idPracticante);  
          
       }catch(OperacionesDeDaoExcepcion e){
           
           throw new ReglaDeNegocioExcepcion("No se pudo inactivar el practicante");
           
       }
   }
    
}
