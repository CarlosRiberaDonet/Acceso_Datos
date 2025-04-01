package OwnAPIs;

import java.util.InputMismatchException;
import java.util.Scanner;

public class DataValidation {

    public static String wordNotEmpty(String message) {
        Scanner teclado = new Scanner(System.in);
        String wordNotEmpty;
        do {
            System.out.println(message);
            wordNotEmpty = teclado.nextLine();
        } while (wordNotEmpty.isEmpty());// pequeño control de errones 
        return wordNotEmpty;
    }

    public static int solicitaInt(int min, int max, String message) {
        Scanner sc = new Scanner(System.in);
        int number = -1;
        do {
            try {
                System.out.println(message);
                number = sc.nextInt();
            } catch (InputMismatchException ex) {
                System.out.println("Wrong option");
                sc.nextLine();
            }
        } while (number < min || number > max);
        return number;
    }
    
    public static double solicitaDouble(double min, double max, String mensaje)
    {
        Scanner sc = new Scanner(System.in);
        double num = 0;
        do
        {
            try
            {
                System.out.println(mensaje);
                num = sc.nextDouble(); 
            }
            catch (InputMismatchException e)
            {
                System.out.println("Los caracteres no están permitidos.");
                sc.nextLine();
            }
        }
        while ( num < min || num > max);
        return num;
    }
    
    public static int solicitaId(int id, String mensaje)
    {
        Scanner sc = new Scanner(System.in);
        int alumnoId = -1;
        boolean error = true;
        do
        {
            try
            {
                System.out.println(mensaje);
                alumnoId = sc.nextInt();
                error = false;
            }
            catch (InputMismatchException e)
            {
                System.out.println("Los caracteres no están permitidos.");
                sc.nextLine();
            }
        }
        while (error); 
        return alumnoId;
    }
}