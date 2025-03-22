/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema4;

import controllers.EmpleadoController;
import controllers.IncidenciaController;
import java.util.Scanner;

/**
 *
 * @author Carlos Ribera
 */
public class Tema4 {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Scanner scanner = new Scanner(System.in);
        String menu = "";      
        
        do{
            System.out.println("------ MENU ------");
            System.out.println("a: Crear Empleado");
            System.out.println("b: Login");
            System.out.println("c: Modificar Empleado");
            System.out.println("d: Cambiar Contrasena");
            System.out.println("e: Eliminar Empleado");
            System.out.println("f: Salir");

            menu = scanner.nextLine().toLowerCase();

            switch(menu){
                case "a":
                {
                    EmpleadoController.CrearEmpleado();
                    break;
                }
                case "b":{
                    if(EmpleadoController.Login()){
                        MenuIncidencias();                       
                    }
                    break;
                }
                case "c":
                {
                    EmpleadoController.ModificarEmpleado();
                    break;
                }
                case "d":{
                    EmpleadoController.CambiarPassword();
                    break;
                }
                case "e":{
                    EmpleadoController.EliminarEmpleado();
                    break;
                }
                case "f":{
                    System.out.println("HASTA LA PROXIMA!");
                    break;
                }
                default:{
                    System.out.println("Opcion no valida");
                    break;
                }
            }
        } while(!menu.equals("f"));   
    }
    
    public static void MenuIncidencias(){
        
        Scanner sc = new Scanner(System.in);
        String menuIncidencias = "";
        
        do{
            System.out.println("------ MENU ------");
            System.out.println("a: BUSCAR INCIDENCIA ID");
            System.out.println("b: LISTAR INCIDENCIAS");
            System.out.println("c: CREAR INCIDENCIA");
            System.out.println("d: BUSCAR INCIDENCIA CREADAS POR EMPLEADO");
            System.out.println("e: LISTAR INCIDENCIAS DESTINADAS A EMPLEADO");
            System.out.println("f: VOLVER AL MENU PRINCIPAL");
            
            menuIncidencias = sc.nextLine().toLowerCase();
            
            switch(menuIncidencias){
                case "a":{
                    // Obtener objeto incidencia mediante su id
                    IncidenciaController.ObtenerIncidenciaId();
                    break;
                }
                case "b":{
                    // Obtener lista de todas las incidencias
                    IncidenciaController.ObtenerListaIncidencias();
                    break;
                }
                case "c":{
                    IncidenciaController.CrearIncidencia();
                    break;
                }
                case "d":{
                   IncidenciaController.InidenciasEmpleadoOrigen();
                    break;
                }
                case "e":{
                    // Obtener las inciencias destinadas para un empleado a partir de un objeto de clase Empleado
                     IncidenciaController.IncidenciasEmpleadoDestino();
                    break;
                }
                case "f":{
                    System.out.println("Saliendo menu principal");
                    break;
                }
                default:{
                    System.out.println("Opcion no valida");
                    break;
                }
            }   
        } while(!menuIncidencias.equals("f"));
    }
}
