/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import modelos.Incidencia;

/**
 *
 * @author Carlos Ribera
 */
public class IncidenciaController {
    
    public static int calcularIdIncidencia(List<Incidencia> incidenciasList){
        
        int id = 0;
        
        for(Incidencia e : incidenciasList){
            if(e.getId() >= id){
               id = e.getId() +1;
            }
        }
        return id;
    }
}
