/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author Carlos Ribera
 */
public class Incidencia {
    private int id;
    private String fechaHora;
    private Empleado empleadoOrigen;
    private Empleado empleadoDestino;
    private String detalleIncidencia;
    private String tipoIncidencia;
    
    // CONSTRUCTOR
    public Incidencia(int id, String fechaHora, Empleado empleadoOrigen, Empleado empleadoDestino, String detalleIncidencia, String tipoIncidencia) {
        this.id = id;
        this.fechaHora = fechaHora;
        this.empleadoOrigen = empleadoOrigen;
        this.empleadoDestino = empleadoDestino;
        this.detalleIncidencia = detalleIncidencia;
        this.tipoIncidencia = tipoIncidencia;
    }
    
    public Incidencia(){
        
    }
    
    // GETTERS Y SETTERS
    public int getId() {
        return id;
    }

    // GETTERS Y SETTERS
    public void setId(int id) {    
        this.id = id;
    }

    public String getFechaHora() {
        return fechaHora;
    }

    public void setFechaHora(String fechaHora) {
        this.fechaHora = fechaHora;
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

    public String getDetalleIncidencia() {
        return detalleIncidencia;
    }

    public void setDetalleIncidencia(String detalleIncidencia) {
        this.detalleIncidencia = detalleIncidencia;
    }

    public String getTipoIncidencia() {
        return tipoIncidencia;
    }

    public void setTipoIncidencia(String tipoIncidencia) {
        this.tipoIncidencia = tipoIncidencia;
    }
    
    @Override
    public String toString(){
        return "------ INCIDENCIA ------\n"
                + "ID: " + id + "\n"
                + "Fecha: " + fechaHora + "\n"
                + "Empleado Origen: " + empleadoOrigen.getNombreUsuario() + "\n"
                + "Empleado Destino: " + empleadoDestino.getNombreUsuario() + "\n"
                + "Detalles: " + detalleIncidencia + "\n"
                + "Tipo: " + tipoIncidencia;
    }
}
