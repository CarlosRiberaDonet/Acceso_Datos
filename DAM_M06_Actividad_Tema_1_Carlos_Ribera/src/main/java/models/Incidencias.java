/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import jakarta.xml.bind.annotation.XmlElement;
import jakarta.xml.bind.annotation.XmlRootElement;
import java.util.List;

/**
 *
 * @author Carlos Ribera
 */
@XmlRootElement(name = "incidencias")
public class Incidencias {

    private List<Incidencia> incidencia;

    // Constructor vacío para JAXB
    public Incidencias() {
        
    }

    @XmlElement(name = "incidencia")
    public List<Incidencia> getIncidencia() {
        return incidencia;
    }

    public void setIncidencia(List<Incidencia> incidencia) {
        this.incidencia = incidencia;
    }
}
