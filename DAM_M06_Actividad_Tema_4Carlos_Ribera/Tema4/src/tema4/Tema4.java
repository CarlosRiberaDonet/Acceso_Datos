/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema4;

import controllers.EmpleadoController;
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
        
        Scanner sc = new Scanner(System.in);
        String menu = "";
        
        System.out.println("------ MENU ------");
        System.out.println("a: Crear Empleado");
        System.out.println("b: Login");
        System.out.println("c: Modificar Empleado");
        System.out.println("d: Cambiar Contraseña");
        System.out.println("e: Eliminar Empleado");
        System.out.println("f: Salir");
        
        menu = sc.nextLine();
        
        switch(menu){
            case "a":
            {
                EmpleadoController.CrearEmpleado();
            }
            case "b":{
                // Login
            }
            case "c":
            {
                // Modificar empleado
            }
            case "d":{
                // Cambiar contraseña
            }
            case "e":{
                // Eliminar empleado
            }
        }
        
    }
    
}
