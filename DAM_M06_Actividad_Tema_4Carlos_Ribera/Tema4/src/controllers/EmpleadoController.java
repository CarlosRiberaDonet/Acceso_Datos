/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import entidades.Empleado;
import java.util.HashSet;
import java.util.Set;
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
        String contrasena = Validation.SolicitaString("Introduce la contraseña del usuario " + nombreUsuario, 30);
        
        // Compruebo si el usuario y la contraseña son correctos
        if(dao.ComprobarCredenciales(nombreUsuario, contrasena)){
            System.out.println("Empleado encontrado");
            return;
        }     
        System.out.println("\n No se ha encontrado el nombre de usuario introducido");
    }
    
    public static void ModificarEmpleado(){
        
        // Solicito el nombre de usuario
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30);
        
        // Compruebo si el nombre de usuario existe en la base de datos
        if(dao.BuscarNombreUsuario(nombreUsuario)){
            Empleado empleadoModificado = dao.getEmpleado(nombreUsuario);
            
            String nuevoNombre = Validation.SolicitaString("Ingrese el nuevo nombre de usuario", 30);
            String nuevaContrasena = Validation.SolicitaString("Introduzca la nueva contrasña", 30);
            String nuevoNombreCompleto = Validation.SolicitaString("Ingrese el nuevo nombre completo", 50);
            String nuevoTelefono = Validation.SolicitaTelefono("Ingrese el nuevo numero de telefono");
            
            empleadoModificado.setNombreUsuario(nuevoNombre);
            empleadoModificado.setContrasena(nuevaContrasena);
            empleadoModificado.setNombreCompleto(nuevoNombreCompleto);
            empleadoModificado.setTelefono(nuevoTelefono);
            
            dao.SaveObject(empleadoModificado);
        }
    }
}
