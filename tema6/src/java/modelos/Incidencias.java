/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelos;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Carlos Ribera
 */
@Entity
@Table(name = "incidencias")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Incidencias.findAll", query = "SELECT i FROM Incidencias i")
    , @NamedQuery(name = "Incidencias.findById", query = "SELECT i FROM Incidencias i WHERE i.id = :id")
    , @NamedQuery(name = "Incidencias.findByFecha", query = "SELECT i FROM Incidencias i WHERE i.fecha = :fecha")
    , @NamedQuery(name = "Incidencias.findByDetalle", query = "SELECT i FROM Incidencias i WHERE i.detalle = :detalle")
    , @NamedQuery(name = "Incidencias.findByTipo", query = "SELECT i FROM Incidencias i WHERE i.tipo = :tipo")})
public class Incidencias implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "fecha")
    @Temporal(TemporalType.TIMESTAMP)
    private Date fecha;
    @Size(max = 250)
    @Column(name = "detalle")
    private String detalle;
    @Column(name = "tipo")
    private Character tipo;
    @JoinColumn(name = "id_empleado_destino", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleados idEmpleadoDestino;
    @JoinColumn(name = "id_empleado_origen", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Empleados idEmpleadoOrigen;

    public Incidencias() {
    }

    public Incidencias(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getDetalle() {
        return detalle;
    }

    public void setDetalle(String detalle) {
        this.detalle = detalle;
    }

    public Character getTipo() {
        return tipo;
    }

    public void setTipo(Character tipo) {
        this.tipo = tipo;
    }

    public Empleados getIdEmpleadoDestino() {
        return idEmpleadoDestino;
    }

    public void setIdEmpleadoDestino(Empleados idEmpleadoDestino) {
        this.idEmpleadoDestino = idEmpleadoDestino;
    }

    public Empleados getIdEmpleadoOrigen() {
        return idEmpleadoOrigen;
    }

    public void setIdEmpleadoOrigen(Empleados idEmpleadoOrigen) {
        this.idEmpleadoOrigen = idEmpleadoOrigen;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Incidencias)) {
            return false;
        }
        Incidencias other = (Incidencias) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "modelos.Incidencias[ id=" + id + " ]";
    }
    
}
