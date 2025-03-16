/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import models.Incidencia;

/**
 *
 * @author Carlos Ribera
 */
public class TxtReader {
    
    public static List<Incidencia> readIncidenciasTxt(String path){
        
        // Creo una lista de Incidencias
        List<Incidencia> incidenciasList = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(path)))) {
        String linea;
        
        while((linea = br.readLine()) != null){
             if (linea.startsWith("%")) { // Si la línea empieza con % significa que comienza una nueva incidencia
                    String[] primeraLinea = linea.split(" "); // Separo la línea en partes usando 
                    String fechahora = primeraLinea[1] + " " + primeraLinea[2]; // Fecha y hora
                    String origen = primeraLinea[3]; // Usuario que reporta
                    String destino = primeraLinea[4]; // Usuario que recibe la incidencia
                    
                    String detalle = br.readLine(); // Segunda línea (detalle)
                    String tipo = br.readLine(); // Tercera línea (tipo de prioridad)

                    // Creo un objeto de tipo Incidencia y añado la incidencia a la lista
                    Incidencia incidencia = new Incidencia();
                    incidencia.setFechahora(fechahora);
                    incidencia.setOrigen(origen);
                    incidencia.setDestino(destino);
                    incidencia.setDetalle(detalle);
                    incidencia.setTipo(tipo);

                    incidenciasList.add(incidencia);
                }
        }
        
        }catch(IOException e){
            e.printStackTrace();
        }
        return incidenciasList;
    }
}
