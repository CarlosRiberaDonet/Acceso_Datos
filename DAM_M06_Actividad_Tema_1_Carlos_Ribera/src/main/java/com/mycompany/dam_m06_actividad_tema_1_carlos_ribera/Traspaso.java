/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dam_m06_actividad_tema_1_carlos_ribera;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import models.Incidencia;
import utils.TxtReader;
import utils.XmlGenerator;
import utils.XmlReader;

/**
 *
 * @author Carlos Ribera
 */
public class Traspaso {

    // Declaro las variables
    public static String se = File.separator;
    // Directorio por defecto del proyecto
    public static final String path = System.getProperty("user.dir");
    // Referencia a archivo txt
    public static String txt = path + se + "/incidencias.txt";
    // Referencia a archivo xml
    public static String xml = path + se + "/incidencias.xml";
    // Defino una List de tipo Incidencia
    public static List<Incidencia> incidenciasList = new ArrayList<>();
    
    public static void main(String[] args) {
        
        
        File archivoTxt = new File(txt);
        
        // Compruebo si el archivo txt existe
        if(archivoTxt.exists() && archivoTxt.isFile()){
            
            // Leo el archivo txt y lo paso a la List de tipo Incidencia
            incidenciasList = TxtReader.readIncidenciasTxt(txt);   
        
            // Guardo la List de tipo Incidencia en formato xml
            XmlGenerator.generateXml(incidenciasList, xml);
        
        
            //Leo el archivo xml y muestro por consola su contenido
            incidenciasList = XmlReader.readXml(xml);
            System.out.println("Incidencias cargadas desde el XML:");
            for(Incidencia inc : incidenciasList){
                System.out.println("Fecha y Hora: " + inc.getFechahora());
                System.out.println("Origen: " + inc.getOrigen());
                System.out.println("Destino: " + inc.getDestino());
                System.out.println("Detalle: " + inc.getDetalle());
                System.out.println("Tipo: " + inc.getTipo());
                System.out.println("--------------------------------------");
            } 
            
        } else{
            System.out.println("No se ha encontrado el archivo .txt");
            
        }     
    }
}
