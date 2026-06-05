/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package spp.logicadenegocio.clasesdto;

/**
 *
 * @author gomes
 */
public class UsuarioEncontrado {
    
    private int idUsuarioEncontrado;
    private String rolUsuarioEncontrado;
    private String hashUsuarioEncontrado;

    public UsuarioEncontrado() {
    }

    public UsuarioEncontrado(int idUsuarioEncontrado, String rolUsuarioEncontrado, String hashUsuarioEncontrado) {
        this.idUsuarioEncontrado = idUsuarioEncontrado;
        this.rolUsuarioEncontrado = rolUsuarioEncontrado;
        this.hashUsuarioEncontrado = hashUsuarioEncontrado;
    }

    public int getIdUsuarioEncontrado() {
        return idUsuarioEncontrado;
    }

    public void setIdUsuarioEncontrado(int idUsuarioEncontrado) {
        this.idUsuarioEncontrado = idUsuarioEncontrado;
    }

    public String getRolUsuarioEncontrado() {
        return rolUsuarioEncontrado;
    }

    public void setRolUsuarioEncontrado(String rolUsuarioEncontrado) {
        this.rolUsuarioEncontrado = rolUsuarioEncontrado;
    }   

    public String getHashUsuarioEncontrado() {
        return hashUsuarioEncontrado;
    }

    public void setHashUsuarioEncontrado(String hashUsuarioEncontrado) {
        this.hashUsuarioEncontrado = hashUsuarioEncontrado;
    }    
    
}
