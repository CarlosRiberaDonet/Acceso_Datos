/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

package Controllers;

import Modelos.Empleados;
import Utils.Validation;

/**
 *
 * @author Carlos Ribera
 */
public class EmpleadoController {
    
    // Método para crear un nuevo empleado
    public static void CrearEmpleado(){
        
        String nombreUsuario;
        boolean error;
        
        do{
            error = false;
            // Solicito nombre de usuario
            nombreUsuario = Validation.SolicitaString("Ingrese un nombre de usuario", 30);  
            
            // Compruebo si el nombre de usuario ya existe en la base de datos
            if(BuscarEmpleado(nombreUsuario)){
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
        Empleados nuevoEmpleado = new Empleados(nombreUsuario, contrasena, nombreCompleto, telefono);
        
        DAO dao = new DAO();
        dao.Guardar(nuevoEmpleado);
    }
    
    // Método para comprobar credenciales del empleado
    public static boolean Login(){
        
        // Solicito el nombre de usuario
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30);
        
        // Compruebo si el usuario existe
        if(BuscarEmpleado(nombreUsuario)){
            
            // Solicito la contraseña
            String contrasena = Validation.SolicitaString("Introduce la contraseña del usuario " + nombreUsuario, 30);
            
            // Llamo al método de DAO
            DAO dao = new DAO();
            Empleados empleado = dao.ValidarCredenciales(nombreUsuario, contrasena);

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
    
    // Método que solicita los datos para modificar un empleado
    public static void ModificarEmpleado(){
           
        // Solicito el nombre de usuario
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario del empleado que desea modificar", 30);
        
        // Compruebo si el usuario existe
        if(BuscarEmpleado(nombreUsuario)){
            // Llamo al método de DAO
            DAO dao = new DAO();
            Empleados empleado = dao.BuscarNombreUsuario(nombreUsuario); // Creo un objeto de tipo Empleados
            
            // Solicito los nuevos datos para modificar al empleado
            empleado.setNombreUsuario(Validation.SolicitaString("Ingrese el nuevo nombre de usuario", 30));
            empleado.setContrasena(Validation.SolicitaString("Introduce la nueva contraseña", 30));
            empleado.setNombreCompleto(Validation.SolicitaString("Introduzca el nuevo nombre completo del empleado", 50));
            empleado.setTelefono(Validation.SolicitaTelefono("Ingrese el nuevo numero de telefono del empleado"));
            
            // Actualizo el empleado en la base de datos
            try{
                dao.ActualizarEmpleado(empleado);
                System.out.println("Empleado " + empleado.getNombreCompleto() + " actualizado correctamente");
            } catch(Exception e){
                System.out.println("Error al actualizar el empleado " + e.getMessage());
            }            
        } else {
            System.out.println("\n No se ha encontrado el nombre de usuario introducido");           
        }
    }
    
    // Método para cambiar la contraseña de un usuario existente
    public static void CambiarContraseña(){
        
        // Solicito nombre de usuario y contraseña actual
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30);
        
        // Compruebo que el nombre de usuario existe
        if(BuscarEmpleado(nombreUsuario)){
            
            String nuevaContraseña = Validation.SolicitaString("Ingrese la nueva contraseña", 30); // Solicito la nueva contraseña          
            DAO dao = new DAO();
            if(dao.ActualizarContraseña(nombreUsuario, nuevaContraseña)){
                System.out.println("Contraseña actualizada correctamente para el usuario: " + nombreUsuario);
            } else{
                System.out.println("No se ha podido modificar la contraseña");
            }
        } else {
            System.out.println("\n No se ha encontrado el nombre de usuario introducido");
        }                 
    }
    
    // Método para eliminar empleado
    public static void EliminarEmpleado(){
        
        // Solicito nombre de usuario y contraseña actual
        String nombreUsuario = Validation.SolicitaString("Ingrese el nombre de usuario", 30);
        
        // Compruebo que el nombre de usuario existe
        if(BuscarEmpleado(nombreUsuario)){
           
            // Si el nombre de usuario existe, llamo al método DAO para eliminarlo de la BD   
            DAO dao = new DAO();
            if(dao.EliminarEmpleado(nombreUsuario)){
                System.out.println("Empleado eliminado exitosamente");
            } else {
                System.out.println("No se ha podido eliminar el empleado");
            }               
        } else {
            System.out.println("\n No se ha encontrado el nombre de usuario introducido");
        }   
    }
    
    // Método para buscar empleado por nombre de usuario
    public static boolean BuscarEmpleado(String nombreUsuario){
                    
        // Llamo al método de DAO
        DAO dao = new DAO();
        Empleados empleado = dao.BuscarNombreUsuario(nombreUsuario);
        
        if(empleado != null){
            return true;
        }    
        return false;
    }
}