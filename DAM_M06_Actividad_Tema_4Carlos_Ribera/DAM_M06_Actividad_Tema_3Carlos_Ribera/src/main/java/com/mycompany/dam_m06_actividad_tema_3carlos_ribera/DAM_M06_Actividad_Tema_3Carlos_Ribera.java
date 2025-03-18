/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.dam_m06_actividad_tema_3carlos_ribera;

import Controllers.EmpleadoController;
import Controllers.IncidenciaController;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 *
 * @author Carlos Ribera
 */
public class DAM_M06_Actividad_Tema_3Carlos_Ribera {

    public static void main(String[] args) {
        
        Scanner sc = new Scanner(System.in);
        int menu = 0;
        
        // Inicio bucle para permanecer en el menú hasta que el USR quiera salir       
        while(menu != 6){ // Repetir bucle mientras menu sea distinto a 6

            // Muestro menú por consola
           System.out.println("------ MENU PRINCIPAL -----");
           System.out.println("1: Añadir empleado");
           System.out.println("2: Login");
           System.out.println("3: Modificar Empleado");
           System.out.println("4: Cambiar Contraseña Empleado");
           System.out.println("5: Eliminar Empleado");
           System.out.println("6: Salir");
           
           // Control de errores
           try{
               menu = sc.nextInt(); // Pido entrada por consola al USR

               // Menú
               switch(menu){
                   case 1:{
                       System.out.println("------ AÑADIR NUEVO EMPLEADO ------");
                       EmpleadoController.CrearEmpleado();
                       break;
                   }
                   case 2:{
                       System.out.println("------ LOGIN ------");
                       if(EmpleadoController.Login()){
                           MenuIncidencias();                          
                       }
                       break;
                   }
                   case 3:{
                        System.out.println("------ MODIFICAR EMPLEADO ------");
                        EmpleadoController.ModificarEmpleado();
                       break;
                   }
                   case 4:{
                       System.out.println("------ MODIFICAR CONTRASEÑA ------");
                       EmpleadoController.CambiarContraseña();
                       break;

                   }
                   case 5:{
                       System.out.println("------ ELIMINAR EMPLEADO ------");
                       EmpleadoController.EliminarEmpleado();
                       break;
                   }
                   case 6:{
                       System.out.println("------ HASTA LA PROXIMA! ------");;
                       break;
                   }
                   default:{
                       System.out.println("Opcion no valida. Debe introducir un numero del 1 al 6");
                       break;
                   }
               }               
           } catch(InputMismatchException e){
               System.out.println("Los caracteres no estan permitidos");
               sc.nextLine(); // Limpio el buffer del Scanner para evitar bucle infinito
           }        
        }
    }
    
    public static void MenuIncidencias(){
        
        Scanner sc = new Scanner(System.in);
        String input = "";
        int n; // Variable para almacenar la opción elegida a la hora de mostrar las incidencias
        
        // Inicio bucle para permanecer en el menú hasta que el USR quiera salir       
        while(input != "q"){ // Repetir bucle mientras menu sea distinto a 6

            // Muestro menú por consola
           System.out.println("------ MENU INCIDENCIAS-----");
           System.out.println("a: Buscar Incidencia");
           System.out.println("b: Listar Incidencias");
           System.out.println("c: Registrar Incidencia");
           System.out.println("d: Buscar Incidencias Creadas por Empleado");
           System.out.println("e: Buscar Incidencias destinadas a Empleado");
           System.out.println("q: Salir");
           
           // Control de errores
           try{
               input = sc.nextLine(); // Pido entrada por consola al USR

               // Menú
               switch(input){
                   case "a":{
                       System.out.println("------ BUSCAR INCIDENCIA ------");
                       IncidenciaController.BuscarIncidencia();
                       break;
                   }
                   case "b":{
                       n = 1;
                       System.out.println("------ LISTAR INCIDENCIAS ------");
                       IncidenciaController.ListarIncidencias(n);
                       break;
                   }
                   case "c":{
                        System.out.println("------ REGISTRAR INCIDENCIA ------");
                        IncidenciaController.CrearIncidencia();
                       break;
                   }
                   case "d":{
                       n = 2;
                       System.out.println("------ BUSCAR INCIDENCIAS CREADAS POR EMPLEADO ------");     
                       IncidenciaController.ListarIncidencias(n);
                       break;
                   }
                   case "e":{
                       
                       System.out.println("------ BUSCAR INCIDENCIAS DESTINADAS A EMPLEADO ------");
                       IncidenciaController.ObtenerIncidenciasDestinadas();
                       
                       break;
                   }
                   case "q":{
                       System.out.println("------ HASTA LA PROXIMA! ------");;
                       return;
                   }
                   default:{
                       System.out.println("Opcion no valida. Debe introducir un caracter de la lista");
                       break;
                   }
               }               
           } catch(InputMismatchException e){
               System.out.println("Los numeros no estan permitidos");
               sc.nextLine(); // Limpio el buffer del Scanner para evitar bucle infinito
           }        
        }
        
    }     
}

