/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.tema_5_carlos_ribera;

import controllers.EmpleadoController;
import controllers.GestorXML;
import java.util.Scanner;
import org.xmldb.api.base.Collection;


/**
 *
 * @author Carlos Ribera
 */
public class Tema_5_Carlos_Ribera {

    public static void main(String[] args) throws Exception {
 
         menuPrincipal();
        
//        GestorXML gestor = new GestorXML();
//        
//        Collection col = gestor.conexionBD();
//        
//        if(col != null){
//            System.out.println("Conexion establecida"); 
//        } else {
//            System.out.println("No se pudo conectar");
//        }     
//        
//       
//        GestorXML xml = new GestorXML();
//        List<Empleado> empleadosList =  xml.obtenerEmpleados();
//        for(Empleado e : empleadosList){
//            System.out.println("------ EMPLEADO ------");
//            System.out.println(e.toString());
//        }
   
    } 
     public static void menuPrincipal() throws Exception{
        Scanner sc = new Scanner(System.in);
        String menu = null;
        do{
            System.out.println("------ MENU ------");
            System.out.println("a: Crear Empleado");
            System.out.println("b: Login");
            System.out.println("c: Modificar Empleado");
            System.out.println("d: Cambiar Contrasena");
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
                        break;
                    }                            
                }
                case "c":{
                    EmpleadoController.modificarPerfil();
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
         
     }
}

