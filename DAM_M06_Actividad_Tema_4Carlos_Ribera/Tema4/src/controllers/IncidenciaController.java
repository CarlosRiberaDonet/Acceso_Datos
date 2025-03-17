/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;

import entidades.Incidencia;
import java.util.Scanner;
import utils.Validation;

/**
 *
 * @author Carlos
 */
public class IncidenciaController {
    
    private static DAO dao = new DAO();
    
    public static void ObtenerIncidenciaId(){
       
        // Declaración de variables
        Scanner sc = new Scanner(System.in);
        
       // Solicito Id al USR
        System.out.println("Introduzca el ID de la incidencia a buscar");
       int id = sc.nextInt();
       Incidencia incidencia = dao.getIncidencia(id);
   
        System.out.println(incidencia.toString());
      
       
    }
    
}
