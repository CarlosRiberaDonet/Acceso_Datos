/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

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
    
    private static GestorXML xml = new GestorXML();
       
    public static void crearEmpleado(){
        Scanner sc = new Scanner(System.in);
        GestorXML xml = new GestorXML();
        
        String nombreUsuario = Utils.solicitaString("Ingrese el nombre de usuario del nuevo empleado");
        String password = Utils.solicitaString("Ingrese el nuevo password");
        String nombre = Utils.solicitaString("Ingrese el nombre");
        String apellidos = Utils.solicitaString("Ingrese los apellidos");
        String direccion = Utils.solicitaString("Ingrese la direccion");
        String telefono = Utils.solicitaTelefono("Ingrese el numero de telefono");
        
        Empleado nuevoEmpleado = new Empleado(nombreUsuario, password, nombre, apellidos, direccion, telefono);  
        xml.insertarEmpleado(nuevoEmpleado);
        System.out.println("Empleado " + nuevoEmpleado.getNombre() + " " + nuevoEmpleado.getApellidos() + " insertado exitosamente.");
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
    
    public static boolean login(){
        
        GestorXML xml = new GestorXML();
        
        String nombreUsuario = Utils.solicitaString("Ingrese el nombre de usuario");
        String password = Utils.solicitaString("Ingrese el password");
        
        if(xml.validarEntrada(nombreUsuario, password)){
            System.out.println("Login correcto");
            return true;
        } else {
            System.out.println("login incorecto");
            return false;
        }
    }
    
    public static void modificarPerfil(){
        
        Empleado empleado = getEmpleado();
        
        if(empleado != null){ // Si el empleado existe
            String nombreUsuario = empleado.getNombreUsuario();
            if(Utils.solicitaConfirmacion("Quiere modificar el nombre de usuario?")){
                String nuevoNombreUsuario = Utils.solicitaString("Ingrese el nuevo nombre de usuario");
                empleado.setNombreUsuario(nuevoNombreUsuario);
            }
            if(Utils.solicitaConfirmacion("Quiere modificar el password del usuario?")){
                 String nuevoPassword = Utils.solicitaString("Ingrese el nuevo password");
                 empleado.setPassword(nuevoPassword);
             }
            System.out.println("S/N");
            if(Utils.solicitaConfirmacion("Quiere modificar el nombre del empleado?")){
                String nuevoNombre = Utils.solicitaString("Ingrese el nuevo nombre del empleado");
                empleado.setNombre(nuevoNombre);
            }
            if(Utils.solicitaConfirmacion("Quiere modificar los apellidos del empleado?")){
                String nuevoApellidos = Utils.solicitaString("Ingrese los nuevos apellidos");
                empleado.setApellidos(nuevoApellidos);
            }
            if(Utils.solicitaConfirmacion("Quiere modificar la direccion del empleado?")){
                String nuevoDireccion = Utils.solicitaString("Ingrese la nueva direccion");
                empleado.setDireccion(nuevoDireccion);
            }
            if(Utils.solicitaConfirmacion("Quiere modificar el numero de telefono del empleado?")){
                String nuevoTelefono = Utils.solicitaTelefono("Ingrese el nuevo numero de telefono");
                empleado.setTelefono(nuevoTelefono);
            } 
   
            xml.updateEmpleado(nombreUsuario, "nombre", empleado.getNombre());
            xml.updateEmpleado(nombreUsuario, "apellidos", empleado.getApellidos());
            xml.updateEmpleado(nombreUsuario, "direccion", empleado.getDireccion());
            xml.updateEmpleado(nombreUsuario, "telefono", empleado.getTelefono());
            xml.updateEmpleado(nombreUsuario, "password", empleado.getPassword());
            xml.updateEmpleado(nombreUsuario, "usuario", empleado.getNombreUsuario());
            
            
            System.out.println("Empleado modificado correctamente");
        }
        // Si el empleado no existe
        else { 
            System.out.println("No se ha encontrado ningun empleado con el nombre y apellidos proporcionados");       
        }     
    }
    
    public static void cambiarPassword(){
        
        Empleado empleado = getEmpleado();
        
        if(empleado != null){
            String nombreUsuario = empleado.getNombreUsuario();
            String nuevoPassword = Utils.solicitaString("Ingrese el nuevo password");
            xml.updateEmpleado(nombreUsuario, "password", nuevoPassword);
            System.out.println("Password modificado exitosamente");
        } else{
            System.out.println("No se ha encontrado al empleado con los datos proporcionados");
        }
    }
    
    // Creo un método auxiliar para obtener un objeto de tipo Empleado mediante su nombre y apellidos
   public static Empleado getEmpleado(){
       
        // Solicito input al USR
        String nombre = Utils.solicitaString("Ingrese el nombre del empleado");
        String apellidos = Utils.solicitaString("Ingrese los apellidos del empleado"); 
        
        // Llamo a obtenerEmpleado de la clase GestorXML
        return xml.obtenerEmpleado(nombre, apellidos); // Devuelve el empelado si lo encuentra, si no, devuelve null
   }
   
   public static void eliminarEmpleado(){
       
       // Solicito input al USR
       String nombreUsuario = Utils.solicitaString("Introduzca el nombre del empleado que desea eliminar");

            // LLamo al metodo deleteUser de la clase GestorXML
            if(xml.deleteUser(nombreUsuario)){ // Si devuelve true, el usuario se ha eliminado
                System.out.println("Uruario: " + nombreUsuario + " eliminado correctamente");
            }
            else{
                System.out.println("No se ha podido eliminar el usuario: " + nombreUsuario);
            }
   }
}
