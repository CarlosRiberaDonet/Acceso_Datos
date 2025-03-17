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
        
        EmpleadoController.Listar();
        
        Scanner sc = new Scanner(System.in);
        String menu = "";      
        
        do{
            System.out.println("------ MENU ------");
            System.out.println("a: Crear Empleado");
            System.out.println("b: Login");
            System.out.println("c: Modificar Empleado");
            System.out.println("d: Cambiar Contrasena");
            System.out.println("e: Eliminar Empleado");
            System.out.println("f: Salir");

            menu = sc.nextLine();

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
                }
            }
        } while(!menu.equals("f"));   
    }
    
    public static void MenuIncidencias(){
        String menu = "";
        
        do{
            switch(menu){
                case "a":{
                    // Obtener objeto incidencia mediante su id
                    IncidenciaController.ObtenerIncidenciaId();
                    break;
                }
                case "b":{
                    // Obtener lista de todas las incidencias
                }
                case "c":{
                    // Insertar una incidencia a partir de un objeto de clase Incidencia
                }
                case "d":{
                    // Obtener las incidencias creadas por un empleado concreto
                }
                case "e":{
                    // Obtener las inciencias destinadas para un empleado a partir de un objeto de clase Empleado
                }
                case "f":{
                    System.out.println("Saliendo menu principal");
                    break;
                }
                default:{
                    System.out.println("Opcion no valida");
                }
            }   
        } while(!menu.equals("f"));
    }
}
