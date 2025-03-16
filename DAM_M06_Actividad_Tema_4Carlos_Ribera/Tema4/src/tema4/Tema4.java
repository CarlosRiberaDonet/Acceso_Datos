/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package tema4;

import entidades.Empleado;
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
        Empleado empleado = new Empleado(1, "Carlos", "1234", "Carlos Ribera Donet", "123456789", null);
        System.out.println(empleado);
        
        Scanner sc = new Scanner(System.in);
        String menu = "";
        
        switch(menu){
            case "a":
            {
                // Insertar empleado
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
