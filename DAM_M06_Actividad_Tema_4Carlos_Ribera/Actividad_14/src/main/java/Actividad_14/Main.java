/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package Actividad_14;
import java.util.Scanner;
import Alumno_Controller.AlumnoController; 
import java.sql.SQLException;
import java.util.InputMismatchException;
/**
 *
 * @author Carlos
 */

public class Main 
{
    
    private static AlumnoController ac = new AlumnoController();
    
    public static void menu()
    {
        System.out.println("**********MENU**********");
        System.out.println("[A] Ingresar alumno");
        System.out.println("[B] Actualizar alumno");
        System.out.println("[C] Borrar alumno");
        System.out.println("[D] Listar alumnos");
        System.out.println("[E] Salir"); 
    }

    public static void main(String[] args) throws SQLException 
    {
        Scanner sc = new Scanner(System.in);
        String menu;
        boolean exit = false;
        do{
            try{
                menu();
                switch (sc.nextLine().toLowerCase())
                {
                    case "a":
                        ac.ingrearAlumno();
                        break;
                    case "b":
                        ac.actualizarAlumno();
                        break;
                    case "c":
                        ac.borrarAlumno();
                        break;
                    case "d":
                        ac.listarAlumnos();
                        break;
                    case "e":
                        exit = true;
                        break;
                    default:
                    {
                        System.out.println("Opción no válida. Elija una opción del menú.");
                    }
                }
            }
            catch ( InputMismatchException e)
            {
                System.out.println("Debe selecciona una opción válida.");
                sc.nextLine();
            }       
        }
        while(!exit);
    }
}
