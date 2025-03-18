/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Carlos Ribera
 */
public class Incidencia {
    
    private int id;
    private String fecha;
    private Empleado empleadoOrigen;
    private Empleado empleadoDestino;
    private String detalle;
    private char tipo;
    
    // CONSTRUCTOR
    public Incidencia(int id, LocalDateTime fecha, Empleado empleadoOrigen, Empleado empleadoDestino, String detalle, char tipo) {
        this.id = id;
        this.fecha = fecha.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME); //  Convertimos LocalDateTime a String
        this.empleadoOrigen = empleadoOrigen;
        this.empleadoDestino = empleadoDestino;
        this.detalle = detalle;
        this.tipo = tipo;
    }
    public Incidencia(){
        
    }
    
    // GETTERS Y SETTERS
    
    public int getId(){
        return id;
    }
    
    public void setId(int id){
        this.id = id;
    }

    public LocalDateTime getFecha() {
        return LocalDateTime.parse(fecha, DateTimeFormatter.ISO_LOCAL_DATE_TIME); //  Convertimos String a LocalDateTime
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha.format(DateTimeFormatter.ISO_LOCAL_DATE_TIME);
    }

    public Empleado getEmpleadoOrigen() {
        return empleadoOrigen;
    }

    public void setEmpleadoOrigen(Empleado empleadoOrigen) {
        this.empleadoOrigen = empleadoOrigen;
    }

    public Empleado getEmpleadoDestino() {
        return empleadoDestino;
    }

    public void setEmpleadoDestino(Empleado empleadoDestino) {
        this.empleadoDestino = empleadoDestino;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public char getTipo() {
        return tipo;
    }

    public void setTipo(char tipo) {
        this.tipo = tipo;
    }
    
    @Override
    public String toString(){
        return  "------ INCIDENCIA ------ \n" +
                "ID: " + id + "\n" +
                "FECHA: " + fecha + " \n" +
                "ORIGEN: " + (empleadoOrigen != null ? empleadoOrigen.getNombreCompleto() : "N/A") + " \n" +
                "DESTINO: " + (empleadoDestino != null ? empleadoDestino.getNombreCompleto() : "N/A") + " \n" +
                "DETALLE: " + detalle + " \n" +
                "TIPO: " + tipo + "\n"
                ;
    }
}
