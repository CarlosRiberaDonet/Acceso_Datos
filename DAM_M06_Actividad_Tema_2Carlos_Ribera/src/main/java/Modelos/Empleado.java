/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelos;

/**
 *
 * @author Carlos Ribera
 */

public class Empleado {
    
  
    private int id;
    private String nombreUsuario;
    private String contraseña;
    private String nombreCompleto;
    private String telefono;
    
    // Constructor
    public Empleado(String nombre_usuario, String contraseña, String nombre_completo, String telefono) {
        this.nombreUsuario = nombre_usuario;
        this.contraseña = contraseña;
        this.nombreCompleto = nombre_completo;
        this.telefono = telefono;
    }
    
    // Constructor vacío
    public Empleado(){
        
    }
    
    // GETTERS Y SETTERS

    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }
    
    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombre_usuario) {
        this.nombreUsuario = nombre_usuario;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombre_completo) {
        this.nombreCompleto = nombre_completo;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }  
}
