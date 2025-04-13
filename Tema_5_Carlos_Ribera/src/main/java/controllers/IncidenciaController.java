/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.List;
import modelos.Empleado;
import modelos.Incidencia;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.Utils;

/**
 *
 * @author Carlos Ribera
 */
public class IncidenciaController {
    
     private static GestorXML xml = new GestorXML();
     
     public static int calcularIdIncidencia(List<Incidencia> incidenciasList){
        
        int id = 0;
        
        for(Incidencia e : incidenciasList){
            if(e.getId() >= id){
               id = e.getId() +1;
            }
        }
        return id;
    }
     
     public static void obtenerIncidencia(){
         
         int id = Utils.solicitaId("Introduzca el id de la incidencia que desea buscar");
         
         Incidencia incidencia = xml.getIncidencia(id);
         
         if(incidencia != null){
             System.out.println(incidencia);
         } else{
             System.out.println("No se ha encontrado ninguna incidencia con el id introducido"); 
         }     
     }
     
     public static Incidencia leerDomIncidencia(NodeList datosIncidencia){
         
         // Declaración de variables
         int id = 0;
         String fechaHora = null;
         Empleado empleadoOrigen = new Empleado();
         Empleado empleadoDestino = new Empleado();
         String detalleIncidencia = null;
         char tipoIncidencia = ' ';
          
         // Recorro los nodos del elemento <incidencia>
         for(int i = 0; i < datosIncidencia.getLength(); i++){
             Node nodo = datosIncidencia.item(i);
             
             // Solo proceso los nodos que sean etiquetas
             if(nodo.getNodeType() == Node.ELEMENT_NODE){
                 String nombreEtiqueta = nodo.getNodeName();
                 String valorTexto = nodo.getTextContent();
                 
                 // Asigno valores a las variables según la etiqueta
                 switch(nombreEtiqueta){
                     
                     case "id":{
                        id = Integer.parseInt(valorTexto);
                        break;
                     }
                     case "origen":{
                         empleadoOrigen.setNombreUsuario(valorTexto);
                         break;
                     }
                     case "destino":{
                         empleadoDestino.setNombreUsuario(valorTexto);
                         break;
                     }
                     case "detalle":{
                         detalleIncidencia = valorTexto;
                         break;
                     }
                     case "tipo":{
                         tipoIncidencia =  valorTexto.charAt(0); // Cojo el primer
                         break;
                     }
                     case "fechahora":{
                         fechaHora = valorTexto;
                         break;
                     }
                 }         
             }
        }
        // Creo y devuelvo el objeto Incidencia
        return new Incidencia(id, fechaHora, empleadoOrigen, empleadoDestino, detalleIncidencia, tipoIncidencia);
     }
     
     public static void obtenerListaIncidencias(){
         
         List<Incidencia> incidenciasList = xml.obtenerListaIncidencias();
         
         for(Incidencia i : incidenciasList){
             System.out.println(i);
         }
     }
     
     public static Incidencia crearIncidencia(){
         
         // int id = calcularIdIncidencia();
         
         return null;
     }
}
