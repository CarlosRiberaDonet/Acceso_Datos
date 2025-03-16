/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Carlos Ribera
 */
public class Validation {

    // Método para validar el input introducido por el USR
    public static String SolicitaString(String texto, int maxLength){
        // Declaración de variables
        Scanner sc = new Scanner(System.in);
        boolean error;
        String nombre = "";
        
        do{
            error = false; 
            try{                            
                System.out.println(texto);
                nombre = sc.nextLine();
                if(nombre.trim().isEmpty() || nombre.length() > maxLength){
                    System.out.println("El campo no puede estar vacio ni contener mas de " + maxLength + " caracteres");
                    error = true;
                }
            
            } catch(InputMismatchException e){
                System.out.println("ERROR");
                error = true;
                sc.nextLine(); // Limpio el buffer del Scanner
            }     
        }while(error);     
    return nombre;
    }
    
    // Método para validar el número de teléfono introducido por el USR
    public static String SolicitaTelefono(String texto){
        Scanner sc = new Scanner(System.in);
        String telefono = "";
        boolean error;
        
        do{
            error = false;
                System.out.println(texto);
                telefono = sc.nextLine().trim()
                        ;
                // Verifico que el input no está vacío y tiene exactamente 9 dígitos
                if(telefono.isEmpty() || telefono.length() != 9){
                    System.out.println("El telefono no puede estar vacio y, debe contener exactamente 9 digitos");
                    error = true;
                    continue; // Salto a la siguiente iteración sin intentar parsear
                }
            try{
                int numeroTelefono = Integer.parseInt(telefono); 
                
            } catch(NumberFormatException e){
                System.out.println("Los caracteres y los espacios no estan permitidos");
                error = true;
            }          
        }while(error);     
    return telefono;
    }
    
    // Método para solicitar la fecha
    public static LocalDateTime SolicitaFecha(){
        // Declaración de variables
        Scanner sc = new Scanner(System.in);
        LocalDateTime fecha = null;
        boolean error = false;      
        
        do{
            error = false;
            System.out.println("1: Introducir fecha y hora actual");
            System.out.println("2: Introducir fecha y hora manualmente");
            // Control de errores
            try{
                int input = sc.nextInt();
                sc.nextLine(); // Consumo salto de línea 
                if (input == 1) {
                    fecha = LocalDateTime.now(); // ✅ Obtiene la fecha y hora actual sin milisegundos
                } else if (input == 2) {
                    System.out.println("Introduzca la fecha con formato yyyy-MM-dd HH:mm:ss");
                    String fechaStr = sc.nextLine();
                    fecha = LocalDateTime.parse(fechaStr, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")); // ✅ Convierte String a LocalDateTime
                } 
       
             } catch(NumberFormatException e){
                 System.out.println("Los caracteres no estan permitidos");
                 error = true;
             }   
              catch(IllegalArgumentException e){
                 System.out.println("Formato de fecha incorrecto. Use yyyy-MM-dd HH:mm:ss");
                 error = true;
             }
                 
        } while(error || fecha == null);
        
        return fecha;
    }
    
    // Método para solicitar ID de empleado
    public static int SolicitaId(String texto){
        // Declaración de variables
        Scanner sc = new Scanner(System.in);
        int id = -1;
        boolean error;
        
        System.out.println(texto);
        do{
            error = false;
            try{
                id = sc.nextInt();
            } catch(Exception e){
                System.out.println("Los caracteres no estan permitidos");
                error = true;
                sc.nextLine();
            }
        } while(error);       
        return id;
    }
}
