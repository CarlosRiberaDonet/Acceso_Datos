/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelos;

import jakarta.persistence.*;
import java.time.LocalDateTime;

/**
 *
 * @author Carlos Ribera
 */

@Entity
@Table(name = "incidencia")
public class Incidencias {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "fecha", nullable = false)
    private LocalDateTime fecha;
    
    @ManyToOne
    @JoinColumn(name = "id_empleado_origen", nullable = false)
    private Empleados empleadoOrigen;  // Cambiado de int a Empleados

    @ManyToOne
    @JoinColumn(name = "id_empleado_destino", nullable = false)
    private Empleados empleadoDestino;  // Cambiado de int a Empleados
    
    @Column(name = "detalle", length = 255, nullable = false)
    private String detalle;
    
    @Column(name = "tipo", length = 1, nullable = false)
    private char tipo;   
    
    // CONSTRUCTOR
    public Incidencias(LocalDateTime fecha, Empleados empleadoOrigen, Empleados empleadoDestino, String detalle, char tipo) {
        this.fecha = fecha;
        this.empleadoOrigen = empleadoOrigen;
        this.empleadoDestino = empleadoDestino;
        this.detalle = detalle;
        this.tipo = tipo;
    }
    
    public Incidencias() {
        
    }

    // GETTERS Y SETTERS
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public LocalDateTime getFecha() { return fecha; }
    public void setFecha(LocalDateTime fecha) { this.fecha = fecha; }

    public Empleados getEmpleadoOrigen() { return empleadoOrigen; }
    public void setEmpleadoOrigen(Empleados empleadoOrigen) { this.empleadoOrigen = empleadoOrigen; }

    public Empleados getEmpleadoDestino() { return empleadoDestino; }
    public void setEmpleadoDestino(Empleados empleadoDestino) { this.empleadoDestino = empleadoDestino; }

    public String getDetalle() { return detalle; }
    public void setDetalle(String detalle) { this.detalle = detalle; }

    public char getTipo() { return tipo; }
    public void setTipo(char tipo) { this.tipo = tipo; }

    // Método toString para depuración
    @Override
    public String toString() {
        return "Incidencias{" +
                "id=" + id +
                ", fecha=" + fecha +
                ", empleadoOrigen=" + empleadoOrigen.getId() +
                ", empleadoDestino=" + empleadoDestino.getId() +
                ", detalle='" + detalle + '\'' +
                ", tipo=" + tipo +
                '}';
    }
}
