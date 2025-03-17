/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controllers;
import com.db4o.ObjectSet;
import entidades.Empleado;
import java.util.ArrayList;
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
        
        int id = CalcularId(); // Calculo el ID que se le asignará a Empleado
        
        do{
            error = false;
            // Solicito nombre de usuario
            nombreUsuario = Validation.SolicitaString("Ingrese un nombre de usuario", 30).toLowerCase();  
            
            // Compruebo si el nombre de usuario ya existe en la base de datos
            if(dao.BuscarNombreUsuario(nombreUsuario)){
                System.out.println("El nombre de usuario ya existe. Por favor, elija otro");
                error = true;
            }
        } while(error);  
          
        System.out.println("Nombre de usuario registrado: " + nombreUsuario);
        
        // Solicito contraseña
        String contrasena = Validation.SolicitaString("Introduce un password", 30);
        System.out.println("password registrado: " + contrasena);
                
        // Solicito nombre completo
        String nombreCompleto = Validation.SolicitaString("Introduzca el nombre completo del empleado", 50).toLowerCase();
        System.out.println("Nombre completo registrado: " + nombreCompleto);
               
        // Solicito número de teléfono
        String telefono = Validation.SolicitaTelefono("Ingrese el numero de telefono del empleado");
        System.out.println("Numero de telefono registrado: " + telefono);
        
        // Llamo al constructor de Empleados
        Empleado nuevoEmpleado = new Empleado(id, nombreUsuario, contrasena, nombreCompleto, telefono, new ArrayList<>());
        
        dao.SaveObject(nuevoEmpleado);
        
    }
    
    // Método para loggearse
    public static boolean Login(){
        
        // Solicito el nombre de usuario
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30).toLowerCase();
        String contrasena = Validation.SolicitaString("Introduce el password del usuario " + nombreUsuario, 30);
        
        // Compruebo si el usuario y la contraseña son correctos
        if(dao.ComprobarCredenciales(nombreUsuario, contrasena)){
            System.out.println("Registro exitoso");
            return true;
        }     
        System.out.println("\n No se ha encontrado el nombre de usuario introducido");
        return false;
    }
    
    public static void ModificarEmpleado(){
        
        // Solicito el nombre de usuario
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30).toLowerCase();
        
        // Compruebo si el nombre de usuario existe en la base de datos
        if(dao.BuscarNombreUsuario(nombreUsuario)){
            Empleado empleadoModificado = dao.getEmpleado(nombreUsuario);
            
            String nuevoNombre = Validation.SolicitaString("Ingrese el nuevo nombre de usuario", 30).toLowerCase();
            String nuevaContrasena = Validation.SolicitaString("Introduzca el nuevo password", 30);
            String nuevoNombreCompleto = Validation.SolicitaString("Ingrese el nuevo nombre completo", 50).toLowerCase();
            String nuevoTelefono = Validation.SolicitaTelefono("Ingrese el nuevo numero de telefono");
            
            empleadoModificado.setNombreUsuario(nuevoNombre);
            empleadoModificado.setContrasena(nuevaContrasena);
            empleadoModificado.setNombreCompleto(nuevoNombreCompleto);
            empleadoModificado.setTelefono(nuevoTelefono);
            
            dao.SaveObject(empleadoModificado);
        }
        System.out.println("No se ha encontrado coincidencias con el nombre de usuario proporcionado");
    }
    
    public static void CambiarPassword(){
        
        // Solicito el nombre de usuario
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30).toLowerCase();
        
        // Compruebo si el nombre de usuario existe en la base de datos
        if(dao.BuscarNombreUsuario(nombreUsuario)){
            String nuevoPassword = Validation.SolicitaString("Introduzca el nuevo password", 30);
            
            Empleado empleadoModificado = dao.getEmpleado(nombreUsuario);
            empleadoModificado.setContrasena(nuevoPassword);
            
            // Guardo el objeto Empleado con la nueva contraseña
            dao.SaveObject(empleadoModificado);
            System.out.println("Password cambiado exitosamente");         
        } else{
            System.out.println("No se ha encontrado coincidencias con el nombre de usuario proporcionado");  
        }
    }
    
    public static void EliminarEmpleado(){
        
        // Solicito el nombre de usuario
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30).toLowerCase();
        if(dao.BuscarNombreUsuario(nombreUsuario)){
            if(dao.DeleteObject(nombreUsuario)){
                System.out.println("Usuario eliminado exitosamente");
            } else{
                System.out.println("No se ha podido eliminar el usuario");
            }
        } else{
            System.out.println("No se ha encontrado coincidencias con el nombre de usuario proporcionado");
        }
    }
    
    public static int CalcularId(){

        ObjectSet<Empleado> empleadosList = dao.getDB().query(Empleado.class);
        int maxId = 0;
        
        if(!empleadosList.isEmpty()){
            for(Empleado e : empleadosList){ // Itero sobre la lista de empleados
                if(e.getId() > maxId){ // Comparo el ID del empleado
                    maxId = e.getId();
                }
            }
            return maxId + 1; // Devuelvo el siguiente ID disponible
        } 
        return +1; // Si es el primer empleado, devuelvo 1
    }
    
    public static void Listar(){
        
        ObjectSet<Empleado> empleadosList = dao.getDB().query(Empleado.class);
        
        for(Empleado e : empleadosList){
            System.out.println(e.toString());        }
    }
            
}
