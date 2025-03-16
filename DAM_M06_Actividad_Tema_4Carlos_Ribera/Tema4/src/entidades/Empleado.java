/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.util.List;

/**
 *
 * @author Carlos Ribera
 */
public class Empleado {
    
    private String nombreUsuario;
    private String contrasena;
    private String nombreCompleto;
    private String telefono;
    private List<Incidencia> incidencias;
    
    // CONSTRUCTOR
    public Empleado(String nombreUsuario, String contrasena, String nombreCompleto, String telefono, List<Incidencia> incidencias){
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.incidencias = incidencias;
    }
    
    public Empleado(String nombreUsuario){
        this.nombreUsuario = nombreUsuario;
    }
    
    public Empleado(){
        
    }

    public String getNombreUsuario() {
        return nombreUsuario;
    }

    public void setNombreUsuario(String nombreUsuario) {
        this.nombreUsuario = nombreUsuario;
    }

    public String getContrasena() {
        return contrasena;
    }

    public void setContrasena(String contrasena) {
        this.contrasena = contrasena;
    }

    public String getNombreCompleto() {
        return nombreCompleto;
    }

    public void setNombreCompleto(String nombreCompleto) {
        this.nombreCompleto = nombreCompleto;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public List<Incidencia> getIncidencias() {
        return incidencias;
    }

    public void setIncidencias(List<Incidencia> incidencias) {
        this.incidencias = incidencias;
    } 
    
    @Override
    public String toString() {
        return "------ Empleado ------ \n" +
                "USUARIO: " + nombreUsuario + "\n" +
                "NOMBRE: " + nombreCompleto + "\n" +
                "TELEFONO: " + telefono + "\n" +
                "INCIDENCIAS: " + (incidencias != null ? incidencias.size() : 0);
    }
}
