/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tema_5_carlos_ribera;

import controllers.EmpleadoController;
import controllers.IncidenciaController;
import java.util.Scanner;


/**
 *
 * @author Carlos Ribera
 */
public class Tema_5_Carlos_Ribera {

    public static void main(String[] args) throws Exception {
 
         menuPrincipal();
   
    } 
    
     public static void menuPrincipal(){
        Scanner sc = new Scanner(System.in);
        String menu = null;
        do{
            System.out.println("------ MENU ------");
            System.out.println("a: Crear Empleado");
            System.out.println("b: Login");
            System.out.println("c: Modificar Empleado");
            System.out.println("d: Cambiar Password");
            System.out.println("e: Eliminar Empleado");
            System.out.println("f: Salir");

            menu = sc.nextLine().toLowerCase();
            
            switch(menu){
                case "a":{
                    System.out.println("------ CREAR EMPLEADO ------");
                    EmpleadoController.crearEmpleado();
                    break;
                }
                case "b":{
                    System.out.println("------ LOGIN ------");
                    if(EmpleadoController.login()){
                        menuLogin();
                    }
                    break;
                }
                case "c":{
                    System.out.println("------ MODIFICAR PERFIL ------");
                    EmpleadoController.modificarPerfil();
                    break;
                }
                case "d":{
                    System.out.println("------ CAMBIAR PASSWORD ------");
                    EmpleadoController.cambiarPassword();
                    break;
                }
                case "e":{
                    System.out.println("------ ELIMINAR EMPLEADO");
                    EmpleadoController.eliminarEmpleado();
                    break;
                }
                case "f":{
                    System.out.println("HASTA LA PROXIMA!!!");
                    break;
                }         
                default:{
                    System.out.println("Opcion no valida");
                    break;
                }
            }
        } while(!menu.equals("f"));
    }
     
     public static void menuLogin(){
         
         Scanner sc = new Scanner(System.in);
         String menu = "";
         
         do{
            System.out.println("------ MENU LOGIN ------");
            System.out.println("a: Buscar Incidencia");
            System.out.println("b: Listar Incidencias");
            System.out.println("c: Crear Incidencia");
            System.out.println("d: Buscar Incidenia Creada");
            System.out.println("e: Buscar Inciencia Destinada");
            System.out.println("f: Volver al menu principal");

            menu = sc.nextLine().toLowerCase();
            
            switch(menu){
                case "a":{
                    System.out.println("------ BUSCAR INCIDENCIA ------");
                    IncidenciaController.obtenerIncidencia();
                    break;
                }
                case "b":{
                    System.out.println("------ LISTAR INCIDENCIAS ------");
                        IncidenciaController.obtenerListaIncidencias();
                        break;
                    }                                      
                case "c":{
                    System.out.println("------ CREAR INCIDENCIA ------");
                    IncidenciaController.crearIncidencia();
                    break;
                }
                case "d":{
                    System.out.println("------ BUSCAR INCIDENCIA CREADA ------");
                   
                    break;
                }
                case "e":{
                    System.out.println("------ BUSCAR INCIDENCIA DESTINADA ------");
                  
                    break;
                }
                case "f":{
                    System.out.println("VOLVER AL MENU PRINCIPAL");
                    break;
                }         
                default:{
                    System.out.println("Opcion no valida");
                    break;
                }
            }
        } while(!menu.equals("f"));
     }
}

