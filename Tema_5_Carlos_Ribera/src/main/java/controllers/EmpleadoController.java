/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import modelos.Empleado;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import utils.Utils;

/**
 *
 * @author Carlos Ribera
 */
public class EmpleadoController {
    private static List<Empleado> empleadosList = new ArrayList<>();
       
    public static void crearEmpleado(){
        Scanner sc = new Scanner(System.in);
        
        System.out.println("------ CREAR EMPLEADO ------");
        String nombreUsuario = Utils.solicitaString("Ingrese el nombre de usuario del nuevo empleado");
        String password = Utils.solicitaString("Ingrese el nuevo password");
        String nombre = Utils.solicitaString("Ingrese el nombre del empelado");
        String apellidos = Utils.solicitaString("Ingrese el nombre completo");
        String direccion = Utils.solicitaString("Ingrese la direccion");
        String telefono = Utils.solicitaTelefono("Ingrese el numero de telefono");
        
        Empleado nuevoEmpleado = new Empleado(nombreUsuario, password, nombre, apellidos, direccion, telefono);  
    }
    
    public static Empleado leerDomEmpleado(NodeList datosEmpleado){
        
        // Declaración de variables
        String nombreUsuario = null;
        String password = null;
        String nombre = null;
        String apellidos = null;
        String direccion = null;
        String telefono = null;
        
        // Itero todos los nodos hijo del elemento <empleado>
        for(int i = 0; i < datosEmpleado.getLength(); i++){
            
            // Obtengo el nodo actual
             Node nodo = datosEmpleado.item(i);
             
             // Compruebo que es un nodo de tipo ELEMENT_NODE 
             if(nodo.getNodeType() == Node.ELEMENT_NODE){
                 
                 // Guardo el nombre de la etiqueta y su contenido
                 String nombreEtiqueta = nodo.getNodeName();
                 String valorTexto = nodo.getTextContent();
                 
         

                 
                 switch(nombreEtiqueta){
                     
                     case "usuario":{
                         nombreUsuario = valorTexto;
                         break;
                     }
                     case "password":{
                         password = valorTexto;
                         break;
                     }
                     case "nombre":{
                         nombre = valorTexto;
                         break;
                     }
                     case "apellidos":{
                         apellidos = valorTexto;
                         break;
                     }
                     case "direccion":{
                         direccion = valorTexto;
                         break;
                     }
                     case "telefono":{
                         telefono = valorTexto;
                         break;
                     }
                 }
             }
        }
        
        // Creo un objeto de tipo Empleado con los datos extraidos del XML
        Empleado empleado = new Empleado(nombreUsuario, password, nombre, apellidos,direccion, telefono);
        
        return empleado; // Devuelvo el objeto empleado
    }
}
