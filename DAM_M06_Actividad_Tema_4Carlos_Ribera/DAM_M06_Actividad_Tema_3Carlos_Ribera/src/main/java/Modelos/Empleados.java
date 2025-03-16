/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Modelos;
import jakarta.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Ribera
 */

@Entity
@Table(name = "empleado")
public class Empleados {
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    
    @Column(name = "nombre_usuario", length = 30, unique = true, nullable = false)
    private String nombreUsuario;
    
    @Column(name = "contrasena", length = 30, nullable = false)
    private String contrasena;
    
    @Column(name = "nombre_completo", length = 50, nullable = false)
    private String nombreCompleto;
    
    @Column(name = "telefono", length = 9)
    private String telefono;
    
    @OneToMany(mappedBy = "empleadoOrigen", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencias> incidenciasCreadas = new ArrayList<>();
    
    @OneToMany(mappedBy = "empleadoDestino", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Incidencias> incidenciasDestinadas = new ArrayList<>();
    
    // Constructor con parámetros
    public Empleados(String nombreUsuario, String contrasena, String nombreCompleto, String telefono) {
        this.nombreUsuario = nombreUsuario;
        this.contrasena = contrasena;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
    }
    
    // Constructor vacío obligatorio para Hibernate
    public Empleados() {}

    // Getters y Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getNombreUsuario() { return nombreUsuario; }
    public void setNombreUsuario(String nombreUsuario) { this.nombreUsuario = nombreUsuario; }

    public String getContrasena() { return contrasena; }
    public void setContrasena(String contrasena) { this.contrasena = contrasena; }

    public String getNombreCompleto() { return nombreCompleto; }
    public void setNombreCompleto(String nombreCompleto) { this.nombreCompleto = nombreCompleto; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public List<Incidencias> getIncidenciasCreadas() {
        return incidenciasCreadas;
    }

    public void setIncidenciasCreadas(List<Incidencias> incidenciasCreadas) {
        this.incidenciasCreadas = incidenciasCreadas;
    }

    public List<Incidencias> getIncidenciasDestinadas() {
        return incidenciasDestinadas;
    }

    public void setIncidenciasDestinadas(List<Incidencias> incidenciasDestinadas) {
        this.incidenciasDestinadas = incidenciasDestinadas;
    }
    
    // Método toString para facilitar depuración
    @Override
    public String toString() {
        return "Empleado{" +
                "id=" + id +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasena='" + contrasena + '\'' +
                ", nombreCompleto='" + nombreCompleto + '\'' +
                ", telefono='" + telefono + '\'' +
                '}';
    }
}
