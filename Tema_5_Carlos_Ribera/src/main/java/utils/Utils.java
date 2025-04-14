/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Carlos Ribera
 */
public class Utils {
    public static String solicitaString(String mensaje){
        Scanner sc = new Scanner(System.in);
        String texto;
        boolean error;
           
        do{
            error = false;
            System.out.println(mensaje);
            texto = sc.nextLine();
            
            if(texto.isBlank() || texto.trim().isEmpty()){
                System.out.println("No se aceptan entradas vacias.");
                error = true;
            }      
        } while(error);
        return texto;
    }
    
    public static String solicitaTelefono(String mensaje){
        Scanner sc = new Scanner(System.in);      
        String input = "";
        boolean error;
        
        do{
            error = false;
            try{
                System.out.println(mensaje);
                input = sc.nextLine().trim();
                
                if(input.isEmpty() || input.length() != 9){
                    System.out.println("El telefono debe contener 9 digitos.");
                    error = true;
                    continue;
                }
            
                
            } catch(NumberFormatException e){
                System.out.println("Los caracteres y los espacios no estan permitidos.");
                error = true;
            }   
        }while(error);
        return input;
    }
    
    public static boolean solicitaConfirmacion(String mensaje){
        
        Scanner sc = new Scanner(System.in);
        String input;
        boolean modificar = false;
        boolean error;
        
        do{
            error = false;                 
            System.out.println(mensaje);
            input = sc.nextLine().toLowerCase();
            if(input.equals("s")){
                modificar = true;
            } else if(input.equals("n")){
                modificar = false;
            } else{
                 System.out.println("pulse S para modificar o N si no desea modificar");  
                error = true;
            }        
        } while(error);
        return modificar;
    }
    
    public static int solicitaId(String mensaje){
        
        Scanner sc = new Scanner(System.in);
        int input = 0;
        boolean error;
        do{
            error = false;
            try{
                System.out.println(mensaje);
                input = sc.nextInt();
            }catch(InputMismatchException e){
                System.out.println("Los caracteres no estan permitidos");
                error = true;
                sc.nextLine();
            }
        }while(error);
  
        return input;
    }
    
    public static String solicitaTipoIncidencia(String mensaje){
        
        Scanner sc = new Scanner (System.in);
        String tipoIncidencia = null;
        String input = null;
        do{        
            System.out.println(mensaje);
            input = sc.nextLine().toLowerCase();
            if(input.equals("n")){
               tipoIncidencia = "normal";
            } else if(input.equals("u")){
               tipoIncidencia = "urgente";
            } else {
                System.out.println("Entrada no valida. Seleccione 'n' o 'u'.");
            }

        }while(!input.equals("n") && !input.equals("u"));
        
        return tipoIncidencia;
    }
    
    public static String solicitaFecha(String mensaje){
        
        Scanner sc = new Scanner(System.in);
        String fechaFormateada = "";
        boolean error;
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        
        do{
            error = false;
            System.out.println(mensaje);
            String input = sc.nextLine();
            
            try{
                // Intento parsear la fecha introducida por el USR
                LocalDateTime fechaHora = LocalDateTime.parse(input, formatter);
                
                // Si es válida, la convierto de nuevo en String para devolverla
                fechaFormateada = fechaHora.format(formatter);
                
            } catch(DateTimeParseException e){
                System.out.println("Formato de fecha y hora incorrecto. Ejemplo valido: 13/04/2025 12:30");
                error = true;
            }
            
           
        }while(error);
        
        return fechaFormateada;
    }
    
    public static String solicitaInt(String mensaje){
        
        Scanner sc = new Scanner(System.in);
        int input = 0;
        String fecha = null;
        
        do{
            System.out.println(mensaje);
            try{
                input = sc.nextInt();
                if(input == 1){
                    Date fechaActual = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
                    fecha = sdf.format(fechaActual);           
                } else if(input == 2){
                    fecha = solicitaFecha("Introduzca la fecha y hora: ) dd/mm/yyyy hh/mm");
                } else{
                    System.out.println("Debe selecciona una opcion valida. 1/2");
                }
            } catch(InputMismatchException e){
                System.out.println("Los caracteres no estan permitidos");
                sc.nextLine();
            }
        }while(input != 1 && input != 2);
        
        return fecha; 
    }
}
