/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Carlos Ribera
 */
public class Empleado {
    private String nombreUsuario;
    private String password;
    private String nombre;
    private String apellidos;
    private String direccion;
    private String telefono;
    
    // CONSTRUCTOR
    public Empleado(String nombreUsuario, String password, String nombre, String apellidos, String direccion, String telefono){
        this.nombreUsuario = nombreUsuario;
        this.password = password;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.direccion = direccion;
        this.telefono = telefono;
    }
    
    public Empleado(){
        
    }
    
    // GETTERS Y SETTERS

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }
   
    
    @Override
    public String toString(){
        return "------ Empleado ------\n"
               + "NombreUsuario: " + nombreUsuario + "\n"
               + "Password: " + password + "\n"
               + "Apellidos: " + apellidos + "\n"
               + "Telefono: " + telefono;
    }
}
