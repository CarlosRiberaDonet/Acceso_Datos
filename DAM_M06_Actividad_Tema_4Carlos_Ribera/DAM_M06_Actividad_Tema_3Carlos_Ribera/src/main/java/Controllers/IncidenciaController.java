/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controllers;

import Modelos.Empleados;
import Modelos.Incidencias;
import Utils.Validation;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Carlos Ribera
 */
public class IncidenciaController {
    
    public static void BuscarIncidencia(){
        
        // Instancias
        Scanner sc = new Scanner(System.in);
        DAO dao = new DAO();
        // Variables
        Incidencias incidencia;
        int id;
        
        try{
            System.out.println("Introduzca el numero de ID de la incidencia");
            id = sc.nextInt();
            
            incidencia = dao.GetIncidencia(id); // Obtengo el objeto Incidencias
            
            if(incidencia != null){
                System.out.println("------ RESUMEN INCIDENCIA ------");
                System.out.println("id: " + incidencia.getId());
                System.out.println("Fecha: " + incidencia.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                System.out.println("id_empleado_origen: " + incidencia.getEmpleadoOrigen().getNombreCompleto());
                System.out.println("id_empleado_destino: " + incidencia.getEmpleadoDestino().getNombreCompleto());
                System.out.println("detalle: " + incidencia.getDetalle());
                System.out.println("tipo: " + incidencia.getTipo());
            } else{
                System.out.println("No se ha encontrado ninguna incidencia con el id introducido");
            }
            
        } catch(InputMismatchException e){
            System.out.println("Los caracteres no estan permitidos");
        }   
    }
    
    // Método para listar incidencias
    public static void ListarIncidencias(int opcion){
        
        DAO dao = new DAO();
        List<Incidencias> incidenciasList = new ArrayList<>();
        
        try{
            
            // Opción 1 obtiene todas las incidencias
            if(opcion == 1){
                incidenciasList = dao.GetIncidenciasList();
            }
            
            // Opción 2 obtiene las incidencias creadas por un empleado concreto
            if(opcion == 2){
                String nombreCompleto = Validation.SolicitaString("Introduzca el nombre completo del empleado", 50);
                Empleados empleado = dao.BuscarNombreEmpleado(nombreCompleto);
                if(empleado == null){
                    System.out.println("El nombre introducido no coincide con ningun empleado");
                    return;
                }
                incidenciasList = dao.IncidenciasList(nombreCompleto); // Obtengo la lista de incidencias del empleado 
                
            }
            // Opción 3 obtiene todas las incidencias destinadas a un empleado concreto
            /*if(opcion == 3){
                String nombreCompleto = Validation.SolicitaString("Introduzca el nombre completo del empleado", 50);
                Empleados empleado = dao.BuscarNombreEmpleado(nombreCompleto);// Busco el nombre en la BD
                
                // Si no hay ningún empleado en la BD de datos con el nombre introducido
                if(empleado == null){
                    System.out.println("El nombre introducido no coincide con ningun empleado");
                    return;
                }
                incidenciasList = dao.IncidenciasList(nombreCompleto); // Obtengo la lista de incidencias del empleado 
            }*/
            
            // Si la lista de incidencias no esta vacía
            if(incidenciasList != null && !incidenciasList.isEmpty())
            {
                // Itero sobre incidenciasList
                for(Incidencias i : incidenciasList){
                    System.out.println("------ RESUMEN INCIDENCIA ------");
                    System.out.println("ID: " + i.getId());
                    System.out.println("FECHA: " + i.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    System.out.println("EMPLEADO_ORIGEN: " + i.getEmpleadoOrigen().getNombreCompleto());
                    System.out.println("EMPLEADO_DESTINO: " + i.getEmpleadoDestino().getNombreCompleto());
                    System.out.println("DETALLES: " + i.getDetalle());
                    System.out.println("TIPO: " + i.getTipo());
                }
            } else{
                System.out.println("No hay incidencias en la lista");
            }
        } catch(Exception e){
            System.out.println("No se ha podido obtener la lista de incidencias");
        }      
    }
    
    public static void CrearIncidencia(){
        
        DAO dao = new DAO();
        LocalDateTime fecha;
        Empleados empleadoOrigen;
        Empleados empleadoDestino;
        String detalle;
        String tipoStr;
        char tipo;
        
        try{
            // Solicito la fecha hora actual
            fecha = Validation.SolicitaFecha();      
            System.out.println("Fecha introducida correctamente");

            // Solicito el empleado de origen
            do{
                // Solicito el nombre de usuario
                String nombreEmpleadoOrigen = Validation.SolicitaString("Introduzca el nombre de usuario del empleado de origen", 30);      
                
                // Busco el usuario en la BD
                empleadoOrigen = dao.BuscarNombreUsuario(nombreEmpleadoOrigen); 
                
                if(empleadoOrigen == null){
                    System.out.println("No se ha encontrado ningun usuario con el nombre de usuario introducido");
                }
            } while(empleadoOrigen == null); // Se repite hasta encontrar un usuario válido
            
            // Solicito el empleado de destino
            do{
                // Solicito el nombre de usuario  
                String nombreEmpleadoDestino = Validation.SolicitaString("Introduzca el nombre de usuario del empleado de destino", 30);
                
                 // Busco el usuario en la BD
                 empleadoDestino = dao.BuscarNombreUsuario(nombreEmpleadoDestino);
                
                if(empleadoDestino == null){
                    System.out.println("No se ha encontrado ningun usuario con el nombre de usuario introducido");
                }
                
            } while(empleadoDestino == null); // Se repite hasta encontrar un usuario válido
            
           // Solicito el campo detalle de la tabla incidencia
           detalle = Validation.SolicitaString("Introduzca los detalles de la incidencia", 255);

           do{
                // Solicito el campo tipo de la tabla incidencia
                tipoStr = Validation.SolicitaString("Introduzca el tipo de incidencia. U/N", 1).toUpperCase();
                tipo = tipoStr.charAt(0); // Convierto String a char
           } while(!tipoStr.equals("N") && !tipoStr.equals("U"));
           
           Incidencias nuevaIncidencia = new Incidencias (fecha, empleadoOrigen, empleadoDestino, detalle, tipo);
              
           dao.Guardar(nuevaIncidencia);
            System.out.println("Incidencia creada exitosamente");
            
        } catch(Exception e){
            e.printStackTrace();
        }       
    }
    
    public static void ObtenerIncidenciasDestinadas(){  
        
        DAO dao = new DAO();
        
        String nombreEmpleado = Validation.SolicitaString("Introduzca el nombre completo del empleado", 50);
        
        Empleados empleado = dao.BuscarNombreEmpleado(nombreEmpleado);
        
        if(empleado != null){
            
            if(empleado.getIncidenciasDestinadas()!= null){
                for(Incidencias i : empleado.getIncidenciasDestinadas()){
                    System.out.println("------ INCIDENCIA ------");
                    System.out.println("------ RESUMEN INCIDENCIA ------");
                    System.out.println("ID: " + i.getId());
                    System.out.println("FECHA: " + i.getFecha().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
                    System.out.println("EMPLEADO_ORIGEN: " + i.getEmpleadoOrigen().getNombreCompleto());
                    System.out.println("EMPLEADO_DESTINO: " + i.getEmpleadoDestino().getNombreCompleto());
                    System.out.println("DETALLES: " + i.getDetalle());
                    System.out.println("TIPO: " + i.getTipo());
                }
            } else{
                System.out.println("No hay incidencias destinadas para el empleado seleccionado");
            }     
        } else{
            System.out.println("El nombre introducido no coincide con ningun empleado");
        }
        
    }
}

                       
            