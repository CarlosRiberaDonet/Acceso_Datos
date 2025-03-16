/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entidades;

import java.time.LocalDateTime;

/**
 *
 * @author Carlos Ribera
 */
public class Incidencia {
    
    private int incidenciaId;
    private LocalDateTime fecha;
    private Empleado empleadoOrigen;
    private Empleado empleadoDestino;
    private String detalle;
    private char tipo;
    
    // CONSTRUCTOR
    public Incidencia(int incidenciaId, LocalDateTime fecha, Empleado empleadoOrigen, Empleado empleadoDestino, String detalle, char tipo) {
        this.incidenciaId = incidenciaId;
        this.fecha = LocalDateTime.now();
        this.empleadoOrigen = empleadoOrigen;
        this.empleadoDestino = empleadoDestino;
        this.detalle = detalle;
        this.tipo = tipo;
    }
    public Incidencia(){
        
    }
    
    // GETTERS Y SETTERS
    public int getIncidenciaId() {
        return incidenciaId;
    }

    public void setIncidenciaId(int incidenciaId) {
        this.incidenciaId = incidenciaId;
    }

    public LocalDateTime getFecha() {
        return fecha;
    }

    public void setFecha(LocalDateTime fecha) {
        this.fecha = fecha;
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
        return "Incidencia{" +
                "ID: " + incidenciaId +
                "FECHA: " + fecha +
                "ORIGEN: " + (empleadoOrigen != null ? empleadoOrigen.getNombreCompleto() : "N/A") +
                "DESTINO: " + (empleadoDestino != null ? empleadoDestino.getNombreCompleto() : "N/A") +
                "DETALLE: " + detalle +
                "TIPO: " + tipo +
                "}";
    }
}
