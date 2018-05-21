/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

/**
 *
 * @author Cristian
 */
public class Usuario {
    private String nombre;
    private String correo;
    private boolean validado;
    private RolUsuario rol;
    
    public Usuario(String nombre, String correo, RolUsuario rol){
        this.validado = true;
        this.nombre = nombre;
        this.correo = correo;
        this.rol = rol;
    }
    
    public String verNombre(){
        return nombre;
    }
    
    public String verCorreo(){
        return correo;
    }
    
    public RolUsuario verRol(){
        return rol;
    }
    
    
}
