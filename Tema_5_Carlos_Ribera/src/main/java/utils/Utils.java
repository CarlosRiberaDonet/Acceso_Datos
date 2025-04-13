/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package utils;

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
}
