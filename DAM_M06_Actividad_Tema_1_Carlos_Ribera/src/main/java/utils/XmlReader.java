/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Unmarshaller;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import models.Incidencia;
import models.Incidencias;

/**
 *
 * @author Carlos Ribera
 */
public class XmlReader {
    
    public static List<Incidencia> readXml(String path){
        
        try{
            JAXBContext context = JAXBContext.newInstance(Incidencias.class);
            Unmarshaller unmarshaller = context.createUnmarshaller();
            
            // Leo el XML y lo convierto en un objeto Incidencias
            Incidencias incidencias = (Incidencias) unmarshaller.unmarshal(new File(path));
            
            // Devuelvo la lista de incidencias
            return incidencias.getIncidencia();
            
        }catch(JAXBException e){
            e.printStackTrace();
        }
        
        // Si hay alguún error devuelvo la lista vacía
        return new ArrayList<>();
    }
}
