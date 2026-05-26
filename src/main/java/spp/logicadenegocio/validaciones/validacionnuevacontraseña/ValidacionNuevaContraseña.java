/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.validaciones.validacionnuevacontraseña;

import spp.logicadenegocio.clasesdao.UsuarioDAO;
import spp.logicadenegocio.clasesdto.CredencialContraseña;
import spp.logicadenegocio.clasesdto.SesionUsuario;
import spp.logicadenegocio.clasesdto.Usuario;
import spp.utilerias.excepciones.OperacionesDeDaoExcepcion;
import spp.utilerias.excepciones.ReglaDeNegocioExcepcion;
import spp.utilerias.hasheodecontrasenas.HasheoContrasena;

/**
 *
 * @author gomes
 */
public class ValidacionNuevaContraseña {
    

    public void cambiarContraseña(CredencialContraseña credenciales, Usuario usuario)throws ReglaDeNegocioExcepcion {

        validarContraseñaActual(credenciales);

        validarCoincidenciaContraseñas(credenciales);

        validarLongitudNuevaContraseña(credenciales);

        actualizarContraseña(usuario,credenciales);

    }

    private void validarContraseñaActual(CredencialContraseña credenciales)throws ReglaDeNegocioExcepcion {

        SesionUsuario sesionUsuario = SesionUsuario.getInstancia();
        boolean esContraseñaCorrecta = HasheoContrasena.verificarContraseña(credenciales.getContraseñaActual(),
                sesionUsuario.getHashContrasena());
            
            if (!esContraseñaCorrecta) {
                throw new ReglaDeNegocioExcepcion("La contraseña actual no es correcta");
            }

    }

    private void validarCoincidenciaContraseñas(CredencialContraseña credenciales) throws ReglaDeNegocioExcepcion {

        boolean coincidenContrasenas = credenciales.getContraseñaNueva()
                .equals(credenciales.getContraseñaConfirmada());

        if (!coincidenContrasenas) {

            throw new ReglaDeNegocioExcepcion("Las contraseñas no coinciden");

        }

    }

    private void validarLongitudNuevaContraseña(CredencialContraseña credenciales)throws ReglaDeNegocioExcepcion {

        int longitudMinima = 10;

        boolean longitudInvalida = credenciales.getContraseñaNueva().length() < longitudMinima;

        if (longitudInvalida) {

            throw new ReglaDeNegocioExcepcion("La nueva contraseña debe tener al menos"+ longitudMinima +"caracteres");

        }

    }

    private void actualizarContraseña(Usuario usuario, CredencialContraseña credenciales)throws ReglaDeNegocioExcepcion {

        try {
            
            String contraseñaNuevaHasheada = HasheoContrasena.hashearContraseña(credenciales.getContraseñaNueva());
            UsuarioDAO usuarioDAO = new UsuarioDAO();
            usuarioDAO.actualizarContraseña(usuario.getIdUsuario(), contraseñaNuevaHasheada);
            SesionUsuario.getInstancia().setHashContrasena(contraseñaNuevaHasheada);
            
        } catch (OperacionesDeDaoExcepcion e) {

            throw new ReglaDeNegocioExcepcion("No se pudo actualizar la contraseña");

        }

    }
    
}
