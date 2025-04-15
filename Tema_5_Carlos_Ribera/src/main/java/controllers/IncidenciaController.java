/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
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
     
    /* public static int calcularIdIncidencia(List<Incidencia> incidenciasList){
        
        int id = 0;
        
        for(Incidencia e : incidenciasList){
            if(e.getId() >= id){
               id = e.getId() +1;
            }
        }
        return id;
    } */
     
     public static void obtenerIncidencia(){
         
         int id = Utils.solicitaId("Introduzca el id de la incidencia que desea buscar");
         
         Incidencia incidencia = xml.getIncidenciaById(id);
         
         if(incidencia != null){
             System.out.println(incidencia);
         } else{
             System.out.println("No se ha encontrado ninguna incidencia con el id introducido"); 
         }     
     }
     
     public static Incidencia leerDomIncidencia(NodeList datosIncidencia){
         
         // Declaración de variables
         int idIncidencia = 0; 
         Empleado empleadoOrigen = new Empleado();
         Empleado empleadoDestino = new Empleado();
         String tipoIncidencia = null;
         String detalleIncidencia = null;
         String fechaHora = null;
          
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
                        idIncidencia = Integer.parseInt(valorTexto);
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
                     case "tipo":{
                         tipoIncidencia =  valorTexto;
                         break;
                     }
                     case "detalle":{
                         detalleIncidencia = valorTexto;
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
        return new Incidencia(idIncidencia, empleadoOrigen, empleadoDestino, tipoIncidencia, detalleIncidencia,  fechaHora);
     }
     
     public static void obtenerListaIncidencias(){
         
         List<Incidencia> incidenciasList = xml.getIncidenciasList();
         
         for(Incidencia i : incidenciasList){
             System.out.println(i);
         }
     }
     
     public static void crearIncidencia(){

         // Defino e inicializo variables
         int idIncidencia;
         Empleado empleadoOrigen;
         Empleado empleadoDestino;
         String tipoIncidencia;
         String detalle;
         String fechaHora;
         
        // Pido datos al USR
        String nombreOrigen = Utils.solicitaString("Introduza el nombre del empleado de origen");    
        String apellidosOrigen = Utils.solicitaString("Introduzca los apellidos del empleado de origen");
        
        // Intento obtener el objeto Empleado (origen)
        empleadoOrigen = xml.obtenerEmpleado(nombreOrigen, apellidosOrigen);
        
        // Si el empleado existe en la BD
        if(empleadoOrigen != null){
            // Solicito los datos del empleado de destino
            String nombreDestino = Utils.solicitaString("Introduza el nombre del empleado de destino");    
            String apellidosDestino = Utils.solicitaString("Introduzca los apellidos del empleado de destino");
            
            // Intento obtener el objeto Empleado (destino)
            empleadoDestino = xml.obtenerEmpleado(nombreDestino, apellidosDestino);
            
            // Si los 2 empleados introducidos existen en la BD
            if(empleadoDestino != null){ 
                // Solicito y asigno a las respectivas varibles el resto de datos
                idIncidencia = xml.getMaxIdIncidencia() +1; // Obtengo el último id de <incidencia> y le sumo 1   
                tipoIncidencia = Utils.solicitaTipoIncidencia("Ingrese el tipo de incidencia: \n N: Normal \n U: Urgente");
                detalle = Utils.solicitaString("Introduzca los detalles de la incidencia");
                fechaHora = Utils.solicitaInt("1- Asignar fecha y hora actual \n 2- Asignar fecha y hora manualmente");
                
                Incidencia nuevaIncidencia = new Incidencia(idIncidencia, empleadoOrigen, empleadoDestino, tipoIncidencia, detalle, fechaHora);
        
                xml.insertarIncidencia(nuevaIncidencia);
            } else{
                System.out.println("No existe ningun empleado con los datos introducidos");
            }
        } else{
            System.out.println("No existe ningun empleado con los datos introducidos");
        }
     }
     
     // Si recibe true mostrará las incidencias creadas, si recibe false, las incidencias recibidas
     public static void listarIncidenciasCreadas(boolean incidenciaCreada){
        
        List<Incidencia> incidenciasList = new ArrayList<>();
        String nombreUsuario = Utils.solicitaString("Introduzca el nombre de usuario");
        
        // Si incidencia creada = true
        if(incidenciaCreada){
            incidenciasList = xml.getIncicidenciasEmpleado(nombreUsuario, true);
        } 
        // Si incidencia creada = false
        else if(!incidenciaCreada){
            incidenciasList = xml.getIncicidenciasEmpleado(nombreUsuario, false);
        }     
        if(incidenciasList != null && !incidenciasList.isEmpty()){
            for(Incidencia i : incidenciasList){
                System.out.println(i);
            }
        }
        else{
            System.out.println("No se han encontrado incidencias con el nombre de usuario introducido");
        }
     }
}
