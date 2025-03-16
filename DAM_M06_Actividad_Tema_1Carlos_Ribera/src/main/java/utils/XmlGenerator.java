/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import jakarta.xml.bind.JAXBContext;
import jakarta.xml.bind.JAXBException;
import jakarta.xml.bind.Marshaller;
import java.io.File;
import java.util.List;
import models.Incidencia;
import models.Incidencias;

/**
 *
 * @author Carlos Ribera
 */
public class XmlGenerator {
    
    
    public static void generateXml(List<Incidencia> incidenciasList,String path){
        
        // Compruebo que el valor pasado al metodo no sea null ni este vacío
        if(incidenciasList != null && !incidenciasList.isEmpty()){
            
            // Creo un objeto de tipo Incidencias
            Incidencias incidencias = new Incidencias();
            
            //Guardo la lista dentro
            incidencias.setIncidencia(incidenciasList);
            
            try{
                JAXBContext context = JAXBContext.newInstance(Incidencias.class);

                Marshaller marshaller = context.createMarshaller();
                marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);

                marshaller.marshal(incidencias, new File(path));
                
                System.out.println("XML generado correctamente en: " + path);
            } catch(JAXBException e){
                e.printStackTrace();
            }
        }
            
    }
}
