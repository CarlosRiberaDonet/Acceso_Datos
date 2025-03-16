/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import entidades.Empleado;
import utils.Validation;
/**
 *
 * @author Carlos Ribera
 */
public class EmpleadoController {
    
        private static DAO dao = new DAO();
        
    // Método para crear un nuevo empleado
    public static void CrearEmpleado(){
       
        String nombreUsuario;
        boolean error; 
        
        do{
            error = false;
            // Solicito nombre de usuario
            nombreUsuario = Validation.SolicitaString("Ingrese un nombre de usuario", 30);  
            
            // Compruebo si el nombre de usuario ya existe en la base de datos
            if(dao.BuscarNombreUsuario(nombreUsuario)){
                System.out.println("El nombre de usuario ya existe. Por favor, elija otro");
                error = true;
            }
        } while(error);  
          
        System.out.println("Nombre de usuario registrado: " + nombreUsuario);
        
        // Solicito contraseña
        String contrasena = Validation.SolicitaString("Introduce una contraseña", 30);
        System.out.println("Contraseña registrada: " + contrasena);
                
        // Solicito nombre completo
        String nombreCompleto = Validation.SolicitaString("Introduzca el nombre completo del empleado", 50);
        System.out.println("Nombre completo registrado: " + nombreCompleto);
               
        // Solicito número de teléfono
        String telefono = Validation.SolicitaTelefono("Ingrese el numero de telefono del empleado");
        System.out.println("Numero de telefono registrado: " + telefono);
        
        // Llamo al constructor de Empleados
        Empleado nuevoEmpleado = new Empleado(nombreUsuario, contrasena, nombreCompleto, telefono, null);
        
        dao.SaveObject(nuevoEmpleado);
        
    }
    
    // Método para loggearse
    public static void Login(){
        
        // Solicito el nombre de usuario
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30);
        
        // Compruebo si el usuario existe
        if(dao.BuscarNombreUsuario(nombreUsuario)){
            
            // Solicito la contraseña
            String contrasena = Validation.SolicitaString("Introduce la contraseña del usuario " + nombreUsuario, 30);
            
            // Llamo al método de DAO
            DAO dao = new DAO();
            Empleado empleado = dao.ValidarCredenciales(nombreUsuario, contrasena);

            if(empleado != null){
                System.out.println("\n Inicio de sesion exitoso para " + empleado.getNombreUsuario());
                return true;             
            } else{
                System.out.println("\n Contraseña incorrecta");
                return false;
            }    
        }     
        System.out.println("\n No se ha encontrado el nombre de usuario introducido");
        return false;
    }
    
}
