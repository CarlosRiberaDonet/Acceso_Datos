/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import com.db4o.ObjectSet;
import entidades.Empleado;
import entidades.Incidencia;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import utils.Validation;

/**
 *
 * @author Carlos Ribera
 */
public class IncidenciaController {
    
    private static DAO dao = new DAO();
    
    // Método para calcular id de la incidencia
    public static int CalcularIdIncidencia(){
        
        int maxId = 0;
        
        ObjectSet<Incidencia> incidenciasList = dao.getDB().query(Incidencia.class);
        
        if(!incidenciasList.isEmpty()){
            for(Incidencia i : incidenciasList){
                if(i.getId() > maxId){
                    maxId = i.getId();
                }
            }
            return maxId +1; // Devuelvo el siguiente ID disponible
        } 
        return 1; // Si es la primera incidencia, devuelvo 1
    }
    
    // Método para crear incidencias
    public static void CrearIncidencia(){
        Scanner sc = new Scanner(System.in);
        
        int nuevoid = CalcularIdIncidencia();
        LocalDateTime fecha = LocalDateTime.now();
        
        String usuarioOrigen = Validation.SolicitaString("Introduzca el nombre de usuario del empleado de origen", 30).toLowerCase();
        
        // Si el usuario no existe salgo del método
        if(!dao.BuscarNombreUsuario(usuarioOrigen)){
           System.out.println("No se ha encontrado el nombre de usuario introducido");
           return;
            
        }
        Empleado empleadoOrigen = dao.getEmpleado(usuarioOrigen);
        
        String usuarioDestino = Validation.SolicitaString("Introduzca el nombre de usuario del empleado de destino", 30).toLowerCase();
        
        if(!dao.BuscarNombreUsuario(usuarioDestino)){
             System.out.println("No se ha encontrado el nombre de usuario introducido");
             return;
        }
        
        Empleado empleadoDestino = dao.getEmpleado(usuarioDestino);
        
        System.out.println("Escriba los detalles de la incidencia");
        String detalle = sc.nextLine();
        
        char tipo = Validation.TipoIncidenia("Introduzca el tipo de incidencia: U/N");
        
        Incidencia nuevaIncidencia = new Incidencia(nuevoid, fecha, empleadoOrigen, empleadoDestino, detalle, tipo);
        
        empleadoOrigen.getIncidencias().add(nuevaIncidencia);
        empleadoDestino.getIncidencias().add(nuevaIncidencia);
        
        dao.SaveObject(empleadoOrigen);
        dao.SaveObject(empleadoDestino);
        
        dao.SaveObject(nuevaIncidencia);
        
        
    }
    // Método para obtener incidencia por id
    public static void ObtenerIncidenciaId(){
       
        // Solicito ID por consola
       int id = Validation.SolicitaId("Ingrese el ID de la incidencia que desea buscar");
       
       // Llamo al método que busca la incidencia
       Incidencia incidencia = dao.getIncidencia(id);
   
       if(incidencia != null){
           System.out.println(incidencia.toString());   
       } else{
           System.out.println("No se ha encontrado ninguna incidencia con el ID introducido");
       }   
    }
    
    // Método para listar todas las incidencias
    public static void ObtenerListaIncidencias(){
        
        List<Incidencia> incidenciasList = dao.getIncidenciasList();
        
        for(Incidencia i : incidenciasList){
            System.out.println("------ INCIDENCIA ------");
            System.out.println(i.toString());
        }
    }
    
    // Método para obtener inciencias de un empleado
    public static void InidenciasEmpleadoOrigen(){
        
        List<Incidencia> incidencias = new ArrayList<>();
        
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30).toLowerCase();
        
        if(dao.BuscarNombreUsuario(nombreUsuario)){
            Empleado empleado  = dao.getEmpleado(nombreUsuario);
        
            List<Incidencia> incidenciasList = dao.getIncidenciasList();
            
            for(Incidencia i : incidenciasList){
                    if(i.getEmpleadoOrigen().equals(empleado)){
                        incidencias.add(i);
                        System.out.println(i.toString());
                    }
            }
            return;
        }
         System.out.println("No se ha encontrado ningun usuario con el nombre proporcionado"); 
    }
    
    public static void InidenciasEmpleadoDestino(){
        
        
        List<Incidencia> incidencias = new ArrayList<>();
        
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30);
        
        if(dao.BuscarNombreUsuario(nombreUsuario)){
            Empleado empleado  = dao.getEmpleado(nombreUsuario);
            
             List<Incidencia> incidenciasList = dao.getIncidenciasList();
            
           incidencias =  empleado.getIncidencias();
           
            for(Incidencia i : incidenciasList){
                    if(i.getEmpleadoDestino().equals(empleado)){
                        incidencias.add(i);
                         System.out.println(i.toString());
                    }
            }
            return;
        }
    }
    
}
